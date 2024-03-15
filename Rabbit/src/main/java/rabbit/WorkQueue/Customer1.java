package rabbit.WorkQueue;

import com.rabbitmq.client.*;
import rabbit.utils.RabbitConnectUtils.RabbitUtils;

import java.io.IOException;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/14 20:54
 */

/**
 * `总结:默认情况下，RabbitMQ将按顺序将每个消息发送给下一个使用者。平均而言，每个消费者都会收到相同数量的消息。这种分发消息的方式称为循环。
 * - 设置通道一次只能消费一个消息
 * - 关闭消息的自动确认,开启手动确认消息
 */
public class Customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("hello",true,false,false,null);
        channel.basicQos(1);//一次只接受一条未确认的消息
        channel.basicConsume("hello",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1: "+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);//手动确认消息
            }
        });
    }
}
