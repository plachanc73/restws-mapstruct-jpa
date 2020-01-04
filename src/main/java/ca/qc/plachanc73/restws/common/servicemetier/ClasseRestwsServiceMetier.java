package ca.qc.plachanc73.restws.common.servicemetier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import ca.qc.plachanc73.restws.common.dao.ClasseDao;
import ca.qc.plachanc73.restws.common.domaine.TypeClasse;
import ca.qc.plachanc73.restws.common.entity.Classe;

@Service
@CacheConfig(cacheNames = "classes")
public class ClasseRestwsServiceMetier implements ClasseServiceMetier {

	@Autowired
	private ClasseDao classeDao;

	@Override
	public List<Classe> getClassesByType(TypeClasse typeClasse) {
		return classeDao.getClassesByType(typeClasse);
	}

	@Override
	public Optional<Classe> getClasseById(Long idClasse) {
		return classeDao.getClasseById(idClasse);
	}

	@Override
	public Optional<Classe> getClasseByCode(String codeClasse) {
		return classeDao.getClasseByCode(codeClasse);
	}

	@Override
	public Classe save(Classe classe) {
		return classeDao.save(classe);
	}

	@Override
	public void delete(Classe classe) {
		classeDao.delete(classe);
	}
}