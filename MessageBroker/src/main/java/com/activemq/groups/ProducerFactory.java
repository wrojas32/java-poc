package com.activemq.groups;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ProducerFactory {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(ProducerFactory.class);

  private Connection connection;
  private Session session;
  private MessageProducer messageProducer;

  public void openConnection() throws JMSException {
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
    connection = connectionFactory.createConnection();
  }

  public void closeConnection() throws JMSException {
    connection.close();
  }

  public void createProducerQueue(String queue) throws JMSException {
    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Destination destination = session.createQueue(queue);
    messageProducer = session.createProducer(destination);
  }

  public void createProducerTopic(String queue) throws JMSException {
    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Destination destination = session.createTopic(queue);
    messageProducer = session.createProducer(destination);
  }

  public void send(String groupId, int i) throws JMSException {
      String msg = "Message-" + i;
      TextMessage message = session.createTextMessage(msg);
      message.setStringProperty("JMSXGroupID", groupId);
      message.setIntProperty("JMSXGroupSeq", i);
      messageProducer.send(message);
      LOGGER.info("message sent: {} ", msg);
  }

  /**
   * To close a group you can add a negative sequence number,
   * then closes the message group so if another message is sent
   * in the future with the same message group ID it will be reassigned to a new consumer.
   * @param groupId
   * @throws JMSException
   */
  public void closeGroup(String groupId) throws JMSException {
    TextMessage message = session.createTextMessage("Final message");
    message.setStringProperty("JMSXGroupID", groupId);
    message.setIntProperty("JMSXGroupSeq", -1);
    messageProducer.send(message);
    LOGGER.info("TEST-GROUP Closed");
  }

}
