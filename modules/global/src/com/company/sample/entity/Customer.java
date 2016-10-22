package com.company.sample.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.global.PersistenceHelper;

@NamePattern("%s|name")
@Table(name = "SAMPLE_CUSTOMER")
@Entity(name = "sample$Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = 5202885541508145548L;

    @Column(name = "NAME")
    protected String name;

    @OrderBy("num")
    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "customer")
    protected List<Phone> phones;

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Phone> getPhones() {
        return phones;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @MetaProperty(related = "phones")
    public Phone getMainPhone() {
        if (PersistenceHelper.isLoaded(this, "phones") && phones != null) {
            return phones.stream()
                    .filter(phone -> Boolean.TRUE.equals(phone.getMain()))
                    .findFirst()
                    .orElse(null);
        } else {
            return null;
        }
    }

    @MetaProperty(related = "phones")
    public String getPhonesAsString() {
        if (PersistenceHelper.isLoaded(this, "phones") && phones != null) {
            return phones.stream()
                    .map(phone -> phone.getNum())
                    .collect(Collectors.joining(", "));
        } else {
            return "";
        }
    }
}