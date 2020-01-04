package ca.qc.plachanc73.restws.common.servicerest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ca.qc.plachanc73.restws.common.entity.Classe;
import ca.qc.plachanc73.restws.common.json.FilterClasseJson;

public interface ClasseController extends Controller {

	public static final String PATH_CLASSE = PATH_SERVICE + "/classe";

	/**
	 * REST Web Service to filter list of classes.
	 * 
	 * @param FilterClasseJson
	 * @return list of {@link Classe}
	 */
	ResponseEntity<List<Classe>> FilterClasses(FilterClasseJson FilterClasseJson);

	/**
	 * REST Web Service to get a classe depending on the given id.
	 * 
	 * @param idClasse
	 * @return {@link Classe}
	 */
	ResponseEntity<Classe> getClasse(Long idClasse);
}