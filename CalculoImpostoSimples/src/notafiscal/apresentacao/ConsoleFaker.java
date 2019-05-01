package notafiscal.apresentacao;

import com.github.javafaker.Faker;

public class ConsoleFaker extends Console {

	private static Faker faker;
	private static boolean isFake;
	/**
	 * Solicita o nome da empresa, ou gera automaticamente se isFake=true
	 * 
	 * @return
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
	 * Modo Fake, ou seja, gera valores randomicos
	 * @return true ou false
	 */
	public static boolean isFake() {
		return isFake;
	}

	/**
	 * Seta modo gerador randomico para nome, empresa, etc
	 * @param isFake
	 */
	public static void setFake(boolean isFake) {
		ConsoleFaker.isFake = isFake;
		if (ConsoleFaker.faker == null) {
			ConsoleFaker.faker = new Faker();
		}
	}

	public static String numeroRandomico() {
		String retorno;
		if (ConsoleFaker.isFake) {
			retorno = ConsoleFaker.faker.idNumber().toString();
		} else {
			retorno = recuperaTexto("Digite o Numero");
		}
		return retorno;
	}
	
	public static void clearScreen() {  
	    System.out.flush();  
	} 
}
