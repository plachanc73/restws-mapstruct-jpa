package ca.qc.plachanc73.restws.business.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.qc.plachanc73.restws.data.dao.UserDao;
import ca.qc.plachanc73.restws.data.entity.User;

@Service
public class UserRestBusinessService implements UserBusinessService {

	@Autowired
	private UserDao userDao;

	@Override
	public Optional<User> getUserByCode(String codeUsager) {
		return userDao.getUserByCode(codeUsager);
	}
}