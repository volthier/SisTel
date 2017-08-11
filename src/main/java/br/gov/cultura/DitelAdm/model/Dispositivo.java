package br.gov.cultura.DitelAdm.model;
// Generated 24/08/2016 14:33:52 by Hibernate Tools 4.3.4.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Dispositivo MODEL
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "dispositivo", catalog = "dbditel")
public class Dispositivo implements java.io.Serializable {

	private Integer idDispositivo;
	private String imeiDispositivo;
	private String macDispositivo;
	private String marcaDispositivo;
	private String modeloDispositivo;
	private String numeroSerieDispositivo;
	private String patrimonioDispositivo;
	private String tipoDispositivo;
	private float valorDispositivo;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@JsonManagedReference
	private Set<Alocacao> alocacaos = new HashSet(0);


	public Dispositivo() {
	}

	public Dispositivo(String imeiDispositivo, String macDispositivo, String marcaDispositivo, String modeloDispositivo,
			String numeroSerieDispositivo, String patrimonioDispositivo, String tipoDispositivo,
			float valorDispositivo) {
		this.imeiDispositivo = imeiDispositivo;
		this.macDispositivo = macDispositivo;
		this.marcaDispositivo = marcaDispositivo;
		this.modeloDispositivo = modeloDispositivo;
		this.numeroSerieDispositivo = numeroSerieDispositivo;
		this.patrimonioDispositivo = patrimonioDispositivo;
		this.tipoDispositivo = tipoDispositivo;
		this.valorDispositivo = valorDispositivo;
	}

	public Dispositivo(String imeiDispositivo, String macDispositivo, String marcaDispositivo, String modeloDispositivo,
			String numeroSerieDispositivo, String patrimonioDispositivo, String tipoDispositivo, float valorDispositivo,
			Set<Alocacao> alocacao) {
		this.imeiDispositivo = imeiDispositivo;
		this.macDispositivo = macDispositivo;
		this.marcaDispositivo = marcaDispositivo;
		this.modeloDispositivo = modeloDispositivo;
		this.numeroSerieDispositivo = numeroSerieDispositivo;
		this.patrimonioDispositivo = patrimonioDispositivo;
		this.tipoDispositivo = tipoDispositivo;
		this.valorDispositivo = valorDispositivo;
		this.alocacaos = alocacao;
	}
	
	@Override
	public String toString() {
	   return "{Id: " + idDispositivo +", Imei: " + imeiDispositivo + ","
	   		+ " End. MAC: " + macDispositivo + ", Marca: "+ marcaDispositivo +", Modelo: "+ modeloDispositivo +
	   		",NumSerie: "+ numeroSerieDispositivo+", Patrimonio: "+patrimonioDispositivo+", Tipo: "+tipoDispositivo+
	   		", Valor: R$ "+valorDispositivo+" }";
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_dispositivo", unique = true, nullable = false)
	public Integer getIdDispositivo() {
		return this.idDispositivo;
	}

	public void setIdDispositivo(Integer idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	@Column(name = "imei_dispositivo", nullable = false)
	@Size(min=5,message="Verifique o numero IMEI!")
	@NotEmpty(message="Numero IMEI e obrigatório!")
	public String getImeiDispositivo() {
		return this.imeiDispositivo;
	}

	public void setImeiDispositivo(String imeiDispositivo) {
		this.imeiDispositivo = imeiDispositivo;
	}

	@Column(name = "mac_dispositivo")
	@Size(min=5,message="Verifique o numero MAC!")
	public String getMacDispositivo() {
		return this.macDispositivo;
	}

	public void setMacDispositivo(String macDispositivo) {
		this.macDispositivo = macDispositivo;
	}

	@Column(name = "marca_dispositivo", nullable = false, length = 250)
	@NotEmpty(message="Marca e obrigatorio!")
	@Size(max = 250, message = "Maximo de caracteres para marca excedido!")
	public String getMarcaDispositivo() {
		return this.marcaDispositivo;
	}

	public void setMarcaDispositivo(String marcaDispositivo) {
		this.marcaDispositivo = marcaDispositivo;
	}

	@Column(name = "modelo_dispositivo", nullable = false, length = 250)
	@NotEmpty(message="Modelo do dispositivo e obrigatorio!")
	@Size(max = 250, message = "Maximo de caracteres para modelo excedido!")
	public String getModeloDispositivo() {
		return this.modeloDispositivo;
	}

	public void setModeloDispositivo(String modeloDispositivo) {
		this.modeloDispositivo = modeloDispositivo;
	}

	@Column(name = "numero_serie_dispositivo", nullable = false)
	@Size(min=5,message="Verifique o numero de serie!")
	@NotEmpty(message="Numero de serie e obrigatório!")
	public String getNumeroSerieDispositivo() {
		return this.numeroSerieDispositivo;
	}

	public void setNumeroSerieDispositivo(String numeroSerieDispositivo) {
		this.numeroSerieDispositivo = numeroSerieDispositivo;
	}

	@Column(name = "patrimonio_dispositivo", nullable = false)
	@Size(min=5,message="Verifique o numero do Patrimonio!")
	@NotEmpty(message="Numero do Patrimonio e obrigatório!")
	public String getPatrimonioDispositivo() {
		return this.patrimonioDispositivo;
	}

	public void setPatrimonioDispositivo(String patrimonioDispositivo) {
		this.patrimonioDispositivo = patrimonioDispositivo;
	}

	@Column(name = "tipo_dispositivo", nullable = false)
	@NotBlank(message="Selecione Tipo!")
	public String getTipoDispositivo() {
		return this.tipoDispositivo;
	}

	public void setTipoDispositivo(String tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	@Column(name = "valor_dispositivo", nullable = false, precision = 10, scale = 0)
	@NotNull(message="Valor e obrigatorio!")
	@DecimalMin(value = "0.01", message = "Valor minimo de R$0,01!" )
	@DecimalMax(value = "9999999.99", message ="Valor ultrapassa valor maximo!")
	@NumberFormat(pattern="#,##0.00")
	public float getValorDispositivo() {
		return this.valorDispositivo;
	}

	public void setValorDispositivo(float valorDispositivo) {
		this.valorDispositivo = valorDispositivo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dispositivo")
	public Set<Alocacao> getAlocacao() {
		return this.alocacaos;
	}

	public void setAlocacao(Set<Alocacao> alocacao) {
		this.alocacaos = alocacao;
	}

}
