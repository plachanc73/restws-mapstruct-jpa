package ca.qc.plachanc73.restws.common.servicemetier;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.qc.plachanc73.restws.common.dao.UserDao;
import ca.qc.plachanc73.restws.common.entity.User;

@Service
public class UserRestwsServiceMetier implements UserServiceMetier {

	@Autowired
	private UserDao userDao;

	@Override
	public Optional<User> getUserByCode(String codeUsager) {
		return userDao.getUserByCode(codeUsager);
	}
}