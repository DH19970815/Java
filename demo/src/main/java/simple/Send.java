package simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import common.ConnectionUtil;
import common.GetByte;
import entiy.Student;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/*
* 消息生产者
* */
public class Send {
    //队列名字
    private static final String QUEUE_NAME = "q_test_01";

    public static void simple() throws IOException, TimeoutException {
        //获取链接
        Connection con = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = con.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String str = "hello world";
        Student student = new Student("杜航",23,"男");
        final byte[] stu = GetByte.getbyte(student);

        //发布消息
        channel.basicPublish("", QUEUE_NAME, null, stu);
        System.out.println(" [x] Sent '" + str + "'");
        //关闭通道和连接
        channel.close();
        con.close();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        simple();
    }



}
