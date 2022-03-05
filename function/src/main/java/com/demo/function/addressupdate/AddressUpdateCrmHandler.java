package com.demo.function.addressupdate;

import com.demo.function.vo.ServiceBusMessage;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusTopicTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

@SuppressWarnings("unused")
public class AddressUpdateCrmHandler extends FunctionInvoker<ServiceBusMessage, Boolean>{

    @FunctionName("addressUpdateCrm")
    public void run(
            @ServiceBusTopicTrigger(
                name = "message",
                topicName = "address-update",
                subscriptionName = "crm",
                connection = "ServiceBusConnection"
            )
                String message,
                final ExecutionContext executionContext,
                @BindingName("MessageId") String messageId){
                    executionContext.getLogger().info("Handler messageId received: " + messageId);
                    var event = new ServiceBusMessage();
                    event.setMessageId(messageId);
                    event.setMessage(message);
                    handleRequest(event, executionContext);
    }
}
