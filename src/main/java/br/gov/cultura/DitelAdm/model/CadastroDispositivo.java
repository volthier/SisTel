package br.gov.cultura.DitelAdm.model;

import java.math.BigDecimal;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class CadastroDispositivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message="Marca e obrigatorio!")
	@Size(max = 100, message = "Maximo de 30 caracteres para marca do dispositivo!")
	private String marca;
	
	@NotEmpty(message="Modelo do dispositivo e obrigatorio!")
	@Size(max = 100, message = "Maximo de 30 caracteres para modelo do dispositivo!")
	private String modelo;

	@Size(min=5,message="Verifique o numero de serie!")
	@NotEmpty(message="Numero de serie e obrigatório!")
	private String nrserie;
	
	@Size(min=5,message="Verifique o numero IMEI!")
	@NotEmpty(message="Numero IMEI e obrigatório!")
	private String imei;
	
	@Size(min=5,message="Verifique o numero do Patrimonio!")
	@NotEmpty(message="Numero do Patrimonio e obrigatório!")
	private String patrimonio;
	
	private String tipo;
	
	@NotNull(message="Valor e obrigatorio!")
	@DecimalMin(value = "0.01", message = "Valor minimo de R$0,01!" )
	@DecimalMax(value = "9999999.99", message ="Valor ultrapassa valor maximo!")
	@NumberFormat(pattern="#,##0.00")
	private BigDecimal valor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNrserie() {
		return nrserie;
	}

	public void setNrserie(String nrserie) {
		this.nrserie = nrserie;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		CadastroDispositivo other = (CadastroDispositivo) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}