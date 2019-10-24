package rotuer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import common.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/*
* 路由模式*/
public class Send {

    private static final String EXCHANGE_NAME="test_exchange_direct";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        String rotuerKey = "info";
        channel.basicPublish(EXCHANGE_NAME,rotuerKey,null,"hello world".getBytes());
        channel.close();
        connection.close();
    }
}
