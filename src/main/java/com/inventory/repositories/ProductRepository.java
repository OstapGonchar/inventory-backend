package com.inventory.repositories;

import com.inventory.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    List<ProductEntity> findAll();

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findByClient(String client);
}
