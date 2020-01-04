package ca.qc.plachanc73.restws.common.servicerest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.qc.plachanc73.restws.common.comparator.ClasseLibelleComparator;
import ca.qc.plachanc73.restws.common.config.MessagesConfig;
import ca.qc.plachanc73.restws.common.domaine.TypeRole;
import ca.qc.plachanc73.restws.common.entity.Classe;
import ca.qc.plachanc73.restws.common.entity.User;
import ca.qc.plachanc73.restws.common.exception.BadRequestServiceException;
import ca.qc.plachanc73.restws.common.json.ClasseJson;
import ca.qc.plachanc73.restws.common.json.FilterClasseJson;
import ca.qc.plachanc73.restws.common.mapper.ClasseMapper;
import ca.qc.plachanc73.restws.common.servicemetier.ClasseServiceMetier;
import ca.qc.plachanc73.restws.common.validation.CommonMessages;

@RestController
@RequestMapping(value = ClasseController.PATH_CLASSE, produces = "application/json")
public class ClasseRestwsController extends AbstractRestwsController implements ClasseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClasseRestwsController.class);

	private static final String PATH_ID_CLASSE = "/{idClasse}";

	private static final String CLASSE_WITH_ID = "Classe with id ";

	@Autowired
	private ClasseServiceMetier classeServiceMetier;

	@Autowired
	private ClasseMapper classeMapper;

	@Override
	@PostMapping(value = PATH_FILTER)
	public ResponseEntity<List<ClasseJson>> filterClasses(@Valid @RequestBody FilterClasseJson FilterClasseJson) {
		if (FilterClasseJson == null) {
			throw new BadRequestServiceException(
					MessagesConfig.getMessage(CommonMessages.ERR_REQUEST_EMPTY, getUserLanguage()));
		}

		Optional<User> user = getUserFromRequest();

		if (!user.isPresent() || (!user.get().getActiveRoles().contains(TypeRole.TEACHER)
				&& !user.get().getActiveRoles().contains(TypeRole.ADMIN))) {
			// The user has to be a teacher or an admin to filter classes
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		List<Classe> classesFiltered = classeServiceMetier.getClassesByType(FilterClasseJson.getTypeClasse());

		Collections.sort(classesFiltered, new ClasseLibelleComparator(getUserLanguage(), Direction.ASC));

		List<ClasseJson> classesJsonFiltered = classeMapper.classeListeToClasseJsonListe(classesFiltered);

		return new ResponseEntity<>(classesJsonFiltered, HttpStatus.OK);
	}

	@Override
	@GetMapping(value = PATH_ID_CLASSE)
	public ResponseEntity<ClasseJson> getClasse(@PathVariable Long idClasse) {
		Optional<Classe> optionalClasse = classeServiceMetier.getClasseById(idClasse);
		if (!optionalClasse.isPresent()) {
			LOGGER.error(CLASSE_WITH_ID + idClasse + TEXT_NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		ClasseJson classeJson = classeMapper.classeToClasseJson(optionalClasse.get());

		return new ResponseEntity<>(classeJson, HttpStatus.OK);
	}

	@Override
	@PostMapping()
	public ResponseEntity<ClasseJson> createClasse(@Valid @RequestBody ClasseJson classeJson) {
		Optional<User> user = getUserFromRequest();

		if (!user.get().getActiveRoles().contains(TypeRole.ADMIN)) {
			// The user has to be an admin to create a classe
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		Classe classe = classeMapper.classeJsonToClasse(classeJson);
		Classe classeSaved = classeServiceMetier.save(classe);
		ClasseJson classeSavedJson = classeMapper.classeToClasseJson(classeSaved);

		return new ResponseEntity<>(classeSavedJson, HttpStatus.CREATED);
	}

	@Override
	@PutMapping(value = PATH_ID_CLASSE)
	public ResponseEntity<ClasseJson> saveClasse(@PathVariable Long idClasse,
			@Valid @RequestBody ClasseJson classeJson) {
		Optional<Classe> optionalClasse = classeServiceMetier.getClasseById(idClasse);
		if (!optionalClasse.isPresent()) {
			LOGGER.error(CLASSE_WITH_ID + idClasse + TEXT_NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Optional<User> user = getUserFromRequest();

		if (!user.get().getActiveRoles().contains(TypeRole.ADMIN)) {
			// The user has to be an admin to update a classe
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		Classe classe = classeMapper.classeJsonToClasse(classeJson);
		classe.setId(idClasse);
		Classe classeUpdated = classeServiceMetier.save(classe);
		ClasseJson classeSavedJson = classeMapper.classeToClasseJson(classeUpdated);

		return new ResponseEntity<>(classeSavedJson, HttpStatus.OK);
	}

	@Override
	@DeleteMapping(value = PATH_ID_CLASSE)
	public ResponseEntity<Void> deleteClasse(@PathVariable Long idClasse) {
		Optional<Classe> optionalClasse = classeServiceMetier.getClasseById(idClasse);
		if (!optionalClasse.isPresent()) {
			LOGGER.error(CLASSE_WITH_ID + idClasse + TEXT_NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Optional<User> user = getUserFromRequest();

		if (!user.get().getActiveRoles().contains(TypeRole.ADMIN)) {
			// The user has to be an admin to delete a classe
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		classeServiceMetier.delete(optionalClasse.get());

		return new ResponseEntity<>(HttpStatus.OK);
	}
}