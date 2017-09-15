package com.hotmart.filemanager;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.hotmart.filemanager.service.DownloadEndpoint;
import com.hotmart.filemanager.service.FileEndpoint;
import com.hotmart.filemanager.service.UploadEndpoint;

import io.swagger.jaxrs.config.BeanConfig;

@Component
@ApplicationPath("/filemanager")
public class JerseyConfig extends ResourceConfig {

	private static final String RESOURCE_PACKAGE = "com.hotmart.filemanager";
	
	public JerseyConfig() {
		register(FileEndpoint.class);
		register(UploadEndpoint.class);
		register(DownloadEndpoint.class);

        // Swagger
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        // allows @RolesAllowed
        register(RolesAllowedDynamicFeature.class);

        packages(RESOURCE_PACKAGE);
	}
	
    @Bean
    public BeanConfig swaggerConfig() {
        final BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setBasePath("/filemanager");
        swaggerConfig.setVersion("1.0.0");
        swaggerConfig.setTitle("File Manager");
        swaggerConfig.setResourcePackage(RESOURCE_PACKAGE);
        swaggerConfig.setScan(true);
        return swaggerConfig;
    }

}
