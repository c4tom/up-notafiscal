package notafiscal.apresentacao;

import com.github.javafaker.Faker;

public class ConsoleFaker extends Console {

	private static Faker faker;
	private static boolean isFake;

	/**
	 * Solicita o nome da empresa, ou gera automaticamente se isFake=true
	 * 
	 * @return Nome da Empresa
	 */
	public static String nomeEmpresa() {
		String retorno;
		if (ConsoleFaker.isFake) {
			retorno = ConsoleFaker.faker.company().name();
		} else {
			retorno = recuperaTexto("Digite o nome da Empresa");
		}
		return retorno;
	}

	/**
	 * Solicita o numero do CNPJ
	 * 
	 * @return Número do CNPJ
	 */
	public static String cnpj() {
		String retorno;
		if (ConsoleFaker.isFake) {
			retorno = ConsoleFaker.faker.code().ean13();
		} else {
			retorno = recuperaTexto("Digite o nome CNPJ");
		}
		return retorno;
	}

	/**
	 * Solicita o valor da nota (Dinheiro)
	 * 
	 * @return Número 1000.00
	 */
	public static Double valorDaNota() {
		Double retorno;
		if (ConsoleFaker.isFake) {
			retorno = ConsoleFaker.faker.number().randomDouble(2, 1, 10000);
		} else {
			retorno = recuperaDecimal("Digite o valor R$ (exemplo: 5000.10)");
		}
		return retorno;
	}

	/**
	 * Gera um numero inteiro randômico +
	 * 
	 * @retun Número inteiro +
	 */
	public static String numeroRandomico() {
		String retorno;
		if (ConsoleFaker.isFake) {
			retorno = ConsoleFaker.faker.idNumber().toString();
		} else {
			retorno = recuperaTexto("Digite o Numero");
		}
		return retorno;
	}

	/**
	 * Solicita a descrição da nota
	 * @return Descrição da nota
	 */
	public static String descricaoNota() {
		String retorno;
		if (ConsoleFaker.isFake) {
			retorno = ConsoleFaker.faker.instance().beer().name();
		} else {
			retorno = recuperaTexto("Digite a descrição da nota");
		}
		return retorno;	
	}
	
	
	/**
	 * Modo Fake, ou seja, gera valores randomicos
	 * 
	 * @return true ou false
	 */
	public static boolean isFake() {
		return isFake;
	}

	/**
	 * Seta modo gerador randomico para nome, empresa, etc
	 * 
	 * @param isFake true ou false
	 */
	public static void setFake(boolean isFake) {
		ConsoleFaker.isFake = isFake;
		if (ConsoleFaker.faker == null) {
			ConsoleFaker.faker = new Faker();
		}
	}

	/**
	 * Limpa a tela
	 */
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
	}

	/**
	 * Imprime um texto dentro de uma demarcação tipo banner
	 * 
	 * @param texto - Texto que aparecerá dentro do banner
	 */
	public static void banner(String texto) {
		txtCor(Color.BLUE_BOLD, Color.RED_BOLD_BRIGHT + texto);
	}

	/**
	 * Imprime na tela um texto com a cor desejada
	 * 
	 * @param cor   - Cor (Color enum)
	 * @param texto - Texto
	 */
	public static void txtCor(Color cor, String texto) {
		System.out.print(cor);
		System.out.println(texto);
		System.out.print(Color.RESET);
	}
}
