package com.activemq.groups;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jms.JMSException;

public class ProducerTest {
  private static ProducerFactory producerFactory;

  private static String QUEUE = "INBOUND.D";
  private static String TOPIC = "abc";
  private static String VIRTUAL_TOPIC = "VirtualTopic.poc";

  @Before
  public void setUpBefore() throws JMSException {
    producerFactory = new ProducerFactory();
    producerFactory.openConnection();
    //producer.createProducerQueue(QUEUE);
    producerFactory.createProducerTopic(TOPIC);
  }

  @After
  public void tearDownAfter() throws JMSException {
    producerFactory.closeConnection();
  }

  @Test
  public void testSend() throws JMSException {
    for (int j=1; j<=2 ; j++){
      for (int i=1; i<=5 ; i++) {
        producerFactory.send("TEST-GROUP-" + i, j);
      }
    }
  }
}
