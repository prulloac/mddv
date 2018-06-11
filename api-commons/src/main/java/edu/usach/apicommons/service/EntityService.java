package edu.usach.apicommons.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.impl.AbstractDeletableEntity;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@SuppressWarnings("unchecked")
@Slf4j
public abstract class EntityService<T extends IEntity> extends AbstractService implements IEntityService<T> {

	private Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	protected String serviceOf() {
		return tClass.getSimpleName();
	}

	@Override
	@Transactional(readOnly = true)
	public T findOne(long id) throws ApiException {
		Optional<T> entity = getDao().findById(id);
		if (!entity.isPresent())
			throw new ApiException(ErrorCode.OBJECT_NOT_FOUND, serviceOf());
		return entity.get();
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() throws ApiException {
		ArrayList<T> list = new ArrayList<>();
		getDao().findAll().forEach(list::add);
		if (list.isEmpty())
			throw new ApiException(ErrorCode.NO_OBJECTS_FOUND, serviceOf());
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<T> findPaginated(Integer page, Integer size) throws ApiException {
		Page<T> list = getDao().findAll(PageRequest.of(null == page ? 0 : page, null == size ? 10 : size));
		if (list.getTotalElements()<1)
			throw new ApiException(ErrorCode.NO_OBJECTS_FOUND, serviceOf());
		return list;
	}

	@Override
	@Transactional
	public T create(final T entity) throws ApiException {
		try {
			return getDao().save(entity);
		} catch (RuntimeException e) {
			throw new ApiException(ErrorCode.CREATION_ERROR, serviceOf());
		}
	}

	@Override
	@Transactional
	public T update(final T entity) throws ApiException {
		try {
			return getDao().save(entity);
		} catch (RuntimeException e) {
			throw new ApiException(ErrorCode.UPDATE_ERROR, serviceOf());
		}
	}

	@Override
	@Transactional
	public void delete(final T entity) throws ApiException {
		try {
			if (entity instanceof AbstractDeletableEntity) {
				if (((AbstractDeletableEntity) entity).getDeleted()) return;
				((AbstractDeletableEntity) entity).setDeleted(true);
				((AbstractDeletableEntity) entity).setDeletedTimestamp(LocalDateTime.now());
				getDao().save(entity);
			} else {
				getDao().delete(entity);
			}
		} catch (RuntimeException e) {
			throw new ApiException(ErrorCode.DELETE_ERROR, serviceOf());
		}
	}

	@Override
	@Transactional
	public void deleteById(final long id) throws ApiException {
		try {
			T entity = getDao().getOne(id);
			delete(entity);
		} catch (RuntimeException e) {
			throw new ApiException(ErrorCode.DELETE_ERROR, serviceOf());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public JSONObject findAndFilter(final long id, String filterString) throws ApiException {
		T entity = getDao().findById(id).get();
		ObjectMapper mapper = new ObjectMapper();
		try {
			JSONParser parser = new JSONParser();
			JSONObject predto = (JSONObject) parser.parse(mapper.writeValueAsString(entity));
			JSONObject dto = new JSONObject();
			for(String property : filterString.split(",")) dto.put(property, predto.get(property));
			return dto;
		} catch (ParseException | JsonProcessingException e) {
			throw new ApiException(ErrorCode.INVALID_FILTERS, serviceOf());
		}
	}

	protected abstract JpaRepository<T, Long> getDao();

}
