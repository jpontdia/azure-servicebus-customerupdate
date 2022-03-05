package com.demo.function.vo;

import lombok.Data;

@Data
public class ServiceBusMessage {
    String messageId;
    String message;
}