package com.hotmart.filemanager.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotmart.filemanager.business.DownloadBusiness;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/download")
@Api(value = "download")
@Component
public class DownloadEndpoint {
	
	@Autowired
	private DownloadBusiness downloadBusiness; 
	
	@GET 
	@ApiOperation(value = "Realizar o download de arquivos")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found") })
	public void get() {	
		downloadBusiness.download("C:\\Users\\IBM_ADMIN\\Desktop\\New folder\\AWSCLI32.msi");
		
		// TODO: TESTAR
	}

}
