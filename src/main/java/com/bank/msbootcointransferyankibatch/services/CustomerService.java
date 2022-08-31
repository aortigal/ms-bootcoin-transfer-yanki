package com.bank.msbootcointransferyankibatch.services;

import com.bank.msbootcointransferyankibatch.models.documents.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findByPhoneNumber(String phoneNumber);


}
