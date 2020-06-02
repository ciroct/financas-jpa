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

import financas.model.PessoaJuridica;
import financas.model.dao.DAO;

@Path("/pessoas_juridicas/protected")
public class PessoaJuridicaResource implements ResourceInterface<PessoaJuridica> {

	@GET
	@Produces("application/json")
	@Override
	public Response get() {
		DAO<PessoaJuridica> dao = new DAO<>(PessoaJuridica.class);
		List<PessoaJuridica> contas = dao.listarGenerico("PessoaJuridica.listarTodas");

		return Response.ok(contas).build();
	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	@Override
	public Response getById(@PathParam("id") Long id) {
		DAO<PessoaJuridica> dao = new DAO<>(PessoaJuridica.class);
		PessoaJuridica pf = dao.consultar(id);
		if (pf != null) {
			return Response.ok(pf).build();
		} 
		return Response.status(Response.Status.NOT_FOUND).build();		
	}


	@Path("/cnpj/{cnpj}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("cnpj") String cnpj) {
		DAO<PessoaJuridica> dao = new DAO<>(PessoaJuridica.class);
		PessoaJuridica pf = dao.consultarGenerico("PessoaJuridica.consultarPorCnpj", cnpj);
		if (pf != null) {
			return Response.ok(pf).build();
		} 
		return Response.status(Response.Status.NOT_FOUND).build();		
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Override
	public Response post(PessoaJuridica pessoaJuridica) {
		DAO<PessoaJuridica> dao = new DAO<>(PessoaJuridica.class);
		dao.adicionar(pessoaJuridica);
		return Response.ok(pessoaJuridica).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Override
	public Response put(PessoaJuridica pessoaJuridica) {
		DAO<PessoaJuridica> dao = new DAO<>(PessoaJuridica.class);
		dao.alterar(pessoaJuridica);
		return Response.ok(pessoaJuridica).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	@Override
	public Response delete(@PathParam("id") Long id) {
		DAO<PessoaJuridica> dao = new DAO<>(PessoaJuridica.class);
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
