package com.company.sample.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s|num")
@Table(name = "SAMPLE_PHONE")
@Entity(name = "sample$Phone")
public class Phone extends StandardEntity {
    private static final long serialVersionUID = -5141083394802606676L;

    @Column(name = "NUM")
    protected String num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    @Column(name = "MAIN")
    protected Boolean main;

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Boolean getMain() {
        return main;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }


    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }


}