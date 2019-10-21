/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harvestsuper.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.harvestsuper.util.RegexPattern;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sashni
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")})

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item implements Serializable {

    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Column(name = "dointroduced")
    @Temporal(TemporalType.DATE)
    private Date dointroduced;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Porderitem> porderitemList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Supply> supplyList;

    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    @Pattern(regexp = "^[A-Za-z]+([\\\\-]+)*([0-9A-Za-z]*)$", message = "Invalid Item name")
    private String name;
    
    @Column(name = "code")
    @Pattern(regexp = "^([A-Z0-9a-z]{4})$", message = "Invalid Code")
    private String code;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salesprice")
    @RegexPattern (regexp = "^([0-9]{0,6}[.][0-9]{2})$", message = "Invalid Sales Price")
    private BigDecimal salesprice;
    @Column(name = "purchaseprice")
    @RegexPattern(regexp = "^([0-9]{0,6}[.][0-9]{2})$", message = "Invalid Purchase Price")
    private BigDecimal purchaseprice;
    @Column(name = "quantity")
    @RegexPattern(regexp = "^([0-9]{1,6})$", message = "Invalid Quantity")
    private Integer quantity;
    @Column(name = "rop")
    @RegexPattern(regexp = "^([0-9]{1,4})$", message = "Invalid ROP")
    private Integer rop;
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Brand brandId;
    @JoinColumn(name = "itemstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Itemstatus itemstatusId;
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Subcategory subcategoryId;
    @JoinColumn(name = "unittype_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Unittype unittypeId;

    public Item() {
    }

    public Item(Integer id) {
        this.id = id;
    }

    public Item(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(Integer id, String name, BigDecimal purchaseprice) {
        this.id = id;
        this.name = name;
        this.purchaseprice = purchaseprice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getSalesprice() {
        return salesprice;
    }

    public void setSalesprice(BigDecimal salesprice) {
        this.salesprice = salesprice;
    }

    public BigDecimal getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(BigDecimal purchaseprice) {
        this.purchaseprice = purchaseprice;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRop() {
        return rop;
    }

    public void setRop(Integer rop) {
        this.rop = rop;
    }


    public Brand getBrandId() {
        return brandId;
    }

    public void setBrandId(Brand brandId) {
        this.brandId = brandId;
    }

    public Itemstatus getItemstatusId() {
        return itemstatusId;
    }

    public void setItemstatusId(Itemstatus itemstatusId) {
        this.itemstatusId = itemstatusId;
    }

    public Subcategory getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Subcategory subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Unittype getUnittypeId() {
        return unittypeId;
    }

    public void setUnittypeId(Unittype unittypeId) {
        this.unittypeId = unittypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.harvestsuper.entity.Item[ id=" + id + " ]";
    }


    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }


    @XmlTransient
    public List<Supply> getSupplyList() {
        return supplyList;
    }

    public void setSupplyList(List<Supply> supplyList) {
        this.supplyList = supplyList;
    }


    public Date getDointroduced() {
        return dointroduced;
    }

    public void setDointroduced(Date dointroduced) {
        this.dointroduced = dointroduced;
    }

    @XmlTransient
    public List<Porderitem> getPorderitemList() {
        return porderitemList;
    }

    public void setPorderitemList(List<Porderitem> porderitemList) {
        this.porderitemList = porderitemList;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }


    
}
