package com.vcs.ecommerce.config;

import com.vcs.ecommerce.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
//        RepositoryRestConfigure.super.configureRepositoryRestConfiguration(config, cors);

        cors.addMapping(config.getBasePath() + "/**").allowedOrigins("https://localhost:4200");

        // Disable the below methods
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        // for Product
        disableHttpMethods(Product.class, config, theUnsupportedActions);

        // For ProductCategory
        disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);

        // country
        disableHttpMethods(Country.class, config, theUnsupportedActions);

        // state
        disableHttpMethods(State.class, config, theUnsupportedActions);

        // order
        disableHttpMethods(Order.class, config, theUnsupportedActions);

        // call an internal helper method
        exposeIds(config);
    }

    private static void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure( ((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure( ((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
    }

    private void exposeIds(RepositoryRestConfiguration config) {

        // get a list of all entity classes from the entity-manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array of entity types
        List<Class> entityClasses = new ArrayList<>();

        // get the entity types for the entities
        for ( EntityType temp : entities )
            entityClasses.add(temp.getJavaType());

        // expose the entity ids for the array of  entity-domain-typeS
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
