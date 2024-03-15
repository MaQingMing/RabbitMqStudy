package rabbit.utils.RabbitConnectUtils;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/14 19:49
 */
public class RabbitUtils {
    private static ConnectionFactory connectionFactory;
    //类加载的时候就执行
    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.23.133");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("Mqm");
        connectionFactory.setPassword("mqm");
        connectionFactory.setVirtualHost("/ems");

    }
    //获取连接
    public static Connection getConnection(){
     try {
         Connection connection = connectionFactory.newConnection();
         return connection;
     }catch (Exception e){
         e.printStackTrace();
     }
        return null;
    }

    //关闭连接
    public static void closeConnection(Channel channel,Connection connection){
        try {
            if (channel!=null) {
                channel.close();
            }
            if (connection!=null){
                connection.close();
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}
