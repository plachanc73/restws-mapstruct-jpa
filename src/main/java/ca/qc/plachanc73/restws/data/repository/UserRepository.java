package ca.qc.plachanc73.restws.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.qc.plachanc73.restws.data.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByCode(String code);
}