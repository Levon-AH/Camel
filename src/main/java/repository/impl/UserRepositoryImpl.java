package repository.impl;

import entity.User;
import config.JpaUtil;
import repository.UserRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Stateless
@LocalBean
public class UserRepositoryImpl implements UserRepository {

    @PersistenceUnit(name = "myUnit")
    private EntityManager entityManager;

    @Override
    public void add(User user) {
//        EntityManager entityManager = jpaUtil.getEtf().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
    }
}
