package work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import common.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/*
* 工作对列轮询分发
* */
public class Send {

    private static final String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for (int i=1; i<=50; i++){
            String str = "hello world "+i;
            channel.basicPublish("",QUEUE_NAME,null, str.getBytes());
            System.out.println("[i] done");
            Thread.sleep(1*10);
        }
        channel.close();
        connection.close();

    }
}
