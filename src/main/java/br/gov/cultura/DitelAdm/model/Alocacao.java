package br.gov.cultura.DitelAdm.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Alocacao {

	@OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
	private CadastroChip chip;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private CadastroPessoa pessoa;

	@OneToOne(cascade = CascadeType.REFRESH)
	private CadastroDispositivo dispositivo;

	@OneToOne(cascade = CascadeType.REFRESH)
	private CadastroLinha linha;

	// @NotNull(message = "Data e obrigatorio!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataRecebido;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataDevolvido;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataTemporario;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataRoaming;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@PrePersist
	public void dataRecebido() {
		this.dataRecebido = new Date();
	}

	public CadastroChip getChip() {
		return chip;
	}

	public void setChip(CadastroChip chip) {
		this.chip = chip;
	}

	public CadastroPessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(CadastroPessoa pessoa) {
		this.pessoa = pessoa;
	}

	public CadastroDispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(CadastroDispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public CadastroLinha getLinha() {
		return linha;
	}

	public void setLinha(CadastroLinha linha) {
		this.linha = linha;
	}

	public Date getDataRecebido() {
		return dataRecebido;
	}

	public void setDataRecebido(Date dataRecebido) {
		this.dataRecebido = dataRecebido;
	}

	public Date getDataDevolvido() {
		return dataDevolvido;
	}

	public void setDataDevolvido(Date dataDevolvido) {
		this.dataDevolvido = dataDevolvido;
	}

	public Date getDataTemporario() {
		return dataTemporario;
	}

	public void setDataTemporario(Date dataTemporario) {
		this.dataTemporario = dataTemporario;
	}

	public Date getDataRoaming() {
		return dataRoaming;
	}

	public void setDataRoaming(Date dataRoaming) {
		this.dataRoaming = dataRoaming;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		Alocacao other = (Alocacao) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
