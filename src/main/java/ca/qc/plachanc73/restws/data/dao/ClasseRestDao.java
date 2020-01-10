package ca.qc.plachanc73.restws.data.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.qc.plachanc73.restws.business.domain.TypeClasse;
import ca.qc.plachanc73.restws.data.entity.Classe;
import ca.qc.plachanc73.restws.data.repository.ClasseRepository;

@Service
public class ClasseRestDao implements ClasseDao {

	@Autowired
	private ClasseRepository classeRepository;

	@Override
	public List<Classe> getClassesByType(TypeClasse typeClasse) {
		return classeRepository.findAllByTypeClasse(typeClasse);
	}

	@Override
	public Optional<Classe> getClasseById(Long idClasse) {
		return classeRepository.findById(idClasse);
	}

	@Override
	public Optional<Classe> getClasseByCode(String codeClasse) {
		return classeRepository.findByCode(codeClasse);
	}

	@Override
	public Classe save(Classe classe) {
		return classeRepository.save(classe);
	}

	@Override
	public void delete(Classe classe) {
		classeRepository.delete(classe);
	}
}