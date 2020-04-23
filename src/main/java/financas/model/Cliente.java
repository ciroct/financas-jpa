package financas.model;

<<<<<<< HEAD
import javax.persistence.CascadeType;
=======
>>>>>>> 85d42128fc868fbc6a336e5812a54aec74fcb5fd
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
<<<<<<< HEAD
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
=======
>>>>>>> 85d42128fc868fbc6a336e5812a54aec74fcb5fd
import javax.persistence.Table;

@Table(name = "tb_cliente")
@Entity
<<<<<<< HEAD
@Inheritance(strategy = InheritanceType.JOINED)
=======
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "nm_pertence_a_classe", length = 20)
>>>>>>> 85d42128fc868fbc6a336e5812a54aec74fcb5fd
public abstract class Cliente extends AbstractEntity {
	private static final long serialVersionUID = 1L;
    @Column(name = "nm_nome", length = 60)
    private String nome;
    @Column(name = "ds_endereco", length = 120)
    private String endereco;
    
<<<<<<< HEAD
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Conta conta;
    
=======
>>>>>>> 85d42128fc868fbc6a336e5812a54aec74fcb5fd
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

<<<<<<< HEAD
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

=======
>>>>>>> 85d42128fc868fbc6a336e5812a54aec74fcb5fd
}
