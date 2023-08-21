package com.blackshark.SpringBootRest.domain.service;

import com.blackshark.SpringBootRest.persistance.entity.Product;
import com.blackshark.SpringBootRest.persistance.repository.IProductCrudRepository;
import com.blackshark.SpringBootRest.persistance.repository.IProductPaginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private final IProductCrudRepository crudRepository;
    @Autowired
    private final IProductPaginRepository paginRepository;

    public ProductService(IProductCrudRepository crudRepository, IProductPaginRepository paginRepository) {
        this.crudRepository = crudRepository;
        this.paginRepository = paginRepository;
    }


    public Page<Product> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return paginRepository.findAll(pageable);
    }

    public Optional<Product> getProductBetweenPrice(BigDecimal min, BigDecimal max) {
        return crudRepository.findByPriceBetween(min, max);
    }


    public Page<Product> getProductsBetweenPrice(BigDecimal min, BigDecimal max, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return paginRepository.findByPriceBetween(min, max, pageable);
    }

    public Optional<Product> getByName(String name) {

        try {
            return this.crudRepository.findByName(name);
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<Product> getById(Long id) {
        try {
            return this.crudRepository.findById(id);
        } catch (Exception e) {
            return null;
        }
    }
}
