package rabbit.helloworld;

import com.rabbitmq.client.*;
import org.junit.Test;
import rabbit.utils.RabbitConnectUtils.RabbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/14 21:12
 */
public class Rabbit_HelloWorld {
        /**
         * 开发生产者
         * @throws IOException
         * @throws TimeoutException
         */
        @Test
        public void helloworld() throws IOException, TimeoutException {
            //创建连接工厂
            Connection connection = RabbitUtils.getConnection();
            //创建通道
            Channel channel = connection.createChannel();
            //参数1: 是否持久化  参数2:是否独占队列 参数3:是否自动删除  参数4:其他属性
            channel.queueDeclare("hello",true,false,false,null);
            /**
             *   channel.queueDeclare("hello",true,false,false,null);
             * 	'参数1':用来声明通道对应的队列
             *   '参数2':用来指定是否持久化队列
             *   '参数3':用来指定是否独占队列
             *   '参数4':用来指定是否自动删除队列
             *   '参数5':对队列的额外配置
             */
            channel.basicPublish("","hello", null,"hello rabbitmq".getBytes());

            RabbitUtils.closeConnection(channel,connection);
        }

        /**
         * 生产消费者
         */

        public static void main(String[] args) throws Exception {
            scricde();
        }
        public static void scricde() throws IOException, TimeoutException {
            //创建连接工厂
            Connection connection = RabbitUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare("hello", true, false, false, null);
            channel.basicConsume("hello",true,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println(new String(body));
                }
            });
        }
    }
