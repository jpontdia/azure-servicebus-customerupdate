package com.demo.service.service;

import com.demo.commons.messages.MessageType;
import com.demo.commons.messages.Metadata;
import com.demo.commons.messages.schema.AddressV1;
import com.demo.commons.messages.tools.MessageTools;
import com.demo.commons.services.MessageService;
import com.demo.service.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CustomerUpdateService {

    final MessageService messageService;

    @Autowired
    public CustomerUpdateService(MessageService messageService){
        this.messageService = messageService;
    }

    public void updateCustomer(String customerId, Customer customer){

        //Validate customer object

        //Create message payload for Service Bus
        var metadata = Metadata.builder()
                        .messageType(MessageType.address)
                        .dateTime(LocalDateTime.now())
                        .version("1")
                        .publisherBuildTime(MessageTools.getBuildTime())
                        .publisherBuildVersion(MessageTools.getBuildVersion())
                        .publisherCodeRepository("azure-servicebus-customerupdate")
                        .publisherCommitId(MessageTools.getCommitId())
                        .build();

        var addressV1 = AddressV1.builder()
                .city(customer.getAddress().getCity())
                .country(customer.getAddress().getCountry())
                .line1(customer.getAddress().getLine1())
                .line2(customer.getAddress().getLine2())
                .state(customer.getAddress().getState())
                .zip(customer.getAddress().getZip())
                .metadata(metadata)
                .build();

        log.debug("Message to send to queue: {}", addressV1);
        messageService.publish(addressV1, "address-update");

        return;
    }
}
