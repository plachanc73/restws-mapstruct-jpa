package ca.qc.plachanc73.restws.common.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.qc.plachanc73.restws.common.entity.User;
import ca.qc.plachanc73.restws.common.repository.UserRepository;

@Service
public class UserRestwsDao implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> getUserByCode(String codeUsager) {
		return userRepository.findByCode(codeUsager);
	}
}