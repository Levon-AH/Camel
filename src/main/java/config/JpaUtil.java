package config;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class JpaUtil {
    private EntityManagerFactory etf = Persistence.createEntityManagerFactory("myUnit");


    public EntityManagerFactory getEtf() {
        return etf;
    }

    public void close() {
        etf.close();
    }
}
