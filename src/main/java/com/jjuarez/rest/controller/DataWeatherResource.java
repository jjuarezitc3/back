package com.jjuarez.rest.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jjuarez.rest.responseTemplates.DatosClima;
import com.jjuarez.rest.responseTemplates.JsonPeticionRango;
import com.jjuarez.services.ClimaService;
import com.jjuarez.utilerias.UtileriaFecha;
 
@Component
@Path("/clima")
public class DataWeatherResource {
	
	@Autowired(required=true)
	@Qualifier("climaService")
	private ClimaService climaService;
	
	public Date dataSince;
	public Date dataUntil;
	
	public DataWeatherResource(){ 
		try {
			dataSince = UtileriaFecha.dateFromString("2014-7-1");
			dataUntil = UtileriaFecha.dateFromString("2014-7-5");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/up")
	public Response getUp(){ 
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.build();
	}
	  
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/actual-mean-temp")
	public Response getActualMeanTemp(JsonPeticionRango msg){
		
		Date peticionSince;
		Date peticionUntil;
		
		try {
			
		    
			peticionSince = UtileriaFecha.dateFromString(msg.getSince());
			peticionUntil = UtileriaFecha.dateFromString(msg.getUntil());
			
			System.out.println("peticion since: " + msg.getSince());
			System.out.println("peticion until: " + msg.getUntil());
			
			if( !rangoCorrecto(peticionSince, peticionUntil)){
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		ArrayList<DatosClima> datos = climaService.getActualMeanTemp(peticionSince, peticionUntil);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers","Content-Type")
				.header("Access-Control-Allow-Methods", "POST")
				.entity(datos).build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/actual-min-temp")
	public Response getActualMinTemp(JsonPeticionRango msg){
		Date peticionSince;
		Date peticionUntil;
		try {
			peticionSince = UtileriaFecha.dateFromString(msg.getSince());
			peticionUntil = UtileriaFecha.dateFromString(msg.getUntil());
			
			if( !rangoCorrecto(peticionSince, peticionUntil)){
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		ArrayList<DatosClima> datos = climaService.getActualMinTemp(peticionSince, peticionUntil);
		
		return Response.ok().entity(datos).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/actual-max-temp")
	public Response getActualMaxTemp(JsonPeticionRango msg){
		Date peticionSince;
		Date peticionUntil;
		try {
			peticionSince = UtileriaFecha.dateFromString(msg.getSince());
			peticionUntil = UtileriaFecha.dateFromString(msg.getUntil());
			
			if( !rangoCorrecto(peticionSince, peticionUntil)){
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		ArrayList<DatosClima> datos = climaService.getActualMaxTemp(peticionSince, peticionUntil);
		
		return Response.ok().entity(datos).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/average-min-temp")
	public Response getAverageMinTemp(JsonPeticionRango msg){
		Date peticionSince;
		Date peticionUntil;
		try {
			peticionSince = UtileriaFecha.dateFromString(msg.getSince());
			peticionUntil = UtileriaFecha.dateFromString(msg.getUntil());
			
			if( !rangoCorrecto(peticionSince, peticionUntil)){
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		ArrayList<DatosClima> datos = climaService.getAverageMinTemp(peticionSince, peticionUntil);
		
		return Response.ok().entity(datos).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/average-max-temp")
	public Response getAverageMaxTemp(JsonPeticionRango msg){
		Date peticionSince;
		Date peticionUntil;
		try {
			peticionSince = UtileriaFecha.dateFromString(msg.getSince());
			peticionUntil = UtileriaFecha.dateFromString(msg.getUntil());
			
			if( !rangoCorrecto(peticionSince, peticionUntil)){
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		ArrayList<DatosClima> datos = climaService.getAverageMaxTemp(peticionSince, peticionUntil);
		
		return Response.ok().entity(datos).build();
	}
	
	/*
	 * 
	 * */
	private boolean rangoCorrecto(Date peticionSince, Date peticionUntil){
		if(!peticionSince.before(dataSince) && !peticionSince.after(dataUntil) 
				&& peticionSince.before(peticionUntil)){
			
			if(!peticionUntil.before(dataSince) && !peticionUntil.after(dataUntil) 
					&& peticionUntil.after(peticionSince)){
				return true;
			}
		}
		return false;
	}
}
