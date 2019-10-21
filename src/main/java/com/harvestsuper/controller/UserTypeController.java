package com.harvestsuper.controller;

import com.harvestsuper.dao.CivilstatusDao;
import com.harvestsuper.dao.UserTypeDao;
import com.harvestsuper.entity.Civilstatus;
import com.harvestsuper.entity.Usertype;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserTypeController {

    @Autowired
    private UserTypeDao dao;

    @RequestMapping(value = "/usertypes/list", method = RequestMethod.GET, produces = "application/json")
    public List<Usertype> usertypes() {
        return dao.list();
    }


}
