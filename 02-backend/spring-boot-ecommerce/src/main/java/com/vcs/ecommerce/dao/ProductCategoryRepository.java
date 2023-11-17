package com.vcs.ecommerce.dao;

import com.vcs.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// productCategory - Name of JSON entry, /product_category

@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product_category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
