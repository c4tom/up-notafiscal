
package notafiscal.entidade;

public class ImpostoSaoPaulo extends Imposto {

	private static final Double ALIQUOTA = 0.18;

	public ImpostoSaoPaulo(Double valorDaNota) {
		super(valorDaNota);

	}

	@Override
	public Double calcularImpostoEstadual() {
		return this.valor * ALIQUOTA;
	}

	@Override
	public String toString() {
		return "ImpostoSaoPaulo [valor=" + valor + "]";
	}

}
