package com.company.sample.web.customer;

import com.company.sample.entity.Phone;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.sample.entity.Customer;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.UUID;

public class CustomerEdit extends AbstractEditor<Customer> {

    @Named("phonesTable.create")
    private CreateAction phonesTableCreate;

    @Named("phonesTable.edit")
    private EditAction phonesTableEdit;

    @Inject
    private CollectionDatasource<Phone, UUID> phonesDs;

    @Override
    public void init(Map<String, Object> params) {
        phonesTableCreate.setAfterCommitHandler(entity -> makeSingleMainPhone((Phone) entity));
        phonesTableEdit.setAfterCommitHandler(entity -> makeSingleMainPhone((Phone) entity));
    }

    private void makeSingleMainPhone(Phone phone) {
        if (Boolean.TRUE.equals(phone.getMain())) {
            for (Phone p : phonesDs.getItems()) {
                if (!p.equals(phone) && Boolean.TRUE.equals(p.getMain())) {
                    p.setMain(false);
                }
            }
        }
    }
}