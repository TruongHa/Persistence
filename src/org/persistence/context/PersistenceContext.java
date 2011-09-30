package org.persistence.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.persistence.model.AbstractEntity;

public class PersistenceContext {

	@SuppressWarnings("rawtypes")
	private static Map<Class, Map<Integer, ProxyModel>> context;

	@SuppressWarnings("rawtypes")
	public static void init() {
		context = new HashMap<Class, Map<Integer, ProxyModel>>();
	}

	/**
	 * Merge an entity to persistence context
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public static void merge(AbstractEntity entity) throws Exception {
		Map<Integer, ProxyModel> proxies = context.get(entity.getClass());

		if (proxies == null) {
			proxies = new HashMap<Integer, ProxyModel>();
			context.put(entity.getClass(), proxies);
		}

		// if this is a new object
		if (entity.getId() == null) {
			if (proxies.isEmpty()) {
				entity.setId(1);
			} else {
				Set<Integer> ids = proxies.keySet();
				Integer nextId = Integer.MIN_VALUE;
				for (Integer id : ids) {
					if (nextId < id)
						nextId = id;
				}
				entity.setId(nextId + 1);
			}
			ProxyModel proxy = new ProxyModel(entity);
			proxy.setStatus(ProxyModel.NEW);
			proxies.put(entity.getId(), proxy);
		} else if (entity.getId() != null) {
			ProxyModel proxy = proxies.get(entity.getId());
			if (proxy != null) {
				proxy.setObject(entity);
			}
		}
	}

	/**
	 * Get an enter from persistence context
	 * 
	 * @param <E>
	 * @param id
	 * @param clss
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E extends AbstractEntity> E get(Integer id, Class<E> clss) {

		Map<Integer, ProxyModel> proxies = context.get(clss);

		ProxyModel proxy = proxies.get(id);
		if (proxy != null) {
			if (!proxy.getStatus().equals(ProxyModel.DELETE))
				return (E) proxy.getObject();
		}
		return null;
	}

	public static void remove(AbstractEntity entity) {
		Map<Integer, ProxyModel> proxies = context.get(entity.getClass());
		ProxyModel proxy = proxies.get(entity.getId());
		if(proxy!=null){
			proxy.setStatus(ProxyModel.DELETE);
		}
	}

	public static void close() {
		System.out.println(context);
		
	}
}
