package financas.model.dto;

public class ContaComNumeroEAgenciaDTO {
	private Integer numero;
	private String agencia;
	
	public ContaComNumeroEAgenciaDTO(Integer numero, String agencia) {
		this.numero = numero;
		this.agencia = agencia;		
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

}
