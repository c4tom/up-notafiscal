
package notafiscal.apresentacao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import notafiscal.entidade.Empresa;
import notafiscal.entidade.ImpostoParana;
import notafiscal.entidade.ImpostoSantaCatarina;
import notafiscal.entidade.ImpostoSaoPaulo;
import notafiscal.entidade.NotaFiscal;

public class Principal {

	public static ArrayList<Empresa> empresas = new ArrayList<>(); // Armazena a lista de Empresas
	public static Integer empresaSelecionada; // Variavel para registrar a empresa selecionada pelo indice de 'empresas'

	public static String numeroNota = "1"; // Valor incrementado

	public static void main(String[] args) throws IOException, InterruptedException {
		boolean continua = true;

		ConsoleFaker.setFake(true);
		populaEmpresas(10);

		do {
			ConsoleFaker.clearScreen();
			String opcoes[] = { "Cadastro de Empresa", "Consulta Empresa", "Listar Empresas", "Exclusão de Empresa",
					"Emitir Nota", "Listar Notas","Cancelar Nota","Relatórios" };

			if (empresaSelecionada != null) {
				ConsoleFaker.banner(empresas.get(empresaSelecionada).getCnpjNomeNotas());

			}

			int escolha = Console.mostrarMenu(opcoes, "Calculo de Imposto Simples", null);

			switch (escolha) {

			// ### Cadastro de Empresa
			case 1:
				cadastraEmpresa();
				break;

			// ### Consulta Empresa
			case 2:
				empresaSelecionada = buscaEmpresaPorNome();
				break;
			// ### Listar Empresa
			case 3:
				for (Empresa e : empresas) {
					e.imprimeCNPJeNome();
				}
				System.in.read();
				break;
			// ### Exclusao de Empresa
			case 4:
				deletaEmpresa();
				break;
			// ### Emissao de Nota
			case 5:
				menuNotaEstados();
				break;
			case 6:
				empresas.get(empresaSelecionada).listarNotas();
				break;
			case 7:
				empresaSelecionada = buscaEmpresaPorNome();
				cancelarNota();
				break;
			case 8:
				menuRelatorios();
				break;
			case -1:
				ConsoleFaker.banner("FIM do Programa");
				continua = false;
			}
			System.out.println("\n");
		} while (continua);

	}

	private static void cancelarNota() {
		Integer indice=0;
		for(NotaFiscal nota: empresas.get(empresaSelecionada).getNotasFiscais()) {
			Empresa.imprimirNaTelaNotaFiscal(indice, nota);
			indice++;
		}
		
		Integer escolha = Console.recuperaInteiroPositivo("Qual nota deseja cancelar, escolha pelo índice?");
		if (escolha >= 0) {
			empresas.get(empresaSelecionada).getNotasFiscais().get(escolha).setCancelada(true);
			System.out.println("*** Nota cancelada: ***");
			Empresa.imprimirNaTelaNotaFiscal(null, empresas.get(empresaSelecionada).getNotasFiscais().get(escolha));
		}
		
	}

	private static void menuRelatorios() {
		
		String opcoes[] = {"Notas fiscais por empresa","Notas fiscais canceladas por empresa", 
				"Notas fiscais por empresa ordenadas pelo valor"};
		
		int escolha = Console.mostrarMenu(opcoes, "Relatórios:", null);
		
		Integer empresaIndice = buscaEmpresaPorNome();
		switch (escolha) {
		case 1:
			for(NotaFiscal nota: empresas.get(empresaIndice).getNotasFiscais()) {
				Empresa.imprimirNaTelaNotaFiscal(null, nota);
			}
			break;
		case 2:
			for(NotaFiscal nota: empresas.get(empresaIndice).getNotasFiscais()) {
				if (nota.getCancelada())
				{
					Empresa.imprimirNaTelaNotaFiscal(null, nota);
				}
			}

			break;
		case 3:
			Collections.sort(empresas.get(empresaIndice).getNotasFiscais());
			for(NotaFiscal nota: empresas.get(empresaIndice).getNotasFiscais()) {
				Empresa.imprimirNaTelaNotaFiscal(null, nota);
			}
			break;
			
		default:
			break;
		}
		
	}

	/**
	 * Remove empresa selecionada, porém se tiver nota _nao cancelada_, não pode ser removida
	 */
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
				if (empresas.get(indice).contarNotasNaoCanceladas() == 0) {
					empresas.remove(indice);
					empresaSelecionada--;
					if (empresaSelecionada < 0) {
						empresaSelecionada = null;
					}

				} else {
					ConsoleFaker.txtCor(Color.RED_BOLD_BRIGHT,
							"Atenção: Não pode ser removido pois tem notas vinculadas não canceladas");
				}

			}
		}

	}

	private static void menuNotaEstados() {
		String opcoes[] = { "PR", "SC", "SP" };
		int escolha = Console.mostrarMenu(opcoes, "Emissão da Nota Fiscal no Estado:", null);

		// Numero da nota será sequencial
		numeroNota = String.valueOf(Integer.parseInt(numeroNota) + 1);

		String descricao = ConsoleFaker.descricaoNota();
		double valorDaNota = ConsoleFaker.valorDaNota();

		switch (escolha) {
		case 1:
			ImpostoParana impostoPR = new ImpostoParana(valorDaNota);
			NotaFiscal nfPR = new NotaFiscal(numeroNota, descricao, impostoPR, valorDaNota);
			empresas.get(empresaSelecionada).addNotas(nfPR);
			break;
		case 2:
			ImpostoSantaCatarina impostoSC = new ImpostoSantaCatarina(valorDaNota);
			NotaFiscal nfSC = new NotaFiscal(numeroNota, descricao, impostoSC, valorDaNota);
			empresas.get(empresaSelecionada).addNotas(nfSC);
			break;
		case 3:
			ImpostoSaoPaulo impostoSP = new ImpostoSaoPaulo(valorDaNota);
			NotaFiscal nfSP = new NotaFiscal(numeroNota, descricao, impostoSP, valorDaNota);
			empresas.get(empresaSelecionada).addNotas(nfSP);
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
	private static void populaEmpresas(int tamanho) {
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
	private static void cadastraEmpresa() {
		String nome = ConsoleFaker.nomeEmpresa();
		String cnpj = ConsoleFaker.cnpj();
		Empresa empresa = new Empresa(nome, cnpj);
		if (!empresas.contains(empresa)) {
			empresas.add(empresa);
			empresaSelecionada = empresas.size() - 1;
		}
	}

	/**
	 * Faz uma pesquisa em empresas
	 * @return O índice da empresa selecionada o ArrayList<Empresa>
	 */
	public static Integer buscaEmpresaPorNome() {
		Integer selecao = null;
		String nomeEmpresaAFiltrar = Console.recuperaTexto("Consulta por Nome (parte do nome)");
		int contador = Empresa.filtrarPorNome(nomeEmpresaAFiltrar, empresas);

		if (contador == 0) {
			System.out.println("Nenhuma empresa encontrada para o filtro: " + nomeEmpresaAFiltrar);
		} else {
			selecao = Console.recuperaInteiro("Escolha a empresa pelo índice:");
			if (empresas.get(selecao) == null) {
				System.out.println("Nao selecionou o indice correto");
			}
		}
		return selecao;
	}
	
}
