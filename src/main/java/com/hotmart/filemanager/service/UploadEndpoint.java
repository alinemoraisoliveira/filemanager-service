package com.hotmart.filemanager.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hotmart.filemanager.business.UploadBusiness;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/upload")
@Api(value = "upload")
@Component
public class UploadEndpoint {
	
	@Autowired
	private UploadBusiness uploadBusiness; 
	
	@GET 
	@ApiOperation(value = "Realizar o upload de arquivos")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found") })
	public void get() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		// TODO: uploadBusiness.upload(file, userName); 
		
		uploadBusiness.upload("C:\\Users\\IBM_ADMIN\\Desktop\\New folder\\AWSCLI32.msi", "TESTE");
	}


}
