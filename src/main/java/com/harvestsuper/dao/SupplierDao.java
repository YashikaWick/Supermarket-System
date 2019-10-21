package com.harvestsuper.dao;


import com.harvestsuper.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierDao extends JpaRepository<Supplier,Integer> {

    @Query("SELECT s FROM Supplier s ORDER BY s.id DESC")
    Page<Supplier> findAll(Pageable of);

    @Query("SELECT s FROM Supplier s WHERE s.regno= :regno")
    Supplier findByRegNo(@Param("regno") String regno);

    @Query(value="SELECT new Supplier(s.id,s.name) FROM Supplier s")
    List<Supplier> list();
}
