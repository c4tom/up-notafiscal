
package notafiscal.entidade;

import java.util.Date;
public class NotaFiscal implements Comparable<NotaFiscal> {
  private String numero;

  private String descricao;

  private Date dataEmissao;

  private Imposto imposto;

  private Double valorSemImposto;

  private Double valorComImposto;

  private Boolean cancelada;

  
  
  /**
 * @param numero
 * @param descricao
 * @param imposto
 * @param valor
 */
public NotaFiscal(String numero, String descricao, Imposto imposto, Double valor) {
	super();
	this.numero = numero;
	this.descricao = descricao;
	this.imposto = imposto;
	this.valorSemImposto = valor;
}

public String getNumero() {
		return numero;
  }

  public void setNumero(String numero) {
		this.numero = numero;
  }

  public String getDescricao() {
		return descricao;
  }

  public void setDescricao(String descricao) {
		this.descricao = descricao;
  }

  public Date getDataEmissao() {
		return dataEmissao;
  }

  public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
  }

  public Imposto getImposto() {
		return imposto;
  }

  public void setImposto(Imposto imposto) {
		this.imposto = imposto;
  }

  public Double getValor() {
		return valorSemImposto;
  }

  public void setValor(Double valor) {
		this.valorSemImposto = valor;
  }

  public Double getValorComImposto() {
		return valorComImposto + this.getValor() * this.imposto.getAliquotaFederal();
  }

  public void setValorComImposto(Double valorComImposto) {
		this.valorComImposto = valorComImposto;
  }

  public Boolean getCancelada() {
		return cancelada;
  }

  public void setCancelada(Boolean cancelada) {
		this.cancelada = cancelada;
  }

  @Override
  public int compareTo(NotaFiscal notaFiscal) {

		return this.getNumero().compareTo(notaFiscal.numero);
  }

}
