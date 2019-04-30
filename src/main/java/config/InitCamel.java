package config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.camel.component.ActiveMQConfiguration;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ServiceStatus;
import org.apache.camel.cdi.ContextName;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class InitCamel {

    @Inject
    @ContextName("web-context")
    private CamelContext camelContext;


    @PostConstruct
    public void initActiveMQInCamelContext()  {
        ActiveMQComponent answer = ActiveMQComponent.activeMQComponent();
        ActiveMQConnectionFactory jmsConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(jmsConnectionFactory);
        ((ActiveMQConfiguration) answer.getConfiguration()).setConnectionFactory(pooledConnectionFactory);
        camelContext.addComponent("activemq", answer);
        if(camelContext.getStatus() == ServiceStatus.Stopped) {
            try {
                camelContext.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public CamelContext getCamelContext() {
        return camelContext;
    }
}
