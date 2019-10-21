package com.harvestsuper.controller;

import com.harvestsuper.dao.GenderDao;
import com.harvestsuper.entity.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenderController {

    @Autowired
    private GenderDao dao;

    @RequestMapping(value = "/genders/list", method = RequestMethod.GET, produces = "application/json")
    public List<Gender> gender() {
        return dao.list();
    }


}
