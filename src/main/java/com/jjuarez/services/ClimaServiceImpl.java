package com.jjuarez.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.jjuarez.data.structure.TuplaClima;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jjuarez.rest.responseTemplates.DatosClima;
import com.jjuarez.utilerias.UtileriaFecha;
 
@Service("climaService")
@Transactional(propagation = Propagation.REQUIRED)
public class ClimaServiceImpl implements ClimaService {
	
	ClimaServiceImpl.ExtractorDatos extractor = new ClimaServiceImpl.ExtractorDatos();

	public ArrayList<DatosClima> getActualMeanTemp(Date since, Date until) {
		ArrayList<DatosClima> datos;
		try {
			datos = extractor.getArrayOfValues(2, since, until);
			return datos;
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace(); 
		}
		return null;
	}


	public ArrayList<DatosClima> getActualMinTemp(Date since, Date until) {
		ArrayList<DatosClima> datos;
		try {
			datos = extractor.getArrayOfValues(3, since, until);
			return datos;
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace(); 
		}
		return null;
	}

	
	public ArrayList<DatosClima> getActualMaxTemp(Date since, Date until) {
		ArrayList<DatosClima> datos;
		try {
			datos = extractor.getArrayOfValues(4, since, until);
			return datos;
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace(); 
		}
		return null;
	}

	
	public ArrayList<DatosClima> getAverageMinTemp(Date since, Date until) {
		ArrayList<DatosClima> datos;
		try {
			datos = extractor.getArrayOfValues(5, since, until);
			return datos;
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace(); 
		}
		return null;
	}

	
	public ArrayList<DatosClima> getAverageMaxTemp(Date since, Date until) {
		ArrayList<DatosClima> datos;
		try {
			datos = extractor.getArrayOfValues(6, since, until);
			return datos;
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace(); 
		}
		return null;
	}
	
	
private static class ExtractorDatos {
		
		private TuplaClima[] getTuplas() {
			ObjectMapper mapper = new ObjectMapper();
			ClassLoader classLoader = getClass().getClassLoader();

			File file = new File(classLoader.getResource("us-weather-history.json").getFile());

			try {
				TuplaClima[] tuplas = mapper.readValue(file, TuplaClima[].class);
				return tuplas;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		/*
		 * 
		 * */
		public ArrayList<DatosClima> getArrayOfValues(int columna, Date since, Date until) throws Exception{
			TuplaClima[] tuplas = getTuplas();
			ArrayList<DatosClima> arrayReturn = new ArrayList<DatosClima>();
			
			for(int i = 0; i < tuplas.length; i++){
				
				Date actualDate = UtileriaFecha.dateFromString(tuplas[i].getDate());
				
				if( !rangoCorrecto(since,until,actualDate)){
					continue;
				}
				
				DatosClima datosClima = new DatosClima();
				datosClima.setDate(tuplas[i].getDate());
				
				switch(columna){
				case 1: throw new Exception("valor no permitido"); // TODO: AVENTAR excepción
				case 2: datosClima.setValue(tuplas[i].getActual_mean_temp());; break;
				case 3: datosClima.setValue(tuplas[i].getActual_min_temp());; break;
				case 4: datosClima.setValue(tuplas[i].getActual_max_temp());; break;
				case 5: datosClima.setValue(tuplas[i].getAverage_min_temp());; break;
				case 6: datosClima.setValue(tuplas[i].getAverage_max_temp());; break;
				case 7: datosClima.setValue(tuplas[i].getRecord_min_temp());; break;
				case 8: datosClima.setValue(tuplas[i].getRecord_max_temp());; break;
				case 9: datosClima.setValue(tuplas[i].getRecord_min_temp_year());; break;
				case 10: datosClima.setValue(tuplas[i].getRecord_max_temp_year());; break;
				case 11: datosClima.setValue(tuplas[i].getActual_precipitation());; break;
				case 12: datosClima.setValue(tuplas[i].getAverage_precipitation());; break;
				case 13: datosClima.setValue(tuplas[i].getRecord_precipitation());; break;
				}
				arrayReturn.add(datosClima);
			}
			return arrayReturn;
		}
		
		/*
		 * */
		private boolean rangoCorrecto(Date since, Date until, Date actualDate){
			if( !actualDate.before(since) && !actualDate.after(until)){
				return true;
			}
			return false;
		}
	}
}
