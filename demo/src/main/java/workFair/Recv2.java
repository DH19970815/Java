package workFair;

import com.rabbitmq.client.*;
import common.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv2 {

    private static final String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String str = new String(body, "utf-8");
                int i = 0;
                System.out.println("Recv2 "+str);
                System.out.println("done2 "+i++);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //消息回执(确认)
                channel.basicAck(envelope.getDeliveryTag(),false);
                /*
                 *消息回执（拒绝），消息重新回到队列，
                 * @param deliveryTag 发布的每一条消息都会获得一个唯一的deliveryTag，deliveryTag在channel范围内是唯一的
                 * @param requeue 是否重回队列 如果值为true，则重新放入RabbitMQ的发送队列，如果值为false，则通知RabbitMQ销毁这条消息
                 */
                //channel.basicReject(envelope.getDeliveryTag(),false);
            }
        };
        //消息确认模式（显示确认），为true时自动模式
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);
    }
}
