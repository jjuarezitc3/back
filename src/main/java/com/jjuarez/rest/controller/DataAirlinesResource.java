package com.jjuarez.rest.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jjuarez.data.structure.TuplaAerolinea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jjuarez.rest.responseTemplates.MensajeBusquedaAerolinea;
import com.jjuarez.services.AerolineaDataService;


@Component
@Path("/aerolineas")
public class DataAirlinesResource {
	
	@Autowired(required=true)
	@Qualifier("aerolineaService")
	private AerolineaDataService aerolineaDataService;
	
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
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/comparativa/incidentes")
	public Response getComparativaIncidentes(){
		return Response.ok()
				.entity(aerolineaDataService.getComparativaIncidentes())
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET")
				.build();
	} 
	  
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/comparativa/accidentes-fatales")
	public Response getComparativaAccidentesFatales(){
		return Response.ok() 
				.entity(aerolineaDataService.getComparativaAccidentesFatales())
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.build();
	} 
	 
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/comparativa/fatalidades")
	public Response getComparativaFatalidades(){
		return Response.ok()
				.entity(aerolineaDataService.getComparativaFatalidades())
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.build();
	} 
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/datos-aerolinea")
	public Response getDatosAerolinea(MensajeBusquedaAerolinea msg){
		TuplaAerolinea tupla = aerolineaDataService.getInfoAirline(msg.getAerolinea());
		
		return Response.ok().entity(tupla).build();
	}

}
// http://localhost:8080/backend/api/aerolineas/comparativa/incidentes