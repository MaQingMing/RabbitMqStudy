package rabbit.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbit.utils.RabbitConnectUtils.RabbitUtils;

import java.io.IOException;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/15 20:48
 */
public class provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics","topic");
        //使用动态路由
        String key ="user.find";
        channel.basicPublish("topics",key,null,("这是动态路由模型"+key).getBytes());
        RabbitUtils.closeConnection(channel,connection);
    }
}
