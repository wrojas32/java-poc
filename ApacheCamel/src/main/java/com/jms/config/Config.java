package com.jms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;

import org.apache.camel.component.jms.JmsComponent;

import javax.jms.ConnectionFactory;

@Configuration
public class Config {

  @Bean
  public JmsTransactionManager jmsTransactionManager(final ConnectionFactory connectionFactory) {
    JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
    jmsTransactionManager.setConnectionFactory(connectionFactory);
    return jmsTransactionManager;
  }

  @Bean
  public JmsComponent jmsComponent(final ConnectionFactory connectionFactory,
      final JmsTransactionManager jmsTransactionManager) {
    JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(connectionFactory, jmsTransactionManager);
    return jmsComponent;
  }
}
