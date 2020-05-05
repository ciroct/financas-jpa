package financas.resources;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import financas.model.Movimentacao;
import financas.model.TipoMovimentacao;
import financas.model.dao.DAO;
import financas.util.jpa.JPAEntityManager;

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
	
	@Path("/media/{num_conta}/{tipo}")
	@GET
	@Produces("application/json")
	public Response getMedia(@PathParam("num_conta") Integer numConta,
			            @PathParam("tipo") String tipo) {
		EntityManager manager = JPAEntityManager.getEntityManager();
		TypedQuery<Double> query = manager.createQuery("select " + 
				   "avg(m.valor) from Movimentacao m where " +    
				   "m.conta.numero = ?1 and m.tipo = ?2", Double.class);
		query.setParameter(1, numConta);
		query.setParameter(2, tipo.equalsIgnoreCase(TipoMovimentacao.SAIDA.name()) ? 
						TipoMovimentacao.SAIDA : TipoMovimentacao.ENTRADA);
		Double media = query.getSingleResult();
				
		return Response.ok(media).build();
	}
	
	@Path("/quantidade/{num_conta}")
	@GET
	@Produces("application/json")
	public Response getQuantidade(@PathParam("num_conta") Integer numConta) {
		EntityManager manager = JPAEntityManager.getEntityManager();

		TypedQuery<Long> query = manager.createQuery("select " + 
			     "count(m) from Movimentacao m where " +    
			     "m.conta.numero = ?1", Long.class);
		query.setParameter(1, numConta);
		Long cont = query.getSingleResult();
				
		return Response.ok(cont).build();
	}
	
	
	@Path("/maximo/{num_conta}/{tipo}")
	@GET
	@Produces("application/json")
	public Response getMaximo(@PathParam("num_conta") Integer numConta,
			@PathParam("tipo") String tipo) {
		EntityManager manager = JPAEntityManager.getEntityManager();
		TypedQuery<Movimentacao> query = manager.createQuery("select " + 
			     "max(m) from Movimentacao m where " +    
			     "m.conta.numero = ?1 and m.tipo = ?2", Movimentacao.class);
		query.setParameter(1, numConta);
		query.setParameter(2, tipo.equalsIgnoreCase(TipoMovimentacao.SAIDA.name()) ? 
				TipoMovimentacao.SAIDA : TipoMovimentacao.ENTRADA);
		Movimentacao max = query.getSingleResult();				
		return Response.ok(max).build();
	}
	
	@Path("/minimo/{num_conta}/{tipo}")
	@GET
	@Produces("application/json")
	public Response getMinimo(@PathParam("num_conta") Integer numConta,
			@PathParam("tipo") String tipo) {
		EntityManager manager = JPAEntityManager.getEntityManager();
		TypedQuery<Movimentacao> query = manager.createQuery("select " + 
			     "min(m) from Movimentacao m where " +    
			     "m.conta.numero = ?1 and m.tipo = ?2", Movimentacao.class);
		query.setParameter(1, numConta);
		query.setParameter(2, tipo.equalsIgnoreCase(TipoMovimentacao.SAIDA.name()) ? 
				TipoMovimentacao.SAIDA : TipoMovimentacao.ENTRADA);
		Movimentacao min = query.getSingleResult();				
		return Response.ok(min).build();
	}
	
	@Path("/soma/{num_conta}/{tipo}/{from}/{to}")
	@GET
	@Produces("application/json")
	public Response getSoma(@PathParam("num_conta") Integer numConta,
			@PathParam("tipo") String tipo, @PathParam("from") String from,
            @PathParam("to") String to) {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDt, toDt;
		try {
			fromDt = dateFormat.parse(from);
			toDt = dateFormat.parse(to);
		} catch (ParseException e) {			
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		EntityManager manager = JPAEntityManager.getEntityManager();
		TypedQuery<BigDecimal> query = manager.createQuery("select sum(m.valor)" 
				 + " from Movimentacao m where m.conta.numero = ?1 "
				 + "and m.tipo = ?2 and m.data between ?3 and ?4", BigDecimal.class);
		query.setParameter(1, numConta);
		query.setParameter(2, tipo.equalsIgnoreCase(TipoMovimentacao.SAIDA.name()) ? 
				TipoMovimentacao.SAIDA : TipoMovimentacao.ENTRADA);
		query.setParameter(3, fromDt);
		query.setParameter(4, toDt);
		BigDecimal soma = query.getSingleResult();
		return Response.ok(soma).build();
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
