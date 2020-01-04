package ca.qc.plachanc73.restws.common.servicerest;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.qc.plachanc73.restws.common.domaine.Language;
import ca.qc.plachanc73.restws.common.entity.User;

public abstract class AbstractRestwsController implements Controller {

	protected static final String PATH_FILTER = "/filter";

	protected static final String TEXT_NOT_FOUND = " is not found.";

	@Autowired
	protected HttpServletRequest httpRequest;

	@Override
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handle(HttpMessageNotReadableException e) {
		// Returns HTTP 400 Bad Request
		throw e;
	}

	/**
	 * Get user language.
	 * 
	 * @return {@link Language}
	 */
	protected Language getUserLanguage() {
		Optional<User> optionalUser = getUserFromRequest();
		Language userLanguage;

		if (optionalUser.isPresent()) {
			userLanguage = optionalUser.get().getLanguage();
		} else {
			userLanguage = Language.EN;
		}
		return userLanguage;
	}

	/**
	 * Get user from request attribute 'USER'.
	 * 
	 * @return {@link User}
	 */
	@SuppressWarnings("unchecked")
	protected Optional<User> getUserFromRequest() {
		return (Optional<User>) httpRequest.getAttribute(User.PARAM_KEY_USER);
	}
}