package pers.darren.mq.activemq.pubsub;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;
import static org.apache.activemq.ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL;

import java.io.IOException;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息订阅者
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 10, 2021 5:32:16 PM
 */
public class Subscriber {

    public static void main(final String[] args) throws JMSException, IOException {
        // 创建一个ConnectionFactory对象连接MQ服务器
        final ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_BIND_URL);
        // 创建一个连接对象
        final var connection = connectionFactory.createConnection();
        // 开启连接
        connection.start();
        // 使用Connection对象创建一个Session对象
        final var session = connection.createSession(false, AUTO_ACKNOWLEDGE);
        // 创建一个Destination对象。topic对象
        final Destination topic = session.createTopic("test-topic");
        // 使用Session对象创建一个消费者对象。
        final var consumer = session.createConsumer(topic);
        // 接收消息
        consumer.setMessageListener(message -> {
            // 打印结果
            final var textMessage = (TextMessage) message;
            String text;
            try {
                text = textMessage.getText();
                System.out.println("这是接收到的消息：" + text);
            } catch (final JMSException e) {
                e.printStackTrace();
            }
        });
        System.out.println("topic消费者启动。。。。");
        // 等待接收消息
        System.in.read();
        // 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}