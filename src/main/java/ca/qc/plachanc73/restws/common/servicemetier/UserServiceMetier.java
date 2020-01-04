package ca.qc.plachanc73.restws.common.servicemetier;

import java.util.Optional;

import ca.qc.plachanc73.restws.common.entity.User;

public interface UserServiceMetier {

	Optional<User> getUserByCode(String codeUsager);
}