package ca.qc.plachanc73.restws.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.qc.plachanc73.restws.common.domaine.TypeClasse;
import ca.qc.plachanc73.restws.common.entity.Classe;

@Repository
public interface ClasseRepository extends CrudRepository<Classe, Long> {

	List<Classe> findAllByTypeClasse(TypeClasse typeClasse);

	Optional<Classe> findByCode(String codeClasse);
}