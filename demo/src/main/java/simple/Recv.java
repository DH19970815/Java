package simple;

import com.rabbitmq.client.*;
import common.ConnectionUtil;
import common.GetByte;
import entiy.Student;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
* 消费者
* */
public class Recv {

    private static final String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //simple();
        newSimple();

    }

    //新版本
    public static void newSimple() throws IOException, TimeoutException {
        //获取链接
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //定义消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                try {
                    Student student = (Student) GetByte.DeSerialization(body);
                    System.out.println(student.getName()+""+student.getAge()+""+student.getSex());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                //System.out.println("new api revc "+str);

            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,true, defaultConsumer);

    }

    //旧版本
    public static void simple() throws IOException, TimeoutException, InterruptedException {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //定义队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String str = new String(delivery.getBody());
            System.out.println("recv    " + str);
        }
    }
}
