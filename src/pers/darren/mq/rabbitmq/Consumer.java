package pers.darren.mq.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 消息消费者
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Jul 14, 2021 11:35:53 AM
 */
public class Consumer {

    public static void main(final String[] args) throws IOException, TimeoutException, InterruptedException {
        // 创建连接工厂
        final var factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("localhost");
        factory.setPort(5671);
        // 建立到代理服务器到连接
        final var connection = factory.newConnection();
        // 获得信道
        final var channel = connection.createChannel();
        // 声明交换器
        final var exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);
        // 声明队列
        final var queueName = channel.queueDeclare().getQueue();
        final var routingKey = "hola";
        // 绑定队列，通过键 hola 将队列和交换器绑定起来
        channel.queueBind(queueName, exchangeName, routingKey);

        while (true) {
            // 消费消息
            final var autoAck = false;
            final var consumerTag = "";
            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(final String consumerTag, final Envelope envelope,
                        final AMQP.BasicProperties properties, final byte[] body) throws IOException {
                    final var routingKey = envelope.getRoutingKey();
                    final var contentType = properties.getContentType();
                    System.out.println("消费的路由键：" + routingKey);
                    System.out.println("消费的内容类型：" + contentType);
                    final var deliveryTag = envelope.getDeliveryTag();
                    // 确认消息
                    channel.basicAck(deliveryTag, false);
                    System.out.println("消费的消息体内容：");
                    final var bodyStr = new String(body, "UTF-8");
                    System.out.println(bodyStr);
                }
            });
            Thread.sleep(1000);
        }
    }
}