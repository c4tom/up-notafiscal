
package notafiscal.entidade;

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

	public void listarNotas() {
		for(NotaFiscal nota: getNotasFiscais()) {
			System.out.println(nota.toString());
		}
		
	}

	
}
