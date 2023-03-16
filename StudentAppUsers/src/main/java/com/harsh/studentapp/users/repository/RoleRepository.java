package com.harsh.studentapp.users.repository;

import java.util.Optional;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.harsh.studentapp.users.models.ERole;
import com.harsh.studentapp.users.models.Role;
import org.springframework.data.mongodb.repository.Query;

public interface RoleRepository extends MongoRepository<Role, Long> {
	@Query("{name:'?0'}")
	Optional<Role> findByName(ERole name);
}