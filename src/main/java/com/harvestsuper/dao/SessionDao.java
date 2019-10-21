package com.harvestsuper.dao;


import com.harvestsuper.entity.Sessionlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionDao extends JpaRepository<Sessionlog, Integer>
{

}