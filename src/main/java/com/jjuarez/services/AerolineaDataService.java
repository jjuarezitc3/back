package com.jjuarez.services;

import org.jjuarez.data.structure.TuplaAerolinea;

import com.jjuarez.rest.responseTemplates.Comparativa;

public interface AerolineaDataService {
	public Comparativa getComparativaIncidentes();
	public Comparativa getComparativaAccidentesFatales();
	public Comparativa getComparativaFatalidades();
	public TuplaAerolinea getInfoAirline(String aerolinea);
}
