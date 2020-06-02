package financas.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import financas.model.Usuario;
import financas.model.dao.DAO;

@Path("/usuarios")
public class UsuarioResource implements ResourceInterface<Usuario> {

	@Path("/protected")
	@GET	
	@Produces("application/json")
	@Override
	public Response get() {
		DAO<Usuario> dao = new DAO<>(Usuario.class);
		List<Usuario> usuarios = dao.listarGenerico("Usuario.listarTodos");
		return Response.ok(usuarios).build();
	}

	@GET
	@Path("/protected/{id}")
	@Produces("application/json")
	@Override
	public Response getById(@PathParam("id") Long id) {
		DAO<Usuario> dao = new DAO<>(Usuario.class);
		Usuario usuario = dao.consultar(id);
		if (usuario != null) {
			return Response.ok(usuario).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@POST
	@Path("/auth")
	@Produces("application/json")
	@Consumes("application/json")
	public Response autenticar(Usuario usuario) {
		DAO<Usuario> dao = new DAO<>(Usuario.class);
		Usuario _usuario = dao.consultarGenerico("Usuario.autenticar", usuario.getEmail(), usuario.getSenha());
		if (_usuario != null) {
			return Response.ok(_usuario).build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();		
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Override
	public Response post(Usuario obj) {
		DAO<Usuario> dao = new DAO<>(Usuario.class);
		try {
			dao.adicionar(obj);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}
		return Response.ok(obj).build();
	}

	@Path("/protected")
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Override
	public Response put(Usuario obj) {
		DAO<Usuario> dao = new DAO<>(Usuario.class);
		if (dao.consultar(obj.getId()) != null) {
			dao.alterar(obj);
			return Response.ok(obj).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@Path("/protected")
	@DELETE
	@Produces("application/json")
	@Override
	public Response delete(Long id) {
		DAO<Usuario> dao = new DAO<>(Usuario.class);
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}
