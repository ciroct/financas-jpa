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
		EntityManager manager = JPAEntityManager.getEntityManager();
		Conta _conta = manager.find(Conta.class, id);
		manager.close();
		if (_conta != null) {
			return Response.ok(_conta).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response add(Conta conta) {
		EntityManager manager = JPAEntityManager.getEntityManager();
		try {
			manager.getTransaction().begin();

			manager.persist(conta);

			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
		return Response.ok(conta).build();
	}

	@PUT
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response update(Conta conta) {
		EntityManager manager = JPAEntityManager.getEntityManager();
		try {
			manager.getTransaction().begin();
			manager.merge(conta);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
		return Response.ok(conta).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("id") Long id) {
		EntityManager manager = JPAEntityManager.getEntityManager();
		Conta _conta = manager.find(Conta.class, id);
		if (_conta != null) {
			try {
				manager.getTransaction().begin();
				manager.remove(_conta);
				manager.getTransaction().commit();
			} finally {
				manager.close();
			}
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}
