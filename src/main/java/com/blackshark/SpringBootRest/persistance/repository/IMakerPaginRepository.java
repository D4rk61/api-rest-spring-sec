package com.blackshark.SpringBootRest.persistance.repository;

import com.blackshark.SpringBootRest.persistance.entity.Maker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMakerPaginRepository extends ListPagingAndSortingRepository<Maker, Long> {

    Page<Maker> findAll(Pageable pageable);


}
