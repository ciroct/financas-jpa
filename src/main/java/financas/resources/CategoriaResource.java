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

import financas.model.Categoria;
import financas.model.dao.DAO;

@Path("/categorias/protected")
public class CategoriaResource implements ResourceInterface<Categoria> {

	@GET
	@Produces("application/json")
	@Override
	public Response get() {
		DAO<Categoria> dao = new DAO<>(Categoria.class);
		List<Categoria> categorias = dao.listarGenerico("Categoria.listarTodas");

		return Response.ok(categorias).build();
	}


	@Path("/{id}")
	@GET
	@Produces("application/json")
	@Override
	public Response getById(@PathParam("id") Long id) {
		DAO<Categoria> dao = new DAO<>(Categoria.class);
		Categoria categoria = dao.consultarGenerico("Categoria.consultarPorId", id);
		if (categoria != null) {
			return Response.ok(categoria).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@Path("nome/{nome}")
	@GET
	@Produces("application/json")
	public Response getByName(@PathParam("nome") String nome) {
		DAO<Categoria> dao = new DAO<>(Categoria.class);
		List<Categoria> categorias = dao.listarGenerico("Categoria.listarPorNome", '%'+ nome + '%');
		return Response.ok(categorias).build();
	}

	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Override
	public Response post(Categoria categoria) {
		DAO<Categoria> dao = new DAO<>(Categoria.class);
		dao.adicionar(categoria);
		return Response.ok(categoria).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Override
	public Response put(Categoria categoria) {
		DAO<Categoria> dao = new DAO<>(Categoria.class);
		dao.alterar(categoria);
		return Response.ok(categoria).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	@Override
	public Response delete(@PathParam("id") Long id) {
		DAO<Categoria> dao = new DAO<>(Categoria.class);
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
