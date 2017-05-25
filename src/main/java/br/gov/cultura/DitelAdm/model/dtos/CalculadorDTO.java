package br.gov.cultura.DitelAdm.model.dtos;

import java.util.Date;

public class CalculadorDTO {

	int inteiroA;
	int inteiroB;
	int resultadoI;
	float floatA;
	float floatB;
	float resultadoF;
	double doubleA;
	double doubleB;
	double resultadoD;
	Date dataA;
	Date dataB;
	Date resultadoDt;
	String stringA;
	String stringB;
	String resultadoS;
	public CalculadorDTO() {
		super();
	}
	public CalculadorDTO(int inteiroA, int inteiroB, int resultadoI, float floatA, float floatB, float resultadoF,
			double doubleA, double doubleB, double resultadoD, Date dataA, Date dataB, Date resultadoDt, String stringA,
			String stringB, String resultadoS) {
		super();
		this.inteiroA = inteiroA;
		this.inteiroB = inteiroB;
		this.resultadoI = resultadoI;
		this.floatA = floatA;
		this.floatB = floatB;
		this.resultadoF = resultadoF;
		this.doubleA = doubleA;
		this.doubleB = doubleB;
		this.resultadoD = resultadoD;
		this.dataA = dataA;
		this.dataB = dataB;
		this.resultadoDt = resultadoDt;
		this.stringA = stringA;
		this.stringB = stringB;
		this.resultadoS = resultadoS;
	}
	public int getInteiroA() {
		return inteiroA;
	}
	public void setInteiroA(int inteiroA) {
		this.inteiroA = inteiroA;
	}
	public int getInteiroB() {
		return inteiroB;
	}
	public void setInteiroB(int inteiroB) {
		this.inteiroB = inteiroB;
	}
	public int getResultadoI() {
		return resultadoI;
	}
	public void setResultadoI(int resultadoI) {
		this.resultadoI = resultadoI;
	}
	public float getFloatA() {
		return floatA;
	}
	public void setFloatA(float floatA) {
		this.floatA = floatA;
	}
	public float getFloatB() {
		return floatB;
	}
	public void setFloatB(float floatB) {
		this.floatB = floatB;
	}
	public float getResultadoF() {
		return resultadoF;
	}
	public void setResultadoF(float resultadoF) {
		this.resultadoF = resultadoF;
	}
	public double getDoubleA() {
		return doubleA;
	}
	public void setDoubleA(double doubleA) {
		this.doubleA = doubleA;
	}
	public double getDoubleB() {
		return doubleB;
	}
	public void setDoubleB(double doubleB) {
		this.doubleB = doubleB;
	}
	public double getResultadoD() {
		return resultadoD;
	}
	public void setResultadoD(double resultadoD) {
		this.resultadoD = resultadoD;
	}
	public Date getDataA() {
		return dataA;
	}
	public void setDataA(Date dataA) {
		this.dataA = dataA;
	}
	public Date getDataB() {
		return dataB;
	}
	public void setDataB(Date dataB) {
		this.dataB = dataB;
	}
	public Date getResultadoDt() {
		return resultadoDt;
	}
	public void setResultadoDt(Date resultadoDt) {
		this.resultadoDt = resultadoDt;
	}
	public String getStringA() {
		return stringA;
	}
	public void setStringA(String stringA) {
		this.stringA = stringA;
	}
	public String getStringB() {
		return stringB;
	}
	public void setStringB(String stringB) {
		this.stringB = stringB;
	}
	public String getResultadoS() {
		return resultadoS;
	}
	public void setResultadoS(String resultadoS) {
		this.resultadoS = resultadoS;
	}
	
}
