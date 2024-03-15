package rabbit.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbit.utils.RabbitConnectUtils.RabbitUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/14 21:33
 */
public class provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare("log","fanout");     //广播 一条消息多个消费者同时消费
        channel.basicPublish("log","",null,"hello".getBytes());
        RabbitUtils.closeConnection(channel,connection);
    }
}
