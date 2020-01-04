package ca.qc.plachanc73.restws.common.dao;

import java.util.List;
import java.util.Optional;

import ca.qc.plachanc73.restws.common.domaine.TypeClasse;
import ca.qc.plachanc73.restws.common.entity.Classe;

public interface ClasseDao {

	List<Classe> getClassesByType(TypeClasse typeClasse);

	Optional<Classe> getClasseById(Long idClasse);

	Optional<Classe> getClasseByCode(String codeClasse);

	Classe save(Classe classe);

	void delete(Classe classe);
}