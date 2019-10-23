package pb;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import common.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String EXCHANGE_NAME="test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        channel.basicPublish(EXCHANGE_NAME,"",null,"hello world".getBytes());
        System.out.println("Send hello world");
        channel.close();
        connection.close();

    }

    /*
    *交换机
    * channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
    * 接受生产者的消息，向队列推送消息
    * 匿名转发 “” channel.basicPublish("",QUEUE_NAME,null, str.getBytes());
    * fanout 不处理路由键，只要与交换机绑定的队列都能收到消息
    * Direct 处理路由键， channel.basicPublish(EXCHANGE_NAME,Direct,null,"hello world".getBytes());
    * 路由模式：
    *
    * */

}
