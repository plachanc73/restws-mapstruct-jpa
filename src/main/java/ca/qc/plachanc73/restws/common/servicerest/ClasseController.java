package ca.qc.plachanc73.restws.common.servicerest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ca.qc.plachanc73.restws.common.json.ClasseJson;
import ca.qc.plachanc73.restws.common.json.FilterClasseJson;

public interface ClasseController extends Controller {

	public static final String PATH_CLASSE = PATH_SERVICE + "/classe";

	/**
	 * REST Web Service to filter list of classes.
	 * 
	 * @param FilterClasseJson
	 * @return list of {@link ClasseJson}
	 */
	ResponseEntity<List<ClasseJson>> filterClasses(FilterClasseJson FilterClasseJson);

	/**
	 * REST Web Service to get a classe depending on the given id.
	 * 
	 * @param idClasse
	 * @return {@link ClasseJson}
	 */
	ResponseEntity<ClasseJson> getClasse(Long idClasse);

	/**
	 * REST Web Service to create a classe.
	 * 
	 * @param classe
	 * @return {@link ClasseJson}
	 */
	ResponseEntity<ClasseJson> createClasse(ClasseJson classeJson);

	/**
	 * REST Web Service to save a classe.
	 * 
	 * @param idClasse
	 * @param classe
	 * @return {@link ClasseJson}
	 */
	ResponseEntity<ClasseJson> saveClasse(Long idClasse, ClasseJson classeJson);

	/**
	 * REST Web Service to delete a classe.
	 * 
	 * @param idClasse
	 * @return {@link ClasseJson}
	 */
	ResponseEntity<Void> deleteClasse(Long idClasse);
}