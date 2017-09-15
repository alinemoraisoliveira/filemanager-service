package com.hotmart.filemanager.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotmart.filemanager.domain.File;
import com.hotmart.filemanager.repository.FileRepository;

@Component
public class FileBusiness {

	@Autowired
	private FileRepository fileBusiness; 
	
	public List<File> findAll() {
		List<File> files = new ArrayList<File>();
		for(File file : fileBusiness.findAll()){
			files.add(file);
		}
		return files;
	}

}