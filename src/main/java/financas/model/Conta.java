package financas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conta(Integer numero) {
		setNumero(numero);
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Conta))
			return false;
		Conta other = (Conta) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ",titular=" + titular + ", banco=" + banco + ", agencia=" + agencia + ", numero="
				+ numero + "]";
	}

}
