package com.harvestsuper.dao;

import com.harvestsuper.entity.Porder;
import com.harvestsuper.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseOrderDao extends JpaRepository<Porder,Integer> {

    @Query("SELECT po FROM Porder po ORDER BY po.id DESC")
    Page<Porder> findAll(Pageable of);

    @Query("SELECT po FROM Porder po WHERE po.porderno= :pono")
    Porder findByPoNo(@Param("pono") String pono);

    @Query("SELECT SUBSTRING(MAX (po.porderno),2,5) FROM Porder po")
    String getLastPorder();
}
