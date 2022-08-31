package com.bank.msbootcointransferyankibatch.services.impl;

import com.bank.msbootcointransferyankibatch.constants.Constant;
import com.bank.msbootcointransferyankibatch.models.documents.Customer;
import com.bank.msbootcointransferyankibatch.models.documents.Transaction;
import com.bank.msbootcointransferyankibatch.models.enums.StatusEnum;
import com.bank.msbootcointransferyankibatch.models.utils.DataEvent;
import com.bank.msbootcointransferyankibatch.models.utils.Status;
import com.bank.msbootcointransferyankibatch.producer.KafkaProducer;
import com.bank.msbootcointransferyankibatch.services.ProcessYankiService;
import com.bank.msbootcointransferyankibatch.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class ProcessYankiServiceImpl implements ProcessYankiService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private KafkaProducer kafkaProducer;
    private static final Logger log = LoggerFactory.getLogger(ProcessYankiServiceImpl.class);

    @Override
    public void process(DataEvent<Transaction> dataEvent) {
        log.info("[INI] Process {}", dataEvent.getId());
        if(dataEvent.getProcess().equals(Constant.PROCESS_TRANSFER_YANKI)){
            log.info("findByPhoneNumber Customer {}", dataEvent.getData().getRecipientAccount());
            Optional<Customer> customer = customerService.findByPhoneNumber(dataEvent.getData().getRecipientAccount());
            if(customer.isPresent()){
                dataEvent.setDateEvent(LocalDateTime.now());
                dataEvent.setProcess(Constant.PROCESS_BOOTCOIN_TRANSFER_YANKI_PAYMENT);
                kafkaProducer.sendMessagePaymentYanki(dataEvent);
            }else{
                dataEvent.setDateEvent(LocalDateTime.now());
                dataEvent.setProcess(Constant.PROCESS_BOOTCOIN_TRANSFER_YANKI_STATUS);
                dataEvent.getData().setStatus(new Status(StatusEnum.ERROR.getCode(),"The recipient's account does not exist."));
                kafkaProducer.sendMessageTransactionBootCoin(dataEvent);
            }
        }else{
            log.info("Procces Invalid {}", dataEvent.getProcess());
        }

        log.info("[END] Process {}", dataEvent.getId());
    }

}
