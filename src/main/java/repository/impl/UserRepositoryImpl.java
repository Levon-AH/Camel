package repository.impl;

import entity.User;
import config.JpaUtil;
import repository.UserRepository;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@LocalBean
@Singleton
public class UserRepositoryImpl implements UserRepository {


    @Inject
    private JpaUtil jpaUtil;

    @Override
    public void add(User user) {
        EntityManager entityManager = jpaUtil.getEtf().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
    }
}
