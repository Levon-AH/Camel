package config;

import entity.User;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Stateless
public class JaxbConfig {
    public JAXBContext getJaxbContext() {
        try {
            return JAXBContext.newInstance(User.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
