package com.example.springboot_rabbitmq.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/15 22:15
 */
public class RabbitRoute {
    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @Queue(),
                    key={"info","error"},
                    exchange = @Exchange(type = "direct",name="directs")
            )})
    public void receive1(String message){
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @Queue(),
                    key={"error"},
                    exchange = @Exchange(type = "direct",name="directs")
            )})
    public void receive2(String message){
        System.out.println("message2 = " + message);
    }

}
