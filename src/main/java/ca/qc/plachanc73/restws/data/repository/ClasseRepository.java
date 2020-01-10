package ca.qc.plachanc73.restws.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.qc.plachanc73.restws.business.domain.TypeClasse;
import ca.qc.plachanc73.restws.data.entity.Classe;

@Repository
public interface ClasseRepository extends CrudRepository<Classe, Long> {

	List<Classe> findAllByTypeClasse(TypeClasse typeClasse);

	Optional<Classe> findByCode(String codeClasse);
}