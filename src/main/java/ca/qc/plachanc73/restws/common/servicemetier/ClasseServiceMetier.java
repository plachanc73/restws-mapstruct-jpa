package ca.qc.plachanc73.restws.common.servicemetier;

import java.util.List;
import java.util.Optional;

import ca.qc.plachanc73.restws.common.domaine.TypeClasse;
import ca.qc.plachanc73.restws.common.entity.Classe;

public interface ClasseServiceMetier {

	List<Classe> getClassesByType(TypeClasse typeClasse);

	Optional<Classe> getClasseById(Long idClasse);

	Optional<Classe> getClasseByCode(String codeClasse);
}