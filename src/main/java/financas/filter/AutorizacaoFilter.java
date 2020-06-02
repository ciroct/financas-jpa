package financas.filter;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import financas.model.Usuario;
import financas.model.dao.DAO;

@Provider
public class AutorizacaoFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String PROTECTED_URL = "protected";

	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		System.out.println(request.getUriInfo().getAbsolutePath());
		if (request.getUriInfo().getAbsolutePath().toString().contains(PROTECTED_URL)) {
			List<String> _authHeader = request.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if (_authHeader != null && !_authHeader.isEmpty()) {
				String _authToken = _authHeader.get(0);
				_authToken = _authToken.replace(AUTHORIZATION_HEADER_PREFIX, "");
				String _decoded = Base64.decodeAsString(_authToken);
				StringTokenizer _tokenizer = new StringTokenizer(_decoded, ":");
				String _username = _tokenizer.nextToken();
				String _password = _tokenizer.nextToken();
				DAO<Usuario> dao = new DAO<>(Usuario.class);
				if (dao.consultarGenerico("Usuario.autenticar", _username, _password) == null) {
					Response _unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
						.entity("Login e/ou senha incorretos").build();
					request.abortWith(_unauthorizedStatus);
				}
				return;
			}
			Response _unauthorizedStatus = Response.status(Response.Status.FORBIDDEN)
					.entity("Você não tem autorização para acessar esse recurso").build();
			request.abortWith(_unauthorizedStatus);
		}
	}

}
