package ca.qc.plachanc73.restws.web.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import ca.qc.plachanc73.restws.config.ThreadUserManager;
import ca.qc.plachanc73.restws.data.entity.User;
import ca.qc.plachanc73.restws.exception.ServiceException;
import ca.qc.plachanc73.restws.business.service.UserBusinessService;

@Component
@Profile("prod")
public class UserFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserFilter.class);

	protected static final String PARAM_KEY_UID = "uid";

	public static final String USER_FILTER_NAME = "userFilter";

	@Autowired
	private UserBusinessService userServiceMetier;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// Aucune initialisation
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String codeUsager = getUserCode(httpRequest);

		if (StringUtils.isBlank(codeUsager)) {
			String errorMsg = String.format(
					"doFilter - The parameter %s has to be given with the user code to get an autorisation for the request.",
					PARAM_KEY_UID);
			LOGGER.error(errorMsg);

			throw new ServiceException(errorMsg);
		} else {
			request.setAttribute(PARAM_KEY_UID, codeUsager);
			ThreadUserManager.setClientId(codeUsager);
		}

		LOGGER.info(String.format("doFilter *** %s '%s' - HTTP Method '%s' - URL '%s%s'", PARAM_KEY_UID, codeUsager,
				httpRequest.getMethod(), httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()),
				StringUtils.isBlank(httpRequest.getQueryString()) ? "" : "?" + httpRequest.getQueryString()));

		User user = getUser(codeUsager);

		request.setAttribute(User.PARAM_KEY_USER, user);

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// Aucune destruction
	}

	/**
	 * Obtient le code d'usager.
	 * 
	 * @param httpRequest
	 * @return String Le code d'usager.
	 */
	protected String getUserCode(HttpServletRequest httpRequest) {
		return (String) httpRequest.getAttribute(PARAM_KEY_UID);
	}

	/**
	 * Obtient le User selon le code d'usager fourni.
	 * 
	 * @param codeUsager
	 * @return User
	 */
	protected User getUser(String codeUsager) {
		Optional<User> optionalUser = userServiceMetier.getUserByCode(codeUsager);

		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			String errorMsg = String.format("getUser - No user exist with the code (%s) '%s'.", PARAM_KEY_UID,
					codeUsager);
			LOGGER.error(errorMsg);
			throw new ServiceException(errorMsg);
		}
	}
}