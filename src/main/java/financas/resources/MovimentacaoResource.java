package financas.resources;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import financas.model.Movimentacao;
import financas.model.dao.DAO;

@Path("/movimentacao")
public class MovimentacaoResource {

	@GET
	@Produces("application/json")
	public Response getAll() {
		DAO<Movimentacao> dao = new DAO<>(Movimentacao.class);
		List<Movimentacao> mov = dao.listarGenerico("Movimentacao.listarTodas");

		return Response.ok(mov).build();
	}

	@Path("/{num_conta}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("num_conta") Integer numConta) {
		DAO<Movimentacao> dao = new DAO<>(Movimentacao.class);
		List<Movimentacao> mov = dao.listarGenerico("Movimentacao.listarPorConta", numConta);
		return Response.ok(mov).build();
	}	

	@Path("/categoria/{categoria}")
	@GET
	@Produces("application/json")
	public Response getByCategeoria(@PathParam("categoria") String categoria) {
		DAO<Movimentacao> dao = new DAO<>(Movimentacao.class);
		List<Movimentacao> mov = dao.listarGenerico("Movimentacao.listarPorCategoria", categoria);
		return Response.ok(mov).build();
	}	

	@Path("/{from}/{to}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("from") String from,
			            @PathParam("to") String to) {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDt, toDt;
		try {
			fromDt = dateFormat.parse(from);
			toDt = dateFormat.parse(to);
		} catch (ParseException e) {			
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		DAO<Movimentacao> dao = new DAO<>(Movimentacao.class);
		List<Movimentacao> mov= dao.listarGenerico("Movimentacao.listarPorData", fromDt, toDt);
		return Response.ok(mov).build();
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response add(Movimentacao movimentacao) {
		DAO<Movimentacao> dao = new DAO<>(Movimentacao.class);
		dao.adicionar(movimentacao);
		return Response.ok(movimentacao).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response update(Movimentacao movimentacao) {
		DAO<Movimentacao> dao = new DAO<>(Movimentacao.class);
		dao.alterar(movimentacao);
		return Response.ok(movimentacao).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("id") Long id) {
		DAO<Movimentacao> dao = new DAO<>(Movimentacao.class);
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
