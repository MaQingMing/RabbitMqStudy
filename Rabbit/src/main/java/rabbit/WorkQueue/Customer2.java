package rabbit.WorkQueue;

import com.rabbitmq.client.*;
import rabbit.utils.RabbitConnectUtils.RabbitUtils;

import java.io.IOException;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/14 20:54
 */
public class Customer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);//一次只接受一条未确认的消息
        channel.queueDeclare("hello",true,false,false,null);
            //参数2:关闭自动确认消息
        channel.basicConsume("hello",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(1000);   //处理消息比较慢 一秒处理一个消息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者2: "+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);//手动确认消息
            }
        });
    }
}
