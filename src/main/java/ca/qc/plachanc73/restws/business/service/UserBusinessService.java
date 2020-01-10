package ca.qc.plachanc73.restws.business.service;

import java.util.Optional;

import ca.qc.plachanc73.restws.data.entity.User;

public interface UserBusinessService {

	Optional<User> getUserByCode(String codeUsager);
}