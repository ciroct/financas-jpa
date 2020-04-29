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

@Path("/pessoa_juridica")
public class PessoaJuridicaResource {

	@GET
	@Produces("application/json")
	public Response getAll() {
		DAO<PessoaJuridica> dao = new DAO<>(PessoaJuridica.class);
		List<PessoaJuridica> contas = dao.listarGenerico("PessoaJuridica.listarTodas");

		return Response.ok(contas).build();
	}


	@Path("/{cnpj}")
	@GET
	@Produces("application/json")

	public Response get(@PathParam("cnpj") String cnpj) {
		DAO<PessoaJuridica> dao = new DAO<>(PessoaJuridica.class);
		PessoaJuridica pf = dao.consultarGenerico("PessoaJuridica.consultarPorCnpj", cnpj);
		if (pf != null) {
			return Response.ok(pf).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response add(PessoaJuridica pessoaJuridica) {
		DAO<PessoaJuridica> dao = new DAO<>(PessoaJuridica.class);
		dao.adicionar(pessoaJuridica);
		return Response.ok(pessoaJuridica).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response update(PessoaJuridica pessoaJuridica) {
		DAO<PessoaJuridica> dao = new DAO<>(PessoaJuridica.class);
		dao.alterar(pessoaJuridica);
		return Response.ok(pessoaJuridica).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("id") Long id) {
		DAO<PessoaJuridica> dao = new DAO<>(PessoaJuridica.class);
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
