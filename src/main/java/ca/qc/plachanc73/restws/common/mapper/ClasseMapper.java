package ca.qc.plachanc73.restws.common.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.qc.plachanc73.restws.common.entity.Classe;
import ca.qc.plachanc73.restws.common.json.ClasseJson;

@Mapper(componentModel = "spring", uses = { CommonEntityMapper.class })
public abstract class ClasseMapper {

	// Map JSON to Entity

	public abstract Classe classeJsonToClasse(ClasseJson classeJson);

	// Map Entity to JSON

	@Mapping(target = "libelleTypeClasse", expression = "java( getLibelleTypeClasse(classe) )")
	public abstract ClasseJson classeToClasseJson(Classe classe);

	public abstract List<ClasseJson> classeListeToClasseJsonListe(List<Classe> classes);

	/**
	 * Obtient le libelle du type de classe.
	 * 
	 * @param classe
	 * @return String
	 */
	protected String getLibelleTypeClasse(Classe classe) {
		return classe.getTypeClasse().getLibelle();
	}
}