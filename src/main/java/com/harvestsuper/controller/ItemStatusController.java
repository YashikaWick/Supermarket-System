package com.harvestsuper.controller;

import com.harvestsuper.dao.ItemStatusDao;
import com.harvestsuper.entity.Itemstatus;
import com.harvestsuper.entity.Unittype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemStatusController {
    @Autowired
    private ItemStatusDao dao;
    @RequestMapping(value = "/itemstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Itemstatus> list()

    {
        return dao.list();
    }
}
