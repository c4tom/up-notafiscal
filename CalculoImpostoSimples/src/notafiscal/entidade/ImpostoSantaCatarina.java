
package notafiscal.entidade;

public class ImpostoSantaCatarina extends Imposto {
	private static final Double ALIQUOTA = 0.1;
	
	public ImpostoSantaCatarina(Double valorDaNota) {
		super(valorDaNota);
	}

	@Override
	public Double calcularImpostoEstadual() {
		return this.valor * ALIQUOTA;

	}

}
