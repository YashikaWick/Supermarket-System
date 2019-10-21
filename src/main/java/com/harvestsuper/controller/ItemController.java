package com.harvestsuper.controller;

import com.harvestsuper.dao.BrandDao;
import com.harvestsuper.dao.CategoryDao;
import com.harvestsuper.dao.ItemDao;
import com.harvestsuper.entity.Item;
import com.harvestsuper.util.ModuleList;
import org.hibernate.internal.util.collections.EmptyIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController

public class ItemController {

    @Autowired
    private CategoryDao daocategory;

    @Autowired
    private BrandDao daobrand;

    @Autowired
    private ItemDao dao;


    @RequestMapping(value = "/items/list", method = RequestMethod.GET, produces = "application/json")
    public  List<Item> list(){
        return dao.list();
    }

    @RequestMapping(value = "/items/listbysupplier",params = "supplierid", method = RequestMethod.GET, produces = "application/json")
    public  List<Item> listbysupplier(@RequestParam("supplierid") Integer supplierid){
        return dao.listbysupplier(supplierid);
    }



    @RequestMapping(value = "/items", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Item> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.ITEM,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Item item) {
        //System.out.print("Added Successfully.....!");

        if(AuthProvider.isAuthorized(username,password,ModuleList.ITEM,AuthProvider.INSERT)) {

            Item itm = dao.findByCode(item.getCode());
            if (itm != null)
                return "Error-Validation : Item Code Already Exists";
            else
                try {
                    dao.save(item);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/items", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Item item){

        if(AuthProvider.isAuthorized(username,password,ModuleList.ITEM,AuthProvider.UPDATE)) {
            try {
                dao.save(item);
                return "0";
            } catch (Exception e) {
                return "Error-Update : " + e.getMessage();
            }
        } return "Error-Updating : You have no Permission";
    }
    @RequestMapping(value = "/items", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Item item){

        if(AuthProvider.isAuthorized(username,password,ModuleList.ITEM,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(item.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Delete : " + e.getMessage();
            }
        } return "Error-Deleting : You have no Permission";
    }



   /* @RequestMapping(value = "/items", params = {"page", "size","name","categoryId","brandId"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Item> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("name") String name, @RequestParam("categoryId") Integer categoryId, @RequestParam("brandId") Integer brandId) {

     //   System.out.println(name+"-"+categoryId+"-"+brandId);

        if(AuthProvider.isAuthorized(username,password, ModuleList.ITEM,AuthProvider.SELECT)) {

            List<Item> items = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Item> itemstream = items.stream();


            if (categoryId != null)
                itemstream = itemstream.filter(e -> e.getSubcategoryId().getCategoryId().equals(daocategory.getOne(categoryId)));

            if (brandId != null)
                itemstream = itemstream.filter(e -> e.getBrandId().equals(daobrand.getOne(brandId)));

            //itemstream = itemstream.filter(e -> e.getName().startsWith(name));
            itemstream = itemstream.filter(e -> e.getName().contains(name));


            List<Item> items2 = itemstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < items2.size() ? start + size : items2.size();
            Page<Item> emppage = new PageImpl<>(items2.subList(start, end), PageRequest.of(page, size), items2.size());

            return emppage;
        }

        return null;


    }
*/

/*

   @RequestMapping(value = "/items", params = {"page", "size","name","categoryId","brandId"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Item> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("name") String name, @RequestParam("categoryId") Integer categoryId, @RequestParam("brandId") Integer brandId) {

       // System.out.println(name+"-"+categoryId+"-"+brandId);


        if(AuthProvider.isAuthorized(username,password, ModuleList.EMPLOYEE,AuthProvider.SELECT)) {

            List<Item> items = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Item> itemstream = items.stream();


            if (categoryId != null)
                itemstream = itemstream.filter(e -> e.getSubcategoryId().getCategoryId().equals(daocategory.getOne(categoryId)));
            if (brandId != null)
                itemstream = itemstream.filter(e -> e.getBrandId().equals(daobrand.getOne(brandId)));
           // itemstream = itemstream.filter(e -> e.getName().startsWith(name));
            itemstream = itemstream.filter(e -> e.getName().contains(name));

            List<Item> items2 = itemstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < items2.size() ? start + size : items2.size();
            Page<Item> emppage = new PageImpl<>(items2.subList(start, end), PageRequest.of(page, size), items2.size());

            return emppage;
        }

        return null;

    }

*/
    /*@RequestMapping(value = "/items", params = {"page", "size","name","categoryId","brandId"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Item> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("name") String name, @RequestParam("categoryId") Integer categoryId, @RequestParam("brandId") Integer brandId) {

       // System.out.println(name+"-"+categoryId+"-"+brandId);


    if(AuthProvider.isAuthorized(username,password, ModuleList.EMPLOYEE,AuthProvider.SELECT)) {

        List<Item> items = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
        Stream<Item> itemstream = items.stream();


        if (categoryId != null)
            itemstream = itemstream.filter(e -> e.getSubcategoryId().getCategoryId().equals(daocategory.getOne(categoryId)));

        if (brandId != null)
            itemstream = itemstream.filter(e -> e.getBrandId().equals(daobrand.getOne(brandId)));

       // itemstream = itemstream.filter(e -> e.getName().startsWith(name));
         itemstream = itemstream.filter(e -> e.getName().contains(name));

        List<Item> items2 = itemstream.collect(Collectors.toList());

        int start = page * size;
        int end = start + size < items2.size() ? start + size : items2.size();
        Page<Item> emppage = new PageImpl<>(items2.subList(start, end), PageRequest.of(page, size), items2.size());

        return emppage;
    }

    return null;

    }*/

    @RequestMapping(value = "/items", params = {"page", "size","name","categoryid","brandid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Item> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("name") String name,  @RequestParam("categoryid") Integer categoryid, @RequestParam("brandid") Integer brandid) {

     System.out.println(name+"-"+categoryid+"-"+brandid);


        if(AuthProvider.isAuthorized(username,password, ModuleList.ITEM,AuthProvider.SELECT)) {

            List<Item> items = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Item> itemstream = items.stream();

            if (categoryid != null)
                itemstream = itemstream.filter(i -> i.getSubcategoryId().getCategoryId().equals(daocategory.getOne(categoryid)));
            if (brandid != null)
                itemstream = itemstream.filter(i -> i.getBrandId().equals(daobrand.getOne(brandid)));
         //   itemstream = itemstream.filter(i -> i.getName().startsWith(name));
            itemstream = itemstream.filter(i -> i.getName().contains(name));


            List<Item> items2 = itemstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < items2.size() ? start + size : items2.size();
            Page<Item> itempage = new PageImpl<>(items2.subList(start, end), PageRequest.of(page, size), items2.size());

            return itempage;
        }

        return null;

    }


}
