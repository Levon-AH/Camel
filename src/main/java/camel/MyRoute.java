package camel;

import config.JaxbConfig;
import entity.User;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;

import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@Startup
@ApplicationScoped
@ContextName("web-context")
public class MyRoute extends RouteBuilder {

    @Inject
    private JaxbConfig jaxbConfig;

    @Inject
    private UserRepository repository;




    @Override
    public void configure() throws Exception {
        DataFormat dataFormat = new JaxbDataFormat(jaxbConfig.getJaxbContext());
        from("file:C:/folder")
                .unmarshal(dataFormat)
                .to("activemq:queue:testb").end();

        from("activemq:queue:testb").unmarshal(dataFormat).process(exchange -> {
            User user = exchange.getIn().getBody(User.class);
            System.out.println(exchange.getIn().getBody().getClass().getSimpleName());
            System.out.println(user);
            repository.add(user);
        });

    }
}
