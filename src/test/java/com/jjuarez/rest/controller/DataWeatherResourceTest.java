package com.jjuarez.rest.controller;

import org.junit.Test;

import io.restassured.RestAssured;

public class DataWeatherResourceTest extends TestApi{
	
	@Test
	public void basicPingTest(){
		@SuppressWarnings("unused")
		String body = RestAssured
						.given()
						.when()
							.get()
						.then().statusCode(200)
						.extract()
						.body().asString();
	}
	
	
}
