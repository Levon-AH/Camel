package repository;

import entity.User;

import javax.ejb.Local;

@Local
public interface UserRepository {
    void add(User user);
}
