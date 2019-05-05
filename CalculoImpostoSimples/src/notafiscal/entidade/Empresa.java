
package notafiscal.entidade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Empresa {
	private String nome;
	private String cnpj;

	private ArrayList<NotaFiscal> notasFiscais;

	/**
	 * @param nome
	 * @param cnpj
	 */
	public Empresa(String nome, String cnpj) {
		this.nome = nome;
		this.cnpj = cnpj;
		
		this.notasFiscais = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void addNotas(NotaFiscal nota) {
		getNotasFiscais().add(nota);
	}
	
	public ArrayList<NotaFiscal> getNotasFiscais() {
		return notasFiscais;
	}

	public void imprimeCNPJeNome() {
		System.out.println(getCnpjNomeNotas());
	}
	
	public String getCnpjNomeNotas() {
		return "CNPJ="+getCnpj() + " - " + getNome() + " - Notas=" + getNotasFiscais().size();
	}
	
	@Override
	public String toString() {
		return "Empresa [nome=" + nome + ", cnpj=" + cnpj + ", notasFiscais=" + notasFiscais + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}

	/**
	 * Faz um filtro de empresa pelo nome
	 * @param nomeEmpresaAFiltrar
	 * @param empresas
	 * @return Total de empresas encontrada
	 */
	public static int filtrarPorNome(String nomeEmpresaAFiltrar, ArrayList<Empresa> empresas) {
		int indice = 0;
		int contador=0;
		for (Empresa emp : empresas) {
			if (emp.getNome().toUpperCase().contains(nomeEmpresaAFiltrar.toUpperCase())) {
				System.out.print(indice + ") ");
				emp.imprimeCNPJeNome();
				contador++;
			}
			indice++;
		}
		return contador;
	}

	/**
	 * Contador de notas
	 * @param canceladas true somente as canceladas, false somente as não canceladas, null todas as notas
	 * @return
	 */
	public int contarNotasNaoCanceladas() {
		int contador = 0;
		for(NotaFiscal nota: getNotasFiscais()) {
			if (!nota.getCancelada()) contador++;
		}
		return contador;
	}
	
	public void listarNotas() {
		for(NotaFiscal nota: getNotasFiscais()) {
			System.out.println(nota.toString());
		}
		
	}
	/**
	 * Imprime na tela uma nota fiscal
	 * @param indice se não for null, imprime índice
	 * @param nota - Objeto Notafiscal
	 */
	public static void imprimirNaTelaNotaFiscal(Integer indice, NotaFiscal nota) {
		System.out.println("-----------------------------------------------------------");
		if(indice != null) System.out.println(indice + ") ");
		System.out.println("Numero: " + nota.getNumero());
		System.out.println("Descrição: " + nota.getDescricao());
		System.out.println("Valor Total: " + nota.getValorComImposto());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(nota.getDataEmissao());
		
		System.out.println("Data: " + date);
	}
}
