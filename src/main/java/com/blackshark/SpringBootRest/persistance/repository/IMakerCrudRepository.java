package com.blackshark.SpringBootRest.persistance.repository;

import com.blackshark.SpringBootRest.persistance.entity.Maker;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMakerCrudRepository extends JpaRepository<Maker, Long> {

    Optional<Maker> findByNameIgnoreCase(String name);

    @EntityGraph(attributePaths = "products")
    Optional<Maker> findById(Long id);

}
