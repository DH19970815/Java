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

}
