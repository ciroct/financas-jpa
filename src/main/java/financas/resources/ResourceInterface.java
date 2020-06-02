package financas.resources;

import javax.ws.rs.core.Response;

public interface ResourceInterface<T> {
	Response get();
	Response getById(Long id);
	Response post(T obj);
	Response put(T obj);
	Response delete(Long id);
}
