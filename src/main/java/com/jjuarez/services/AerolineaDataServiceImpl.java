package com.jjuarez.services;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.jjuarez.data.structure.TuplaAerolinea;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jjuarez.rest.responseTemplates.Comparativa;

@Service("aerolineaService")
@Transactional(propagation=Propagation.REQUIRED)
public class AerolineaDataServiceImpl implements AerolineaDataService{
	
	private AerolineaDataServiceImpl.ExtractorDatos extractor = 
			new AerolineaDataServiceImpl.ExtractorDatos();
  
	public Comparativa getComparativaIncidentes() {
		double periodo1 = extractor.calculaTotal(3);
		double periodo2 = extractor.calculaTotal(6);
		double total = periodo1 + periodo2;
		
		return new Comparativa(total,periodo1,periodo2);
	}

	public Comparativa getComparativaAccidentesFatales() {
		double periodo1 = extractor.calculaTotal(4);
		double periodo2 = extractor.calculaTotal(7);
		double total = periodo1 + periodo2;
		
		return new Comparativa(total,periodo1,periodo2);
	} 

	
	public Comparativa getComparativaFatalidades() {
		double periodo1 = extractor.calculaTotal(5);
		double periodo2 = extractor.calculaTotal(8);
		double total = periodo1 + periodo2;
		
		return new Comparativa(total,periodo1,periodo2);
	}
	
	public TuplaAerolinea getInfoAirline(String aerolinea) {
		return extractor.getInfoAerolinea(aerolinea);
	}
	
	
	/*
	 * 
	 * */
	private static class ExtractorDatos{
		
		/*
		 * 
		 * */
		private TuplaAerolinea[] getTuplas(){
			
			ObjectMapper mapper = new ObjectMapper();
			ClassLoader classLoader = getClass().getClassLoader();
			
			File file = new File(classLoader.getResource("airline-safety-numerical.json").getFile());
			
			try {
				TuplaAerolinea[] tuplas = mapper.readValue(file, TuplaAerolinea[].class);
				return tuplas;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		public TuplaAerolinea getInfoAerolinea(String aerolinea) {
			TuplaAerolinea[] tuplas = getTuplas();
			for(int i = 0; i < tuplas.length; i++){
				if(tuplas[i].getAirline().equals(aerolinea)){
					return tuplas[i];
				}
			}
			return null;
		}

		/*
		 * 
		 * */
		public double calculaTotal(int posicion){
			TuplaAerolinea[] tuplas = getTuplas();
			double total = 0;
			
			for(int i = 0; i < tuplas.length; i++){
				
				switch(posicion){
				case 1: return -1; // TODO: AVENTAR excepción
				case 2: total += tuplas[i].getAvail_seat_km_per_week(); break;
				case 3: total += tuplas[i].getIncidents_85_99(); break;
				case 4: total += tuplas[i].getFatal_accidents_85_99(); break;
				case 5: total += tuplas[i].getFatalities_85_99(); break;
				case 6: total += tuplas[i].getIncidents_00_14(); break;
				case 7: total += tuplas[i].getFatal_accidents_00_14(); break;
				case 8: total += tuplas[i].getFatalities_00_14(); break;
				}
			}
			return total;
		}
		
	}

	
	
}
