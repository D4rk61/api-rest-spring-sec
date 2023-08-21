package com.blackshark.SpringBootRest.persistance.repository;

import com.blackshark.SpringBootRest.persistance.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface IProductPaginRepository extends ListPagingAndSortingRepository<Product, Long> {

    Page<Product> findByPriceBetween(BigDecimal min, BigDecimal max, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

}
