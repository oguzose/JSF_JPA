package _01.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import _01.domain.User;

@ManagedBean
@SessionScoped
@SuppressWarnings("unused")
public class LoginBean {
	
	private String user;
	private String password;
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public String K_user(){
		
		entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePersistenceUnit");
		// create EntityManagerFactory
		
		entityManager = entityManagerFactory.createEntityManager();
		// create EntityManager
		entityTransaction = entityManager.getTransaction();
		
		//EntityManager em = EntityUtil.getEntityManager();
		
		Query query = (Query) entityManager.createQuery("SELECT c FROM User c where c.username= :username and c.userpassword = :userpassword");
		query.setParameter("username", user);
		query.setParameter("userpassword", password);
		@SuppressWarnings("unchecked")
		List<User> results = ((javax.persistence.Query) query).getResultList();
		// User result = (User) ((javax.persistence.Query) query).getSingleResult();
		for (User c : results) {
			//System.out.println(c.getName());
			return "homepage?faces-redirect=true";			
		}
		
		return "false";
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
