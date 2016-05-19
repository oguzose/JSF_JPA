package _01.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import _01.domain.User;

public interface UserService {

	public User createUser(String username, String usersurname, String useremail, String userpassword);
	public User findUser(int userid);
	public List<User> findAllUsers();
	public String adminname();
	public void removeUser(int userid);
	
	public EntityManager getEntityManager();
	public EntityTransaction getTransaction();
}
