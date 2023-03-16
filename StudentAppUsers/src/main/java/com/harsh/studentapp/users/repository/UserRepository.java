package com.harsh.studentapp.users.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.harsh.studentapp.users.models.*;

public interface UserRepository extends MongoRepository<User, Long> {

	@Query("{username:'?0'}")
	Optional<User> findByUsername(String username);
	@Query("{username:'?0'}")
	Boolean existsByUsername(String username);

	@Query("{email:'?0'}")
	Boolean existsByEmail(String email);

	public long count();

}


