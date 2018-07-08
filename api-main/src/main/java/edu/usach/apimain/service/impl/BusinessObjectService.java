package edu.usach.apimain.service.impl;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.service.EntityService;
import edu.usach.apimain.dao.BusinessObjectDAO;
import edu.usach.apimain.dao.MetadataObjectDAO;
import edu.usach.apimain.model.BusinessObject;
import edu.usach.apimain.model.MetadataObject;
import edu.usach.apimain.service.IBusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class BusinessObjectService extends EntityService<BusinessObject> implements IBusinessObjectService {

	@Autowired
	private BusinessObjectDAO dao;
	@Autowired
	private MetadataObjectDAO metadataObjectDAO;

	@Override
	protected JpaRepository<BusinessObject, Long> getDao() {
		return dao;
	}

	@Override
	@Transactional
	public Object updateRelations(Long id, List<Map<String, Object>> relatedObjects) {
		MetadataObject object = metadataObjectDAO.findById(id).orElse(null);
		if (null == object)
			throw new ApiException(ErrorCode.NO_OBJECTS_FOUND);
		List<MetadataObject> related = metadataObjectDAO.findAllById(relatedObjects
				.stream()
				.map(x -> (Long)x.get("id"))
				.collect(Collectors.toList()));
		object.setLinkedObjects(related);
		metadataObjectDAO.save(object);
		return this.findOne(id);
	}
}
