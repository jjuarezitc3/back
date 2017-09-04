package com.jjuarez.rest.controller;

//import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DataAirlinesResourceTest extends TestApi{
	
	public static Response response;
	public static String jsonAsString;
	
	private float incidentesPeriodo1 = 84.0f;
	private float accidentesPeriodo1 = 14.0f;
	private float fatalidadesPeriodo1 = 128.0f;
	
	private float incidentesPeriodo2 = 7.0f;
	private float accidentesPeriodo2 = 1.0f;
	private float fatalidadesPeriodo2 = 88.0f;
	
	
	@Test
	public void basicPingTest(){
		@SuppressWarnings("unused")
		String body = RestAssured
				.given()
					.when()
					.get("/api/aerolineas/up/")
					.then().statusCode(200)
					
					.extract()
					.body().asString();
		
	}
	
	@Test
	public void comparativaIncidentesTest(){
		@SuppressWarnings("unused")
		String body = RestAssured
				.given()
				.when()
					.get("/api/aerolineas/comparativa/incidentes/")
				.then()
					.log().all()
					.and().assertThat().statusCode(200)
					.and()
						.body("total", response -> notNullValue())
						.and()
							.body("periodo1", response -> notNullValue())
					.extract().body().asString();
	}
	
	@Test
	public void checkResultsIncidents(){
		Response res = RestAssured
				.given()
				.when()
				.get("/api/aerolineas/comparativa/incidentes/")
				.then()
				.contentType(ContentType.JSON).body("periodo1", equalTo((incidentesPeriodo1)))
				.body("periodo2", equalTo(incidentesPeriodo2))
				.extract().response();
	}
	
	@Test
	public void checkResultsAccidents(){
		Response res = RestAssured
				.given()
				.when()
				.get("/api/aerolineas/comparativa/accidentes-fatales/")
				.then()
				.contentType(ContentType.JSON).body("periodo1", equalTo((accidentesPeriodo1)))
				.body("periodo2", equalTo(accidentesPeriodo2))
				.extract().response();
	}
	
	@Test
	public void checkResultsFatalities(){
		Response res = RestAssured
				.given()
				.when()
				.get("/api/aerolineas/comparativa/fatalidades/")
				.then()
				.contentType(ContentType.JSON).body("periodo1", equalTo((fatalidadesPeriodo1)))
				.body("periodo2", equalTo(fatalidadesPeriodo2))
				.extract().response();
	}
}
