package ca.qc.plachanc73.restws.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import ca.qc.plachanc73.restws.web.filter.UserFilter;
import ca.qc.plachanc73.restws.web.filter.UserFilterDev;

public class WebAppInitializer implements WebApplicationInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebAppInitializer.class);

	private static final String KEY_SPRING_PROFILES_ACTIVE = "spring.profiles.active";

	private static final String VALUE_SPRING_PROFILES_ACTIVE_DEV = "dev";

	private static final String VALUE_SPRING_PROFILES_ACTIVE_PROD = "prod";

	public static String getSpringProfilesActive() {
		return System.getProperty(KEY_SPRING_PROFILES_ACTIVE, VALUE_SPRING_PROFILES_ACTIVE_PROD);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext rootContext = createRootContext(servletContext);

		configureSpringMvc(servletContext, rootContext);

		String springProfileActive = getSpringProfilesActive();

		// User filter first
		if (VALUE_SPRING_PROFILES_ACTIVE_DEV.equals(springProfileActive)) {
			FilterRegistration.Dynamic userFilter = servletContext.addFilter(UserFilterDev.USER_FILTER_NAME_DEV,
					new DelegatingFilterProxy());
			userFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/service/*");
		} else if (VALUE_SPRING_PROFILES_ACTIVE_PROD.equals(springProfileActive)) {
			FilterRegistration.Dynamic userFilter = servletContext.addFilter(UserFilter.USER_FILTER_NAME,
					new DelegatingFilterProxy());
			userFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/service/*");
		}

		LOGGER.info(String.format(
				"onStartup - Profil défini '%s' (propriété '%s' du System properties de la console d'administration).",
				springProfileActive, KEY_SPRING_PROFILES_ACTIVE));
	}

	private static WebApplicationContext createRootContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebMvcConfigurer.class);
		servletContext.addListener(new ContextLoaderListener(context));
		return context;
	}

	private static void configureSpringMvc(ServletContext servletContext, WebApplicationContext rootContext) {
		DispatcherServlet dispatcher = new DispatcherServlet(rootContext);
		ServletRegistration.Dynamic servlet = servletContext
				.addServlet(AbstractDispatcherServletInitializer.DEFAULT_SERVLET_NAME, dispatcher);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
}