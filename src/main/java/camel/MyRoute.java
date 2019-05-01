package camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

@Startup
@ApplicationScoped
@ContextName("web-context")
public class MyRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("file:/home/levon/Desktop/folder").split().tokenize("\n").to("activemq:queue:testb").end();
        from("activemq:queue:testb").process(exchange -> System.out.println(exchange.getIn().getBody()));
    }
}
