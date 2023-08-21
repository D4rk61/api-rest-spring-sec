package com.blackshark.SpringBootRest.persistance.repository;

import com.blackshark.SpringBootRest.persistance.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface IProductCrudRepository extends JpaRepository<Product, Long> {

    // buscar un producto entre 2 cantidades, el tipo de price es BigDecimal
    Optional<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    Optional<Product> findByName(String name);

}
