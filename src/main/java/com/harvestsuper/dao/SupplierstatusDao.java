package com.harvestsuper.dao;

import com.harvestsuper.entity.Category;
import com.harvestsuper.entity.Supplierstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierstatusDao extends JpaRepository<Supplierstatus,Integer> {

    @Query(value="SELECT new Supplierstatus(e.id,e.name) FROM Supplierstatus e")
    List<Supplierstatus> list();
}
