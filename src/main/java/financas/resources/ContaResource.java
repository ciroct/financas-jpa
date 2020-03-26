package financas.resources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
import financas.service.ContaService;

@Path("/contas")
public class ContaResource {
	private ContaService contas = new ContaService();

	@GET
	@Produces("application/json")
	public Response getAll() {
		return Response.ok(contas).build();
	}

	@Path("/{numero}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("numero") Integer numero) {
		Conta _conta = contas.get(numero);
		if (_conta != null) {
			return Response.ok(_conta).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response add(Conta conta) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("financasPU");
		EntityManager manager = emf.createEntityManager();

		try {
			manager.getTransaction().begin();

			manager.persist(conta);

			manager.getTransaction().commit();
		} finally {
			manager.close();
			emf.close();
		}
		return Response.ok(conta).build();
	}

	@PUT
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response update(Conta conta) {
		if (contas.update(conta)) {
			return Response.ok(conta).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@Path("/{numero}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("numero") Integer numero) {

		if (contas.delete(numero)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}
