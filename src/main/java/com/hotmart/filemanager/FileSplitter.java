package com.hotmart.filemanager;

import java.io.*;

	/**
	  * Uma classe para tirar um arquivo e dividi-lo em arquivos menores
	  * (Por exemplo, para a montagem de um arquivo em disquetes).
	  * <P> Para executar o programa para dividir arquivos separados, passar o -split
	  * Opção e especifique um nome de arquivo: <BR>
	  * <I> java FileSplitter -split bigFile.zip </ I>
	  * <P> Para entrar em um arquivo de volta juntos, especificar o sinalizador -Junte-se e
	  * Dar o nome de arquivo base: <BR>
	  * <I> java FileSplitter -Junte bigFile.zip </ I> <BR>
	  * @author Keith Trnka
	  */
	
	public class FileSplitter {
		
		/***
		* Uma constante que representa o tamanho de um pedaço de gelo que iria em uma disquete.
		* 1,4 MB é usado em vez de 1,44 MB de modo que não é demasiado perto do limite.
		*/
		public static final long floppySize = (long)(1.4 * 1024 * 1024);
		
		/** O tamanho máximo de cada arquivo de "pedaço" gerado, em bytes */
		public static long chunkSize = 100;
		
		public static void main(String[] args) throws Exception {
			
			if (args.length != 2){
				System.out.println("Deve especificar um -split de bandeira ou -Junte e um argumento de arquivo O argumento arquivo para dividir é o arquivo para dividir e para unir é o nome de arquivo base para se juntar em..");
				System.exit(0);
			}
				
			try {
				if (args[0].equalsIgnoreCase("-split"))
					split(args[1]);
				else if (args[0].equalsIgnoreCase("-join"))
					join(args[1]);
				else {
					System.out.println("O primeiro argumento deve ser uma opção:");
					System.out.println("\t-split: dividir o arquivo especificado");
					System.out.println("\t-join: junte todos os outfiles divisor com o nome do arquivo base especificada");
					System.exit (0);
				}
			} catch (FileNotFoundException e) {
				System.out.println("File not found: " + args[1]);
			} catch (IOException e) {
				System.out.println("IO Error");
			}
		}
			
		/**
		* Dividir o arquivo especificado por arquivo em pedaços, cada um de tamanho
		* ChunkSize excepto para a última, o que pode ser menor
		*/
		public static void split(String filename) throws FileNotFoundException, IOException {
			// open the file
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
			
			// Obter o tamanho do arquivo
			File f = new File(filename);
			long fileSize = f.length();
			
			// Faz um loop para cada pedaço completo
			int subfile;
			for (subfile = 0; subfile < fileSize / chunkSize; subfile++) {
				// open the output file
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename + "." + subfile));
				
				// Escreve a quantidade certa de bytes
				for (int currentByte = 0; currentByte < chunkSize; currentByte++){
					// Carrega um byte do arquivo de entrada e escrevê-lo para o arquivo de saída
					out.write(in.read());
				}
					
				// close the file
				out.close();
			}
			
			// Loop para o último bloco (o qual pode ser mais pequeno do que o tamanho do bloco)
			if (fileSize != chunkSize * (subfile - 1)){
				// open the output file
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename + "." + subfile));
				
				// Escreve o resto do arquivo
				int b;
				while ((b = in.read()) != -1)
					out.write(b);
					
				// close the file
				out.close();			
			}
			
			// close the file
			in.close();
		}
			
		/**
		* Lista todos os arquivos no diretório especificado pela baseFilename
		*, Descobrir quantas partes existem, e então concatenar-los
		* Juntos para criar um arquivo com o nome do arquivo <I> baseFilename </ I>.
		*/
		public static void join(String baseFilename) throws IOException {
			
			int numberParts = getNumberParts(baseFilename);

			// Agora, suponha que os arquivos estão corretamente numerados em ordem (que algum palhaço não excluir qualquer parte)
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(baseFilename));
			for (int part = 0; part < numberParts; part++) {
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(baseFilename + "." + part));

				int b;
				while ( (b = in.read()) != -1 ){
					out.write(b);
				}
				in.close();
			}
			out.close();
		}
		
		/**
		* Descobrir quantos pedaços existem para o nome do arquivo de base
		*/
		private static int getNumberParts(String baseFilename) throws IOException {
			// Lista de todos os arquivos no mesmo diretório
			File directory = new File(baseFilename).getAbsoluteFile().getParentFile();
			final String justFilename = new File(baseFilename).getName();
			
			String[] matchingFiles = directory.list(new FilenameFilter() {
				public boolean accept(File dir, String name){
					return name.startsWith(justFilename) && name.substring(justFilename.length()).matches("^\\.\\d+$");
				}
			});
			return matchingFiles.length;
		}

}
