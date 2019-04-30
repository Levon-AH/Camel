package service;

import config.JaxbConfig;
import entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

@Stateless
public class FileService {

    @Inject
    private JaxbConfig jaxbConfig;

    public void createFromFile(User user) throws IOException, JAXBException {
        File file = new File("C:\\folder\\add-user.xml");
        if (!file.exists()) {
            file.createNewFile();
        }

        JAXBContext jaxbContext = jaxbConfig.getJaxbContext();
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(user, file);
    }
}
