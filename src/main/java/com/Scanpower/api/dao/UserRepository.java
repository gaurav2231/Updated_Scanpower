package com.Scanpower.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Scanpower.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> ,JpaSpecificationExecutor<User>{

	@Query(value = "select * from user where emailid = ?1 and password =?2", nativeQuery = true)
	User getUserByUsername(String emailid,String password);
	 

}

