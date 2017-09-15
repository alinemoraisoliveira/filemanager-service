package com.hotmart.filemanager.business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotmart.filemanager.domain.File;
import com.hotmart.filemanager.domain.SplitFile;
import com.hotmart.filemanager.repository.FileRepository;
import com.hotmart.filemanager.repository.SplitFileRepository;
import com.hotmart.filemanager.util.Constants;

@Component
public class UploadBusiness {

	@Autowired
	private FileRepository fileRepository; 
	
	@Autowired
	private SplitFileRepository splitFileRepository; 
	
	public void upload(String filename, String user) {

		File file = null;
		try{
			java.io.File file_io = new java.io.File(filename);
			long sizeFile = file_io.length();
			long sizeChunck = 1024;
			
			file = new File();
			file.setDateUpload((GregorianCalendar) GregorianCalendar.getInstance());
			file.setName(filename);
			file.setChunksNumber(sizeFile / sizeChunck);
			file.setStatus(Constants.STATUS_UPLOAD_PROGRESS);
			file.setUser(user);
			fileRepository.save(file);
			
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));

			int subfile;
			for(subfile = 0; subfile < sizeFile / sizeChunck; subfile++) {
				
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename + "." + subfile));
				for (int currentByte = 0; currentByte < sizeChunck; currentByte++){
					out.write(in.read());
				}
				
				SplitFile splitFile = new SplitFile();
				splitFile.setName(filename + "." + subfile);
				splitFile.setChunkNumber(subfile);
				//TODO: splitFile.setArquivoQuebrado(arquivoQuebrado);
				splitFile.setFileId(file.getId());
				splitFileRepository.save(splitFile);

				out.close();
			}

			if (sizeFile != sizeChunck * (subfile - 1)){
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename + "." + subfile));

				int b;
				while ((b = in.read()) != -1){
					out.write(b);
				}
				out.close();			
			}
			in.close();
			
			file = fileRepository.findOne(file.getId());
			file.setStatus(Constants.STATUS_UPLOAD_COMPLETED);
			fileRepository.save(file);

		} catch(FileNotFoundException e){
			System.out.println(e);
			file = fileRepository.findOne(file.getId());
			file.setStatus(Constants.STATUS_UPLOAD_FAILURE);
			fileRepository.save(file);
			
		} catch (IOException e) {
			System.out.println(e);
			file = fileRepository.findOne(file.getId());
			file.setStatus(Constants.STATUS_UPLOAD_FAILURE);
			fileRepository.save(file);
		}
	}

}