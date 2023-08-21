package com.blackshark.SpringBootRest.domain.service;

import com.blackshark.SpringBootRest.persistance.entity.Maker;
import com.blackshark.SpringBootRest.persistance.repository.IMakerCrudRepository;
import com.blackshark.SpringBootRest.persistance.repository.IMakerPaginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakerService {
    @Autowired
    private final IMakerCrudRepository crudRepository;
    @Autowired
    private final IMakerPaginRepository paginRepository;

    public MakerService(IMakerCrudRepository crudRepository, IMakerPaginRepository paginRepository) {
        this.crudRepository = crudRepository;
        this.paginRepository = paginRepository;
    }


    public Page<Maker> findAll(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return paginRepository.findAll(pageable);

    }


    public Optional<Maker> findById(Long id){

        try {
            if(!isMakerExistById(id)) {
                throw new RuntimeException("maker not found");
            }
            return crudRepository.findById(id);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Maker save(Maker maker){
        try {
            return crudRepository.save(maker);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Maker> findByName(String name){
        Maker maker = new Maker();

        if(maker.getName().isEmpty()) {
            throw new RuntimeException("maker not found");
        }

        try {
            return crudRepository.findByNameIgnoreCase(name);
        } catch (Exception e) {

            return Optional.empty();
        }

    }

    public Optional<Maker> findAllProductsById(Long id) {

        if(!isMakerExistById(id)) {
            throw new RuntimeException("maker not found");
        }

        try {
            return crudRepository.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private boolean isMakerExistById(Long id) {
        return crudRepository.existsById(id);
    }
}
