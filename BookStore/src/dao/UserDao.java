package dao;

import domain.User;

public interface UserDao {

	void add(User user);

	User find(String id);

	User find(String username, String password);

}