package config;

import dto.UserDto;
import entity.User;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Singleton
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
