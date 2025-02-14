package com.redbus.bean.pojo;
// Generated Jun 22, 2024 7:50:33 PM by Hibernate Tools 3.4.0.CR1


import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * DroppingPoint generated by hbm2java
 */
public class DroppingPoint  implements java.io.Serializable {


     private Long id;
     private String droppingPoint;
     private String isActive;
     private Long modifiedBy;
     private Date modifiedAt;
     private Long createdBy;
     private Timestamp createdAt;
     private Bus bus;
     private Set bookingsDatas = new HashSet(0);

    public DroppingPoint() {
    }

	
    public DroppingPoint(String droppingPoint, String isActive, Long createdBy, Timestamp createdAt) {
        this.droppingPoint = droppingPoint;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
    public DroppingPoint(String droppingPoint, String isActive, Long modifiedBy, Date modifiedAt, Long createdBy, Timestamp createdAt, Bus bus, Set bookingsDatas) {
       this.droppingPoint = droppingPoint;
       this.isActive = isActive;
       this.modifiedBy = modifiedBy;
       this.modifiedAt = modifiedAt;
       this.createdBy = createdBy;
       this.createdAt = createdAt;
       this.bus = bus;
       this.bookingsDatas = bookingsDatas;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getDroppingPoint() {
        return this.droppingPoint;
    }
    
    public void setDroppingPoint(String droppingPoint) {
        this.droppingPoint = droppingPoint;
    }
    public String getIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
    public Long getModifiedBy() {
        return this.modifiedBy;
    }
    
    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public Date getModifiedAt() {
        return this.modifiedAt;
    }
    
    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
    public Long getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
    public Timestamp getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public Bus getBus() {
        return this.bus;
    }
    
    public void setBus(Bus bus) {
        this.bus = bus;
    }
    public Set getBookingsDatas() {
        return this.bookingsDatas;
    }
    
    public void setBookingsDatas(Set bookingsDatas) {
        this.bookingsDatas = bookingsDatas;
    }




}


