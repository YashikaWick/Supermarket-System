package com.harvestsuper.controller;


import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ui")
public class UiController {



    @RequestMapping("/config")
    public ModelAndView config(){
        ModelAndView model = new ModelAndView();
        model.setViewName("config.html");
        return model;
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login.html");
        return model;
    }

    @RequestMapping("/login2")
    public ModelAndView login2(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login2.html");
        return model;
    }

    @RequestMapping("/item")
    public ModelAndView item(){
        ModelAndView model = new ModelAndView();
        model.setViewName("item.html");
        return model;
    }

   /* @RequestMapping("/customer")
    public ModelAndView customer(){
        ModelAndView model = new ModelAndView();
        model.setViewName("customer.html");
        return model;
    }*/


    @RequestMapping("/mainwindow")
    public ModelAndView mainwindow(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("mainwindow.html",username,password);
    }

    @RequestMapping("/mainwindownew")
    public ModelAndView mainwindownew(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("mainwindownew.html",username,password);
    }

    @RequestMapping("/home")
    public ModelAndView home(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("home.html",username,password);
    }

    @RequestMapping("/homenew")
    public ModelAndView homenew(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("homenew.html",username,password);
    }

    @RequestMapping("/employee")
    public ModelAndView employee(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("employee.html",username,password);
    }


    @RequestMapping("/customer")
    public ModelAndView customer(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("customer.html",username,password);
    }


    @RequestMapping("/user")
    public ModelAndView user(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("user.html",username,password);
    }

    @RequestMapping("/previlage")
    public ModelAndView previlage(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("previlage.html",username,password);

    }


    @RequestMapping("/changepassword")
    public ModelAndView changepassword(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("changepassword.html",username,password);
    }


    @RequestMapping("/designation")
    public ModelAndView designation(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("designation.html",username,password);
    }

    @RequestMapping("/supplier")
    public ModelAndView supplier(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("supplier.html",username,password);
    }

    @RequestMapping("/purchasorder")
    public ModelAndView purchasorder(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("purchasorder.html",username,password);
    }

    public ModelAndView getModelView(String page,String username, String password){

        ModelAndView model = new ModelAndView();

        if(AuthProvider.isUser(username,password)) {

            model.setViewName(page);
        }
        else {
            model.setViewName("noprivilage.html");

        }
        return model;

    }



}