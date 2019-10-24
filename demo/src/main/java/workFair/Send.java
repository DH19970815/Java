package workFair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import common.ConnectionUtil;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
/*
* 工作队列公平分发
* */
public class Send {

    private static final String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        /*
        * 消费者返回消息确认之前，消息队列不发送下一个消息给消费者，一次只处理一个消息
        * 限制发送给同一个消费者不超过一条消息
        * */
        int count = 1;
        channel.basicQos(count);
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
