package financas.model;

import javax.persistence.Column;
<<<<<<< HEAD
=======
import javax.persistence.DiscriminatorValue;
>>>>>>> 85d42128fc868fbc6a336e5812a54aec74fcb5fd
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tb_pessoa_juridica")
@Entity
@NamedQueries({
	@NamedQuery(name = "PessoaJuridica.listarTodas", 
			    query="select pj from PessoaJuridica pj order by pj.nome"),
	@NamedQuery(name = "PessoaJuridica.consultarPorCnpj", 
                query="select pj from PessoaJuridica pj where pj.cnpj=?1")
})
<<<<<<< HEAD
=======
//@DiscriminatorValue("Pessoa Jurídica")
>>>>>>> 85d42128fc868fbc6a336e5812a54aec74fcb5fd
public class PessoaJuridica extends Cliente {
	private static final long serialVersionUID = 1L;
    @Column(name = "cd_cnpj", length = 14)
    private String cnpj;
    @Column(name = "nm_ramo_atividade", length = 20)
    private String ramoAtividade;

    public PessoaJuridica() {
    }

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

}
