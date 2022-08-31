package com.bank.msbootcointransferyankibatch.models.dao;

import com.bank.msbootcointransferyankibatch.models.documents.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerDao extends MongoRepository<Customer, String> {
}
