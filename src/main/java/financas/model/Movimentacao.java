package financas.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Table(name="tb_movimentacao")
@Entity
@NamedQueries({
	@NamedQuery(name = "Movimentacao.listarTodas",
	            query = "select m from Movimentacao m order by m.data"),
	@NamedQuery(name = "Movimentacao.listarPorConta",
                query = "select m from Conta c join c.movimentacoes m on c.numero=?1"),
	@NamedQuery(name = "Movimentacao.listarPorData",
                query = "select m from Movimentacao m where m.data between ?1 and ?2")
})
public class Movimentacao extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "vl_valor")
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    @Column(name = "nm_tipo_movimentacao")
    private TipoMovimentacao tipo;
    @Column(name = "ds_descricao", length = 100)
    private String descricao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_data")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date data;

    public Movimentacao() {
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
