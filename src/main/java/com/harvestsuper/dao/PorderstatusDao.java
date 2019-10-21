package com.harvestsuper.dao;


import com.harvestsuper.entity.Civilstatus;
import com.harvestsuper.entity.Porderstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PorderstatusDao extends JpaRepository<Porderstatus, Integer>
{

    @Query(value="SELECT new Porderstatus(pos.id,pos.name) FROM Porderstatus pos")
    List<Porderstatus> list();


}