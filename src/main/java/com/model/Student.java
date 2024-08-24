package com.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private String sname;
    private String scity;
    private double spercentage;
    public int getSid() {
        return sid;
    }
    public String getSname() {
        return sname;
    }
    public String getScity() {
        return scity;
    }
    public double getSpercentage() {
        return spercentage;
    }
    @Override
    public String toString() {
        return "Student [sid=" + sid + ", sname=" + sname + ", scity=" + scity + ", spercentage=" + spercentage + "]";
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public void setScity(String scity) {
        this.scity = scity;
    }
    public void setSpercentage(double spercentage) {
        this.spercentage = spercentage;
    }
}
