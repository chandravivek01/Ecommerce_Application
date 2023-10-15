package com.vcs.ecommerce.config;

import com.vcs.ecommerce.entity.Product;
import com.vcs.ecommerce.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
//        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        // Disable the below methods
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        // for Product
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure( ((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure( ((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));

        // For ProductCategory
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure( ((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure( ((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
    }
}
