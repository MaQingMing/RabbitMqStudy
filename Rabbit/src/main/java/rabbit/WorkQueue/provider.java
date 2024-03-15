package rabbit.WorkQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbit.utils.RabbitConnectUtils.RabbitUtils;

import java.io.IOException;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/14 20:52
 */
public class provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", true, false, false, null);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "hello", null, (i+"====>:我是消息").getBytes());
        }
        RabbitUtils.closeConnection(channel,connection);
    }
}
