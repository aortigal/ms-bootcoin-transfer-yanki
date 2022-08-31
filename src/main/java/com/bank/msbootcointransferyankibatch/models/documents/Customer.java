package com.bank.msbootcointransferyankibatch.models.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "customers")
public class Customer{

    @Id
    private String id;

    private String documentNumber;

    private String phoneNumber;

    private String name;

    private String imei;

    private String email;

}
