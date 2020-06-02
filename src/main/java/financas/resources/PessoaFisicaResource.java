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

import financas.model.PessoaFisica;
import financas.model.dao.DAO;

@Path("/pessoas_fisicas/protected")
public class PessoaFisicaResource implements ResourceInterface<PessoaFisica>{

	@GET
	@Produces("application/json")
	@Override
	public Response get() {
		DAO<PessoaFisica> dao = new DAO<>(PessoaFisica.class);
		List<PessoaFisica> contas = dao.listarGenerico("PessoaFisica.listarTodas");

		return Response.ok(contas).build();
	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	@Override
	public Response getById(@PathParam("id") Long id) {
		DAO<PessoaFisica> dao = new DAO<>(PessoaFisica.class);
		PessoaFisica pf = dao.consultar(id);
		if (pf != null) {
			return Response.ok(pf).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();	
	}
	
	@Path("/cpf/{cpf}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("cpf") String cpf) {
		DAO<PessoaFisica> dao = new DAO<>(PessoaFisica.class);
		PessoaFisica pf = dao.consultarGenerico("PessoaFisica.consultarPorCpf", cpf);
		if (pf != null) {
			return Response.ok(pf).build();
		} 
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Override
	public Response post(PessoaFisica pessoaFisica) {
		DAO<PessoaFisica> dao = new DAO<>(PessoaFisica.class);
		dao.adicionar(pessoaFisica);
		return Response.ok(pessoaFisica).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Override
	public Response put(PessoaFisica pessoaFisica) {
		DAO<PessoaFisica> dao = new DAO<>(PessoaFisica.class);
		dao.alterar(pessoaFisica);
		return Response.ok(pessoaFisica).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	@Override
	public Response delete(@PathParam("id") Long id) {
		DAO<PessoaFisica> dao = new DAO<>(PessoaFisica.class);
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
