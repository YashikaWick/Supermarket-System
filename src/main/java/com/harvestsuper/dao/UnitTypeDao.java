package com.harvestsuper.dao;

import com.harvestsuper.entity.Unittype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnitTypeDao extends JpaRepository<Unittype,Integer> {
    @Query(value="SELECT new Unittype(e.id,e.name) FROM Unittype e")
    List<Unittype> list();
}
