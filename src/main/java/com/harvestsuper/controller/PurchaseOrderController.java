package com.harvestsuper.controller;

import com.harvestsuper.dao.PurchaseOrderDao;
import com.harvestsuper.dao.SupplierDao;
import com.harvestsuper.dao.SupplierstatusDao;
import com.harvestsuper.entity.Porder;
import com.harvestsuper.entity.Porderitem;
import com.harvestsuper.entity.Supplier;
import com.harvestsuper.entity.Supply;
import com.harvestsuper.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderDao dao;

    @Autowired
    private SupplierstatusDao statusdao;



    @RequestMapping(value = "/purchaseorders/pono", method = RequestMethod.GET, produces = "application/json")
    public String lastPonumber(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.PORDERS,AuthProvider.SELECT)) {
            String pono = dao.getLastPorder();
            System.out.println(pono);
            Integer poNumber = Integer.parseInt(pono);
            String porderNumber="";
            if(poNumber<9)
                 porderNumber = "0000"+(poNumber+1);
            else if(poNumber<99)
                porderNumber = "000"+(poNumber+1);
            else if(poNumber<999)
                porderNumber = "00"+(poNumber+1);
            else if(poNumber<9999)
                porderNumber = "0"+ (poNumber+1);

            return porderNumber;
        }
        return null;
    }

    @RequestMapping(value = "/purchaseorders", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Porder> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.PORDERS,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/purchaseorders", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Porder purchasorder) {
        //System.out.print("Added Successfully.....!");

        if(AuthProvider.isAuthorized(username,password,ModuleList.PORDERS,AuthProvider.INSERT)) {
            Porder po = dao.findByPoNo(purchasorder.getPorderno());
            if (po != null)
                return "Error-Validation : Purchase Order Already Exists";
            else
                try {
                for (Porderitem poitm: purchasorder.getPorderitemList())
                    poitm.setPorderId(purchasorder);

                    dao.save(purchasorder);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }


/*
    @RequestMapping(value = "/suppliers", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Supplier supplier) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.SUPPLIER,AuthProvider.UPDATE)) {
            try {
                Supplier supplierfrompersisten = dao.getOne(supplier.getId());

                supplierfrompersisten.setEmployeeId(supplier.getEmployeeId());

                supplierfrompersisten.setRegno(supplier.getRegno());
                supplierfrompersisten.setName(supplier.getName());
                supplierfrompersisten.setAddress(supplier.getAddress());

                supplierfrompersisten.setContactname(supplier.getContactname());
                supplierfrompersisten.setContactnumber(supplier.getContactnumber());

                supplierfrompersisten.setSupplierstatusId(supplier.getSupplierstatusId());

                supplierfrompersisten.setDate(supplier.getDate());
                supplierfrompersisten.setSupplierstatusId(supplier.getSupplierstatusId());
                supplierfrompersisten.setEmail(supplier.getEmail());
                supplierfrompersisten.setNic(supplier.getNic());

                supplierfrompersisten.getSupplyList().clear();

                for (Supply sup:supplier.getSupplyList())
                { supplierfrompersisten.getSupplyList().add(sup);
                    sup.setSupplierId(supplierfrompersisten);
                }

                dao.save(supplierfrompersisten);
                return "0";
            }
            catch(Exception e) {
                return "Error-Saving : " + e.getMessage();
            }
        }
        else
            return "Error-Updating : You have no Permission";
    }


    @Transactional
    @RequestMapping(value = "/suppliers", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Supplier supplier ) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.SUPPLIER,AuthProvider.DELETE)) {
            try {
                Supplier sup = dao.getOne(supplier.getId());
                sup.getSupplyList().remove(sup);
                dao.save(sup);
                dao.delete(sup);
                return "0";

            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }

        else
            return "Error-Deleting : You have no Permission";

    }


    @RequestMapping(value = "/suppliers", params = {"page", "size","name","statusid","itemid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Supplier> findAll(@CookieValue(value="username", required=false) String uname, @CookieValue(value="password", required=false) String pword,@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("name") String name, @RequestParam("statusid") Integer statusid, @RequestParam("itemid") Integer itemid) {

        System.out.println(name+"-"+statusid+"-"+itemid);

       if(AuthProvider.isAuthorized(uname,pword,ModuleList.SUPPLIER,AuthProvider.SELECT)) {

            List<Supplier> suppliers = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));

            Stream<Supplier> supplierstream = suppliers.stream();

            if (itemid != null) {
                supplierstream = supplierstream.filter(u -> {
                    for (Supply ur : u.getSupplyList())
                        if (ur.getItemId().getId().equals(itemid))
                            return true;
                    return false;
                });
            }

            supplierstream = supplierstream.filter(u -> u.getName().startsWith(name));
            if(statusid != null)
            supplierstream = supplierstream.filter(u -> u.getSupplierstatusId().equals(statusdao.getOne(statusid)));

            List<Supplier> supplier2 = supplierstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < supplier2.size() ? start + size : supplier2.size();
            Page<Supplier> suppage = new PageImpl<>(supplier2.subList(start, end), PageRequest.of(page, size), supplier2.size());


            return suppage;
        }
        else
            return  null;

    }
*/
}
