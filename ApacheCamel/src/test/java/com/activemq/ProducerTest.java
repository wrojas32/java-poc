package com.activemq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.activemq.producer.ProducerFactory;

import javax.jms.JMSException;

public class ProducerTest {
  private static ProducerFactory producerFactory;

  private static String QUEUE = "INBOUND.D";
  private static String TOPIC = "abc";
  private static String VIRTUAL_TOPIC = "VirtualTopic.abc";

  @Before
  public void setUpBefore() throws JMSException {
    producerFactory = new ProducerFactory();
    producerFactory.openConnection();
    //producerFactory.createQueueProducer(QUEUE);
    producerFactory.createTopicProducer(VIRTUAL_TOPIC);
    //producerFactory.createTopicProducer(TOPIC);
  }

  @After
  public void tearDownAfter() throws JMSException {
    producerFactory.closeConnection();
  }

  @Test
  public void testSend() throws JMSException {
    for (int j=1; j<=2 ; j++){
      for (int i=1; i<=5 ; i++) {
        producerFactory.sendMessages("TEST-GROUP-" + i, j);
      }
    }
  }
}
