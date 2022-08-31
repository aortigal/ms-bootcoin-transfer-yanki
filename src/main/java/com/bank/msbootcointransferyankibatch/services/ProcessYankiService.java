package com.bank.msbootcointransferyankibatch.services;

import com.bank.msbootcointransferyankibatch.models.documents.Transaction;
import com.bank.msbootcointransferyankibatch.models.utils.DataEvent;

public interface ProcessYankiService {

    void process(DataEvent<Transaction> dataEvent);
}
