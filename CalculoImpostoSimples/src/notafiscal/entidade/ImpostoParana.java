
package notafiscal.entidade;

public class ImpostoParana extends Imposto {
	public ImpostoParana(Double valor) {
		super(valor);
	}

	@Override
	public Double calcularImpostoEstadual() {
		return this.valor;
	}

}
