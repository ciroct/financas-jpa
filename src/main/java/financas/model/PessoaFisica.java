package financas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tb_pessoa_fisica")
@Entity
@NamedQueries({
	@NamedQuery(name = "PessoaFisica.listarTodas", 
			    query="select pf from PessoaFisica pf order by pf.nome"),
	@NamedQuery(name = "PessoaFisica.consultarPorCpf", 
                query="select pf from PessoaFisica pf where pf.cpf=?1")
})
public class PessoaFisica extends Cliente {
	private static final long serialVersionUID = 1L;
    @Column(name = "cd_cpf", length = 11)
    private String cpf;
    @Column(name = "nm_profissao", length = 30)
    private String profissao; 

    public PessoaFisica() {
    }

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

}
