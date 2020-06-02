package financas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tb_usuario")
@Entity
@NamedQueries({
	@NamedQuery(name = "Usuario.listarTodos", 
			    query = "select u from Usuario u order by u.nome"),
	@NamedQuery(name = "Usuario.autenticar", 
	            query = "select u from Usuario u where u.email = ?1 and u.senha = ?2")
})
public class Usuario extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "nm_nome", length = 60)
	private String nome;
	@Column(name = "nm_email", length = 60, unique = true)
	private String email;
	@Column(name = "nm_senha", length = 255)
	private String senha;

	public Usuario() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
		
}
