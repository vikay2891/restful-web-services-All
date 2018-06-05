package com.rest.webservices.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rest.webservices.user.User;

/*@Repository
@Transactional*/

@Component
public class UserDaoService {
	
	
/*	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(User user) {
		
		entityManager.persist(user);
		
		return user.getId();
		
		
	}
*/
	
	public List<User> users= new ArrayList<>();
	public List<User> findAll(){
		return users;
	}
	
}
