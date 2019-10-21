package com.harvestsuper.dao;

import com.harvestsuper.entity.Category;
import com.harvestsuper.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubCategoryDao extends JpaRepository<Subcategory,Integer> {
    @Query(value="SELECT new Subcategory(e.id,e.name,e.categoryId) FROM Subcategory e where e.categoryId.id=:categoryId")
    List<Subcategory> listByCategory(@Param("categoryId")Integer categoryId);


}
