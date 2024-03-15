package rabbit.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbit.utils.RabbitConnectUtils.RabbitUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/14 22:21
 */
public class provider {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        //第一个参数有是交换机名，第二个是交换机模式 (dircet) 是固定的 路由模式
        channel.exchangeDeclare("log_dircet","direct");
        String key = "info";
        //第一个参数 交换机名
        channel.basicPublish("log_dircet",key,null,("生产者发送的消息"+key).getBytes());
    }

}
