package com.harvestsuper.controller;

import com.harvestsuper.dao.UnitTypeDao;
import com.harvestsuper.entity.Subcategory;
import com.harvestsuper.entity.Unittype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UnitTypeController {
    @Autowired
    private UnitTypeDao dao;
    @RequestMapping(value = "/unittypes/list", method = RequestMethod.GET, produces = "application/json")
    public List<Unittype> list()


    {
        return dao.list();
    }
}
