package com.bank.msbootcointransferyankibatch.services.impl;

import com.bank.msbootcointransferyankibatch.models.dao.CustomerDao;
import com.bank.msbootcointransferyankibatch.models.documents.Customer;
import com.bank.msbootcointransferyankibatch.models.enums.StatusEnum;
import com.bank.msbootcointransferyankibatch.models.utils.Status;
import com.bank.msbootcointransferyankibatch.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao dao;

    @Override
    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        return dao.findAll()
                .stream()
                .filter(x -> x.getPhoneNumber().equals(phoneNumber))
                .findFirst();
    }

}
