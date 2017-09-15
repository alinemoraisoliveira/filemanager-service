package com.hotmart.filemanager.business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class DownloadBusiness {

	public void download(String baseFilename) {
		try {
			int numberParts = getNumberParts(baseFilename);
			
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(baseFilename));
			for (int part = 0; part < numberParts; part++){
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(baseFilename + "." + part));

				int b;
				while ((b = in.read()) != -1){
					out.write(b);
				}
				in.close();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int getNumberParts(String baseFilename) throws IOException{
		
		File directory = new File(baseFilename).getAbsoluteFile().getParentFile();
		final String justFilename = new File(baseFilename).getName();
		
		String[] matchingFiles = directory.list(new FilenameFilter(){
			public boolean accept(File dir, String name){
				return name.startsWith(justFilename) && name.substring(justFilename.length()).matches("^\\.\\d+$");
			}
		});
		return matchingFiles.length;
	}
	
}