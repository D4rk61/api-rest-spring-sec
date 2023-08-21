package com.blackshark.SpringBootRest.web.controller;

import com.blackshark.SpringBootRest.domain.service.MakerService;
import com.blackshark.SpringBootRest.persistance.entity.Maker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/makers")
public class MakerController {

    private static final String PAGE_NUMBER = "0";
    private static final String PAGE_SIZE = "10";

    @Autowired
    private final MakerService makerService;

    public MakerController(MakerService makerService) {
        this.makerService = makerService;
    }

    // -> informacion protegida por spring security

    @GetMapping("/list")
    public ResponseEntity<Page<Maker>> getAllMakers(
            @RequestParam(defaultValue = PAGE_NUMBER) int pageNumber,
            @RequestParam(defaultValue = PAGE_SIZE) int pageSize) {

        return ResponseEntity.ok(makerService.findAll(pageNumber, pageSize));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Maker> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(makerService.findById(id).orElse(null));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/name")
    public ResponseEntity<Maker> getByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(makerService.findByName(name).orElse(null));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listProduct/{id}")
    public ResponseEntity<Maker> getAllMakersWithProducts(@PathVariable Long id) {


        try {
            return ResponseEntity.ok(makerService.findAllProductsById(id).orElse(null));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }
}
