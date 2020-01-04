package ca.qc.plachanc73.restws.common.servicerest;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import ca.qc.plachanc73.restws.common.config.AbstractUnitTest;
import ca.qc.plachanc73.restws.common.domaine.TypeClasse;
import ca.qc.plachanc73.restws.common.entity.User;
import ca.qc.plachanc73.restws.common.json.FilterClasseJson;
import ca.qc.plachanc73.restws.common.util.JsonUtil;

public class ClasseControllerUnitTest extends AbstractUnitTest {

	@Test
	public void filter_classes_typeClasse_PRIVEE() throws Exception {
		FilterClasseJson filterClasse = new FilterClasseJson();
		filterClasse.setTypeClasse(TypeClasse.PRIVEE);
		String FilterClasseJson = JsonUtil.serialiseObject(filterClasse);

		mockMvc.perform(post("/service/classe/filter")
				.requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.TEACHER))
				.contentType(MediaType.APPLICATION_JSON).content(FilterClasseJson)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].id").value(4))
				.andExpect(jsonPath("$[0].typeClasse").value(filterClasse.getTypeClasse().toString()))
				.andExpect(jsonPath("$[1].id").value(5))
				.andExpect(jsonPath("$[1].typeClasse").value(filterClasse.getTypeClasse().toString()))
				.andExpect(jsonPath("$[2].id").value(6))
				.andExpect(jsonPath("$[2].typeClasse").value(filterClasse.getTypeClasse().toString()));
	}

	@Test
	public void filter_classes_typeClasse_PUBLIC() throws Exception {
		FilterClasseJson filterClasse = new FilterClasseJson();
		filterClasse.setTypeClasse(TypeClasse.PUBLIC);
		String FilterClasseJson = JsonUtil.serialiseObject(filterClasse);

		mockMvc.perform(post("/service/classe/filter")
				.requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.TEACHER))
				.contentType(MediaType.APPLICATION_JSON).content(FilterClasseJson)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].typeClasse").value(filterClasse.getTypeClasse().toString()))
				.andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].typeClasse").value(filterClasse.getTypeClasse().toString()))
				.andExpect(jsonPath("$[2].id").value(3))
				.andExpect(jsonPath("$[2].typeClasse").value(filterClasse.getTypeClasse().toString()));
	}

	@Test
	public void filter_classes_typeClasse_STUDENT() throws Exception {
		FilterClasseJson filterClasse = new FilterClasseJson();
		filterClasse.setTypeClasse(TypeClasse.PUBLIC);
		String FilterClasseJson = JsonUtil.serialiseObject(filterClasse);

		mockMvc.perform(post("/service/classe/filter")
				.requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.STUDENT))
				.contentType(MediaType.APPLICATION_JSON).content(FilterClasseJson)).andExpect(status().isForbidden());
	}

	@Test
	public void filter_classes_aucunTypeClasse() throws Exception {
		FilterClasseJson filterClasse = new FilterClasseJson();
		filterClasse.setTypeClasse(null);
		String FilterClasseJson = JsonUtil.serialiseObject(filterClasse);

		mockMvc.perform(post("/service/classe/filter")
				.requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.ADMIN))
				.contentType(MediaType.APPLICATION_JSON).content(FilterClasseJson)).andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.error").isNotEmpty());
	}

	@Test
	public void get_classe() throws Exception {
		mockMvc.perform(
				get("/service/classe/1").requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.TEACHER))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.code").value("CPUB1"))
				.andExpect(jsonPath("$.libelle").value("Classe public #1"))
				.andExpect(jsonPath("$.typeClasse").value(TypeClasse.PUBLIC.toString()));
	}
}