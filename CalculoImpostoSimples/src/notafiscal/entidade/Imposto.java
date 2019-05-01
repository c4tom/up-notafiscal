
package notafiscal.entidade;

public abstract class Imposto {
	private Double aliquotaFederal;

	protected Double valor;

	/**
	 * @param valor
	 */
	public Imposto(Double valor) {
		super();
		this.valor = valor;
	}

	public Double calcularImpostoTotal() {
		return this.calcularImpostoFederal() + this.calcularImpostoEstadual();
	}

	public Double calcularImpostoFederal() {
		return aliquotaFederal = valor * 0.15;
	}

	public abstract Double calcularImpostoEstadual();

	public Double getAliquotaFederal() {
		return aliquotaFederal;
	}
}
