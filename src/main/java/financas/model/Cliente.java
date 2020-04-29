package financas.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "tb_cliente")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cliente extends AbstractEntity {
	private static final long serialVersionUID = 1L;
    @Column(name = "nm_nome", length = 60)
    private String nome;
    @Column(name = "ds_endereco", length = 120)
    private String endereco;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Conta conta;
    
    public Cliente() { }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
