package confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import common.ConnectionUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("q_test_01",false,false,false,null);
        int count = 1;
        channel.basicQos(count);
        SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<>());
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {

            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {

            }
        });
    }
}
