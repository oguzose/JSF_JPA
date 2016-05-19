package _01.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import _01.domain.User;

public class UserDAOImpl implements UserDAO{
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	
	public UserDAOImpl() {
		entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePersistenceUnit");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public EntityTransaction getTransaction() {
		return entityTransaction;
	}

	@Override
	public User createUser(String username, String usersurname,
			String useremail, String userpassword) {
		
		User user = new User(username, usersurname, useremail,userpassword);
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		return user;
	}

	@Override
	public User findUser(int userid) {
		return entityManager.find(User.class, userid);
	}

	@Override
	public List<User> findAllUsers() {
		TypedQuery<User> query = entityManager.createQuery("Select e from User e", User.class);
		return query.getResultList();
	}
	
	@Override
	public String adminname() {
//		TypedQuery<User> query2 = entityManager.createQuery("Select e from User e where e.userid=?1",
//				User.class);
//		return query2.getSingleResult();
		return "hosgeldin haci";
	}
	
	//	public List<User> getAuthorsByLastName(String lastName) {
	//	    String queryString = "SELECT a FROM Author a " +
	//	                         "WHERE a.lastName IS NULL OR LOWER(a.lastName) = LOWER(:lastName)";
	//	    Query query = getEntityManager().createQuery(queryString);
	//	    
	//	    query.setParameter("lastName", lastName);
	//	    return query.getResultList();
	//	}

	@Override
	public void removeUser(int userid) {

		User userr = findUser(userid);		
		if (userr != null) {			
			entityManager.getTransaction().begin();
			entityManager.remove(userr);
			entityManager.getTransaction().commit();
		}
	}



}
