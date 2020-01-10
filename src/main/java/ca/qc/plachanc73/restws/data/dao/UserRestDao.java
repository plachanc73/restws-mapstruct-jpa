package ca.qc.plachanc73.restws.data.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.qc.plachanc73.restws.data.entity.User;
import ca.qc.plachanc73.restws.data.repository.UserRepository;

@Service
public class UserRestDao implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> getUserByCode(String codeUsager) {
		return userRepository.findByCode(codeUsager);
	}
}