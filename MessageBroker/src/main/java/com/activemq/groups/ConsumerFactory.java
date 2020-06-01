package com.activemq.groups;

import static java.util.Objects.nonNull;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ConsumerFactory {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(ConsumerFactory.class);

  private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;


  public Connection openConnection() throws JMSException {
    // Create a new connection factory
    ConnectionFactory connectionFactory =
        new ActiveMQConnectionFactory(url);
    return  connectionFactory.createConnection();
  }

  public void closeConnection(Connection connection) throws JMSException {
    connection.close();
  }

  public MessageConsumer createConsumer(Connection connection, String queue) throws JMSException {
    // Create a session for receiving messages

    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    MessageConsumer consumer;

    // Create the destination from which messages will be received
    Destination destination = session.createQueue(queue);

    // Create a MessageConsumer for receiving messages
    consumer = session.createConsumer(destination);

    // Start the connection in order to receive messages
    connection.start();

    return consumer;
  }

  public String receive(MessageConsumer consumer, int timeout) throws JMSException {
    // Read a message from the destination
    Message message = consumer.receive(timeout);
    if(nonNull(message)){
      String groupID = message.getStringProperty("JMSXGroupID");
      String messageSequence = message.getStringProperty("JMSXGroupSeq");

      // Cast the message to the correct type
      TextMessage input = (TextMessage) message;

      // Retrieve the message content
      String text = input.getText();
      LOGGER.info("{} received, GroupId: {}, messageSequence:{}", text, groupID, messageSequence);
      return text;
    } else {
      LOGGER.info("There is not messages for consumming");
      return null;
    }
  }
}
