package com.microservice.microservicegym.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableSqs
public class SQSConfig {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {
        return new QueueMessagingTemplate(amazonSQSAsyncClientBuilder());
    }

    @Primary
    @Bean
    public AmazonSQSAsync amazonSQSAsyncClientBuilder() {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(
                        new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey))
                ).build();
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory() {
        SimpleMessageListenerContainerFactory msgListenerContainerFactory = new SimpleMessageListenerContainerFactory();
        msgListenerContainerFactory.setAmazonSqs(amazonSQSAsyncClientBuilder());
//        msgListenerContainerFactory.setMaxNumberOfMessages(10);
        return msgListenerContainerFactory;
    }


//    @Bean  public QueueMessageHandler queueMessageHandler () {
//        QueueMessageHandlerFactory queueMsgHandlerFactory = new
//                QueueMessageHandlerFactory();
//        queueMsgHandlerFactory.setAmazonSqs(amazonSQSAsyncClientBuilder());
//        QueueMessageHandler queueMessageHandler =
//                queueMsgHandlerFactory.createQueueMessageHandler();
//        List<HandlerMethodArgumentResolver> list = new ArrayList<>();
//        HandlerMethodArgumentResolver resolver = new
//                PayloadMethodArgumentResolver(new MappingJackson2MessageConverter());
//        list.add(resolver);
//        queueMessageHandler.setArgumentResolvers(list);
//
//        return queueMessageHandler;
//    }
//
//    @Bean
//    public QueueMessageHandler queueMessageHandler(QueueMessageHandlerFactory factory) {
//        return factory.createQueueMessageHandler();
//    }
//@Bean
//public QueueMessageHandler queueMessageHandler() {
//    QueueMessageHandlerFactory queueMessageHandlerFactory = new QueueMessageHandlerFactory();
//    queueMessageHandlerFactory.setAmazonSqs(amazonSQSAsyncClientBuilder());
//    QueueMessageHandler queueMessageHandler = queueMessageHandlerFactory.createQueueMessageHandler();
//    return queueMessageHandler;
//}
//    @Bean
//    public QueueMessageHandler queueMessageHandler() {
//        QueueMessageHandlerFactory queueMsgHandlerFactory = new QueueMessageHandlerFactory();
//        queueMsgHandlerFactory.setAmazonSqs(amazonSQSAsyncClientBuilder());
//        QueueMessageHandler queueMessageHandler = queueMsgHandlerFactory.createQueueMessageHandler();
//        List<HandlerMethodArgumentResolver> list = new ArrayList<>();
//        HandlerMethodArgumentResolver resolver = null;
//        list.add(resolver);
//        queueMessageHandler.setArgumentResolvers(list);
//        return queueMessageHandler;
//    }
//
//    @Bean
//    public MessageConverter jacksonJmsMessageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
//        converter.setTypeIdMappings(typeIdMappings);
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        return converter;
//    }
}
