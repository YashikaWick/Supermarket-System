package com.harvestsuper.controller;

import com.harvestsuper.dao.CivilstatusDao;
import com.harvestsuper.dao.PorderstatusDao;
import com.harvestsuper.entity.Civilstatus;
import com.harvestsuper.entity.Porderstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PorderstatusController {

    @Autowired
    private PorderstatusDao dao;

    @RequestMapping(value = "/porderstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Porderstatus> porderstatuses() {
        return dao.list();
    }


}
