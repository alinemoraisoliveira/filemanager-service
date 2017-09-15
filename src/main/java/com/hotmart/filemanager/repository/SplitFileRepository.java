package com.hotmart.filemanager.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hotmart.filemanager.domain.SplitFile;

public interface SplitFileRepository extends PagingAndSortingRepository<SplitFile, Long>, JpaSpecificationExecutor<SplitFile> {
	
}
