package financas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tb_conta")
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Conta.listarTodas", 
                query = "select c from Conta c order by c.titular"),
	@NamedQuery(name = "Conta.consultarPorBanco", 
	            query = "select c from Conta c where c.banco=?1"),
	@NamedQuery(name = "Conta.consultarPorNumero", 
	            query = "select c from Conta c where c.numero=?1") 
})
public class Conta extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "nm_titular", length = 100)
	private String titular;
	@Column(name = "nm_banco", length = 50)
	private String banco;
	@Column(name = "nm_agencia", length = 50)
	private String agencia;
	@Column(name = "nr_numero", unique = true)
	private Integer numero;

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

	@Override
	public String toString() {
		return "Conta [id=" + getId() + ", titular=" + titular + ", banco=" + banco + ", agencia=" + agencia + ", numero=" + numero + "]";
	}
}
