package financas.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "tb_conta")
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Conta.listarTodas", 
                query = "select c from Conta c"),
	@NamedQuery(name = "Conta.consultarPorId", 
    			query = "select c from Conta c where c.id=?1"),
	@NamedQuery(name = "Conta.listarPorBanco", 
	            query = "select c from Conta c where c.banco like ?1"),
	@NamedQuery(name = "Conta.listarPorBancoENumero", 
                query = "select c from Conta c where c.banco=?1 and c.numero between ?2 and ?3"),
	@NamedQuery(name = "Conta.listarPorNomeCliente", 
    			query = "select c from Conta c join Cliente cc on cc.conta = c where cc.nome like ?1")
})
	
public class Conta extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "nm_banco", length = 50)
	private String banco;
	@Column(name = "nm_agencia", length = 50)
	private String agencia;
	@Column(name = "nr_numero")
	private Integer numero;
	
/*	
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "conta")
    //@JoinColumn(name = "conta_id")
    private List<Movimentacao> movimentacoes = 
                                   new ArrayList<>();
*/	

	public Conta() {
	}

	public Conta(Integer numero) {
		setNumero(numero);
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
/*	
    @JsonIgnore
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

    @JsonProperty
	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
*/
	@Override
	public String toString() {
		return "Conta [id=" + getId() + ", banco=" + banco + ", agencia=" + agencia + ", numero=" + numero + "]";
	}
}
