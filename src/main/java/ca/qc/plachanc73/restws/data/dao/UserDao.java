package ca.qc.plachanc73.restws.data.dao;

import java.util.Optional;

import ca.qc.plachanc73.restws.data.entity.User;

public interface UserDao {

	Optional<User> getUserByCode(String codeUsager);
}