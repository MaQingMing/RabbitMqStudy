package com.example.springboot_rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/15 22:03
 */
public class rabbitFanout {

    @RabbitListener(bindings = @QueueBinding(value = @Queue,exchange = @Exchange(name = "logs",type = "fanout")))
    public void customer1(String message){
        System.out.println("message = " + message);
    }
    @RabbitListener(bindings = @QueueBinding(value = @Queue,exchange = @Exchange(name = "logs",type = "fanout")))
    public void customer2(String message){
        System.out.println("message = " + message);
    }
}
