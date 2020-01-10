package ca.qc.plachanc73.restws.web.service;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import ca.qc.plachanc73.restws.config.AbstractUnitTest;
import ca.qc.plachanc73.restws.business.domain.TypeClasse;
import ca.qc.plachanc73.restws.data.entity.User;
import ca.qc.plachanc73.restws.util.JsonUtil;
import ca.qc.plachanc73.restws.web.json.ClasseJson;
import ca.qc.plachanc73.restws.web.json.FilterClasseJson;

public class ClasseControllerUnitTest extends AbstractUnitTest {

	@Test
	public void post_filter_classes_typeClasse_PRIVEE() throws Exception {
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
	public void post_filter_classes_typeClasse_PUBLIC() throws Exception {
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
	public void post_filter_classes_typeClasse_forbidden() throws Exception {
		FilterClasseJson filterClasse = new FilterClasseJson();
		filterClasse.setTypeClasse(TypeClasse.PUBLIC);
		String FilterClasseJson = JsonUtil.serialiseObject(filterClasse);

		mockMvc.perform(post("/service/classe/filter")
				.requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.STUDENT))
				.contentType(MediaType.APPLICATION_JSON).content(FilterClasseJson)).andExpect(status().isForbidden());
	}

	@Test
	public void post_filter_classes_aucunTypeClasse() throws Exception {
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
				.andExpect(jsonPath("$.typeClasse").value(TypeClasse.PUBLIC.name()))
				.andExpect(jsonPath("$.libelleTypeClasse").value(TypeClasse.PUBLIC.getLibelle()));
	}

	@Test
	public void get_classe_notFound() throws Exception {
		mockMvc.perform(
				get("/service/classe/0").requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.TEACHER))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void post_create_classe() throws Exception {
		ClasseJson classe = new ClasseJson();
		classe.setCode("tcode");
		classe.setLibelle("Test Libelle");
		classe.setTypeClasse(TypeClasse.PUBLIC);
		String classeJson = JsonUtil.serialiseObject(classe);

		mockMvc.perform(
				post("/service/classe").requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.ADMIN))
						.contentType(MediaType.APPLICATION_JSON).content(classeJson))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").isNotEmpty()).andExpect(jsonPath("$.code").value(classe.getCode()))
				.andExpect(jsonPath("$.libelle").value(classe.getLibelle()))
				.andExpect(jsonPath("$.typeClasse").value(classe.getTypeClasse().name()))
				.andExpect(jsonPath("$.libelleTypeClasse").value(TypeClasse.PUBLIC.getLibelle()));
	}

	@Test
	public void post_create_classe_forbidden() throws Exception {
		ClasseJson classe = new ClasseJson();
		classe.setCode("tcode");
		classe.setLibelle("Test Libelle");
		classe.setTypeClasse(TypeClasse.PUBLIC);
		String classeJson = JsonUtil.serialiseObject(classe);

		mockMvc.perform(
				post("/service/classe").requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.TEACHER))
						.contentType(MediaType.APPLICATION_JSON).content(classeJson))
				.andExpect(status().isForbidden());
	}

	@Test
	public void put_update_classe() throws Exception {
		String result = mockMvc
				.perform(get("/service/classe/1")
						.requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.ADMIN))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.code").value("CPUB1"))
				.andExpect(jsonPath("$.libelle").value("Classe public #1"))
				.andExpect(jsonPath("$.typeClasse").value(TypeClasse.PUBLIC.name()))
				.andExpect(jsonPath("$.libelleTypeClasse").value(TypeClasse.PUBLIC.getLibelle())).andReturn()
				.getResponse().getContentAsString();

		ClasseJson classe = (ClasseJson) JsonUtil.deserialiseJson(result, ClasseJson.class);
		classe.setLibelle("Test update libelle");
		String classeJson = JsonUtil.serialiseObject(classe);

		mockMvc.perform(
				put("/service/classe/1").requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.ADMIN))
						.contentType(MediaType.APPLICATION_JSON).content(classeJson))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(classe.getId().intValue()))
				.andExpect(jsonPath("$.code").value(classe.getCode()))
				.andExpect(jsonPath("$.libelle").value(classe.getLibelle()))
				.andExpect(jsonPath("$.typeClasse").value(classe.getTypeClasse().name()))
				.andExpect(jsonPath("$.libelleTypeClasse").value(classe.getLibelleTypeClasse()));
	}

	@Test
	public void put_update_classe_forbidden() throws Exception {
		String result = mockMvc
				.perform(get("/service/classe/1")
						.requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.TEACHER))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.code").value("CPUB1"))
				.andExpect(jsonPath("$.libelle").value("Classe public #1"))
				.andExpect(jsonPath("$.typeClasse").value(TypeClasse.PUBLIC.name()))
				.andExpect(jsonPath("$.libelleTypeClasse").value(TypeClasse.PUBLIC.getLibelle())).andReturn()
				.getResponse().getContentAsString();

		ClasseJson classe = (ClasseJson) JsonUtil.deserialiseJson(result, ClasseJson.class);
		classe.setLibelle("Test update libelle");
		String classeJson = JsonUtil.serialiseObject(classe);

		mockMvc.perform(
				put("/service/classe/1").requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.TEACHER))
						.contentType(MediaType.APPLICATION_JSON).content(classeJson))
				.andExpect(status().isForbidden());
	}

	@Test
	public void put_update_classe_notFound() throws Exception {
		String result = mockMvc
				.perform(get("/service/classe/1")
						.requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.ADMIN))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.code").value("CPUB1"))
				.andExpect(jsonPath("$.libelle").value("Classe public #1"))
				.andExpect(jsonPath("$.typeClasse").value(TypeClasse.PUBLIC.name()))
				.andExpect(jsonPath("$.libelleTypeClasse").value(TypeClasse.PUBLIC.getLibelle())).andReturn()
				.getResponse().getContentAsString();

		ClasseJson classe = (ClasseJson) JsonUtil.deserialiseJson(result, ClasseJson.class);
		classe.setLibelle("Test update libelle");
		String classeJson = JsonUtil.serialiseObject(classe);

		mockMvc.perform(
				put("/service/classe/0").requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.ADMIN))
						.contentType(MediaType.APPLICATION_JSON).content(classeJson))
				.andExpect(status().isNotFound());
	}

	@Test
	public void delete_classe() throws Exception {
		mockMvc.perform(delete("/service/classe/1")
				.requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.ADMIN))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void delete_classe_notFound() throws Exception {
		mockMvc.perform(
				get("/service/classe/0").requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.ADMIN))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void delete_classe_TEACHER() throws Exception {
		mockMvc.perform(delete("/service/classe/1")
				.requestAttr(User.PARAM_KEY_USER, userRepository.findByCode(CodeUsager.TEACHER))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}
}