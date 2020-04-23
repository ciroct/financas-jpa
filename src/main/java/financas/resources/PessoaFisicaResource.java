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

@Path("/pessoa_fisica")
public class PessoaFisicaResource {

	@GET
	@Produces("application/json")
	public Response getAll() {
		DAO<PessoaFisica> dao = new DAO<>(PessoaFisica.class);
		List<PessoaFisica> contas = dao.listarGenerico("PessoaFisica.listarTodas");

		return Response.ok(contas).build();
	}


	@Path("/{cpf}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("cpf") String cpf) {
		DAO<PessoaFisica> dao = new DAO<>(PessoaFisica.class);
		PessoaFisica pf = dao.consultarGenerico("PessoaFisica.consultarPorCpf", cpf);
		if (pf != null) {
			return Response.ok(pf).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response add(PessoaFisica pessoaFisica) {
		DAO<PessoaFisica> dao = new DAO<>(PessoaFisica.class);
		dao.adicionar(pessoaFisica);
		return Response.ok(pessoaFisica).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response update(PessoaFisica pessoaFisica) {
		DAO<PessoaFisica> dao = new DAO<>(PessoaFisica.class);
		dao.alterar(pessoaFisica);
		return Response.ok(pessoaFisica).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("id") Long id) {
		DAO<PessoaFisica> dao = new DAO<>(PessoaFisica.class);
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
