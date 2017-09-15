package com.hotmart.filemanager.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hotmart.filemanager.domain.File;

public interface FileRepository extends PagingAndSortingRepository<File, Long>, JpaSpecificationExecutor<File> {
	
}
