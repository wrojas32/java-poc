package com.jms.routes;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.spring.SpringRouteBuilder;

@Slf4j
@Component
public class JmsTestRouter extends SpringRouteBuilder {

  @Override
  public void configure() throws Exception {
    System.out.println("Configuring route");

    from("{{input.queueFromVirtualTopic}}")
        .log(LoggingLevel.INFO, log, "New message received")
        .process(exchange -> {
          log.info("JMSXGroupID: {}" , exchange.getMessage().getHeader("JMSXGroupID"));
          log.info("JMSXGroupSeq: {}" , exchange.getMessage().getHeader("JMSXGroupSeq"));
        })
        .log(LoggingLevel.INFO, "Message: ${body}")
        .end();
  }
}
