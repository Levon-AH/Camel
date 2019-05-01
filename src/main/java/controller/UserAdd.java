package controller;

import entity.User;

import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.FileService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@WebServlet("/add")
public class UserAdd extends HttpServlet {

    @Inject
    private UserRepository userRepository;
    @Inject
    private FileService fileService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        User user = new User();
        user.setName(name);
        user.setAge(age);
        userRepository.add(user);

        try {
            fileService.createFromFile(user);
        } catch (JAXBException e) {
            System.err.println(e);
        }

    }
}
