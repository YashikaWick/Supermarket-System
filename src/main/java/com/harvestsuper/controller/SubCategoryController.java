package com.harvestsuper.controller;

import com.harvestsuper.dao.SubCategoryDao;
import com.harvestsuper.entity.Category;
import com.harvestsuper.entity.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class SubCategoryController {
    @Autowired
    private SubCategoryDao dao;
    @RequestMapping(value = "/subcategories/listbycategory",params ="categoryId",method = RequestMethod.GET, produces = "application/json")
    public List<Subcategory> list(@RequestParam("categoryId") Integer categoryId){
        return dao.listByCategory(categoryId);
    }
}
