
package notafiscal.entidade;

public abstract class Imposto {
	private Double aliquotaFederal = 0.15;

	protected Double valor; // Valor da Nota

	/**
	 * @param valorDaNota
	 */
	public Imposto(Double valorDaNota) {
		super();
		this.valor = valorDaNota;
	}

	/**
	 * Calcula a soma dos valores Federal e Estadual
	 * 
	 * @return a soma das aliquotas a partir do 'valor'
	 */
	public Double calcularImpostoTotal() {
		return this.calcularImpostoFederal() + this.calcularImpostoEstadual();
	}

	/**
	 * Calcula o valor do Imposto Federal
	 * 
	 * @return o valor do imposto, a partir do 'valor' da nota
	 */
	public Double calcularImpostoFederal() {
		return valor * getAliquotaFederal();
	}

	public abstract Double calcularImpostoEstadual();

	public Double getAliquotaFederal() {
		return aliquotaFederal;
	}

	@Override
	public String toString() {
		return "Imposto [aliquotaFederal=" + aliquotaFederal + ", valor=" + valor + "]";
	}

}
