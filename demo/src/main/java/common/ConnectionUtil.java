package common;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/*
* RabbitMQ
* 创建链接
* */
public class ConnectionUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/vhost_mmr");
        factory.setUsername("user_mmr");
        factory.setPassword("123");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;

    }
}
