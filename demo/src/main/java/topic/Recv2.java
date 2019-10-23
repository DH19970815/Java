package topic;

import com.rabbitmq.client.*;
import common.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv2 {

    private static final String QUEUE_NAME = "q_test_topic_2";
    private static final String EXCHANGE_NAME="test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"shop.#");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String str = new String(body,"utf-8");
                System.out.println("don2 "+str);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
