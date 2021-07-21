package pers.darren.mq.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;

/**
 * 消息生产者
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Jul 14, 2021 11:17:29 AM
 */
public class Producer {

    public static void main(final String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        final var factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        // 设置 RabbitMQ 地址
        factory.setHost("localhost");
        factory.setPort(5671);
        // 建立到代理服务器到连接
        final var connection = factory.newConnection();
        // 获得信道
        final var channel = connection.createChannel();
        // 声明交换器
        final var exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);

        final var routingKey = "hola";
        // 发布消息
        final var messageBodyBytes = "quit".getBytes();
        channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);

        channel.close();
        connection.close();
    }
}