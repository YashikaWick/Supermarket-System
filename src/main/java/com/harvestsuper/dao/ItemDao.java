package com.harvestsuper.dao;

import com.harvestsuper.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemDao extends JpaRepository<Item,Integer> {

    @Query("SELECT new Item(i.id, i.name) FROM Item i")
    List<Item> list();

    @Query("SELECT i FROM Item i ORDER BY i.id DESC")
    Page<Item> findAll(Pageable of);

    @Query("SELECT itm FROM Item itm WHERE itm.code= :code")
    Item findByCode(@Param("code")String code);

    @Query("SELECT new Item(itm.id, itm.name, itm.purchaseprice) FROM Item itm WHERE itm in " +
            "(SELECT su.itemId FROM Supply su WHERE su.supplierId.id =:supplierid)")
    List<Item> listbysupplier(@Param("supplierid") Integer supplierid);
}
