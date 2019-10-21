package com.harvestsuper.dao;

import com.harvestsuper.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustomerDao extends JpaRepository<Customer, Integer> {
    /*
        @Query(value="SELECT new Customer(e.id,e.callingname) FROM Customer e")
        List<Customer> list();
*/
        @Query(value="SELECT new Customer(c.id,c.fname) FROM Customer c WHERE c not in (Select u.customerId from User u)")
        List<Customer> listWithoutUsers();

      /*  @Query(value="SELECT new Customer(c.id,c.fname) FROM Customer c WHERE c in (Select u.customerId from User u)")
        List<Customer> listWithUseraccount();
*/
    @Query("SELECT c FROM Customer c ORDER BY c.id DESC")
    Page<Customer> findAll(Pageable of);


    @Query("SELECT e FROM Customer e WHERE e.nic= :nic")
    Customer findByNIC(@Param("nic") String nic);

/*
    @Query("SELECT e FROM Employee e WHERE e.number= :number")
    Employee findByNumber(@Param("number") String number);

*/








    /*

    @Query("SELECT e FROM Employee e WHERE e.employeestatusId.id= :employeestatusid")
    Page<Employee> findAllByEmployeestatus(Pageable of, @Param("employeestatusid")Integer employeestatusid);

    @Query("SELECT e FROM Employee e WHERE e.designationId.id= :designationid")
    Page<Employee> findAllByDesignation(Pageable of, @Param("designationid")Integer designationid);

    @Query("SELECT e FROM Employee e WHERE e.designationId.id= :designationid AND e.employeestatusId.id= :employeestatusid")
    Page<Employee> findAllByDesignationEmployeestaus(Pageable of, @Param("designationid")Integer designationid, @Param("employeestatusid")Integer employeestatusid);

    @Query("SELECT e FROM Employee e WHERE e.fullname like :name AND e.nic like :nic")
    Page<Employee> findAllByNameNIC(Pageable of, @Param("name")String name, @Param("nic")String nic);

    @Query("SELECT e FROM Employee e WHERE e.fullname like :name AND e.nic like :nic AND e.designationId.id= :designationid")
    Page<Employee> findAllByNameNICDesignation(Pageable of, @Param("name")String name, @Param("nic")String nic, @Param("designationid")Integer designationid);

    @Query("SELECT e FROM Employee e WHERE e.fullname like :name AND e.nic like :nic AND e.employeestatusId.id= :employeestatusid")
    Page<Employee> findAllByNameNICEmployeestatus(Pageable of, @Param("name")String name, @Param("nic")String nic, @Param("employeestatusid")Integer employeestatusid);

    @Query("SELECT e FROM Employee e WHERE e.fullname like :name AND e.nic like :nic AND e.designationId.id= :designationid AND e.employeestatusId.id= :employeestatusid")
    Page<Employee> findAllByNameNICDesignationEmployeestatus(Pageable of, @Param("name")String name, @Param("nic")String nic, @Param("designationid")Integer designationid, @Param("employeestatusid")Integer employeestatusid);


*/



}

