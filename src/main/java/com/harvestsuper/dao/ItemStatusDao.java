package com.harvestsuper.dao;

import com.harvestsuper.entity.Itemstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemStatusDao extends JpaRepository<Itemstatus,Integer> {
    @Query(value="SELECT new Itemstatus(e.id,e.name) FROM Itemstatus e")
    List<Itemstatus> list();
}
