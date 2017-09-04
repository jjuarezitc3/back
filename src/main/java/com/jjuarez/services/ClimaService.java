package com.jjuarez.services;

import java.util.ArrayList;
import java.util.Date;

import com.jjuarez.rest.responseTemplates.DatosClima;

public interface ClimaService {
	public ArrayList<DatosClima> getActualMeanTemp(Date since, Date until);
	public ArrayList<DatosClima> getActualMinTemp(Date since, Date until);
	public ArrayList<DatosClima> getActualMaxTemp(Date since, Date until);
	public ArrayList<DatosClima> getAverageMinTemp(Date since, Date until);
	public ArrayList<DatosClima> getAverageMaxTemp(Date since, Date until);
}
