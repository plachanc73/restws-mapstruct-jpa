package ca.qc.plachanc73.restws.business.service;

import java.util.List;
import java.util.Optional;

import ca.qc.plachanc73.restws.business.domain.TypeClasse;
import ca.qc.plachanc73.restws.data.entity.Classe;

public interface ClasseBusinessService {

	List<Classe> getClassesByType(TypeClasse typeClasse);

	Optional<Classe> getClasseById(Long idClasse);

	Optional<Classe> getClasseByCode(String codeClasse);

	Classe save(Classe classe);

	void delete(Classe classe);
}