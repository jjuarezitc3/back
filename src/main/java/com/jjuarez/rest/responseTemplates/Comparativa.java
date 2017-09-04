package com.jjuarez.rest.responseTemplates;

public class Comparativa {
	
	private double total;
	private double periodo1;
	private double periodo2;
	
	public Comparativa(double total, double periodo1, double periodo2) {
		this.total = total;
		this.periodo1 = periodo1;
		this.periodo2 = periodo2;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getPeriodo1() {
		return periodo1;
	}
	public void setPeriodo1(int periodo1) {
		this.periodo1 = periodo1;
	}
	public double getPeriodo2() {
		return periodo2;
	}
	public void setPeriodo2(int periodo2) {
		this.periodo2 = periodo2;
	}
	
}
