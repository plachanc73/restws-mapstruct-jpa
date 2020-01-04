package ca.qc.plachanc73.restws.common.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.qc.plachanc73.restws.common.config.AbstractUnitTest;
import ca.qc.plachanc73.restws.common.domaine.TypeClasse;
import ca.qc.plachanc73.restws.common.entity.Classe;

public class ClasseRepositoryUnitTest extends AbstractUnitTest {

	@Autowired
	private ClasseRepository classeRepository;

	@Test
	public void testFindAll() {
		List<Classe> classe = (List<Classe>) classeRepository.findAll();
		assertTrue(classe.size() == 6);
	}

	@Test
	public void testFindAllByTypeClasse() {
		List<Classe> classe = classeRepository.findAllByTypeClasse(TypeClasse.PUBLIC);
		assertTrue(classe.size() == 3);
	}

	@Test
	public void testFindOneById() {
		Long idClasse = 6L;
		Optional<Classe> optionalClasse = classeRepository.findById(idClasse);
		assertTrue(optionalClasse.isPresent());
		assertTrue(optionalClasse.get().getId().equals(idClasse));
	}

	@Test
	public void testFindOneByCode() {
		String codeClasse = "CPUB1";
		Optional<Classe> optionalClasse = classeRepository.findByCode(codeClasse);
		assertTrue(optionalClasse.isPresent());
		assertTrue(optionalClasse.get().getCode().equals(codeClasse));
	}

	@Test
	public void testDelete() {
		Optional<Classe> optionalClasse = classeRepository.findById(6L);
		assertTrue(optionalClasse.isPresent());
		Classe classe = optionalClasse.get();
		classeRepository.delete(classe);
		optionalClasse = classeRepository.findById(6L);
		assertTrue(!optionalClasse.isPresent());
	}

	@Test
	public void testUpdate() {
		Optional<Classe> optionalClasse = classeRepository.findById(1L);
		assertTrue(optionalClasse.isPresent());
		Classe classe = optionalClasse.get();
		classe.setCode("TTT");
		classeRepository.save(classe);
		optionalClasse = classeRepository.findById(1L);
		assertTrue(optionalClasse.get().getCode().equals("TTT"));
	}
}