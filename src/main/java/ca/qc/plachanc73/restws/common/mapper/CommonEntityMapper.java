package ca.qc.plachanc73.restws.common.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import ca.qc.plachanc73.restws.common.entity.Classe;
import ca.qc.plachanc73.restws.common.exception.MapperException;
import ca.qc.plachanc73.restws.common.repository.ClasseRepository;

@Mapper(componentModel = "spring")
public class CommonEntityMapper extends EntityMapper {

	@Autowired
	protected ClasseRepository classeRepository;

	public Classe toClasse(String codeClasse) {
		if (codeClasse == null) {
			return null;
		}

		Optional<Classe> optionalClasse = classeRepository.findByCode(codeClasse);
		if (!optionalClasse.isPresent()) {
			throw new MapperException("La classe avec le code " + codeClasse + TEXT_NEXISTE_PAS);
		}
		return optionalClasse.get();
	}
}