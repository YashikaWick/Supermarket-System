package com.harvestsuper.controller;

import com.harvestsuper.dao.CustomerDao;
import com.harvestsuper.dao.CustomerstatusDao;
import com.harvestsuper.entity.Customer;
import com.harvestsuper.util.ModuleList;
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
public class CustomerController {

    @Autowired
    private CustomerDao dao;

   /* @Autowired
    private DesignationDao daoDesignation;
*/
    @Autowired
    private CustomerstatusDao daoCustomerstatus;


/*
    @RequestMapping(value = "/employees/list", method = RequestMethod.GET, produces = "application/json")
    public List<Employee> list(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        if(AuthProvider.isUser(username,password)) {
            return dao.list();
        }
        return null;
    }

*/
    @RequestMapping(value = "/customer/list/withoutusers",  method = RequestMethod.GET, produces = "application/json")
    public List<Customer> listwithoutusers(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        if(AuthProvider.isUser(username,password)) {
        return dao.listWithoutUsers();
        }
        return null;
    }
/*
    @RequestMapping(value = "/customer/list/withuseraccount",  method = RequestMethod.GET, produces = "application/json")
    public List<Customer> listwithuseraccount(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        if(AuthProvider.isUser(username,password)) {
        return dao.listWithUseraccount();
        }
        return null;
    }
*/



    @RequestMapping(value = "/customers", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Customer> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.CUSTOMER,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }


    @RequestMapping(value = "/customers", params = {"page", "size","name","nic","arreas","customerstatusid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Customer> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("name") String name, @RequestParam("nic") String nic, @RequestParam("arreas") Integer arreas, @RequestParam("customerstatusid") Integer customerstatusid) {

       System.out.println(name+"-"+nic+"-"+arreas+"-"+customerstatusid);


        if(AuthProvider.isAuthorized(username,password, ModuleList.CUSTOMER,AuthProvider.SELECT)) {

            List<Customer> customers = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Customer> customerstream = customers.stream();

/*
            if (designationid != null)
                customerstream = customerstream.filter(e -> e.getDesignationId().equals(daoDesignation.getOne(designationid)));
      */
            if (customerstatusid != null)
                customerstream = customerstream.filter(e -> e.getCustomerstatusId().equals(daoCustomerstatus.getOne(customerstatusid)));
            customerstream = customerstream.filter(e -> e.getNic().contains(nic));
            customerstream = customerstream.filter(e -> e.getFname().startsWith(name));

            List<Customer> customers2 = customerstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < customers2.size() ? start + size : customers2.size();
            Page<Customer> cumpage = new PageImpl<>(customers2.subList(start, end), PageRequest.of(page, size), customers2.size());

            return cumpage;
        }

        return null;

    }


    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Customer customer) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.EMPLOYEE,AuthProvider.INSERT)) {
            Customer cumnic = dao.findByNIC(customer.getNic());

            if (cumnic != null)
                return "Error-Validation : NIC Exists";
            else
                try {
                    dao.save(customer);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }



    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Customer customer) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.CUSTOMER,AuthProvider.UPDATE)) {
            Customer cus = dao.findByNIC(customer.getNic());
            System.out.println(customer.getNic());
        if(cus!=null || cus.getId()==customer.getId()) {
            try {
                dao.save(customer);
                return "0";
            }
            catch(Exception e) {
                return "Error-Updating : "+e.getMessage();
            }
        }
        else {  return "Error-Updating : NIC Exists"; }
        }
        return "Error-Updating : You have no Permission";
    }


    @RequestMapping(value = "/customers", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Customer customer ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.CUSTOMER,AuthProvider.DELETE)) {
        try {
            dao.delete(dao.getOne(customer.getId()));
            return "0";
        }
        catch(Exception e) {
            return "Error-Deleting : "+e.getMessage();
        }
    }
        return "Error-Deleting : You have no Permission";

    }



}
