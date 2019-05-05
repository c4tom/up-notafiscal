
package notafiscal.entidade;

public class ImpostoParana extends Imposto {
	private static final Double ALIQUOTA = 0.05;
	
	public ImpostoParana(Double valorDaNota) {
		super(valorDaNota);
	}

	@Override
	public Double calcularImpostoEstadual() {
		return this.valor * ALIQUOTA;
	}

	@Override
	public String toString() {
		return "ImpostoParana [valor=" + valor + "]";
	}

}
