package financas.resources;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

import financas.model.Conta;
import financas.model.dao.DAO;
import financas.model.dto.ContaComNumeroEAgenciaDTO;
import financas.util.jpa.JPAEntityManager;

@Path("/contas/protected")
public class ContaResource implements ResourceInterface<Conta> {
	
	@GET
	@Produces("application/json")
	@Override
	public Response get() {
		DAO<Conta> dao = new DAO<>(Conta.class);
		List<Conta> contas = dao.listarGenerico("Conta.listarTodas");

		return Response.ok(contas).build();
	}


	@Path("/{id}")
	@GET
	@Produces("application/json")
	@Override
	public Response getById(@PathParam("id") Long id) {
		DAO<Conta> dao = new DAO<>(Conta.class);
		Conta conta = dao.consultarGenerico("Conta.consultarPorId", id);
		if (conta != null) {
			return Response.ok(conta).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@Path("/{banco}/{from}/{to}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("banco") String banco,
						@PathParam("from") Integer from, 
			            @PathParam("to") Integer to) {
		DAO<Conta> dao = new DAO<>(Conta.class);
		List<Conta> contas = dao.listarGenerico("Conta.listarPorBancoENumero", banco, from, to);
		return Response.ok(contas).build();
	}

	@Path("/titular/{titular}")
	@GET
	@Produces("application/json")
	public Response getByTitular(@PathParam("titular") String titular) {
		DAO<Conta> dao = new DAO<>(Conta.class);
		List<Conta> contas = dao.listarGenerico("Conta.listarPorNomeCliente", '%' + titular + '%');
		return Response.ok(contas).build();
	}
	
	
	@Path("/banco/{banco}")
	@GET
	@Produces("application/json")
	public Response getByBanco(@PathParam("banco") String banco) {
		DAO<Conta> dao = new DAO<>(Conta.class);
		List<Conta> contas = dao.listarGenerico("Conta.listarPorBanco", banco + "___");
		return Response.ok(contas).build();
	}

	@Path("/agencia/{banco}/{agencias}")
	@GET
	@Produces("application/json")
	public Response getByAgencia(@PathParam("banco") String banco, 
			@PathParam("agencias") PathSegment agencias) {
		Set<String> a = agencias.getMatrixParameters().keySet();
		EntityManager manager = JPAEntityManager.getEntityManager();
		
		String jpql = "select c from Conta c where c.banco=?1 and c.agencia in (?2";
		
		for (int i = 2; i <= a.size(); i++) {
			jpql += ", ?" + (i + 1) ;
		}
		jpql += ")";
		
		TypedQuery<Conta> query = manager.createQuery(jpql, Conta.class);
		
		query.setParameter(1, banco);
		Iterator<String> it = a.iterator();
		for (int i = 2; it.hasNext(); i++) {
			query.setParameter(i, it.next());
		}
		
		List<Conta> contas = query.getResultList();
		return Response.ok(contas).build();
	}

	@Path("/dto")
	@GET
	@Produces("application/json")
	public Response getDTO() {
		EntityManager manager = JPAEntityManager.getEntityManager();
		TypedQuery<ContaComNumeroEAgenciaDTO> query = manager.createQuery(
				"select new financas.model.dto.ContaComNumeroEAgenciaDTO(c.numero, c.agencia) from Conta c",
				ContaComNumeroEAgenciaDTO.class);
		List<ContaComNumeroEAgenciaDTO> contas = query.getResultList();

		return Response.ok(contas).build();
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Override
	public Response post(Conta conta) {
		DAO<Conta> dao = new DAO<>(Conta.class);
		dao.adicionar(conta);
		return Response.ok(conta).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Override
	public Response put(Conta conta) {
		DAO<Conta> dao = new DAO<>(Conta.class);
		dao.alterar(conta);
		return Response.ok(conta).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	@Override
	public Response delete(@PathParam("id") Long id) {
		DAO<Conta> dao = new DAO<>(Conta.class);
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}
