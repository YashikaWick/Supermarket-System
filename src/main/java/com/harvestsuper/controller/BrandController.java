package com.harvestsuper.controller;

import com.harvestsuper.dao.BrandDao;
import com.harvestsuper.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BrandController {
    @Autowired
    private BrandDao dao;

    @RequestMapping(value = "/brands/listbycategory",params = "categoryId", method = RequestMethod.GET, produces = "application/json")
    public List<Brand> listByCategory(@Param("categoryId")Integer categoryId) {
        return dao.listbycategory(categoryId);
    }

 @RequestMapping(value = "/brands/list", method = RequestMethod.GET, produces = "application/json")
    public  List<Brand> list(){
        return dao.list();
 }
}
