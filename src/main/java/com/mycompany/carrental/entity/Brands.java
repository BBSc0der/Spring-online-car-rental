package com.mycompany.carrental.entity;
// Generated 2017-06-05 18:33:45 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Brands generated by hbm2java
 */
@Entity
@Table(name="brands"
    ,catalog="rental"
)
public class Brands  implements java.io.Serializable {


     private Integer idBrands;
     private String name;
     private Set<Vehicles> vehicleses = new HashSet<Vehicles>(0);

    public Brands() {
    }

	
    public Brands(String name) {
        this.name = name;
    }
    public Brands(String name, Set<Vehicles> vehicleses) {
       this.name = name;
       this.vehicleses = vehicleses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idBrands", unique=true, nullable=false)
    public Integer getIdBrands() {
        return this.idBrands;
    }
    
    public void setIdBrands(Integer idBrands) {
        this.idBrands = idBrands;
    }

    
    @Column(name="name", nullable=false, length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@OneToMany(fetch=FetchType.EAGER, mappedBy="brands")
    public Set<Vehicles> getVehicleses() {
        return this.vehicleses;
    }
    
    public void setVehicleses(Set<Vehicles> vehicleses) {
        this.vehicleses = vehicleses;
    }




}


