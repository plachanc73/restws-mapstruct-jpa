package ca.qc.plachanc73.restws.common.config;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import ca.qc.plachanc73.restws.common.repository.UserRepository;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { WebMvcConfigurer.class, PersistenceJPAConfigUnitTest.class })
@DbUnitConfiguration(dataSetLoader = NullReplacementDataSetLoader.class)
@DatabaseSetup(value = "/data.xml")
@WebAppConfiguration
public abstract class AbstractUnitTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	protected UserRepository userRepository;

	protected MockMvc mockMvc;

	// Documentation des services Rest
	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

	public RestDocumentationResultHandler document;

	public static class CodeUsager {
		private CodeUsager() {
			// Private constructor to hide the implicit public one
		}

		// Codes des Usagers
		public static final String ADMIN = "ADMIN1";

		public static final String TEACHER = "TEACH1";

		public static final String STUDENT = "STUDE1";
	}

	@Before
	public void setup() {
		this.document = document("{methodName}", preprocessResponse(prettyPrint()));

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(this.restDocumentation)).alwaysDo(this.document).build();
	}
}