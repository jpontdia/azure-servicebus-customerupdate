package com.demo.function.addressupdate;

import com.demo.commons.messages.schema.AddressV1;
import com.demo.commons.messages.tools.MessageTools;
import com.demo.function.vo.ServiceBusMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.function.Function;

@Component
@Slf4j
@SuppressWarnings("unused")
public class AddressUpdateCrm implements Function<ServiceBusMessage, Boolean> {

    public Boolean apply(ServiceBusMessage serviceBusMessage) {
        var addressV1 = (AddressV1) MessageTools.getMessage(serviceBusMessage.getMessage(), AddressV1.class);
        if (addressV1 != null){
            log.debug("Message received: {}", addressV1);

            try{
                //Validate message, will throw exception if hte message
                MessageTools.verify(addressV1);

                //Testing to send the message to Dead Letter Queue
                //Creating an exception
                //throw new RuntimeException("message testing exception")
            }
            catch(ConstraintViolationException cve){
                log.error("The message received is not valid", cve);
                //This message must NOT be processed again because  doesn't
                //meet the attributes expected values.
            }
            catch(Exception e){
                log.error("There was an error during accessing the backend system, please check log", e);
                //This message must be re processed again, and will be sent to
                //the DeadLetter Queue.
                throw e;
            }
        }
        return true;
    }
}