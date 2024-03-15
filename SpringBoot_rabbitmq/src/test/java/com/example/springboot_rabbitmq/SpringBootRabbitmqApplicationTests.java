package com.example.springboot_rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = SpringBootRabbitmqApplication.class)

public class SpringBootRabbitmqApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend("hello","hello,world");
    }


    @Test
    void workQueue(){
        //消费者采用公平策略
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","work模型");
        }
    }

    @Test
    void fanoutQueue(){
        rabbitTemplate.convertAndSend("logs","","fanout模型");
    }

    @Test
    void routeQueue(){
        rabbitTemplate.convertAndSend("dircets","info","fanout模型");

    }


    //topic
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topics","user.save.findAll","user.save.findAll 的消息");
    }
}
