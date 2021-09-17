package pers.darren.mq.activemq.p2p;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;
import static org.apache.activemq.ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息消费者
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 10, 2021 11:36:58 AM
 */
public class Consumer {

    public static void main(final String[] args) throws JMSException {
        final ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_BIND_URL);
        final var connection = connectionFactory.createConnection();
        connection.start();
        final var session = connection.createSession(false, AUTO_ACKNOWLEDGE);
        final Destination destination = session.createQueue("queue");
        final var consumer = session.createConsumer(destination);
        while (true) {
            final var message = (TextMessage) consumer.receive();
            if (message == null) {
                break;
            }
            System.out.println(message.getText());
        }
        if (connection != null) {
            connection.close();
        }
    }
}