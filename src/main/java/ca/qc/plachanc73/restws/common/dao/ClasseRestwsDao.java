package ca.qc.plachanc73.restws.common.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.qc.plachanc73.restws.common.domaine.TypeClasse;
import ca.qc.plachanc73.restws.common.entity.Classe;
import ca.qc.plachanc73.restws.common.repository.ClasseRepository;

@Service
public class ClasseRestwsDao implements ClasseDao {

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