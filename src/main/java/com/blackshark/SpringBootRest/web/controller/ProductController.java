package com.blackshark.SpringBootRest.web.controller;

import com.blackshark.SpringBootRest.domain.service.ProductService;
import com.blackshark.SpringBootRest.persistance.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final String PAGE_NUMBER = "0";
    private static final String PAGE_SIZE = "10";

    private static final BigDecimal MIN_PRICE = new BigDecimal("5");
    private static final BigDecimal MAX_PRICE = new BigDecimal("100");

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Product>> findAll(
            @RequestParam(defaultValue = PAGE_NUMBER) int pageNumber,
            @RequestParam(defaultValue = PAGE_SIZE) int pageSize) {

        try {
            return ResponseEntity.ok(productService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getById(Long id) {
        try {
            return ResponseEntity.ok(productService.getById(id).orElse(null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/between/list")
    public ResponseEntity<Page<Product>> getProductsByPriceBetween(
            @RequestParam(defaultValue = PAGE_NUMBER) int pageNumber,
            @RequestParam(defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(name = "min", defaultValue = "0") BigDecimal min,
            @RequestParam(name = "max", defaultValue = "10") BigDecimal max) {

        try {
            return ResponseEntity.ok(productService.getProductsBetweenPrice(min, max, pageNumber, pageSize));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/between/one")
    public ResponseEntity<Optional<Product>> getProductByPriceBewtween(
            @RequestParam(defaultValue = PAGE_NUMBER) int pageNumber,
            @RequestParam(defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(name = "min", defaultValue = "0") BigDecimal min,
            @RequestParam(name = "max", defaultValue = "10") BigDecimal max) {

        try {
            return ResponseEntity.ok(productService.getProductBetweenPrice(min, max));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

