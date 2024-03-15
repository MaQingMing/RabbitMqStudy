package rabbit.routing;

import com.rabbitmq.client.*;
import rabbit.utils.RabbitConnectUtils.RabbitUtils;

import java.io.IOException;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/14 22:47
 */
public class customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        String exchange="log_dircet";
        channel.exchangeDeclare(exchange,"direct");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,exchange,"error");
        channel.queueBind(queue,exchange,"warning");
        channel.queueBind(queue,exchange,"info");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1" + new String(body));
            }
        });
    }
}
