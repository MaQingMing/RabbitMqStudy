package rabbit.topic;

import com.rabbitmq.client.*;
import rabbit.utils.RabbitConnectUtils.RabbitUtils;

import java.io.IOException;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/15 20:52
 */
public class Customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics","topic");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,"topics","user.*");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1" + new String(body));
            }
        });
    }
}
