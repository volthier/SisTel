package br.gov.cultura.DitelAdm.model;
//Generated 24/08/2016 15:08:19 by Hibernate Tools 4.3.4.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
/**
 * Contrato MODEL
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "contrato", catalog = "dbditel")
public class Contrato implements java.io.Serializable {

	private Integer idContrato;
	private String nroContrato;
	private String nroConta;
	private String razaoSocial;
	private String nomeServico;
	private float valorCobrado;
	private Date dataInicioContrato;
	private Date dataFimContrato;
	
	public Contrato() {
	}

	public Contrato(Integer idContrato, String nroContrato, String nroConta, String razaoSocial, 
				    String nomeServico, float valorCobrado, Date dataInicioContrato, Date dataFimContrato) {
		setIdContrato(idContrato);
		setNroContrato(nroContrato);
		setNroConta(nroConta);
		setRazaoSocial(razaoSocial);
		setNomeServico(nomeServico);
		setValorCobrado(valorCobrado);
		setDataInicioContrato(dataInicioContrato);
		setDataFimContrato(dataFimContrato);
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_contrato", unique = true, nullable = false)
	public Integer getIdContrato() {
		return this.idContrato;
	}

	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}
	
	@Column(name = "nro_contrato", nullable = false)
	@Size(max=50,message="Maximo de caracteres para Numero do Contrato excedido!")
	@NotEmpty(message="Numero do Contrato e obrigatorio!")
	public String getNroContrato() {
		return nroContrato;
	}

	public void setNroContrato(String nroContrato) {
		this.nroContrato = nroContrato;
	}

	@Column(name = "nro_conta", nullable = false)
	@Size(max=20,message="Maximo de caracteres para Numero da Conta excedido!")
	@NotEmpty(message="Numero da Conta e obrigatorio!")
	public String getNroConta() {
		return nroConta;
	}

	public void setNroConta(String nroConta) {
		this.nroConta = nroConta;
	}

	@Column(name = "razao_social", nullable = false)
	@Size(max=50,message="Maximo de caracteres para Operadora excedido!")
	@NotEmpty(message="Operadora e obrigatorio!")
	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	@Column(name = "nome_servico", nullable = false)
	@Size(max=80,message="Maximo de caracteres para Nome do servico excedido!")
	@NotEmpty(message="Nome do servico e obrigatorio!")
	public String getNomeServico() {
		return this.nomeServico;
	}

	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	@Column(name = "valor_cobrado", nullable = false)
	@NotNull(message="Valor e obrigatorio!")
	@DecimalMin(value = "0.01", message = "Valor minimo de R$0,01!" )
	@DecimalMax(value = "9999999.99", message ="Valor ultrapassa valor maximo!")
	@NumberFormat(pattern="#,##0.00")
	public float getValorCobrado() {
		return this.valorCobrado;
	}

	public void setValorCobrado(float valorCobrado) {
		this.valorCobrado = valorCobrado;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inicio_contrato", nullable = false, length = 19)
	public Date getDataInicioContrato() {
		return this.dataInicioContrato;
	}

	public void setDataInicioContrato(Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_fim_contrato", nullable = false, length = 19)
	public Date getDataFimContrato() {
		return this.dataFimContrato;
	}

	public void setDataFimContrato(Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}

}
