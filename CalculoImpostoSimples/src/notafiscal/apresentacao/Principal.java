
package notafiscal.apresentacao;

import java.io.IOException;
import java.util.ArrayList;

import notafiscal.entidade.Empresa;
import notafiscal.entidade.ImpostoParana;
import notafiscal.entidade.ImpostoSantaCatarina;
import notafiscal.entidade.NotaFiscal;

public class Principal {
	private static final Double ALIQUOTA_PR = 0.05;
	private static final Double ALIQUOTA_SC = 0.1;
	private static final Double ALIQUOTA_SP = 0.18;

	public static ArrayList<Empresa> empresas = new ArrayList<>(); // Armazena a lista de Empresas
	public static Integer empresaSelecionada; // Variavel para registrar a empresa selecionada pelo indice de 'empresas'

	public static void main(String[] args) throws IOException {
		boolean continua = true;

		ConsoleFaker.setFake(true);
		populaEmpresas(10);

		do {
			System.out.println("============================================================================");
			ConsoleFaker.clearScreen();
			String opcoes[] = { "Cadastro de Empresa", "Consulta Empresa", "Listar Empresas", "Exclusão de Empresa",
					"Emitir Nota" };

			if (empresaSelecionada != null) {
				empresas.get(empresaSelecionada).ImprimeCNPJeNome();
				System.out.println("============================================================================");
			}

			int escolha = Console.mostrarMenu(opcoes, "Calculo de Imposto Simples", null);

			switch (escolha) {
			case 1:
				cadastraEmpresa();
				break;
			case 2:
				String nomeEmpresaAFiltrar = Console.recuperaTexto("Consulta por Nome (parte do nome)");
				int contador = Empresa.filtrarPorNome(nomeEmpresaAFiltrar, empresas);

				if (contador == 0) {
					System.out.println("Nenhuma empresa encontrada para o filtro: " + nomeEmpresaAFiltrar);
				} else {
					empresaSelecionada = Console.recuperaInteiro("Escolha a empresa pelo índice:");
					if (empresas.get(empresaSelecionada) == null) {
						System.out.println("Nao selecionou o indice correto");
					}
				}

				break;
			case 3:
				for (Empresa e : empresas) {
					e.ImprimeCNPJeNome();
				}
				System.in.read();
				break;

			case 4:
				deletaEmpresa();
				break;
			case 5:
				menuNotaEstados();
				break;

			case -1:
				System.out.println("###################");
				System.out.println("Sistema finalizado.");
				System.out.println("###################");
				continua = false;
			}
			System.out.println("\n");
		} while (continua);

	}

	private static void deletaEmpresa() {
		String nomeEmpresaAFiltrar = Console.recuperaTexto("Consulta por Nome (parte do nome)");
		int contador = Empresa.filtrarPorNome(nomeEmpresaAFiltrar, empresas);

		if (contador == 0) {
			System.out.println("Nenhuma empresa encontrada para o filtro: " + nomeEmpresaAFiltrar);
		} else {
			int indice = Console.recuperaInteiro("Escolha a empresa pelo índice:");
			if (empresas.get(indice) == null) {
				System.out.println("Nao selecionou o indice correto");
			} else {
				empresas.remove(indice);
				empresaSelecionada--;

				if (empresaSelecionada < 0) {
					empresaSelecionada = null;
				}

			}
		}

	}

	public static void menuNotaEstados() {
		String opcoes[] = { "PR", "SC", "SP" };
		int escolha = Console.mostrarMenu(opcoes, "Emissão de Nota Por Estado", null);

		switch (escolha) {
		case 1:
			ImpostoParana impostoPR = new ImpostoParana(ALIQUOTA_PR);
			String numeroNota = ConsoleFaker.numeroRandomico();
			NotaFiscal nfPR = new NotaFiscal("0000001", "Carrinho Controle Remoto", impostoPR, 1000d);

			nfPR.getValorComImposto();
			break;
		case 2:
			new ImpostoSantaCatarina(ALIQUOTA_SC);
			break;
		case 3:
			new ImpostoSantaCatarina(ALIQUOTA_SP);
			break;
		default:
			break;
		}

	}

	/**
	 * Popular o array de Empresas para facilitar o cadastramento
	 * 
	 * @param tamanho - Quantidade registros a gerar
	 */
	public static void populaEmpresas(int tamanho) {
		int i = 0;
		do {
			cadastraEmpresa();
			i++;
		} while (i < tamanho);
	}

	/**
	 * Solicita Nome da Empresa e Cnpj para adicionar a lista de 'empresas' Se
	 * ConsoleFaker.isFake=true, é gerado automaticamente
	 */
	public static void cadastraEmpresa() {
		String nome = ConsoleFaker.nomeEmpresa();
		String cnpj = ConsoleFaker.cnpj();
		Empresa empresa = new Empresa(nome, cnpj);
		if (!empresas.contains(empresa)) {
			empresas.add(empresa);
			empresaSelecionada = empresas.size() - 1;
		}
	}

}
