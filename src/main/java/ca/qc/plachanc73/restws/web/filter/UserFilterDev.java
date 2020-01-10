package ca.qc.plachanc73.restws.web.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class UserFilterDev extends UserFilter {

	public static final String USER_FILTER_NAME_DEV = "userFilterDev";

	private static final String X_UID = "x-uid";

	/**
	 * Obtient le code d'usager.
	 * 
	 * @param httpRequest
	 * @return String Le code d'usager.
	 */
	@Override
	protected String getUserCode(HttpServletRequest httpRequest) {
		String codeUsager = super.getUserCode(httpRequest);

		if (StringUtils.isBlank(codeUsager)) {
			codeUsager = httpRequest.getHeader(PARAM_KEY_UID);

			if (StringUtils.isBlank(codeUsager)) {
				codeUsager = httpRequest.getHeader(X_UID);
			}

			// Ajouté pour permettre à un développeur de fournir un code d'usager différent pour une requête
			Map<String, String[]> parameters = httpRequest.getParameterMap();

			if (parameters != null && parameters.containsKey(X_UID)) {
				codeUsager = parameters.get(X_UID)[0];
			}
		}

		return codeUsager;
	}
}