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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.qc.plachanc73.restws.common.comparator.ClasseLibelleComparator;
import ca.qc.plachanc73.restws.common.config.MessagesConfig;
import ca.qc.plachanc73.restws.common.domaine.TypeRole;
import ca.qc.plachanc73.restws.common.entity.Classe;
import ca.qc.plachanc73.restws.common.entity.User;
import ca.qc.plachanc73.restws.common.exception.BadRequestServiceException;
import ca.qc.plachanc73.restws.common.json.FilterClasseJson;
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

	@Override
	@RequestMapping(value = PATH_FILTER, method = RequestMethod.POST)
	public ResponseEntity<List<Classe>> FilterClasses(@Valid @RequestBody FilterClasseJson FilterClasseJson) {
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

		List<Classe> classesfilteres = classeServiceMetier.getClassesByType(FilterClasseJson.getTypeClasse());

		Collections.sort(classesfilteres, new ClasseLibelleComparator(getUserLanguage(), Direction.ASC));

		return new ResponseEntity<>(classesfilteres, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = PATH_ID_CLASSE, method = RequestMethod.GET)
	public ResponseEntity<Classe> getClasse(@PathVariable Long idClasse) {
		Optional<Classe> optionalClasse = classeServiceMetier.getClasseById(idClasse);
		if (!optionalClasse.isPresent()) {
			LOGGER.error(CLASSE_WITH_ID + idClasse + TEXT_NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(optionalClasse.get(), HttpStatus.OK);
	}
}