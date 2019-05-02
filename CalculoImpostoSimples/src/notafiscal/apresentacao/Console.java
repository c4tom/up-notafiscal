
package notafiscal.apresentacao;

import java.util.Scanner;

/**
 * Classe especifica para realizar operações de entrada e saída de dados no
 * Console.
 * 
 * @author Andre Pimenta
 * 
 */
public class Console {
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Método para monstar menu para seleção de opções
	 * 
	 * @param opcoes
	 * @param titulo
	 * @return
	 */
	public static int mostrarMenu(String[] opcoes, String titulo, String descricao) {
		int opcaoEscolhida = 0;
		if (titulo == null) {
			System.out.println("Por favor selecione uma opções:");
		} else {
			System.out.println(titulo);
		}

		for (int i = 0; i < opcoes.length; i++) {
			System.out.println(i + 1 + ") " + opcoes[i]);
		}
		if (descricao == null) {
			System.out.println(opcoes.length + 1 + ") Para sair.");
		} else {
			System.out.println(opcoes.length + 1 + ") " + descricao + ".");
		}
		try {
			opcaoEscolhida = recuperaInteiroPositivo(descricao);
			if (opcaoEscolhida == opcoes.length + 1) {
				return -1;
			}
		} catch (Exception e) {
			opcaoEscolhida = mostrarMenu(opcoes, "Você selecionou um opções inválida, por favor tente novamente.",
					descricao);
		}
		return opcaoEscolhida;
	}

	/**
	 * Método específico para realizar a captura de dados decimais no console.
	 * 
	 * @param descricao textual do valor a ser informado pelo usuário.
	 * @return
	 */
	public static Double recuperaDecimal(String descricao) {
		Double valor;
		try {
			valor = Double.parseDouble(recuperaTexto(descricao));
		} catch (Exception e) {
			valor = recuperaDecimal(descricao);
		}
		return valor;
	}

	/**
	 * Método específico para realizar a captura de dados inteiros no console.
	 * 
	 * @param descricao textual do valor a ser informado pelo usuário.
	 * @return
	 */
	public static Integer recuperaInteiro(String descricao) {
		Integer valor;
		try {
			valor = Integer.parseInt(recuperaTexto(descricao));
		} catch (Exception e) {
			valor = recuperaInteiro(descricao);
		}
		return valor;
	}

	/**
	 * 
	 * Método específico para realizar a captura de dados inteiros positivos no
	 * console.
	 * 
	 * @param descricao textual do valor a ser informado pelo usuário.
	 * @return
	 */
	public static Integer recuperaInteiroPositivo(String descricao) {
		Integer valor = null;
		do {
			valor = recuperaInteiro(descricao);
			if (valor < 0) {
				System.out.println("Digite um valor positivo.");
			}
		} while (valor < 0);
		return valor;
	}

	/**
	 * Método específico para realizar a captura de dados texto no console.
	 * 
	 * @param descricao textual do valor a ser informado pelo usuário.
	 * @return
	 */
	public static String recuperaTexto(String descricao) {
		String valor;
		if (descricao == null) {
			System.out.println("Entre com valor:");
		} else {
			System.out.println(descricao);
		}
		try {
			valor = scanner.nextLine();
		} catch (Exception e) {
			valor = recuperaTexto(descricao);
		}
		return valor;
	}

}
