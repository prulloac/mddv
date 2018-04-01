package edu.usach.apicommons.service;

import edu.usach.apicommons.annotations.ServiceOfEntity;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Transactional
public abstract class AbstractService<T extends Serializable> implements IService<T> {

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
	public Page<T> findPaginated(int page, int size) throws ApiException {
		Page<T> list = getDao().findAll(PageRequest.of(page, size));
		if (list.getTotalElements()<1)
			throw new ApiException(ErrorCode.NO_OBJECTS_FOUND, serviceOf());
		return list;
	}

	@Override
	public T create(final T entity) throws ApiException {
		return getDao().save(entity);
	}

	@Override
	public T update(final T entity) throws ApiException {
		return getDao().save(entity);
	}

	@Override
	public void delete(final T entity) throws ApiException {
		getDao().delete(entity);
	}

	@Override
	public void deleteById(final long id) throws ApiException {
		getDao().deleteById(id);
	}

	@Override
	public Map<String, Object> convertToMap(final long id, String filterString) throws ApiException {
		Map<String, Object> dto = new HashMap<>();
		String[] filters = filterString.split(",");
		T entity = getDao().findById(id).get();
		try {
			for (String filter : filters) {
				Method method = entity.getClass().getMethod("get"+ StringUtils.capitalize(filter));
				dto.put(filter, method.invoke(entity));
			}
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new ApiException(ErrorCode.INVALID_FILTERS, serviceOf());
		}
		return dto;
	}

	protected abstract PagingAndSortingRepository<T, Long> getDao();

	protected String serviceOf() {
		return getClass().getAnnotation(ServiceOfEntity.class).value();
	}
}
