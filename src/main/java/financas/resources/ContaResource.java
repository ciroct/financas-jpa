package financas.resources;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
import financas.model.dto.ContaComNumeroEAgenciaDTO;
import financas.util.jpa.JPAEntityManager;

@Path("/contas")
public class ContaResource {

	@GET
	@Produces("application/json")
	public Response getAll() {
		EntityManager manager = JPAEntityManager.getEntityManager();
		TypedQuery<Conta> query = manager.createQuery("select c from Conta c", Conta.class);
		List<Conta> contas = query.getResultList();

		return Response.ok(contas).build();
	}

	@Path("/dto")
	@GET
	@Produces("application/json")
	public Response getDTO() {
		EntityManager manager = JPAEntityManager.getEntityManager();
		TypedQuery<ContaComNumeroEAgenciaDTO> query = 
				manager.createQuery("select new financas.model.dto.ContaComNumeroEAgenciaDTO(c.numero, c.agencia) from Conta c", ContaComNumeroEAgenciaDTO.class);
		List<ContaComNumeroEAgenciaDTO> contas = query.getResultList();

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
	@Produces("application/json")
	@Consumes("application/json")
	public Response update(Conta conta) {
		DAO<Conta> dao = new DAO<>(Conta.class);
		dao.alterar(conta);
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
