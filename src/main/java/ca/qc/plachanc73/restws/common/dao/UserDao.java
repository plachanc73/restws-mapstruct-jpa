package ca.qc.plachanc73.restws.common.dao;

import java.util.Optional;

import ca.qc.plachanc73.restws.common.entity.User;

public interface UserDao {

	Optional<User> getUserByCode(String codeUsager);
}