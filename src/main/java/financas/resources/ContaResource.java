package financas.resources;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import financas.model.Conta;
import financas.model.dao.DAO;
import financas.service.ContaService;
import financas.util.jpa.JPAEntityManager;

@Path("/contas")
public class ContaResource {
	private ContaService contas = new ContaService();

	@GET
	@Produces("application/json")
	public Response getAll() {
		return Response.ok(contas).build();
	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("id") Long id) {
		DAO<Conta> dao = new DAO<>(Conta.class);
		Conta _conta = dao.consultar(id);
		if (_conta != null) {
			return Response.ok(_conta).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response add(Conta conta) {
		DAO<Conta> dao = new DAO<>(Conta.class);
		dao.adicionar(conta);
		return Response.ok(conta).build();
	}

	@PUT
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response update(Conta conta) {
		DAO<Conta> dao = new DAO<>(Conta.class);
		dao.alterar(conta);;
		return Response.ok(conta).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("id") Long id) {
		DAO<Conta> dao = new DAO<>(Conta.class);
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}
