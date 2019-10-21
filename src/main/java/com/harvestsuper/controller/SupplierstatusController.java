package com.harvestsuper.controller;

import com.harvestsuper.dao.CategoryDao;
import com.harvestsuper.dao.SupplierstatusDao;
import com.harvestsuper.entity.Category;
import com.harvestsuper.entity.Supplierstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierstatusController {
    @Autowired
    private SupplierstatusDao dao;


    @RequestMapping(value = "/supplierstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Supplierstatus> list(){
        return dao.list();
    }
}
