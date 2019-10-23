package work;

import com.rabbitmq.client.*;
import common.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv1 {

    private static final String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String str = new String(body, "utf-8");
                int i = 0;
                System.out.println("Recv1 "+str);
                System.out.println("done1 "+i++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
