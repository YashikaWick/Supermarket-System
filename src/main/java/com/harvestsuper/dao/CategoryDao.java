package com.harvestsuper.dao;

import com.harvestsuper.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category,Integer> {
    @Query(value="SELECT new Category(e.id,e.name) FROM Category e")
    List<Category> list();
}
