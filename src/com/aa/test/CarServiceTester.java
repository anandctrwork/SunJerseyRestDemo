package com.aa.test;

import java.util.List;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form; 
import javax.ws.rs.core.MediaType;

import com.rt.aa.Car;

public class CarServiceTester  {

   private static Client client;
   private String REST_SERVICE_URL = "http://localhost:9090/MyCarRestDemo/rest/CarService";
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String PASS = "pass";
   private static final String FAIL = "fail";

   private void init(){
      this.client = Client.create();
   }
   
   public static WebResource getTarget()
   {
	   WebResource webResource = client
			   .resource("http://localhost:9090/MyCarRestDemo/rest/CarService");
	   return webResource;
   }

   public static void main(String[] args){
      CarServiceTester tester = new CarServiceTester();
      //initialize the tester
      tester.init();
      //test get all Cars Web Service Method
      tester.testGetAllCars();
      //test get Car Web Service Method 
      tester.testGetCar();
      //test update Car Web Service Method
      tester.testUpdateCar();
      //test add Car Web Service Method
      tester.testAddCar();
      //test delete Car Web Service Method
      tester.testDeleteCar();
   }
   //Test: Get list of all Cars
   //Test: Check if list is not empty
   private void testGetAllCars(){
      GenericType<List<Car>> list = new GenericType<List<Car>>() {};
      List<Car> Cars = getTarget()
         .path("/list")
         .type(MediaType.APPLICATION_XML)
         .get(list);
      String result = PASS;
      if(Cars.isEmpty()){
         result = FAIL;
      }
      System.out.println("Test case name: testGetAllCars, Result: " + result );
   }
   //Test: Get Car of id 1
   //Test: Check if Car is same as sample Car
   private void testGetCar(){
      Car sampleCar = new Car();
      sampleCar.setId(700);

      Car Car = getTarget()
         .path("/car/1")
         .type(MediaType.APPLICATION_XML)
         .get(Car.class);
      String result = FAIL;
      if(sampleCar != null && sampleCar.getId() == Car.getId()){
         result = PASS;
      }
      System.out.println("Test case name: testGetCar, Result: " + result );
   }
   //Test: Update Car of id 1
   //Test: Check if result is success XML.
   private void testUpdateCar(){
      Form form = new Form();
      form.add("id", "1");
      form.add("model", "abc");
      form.add("color", "xyz");
      form.add("year", "2017");
      form.add("price", "45000");

      String callResult = getTarget()
         .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
         .post(String.class, form);
      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testUpdateCar, Result: " + result );
   }
   //Test: Add Car of id 2
   //Test: Check if result is success XML.
   private void testAddCar(){
      Form form = new Form();
      form.add("id", "2");
      form.add("model", "abc2");
      form.add("color", "xyz2");
      form.add("year", "20172");
      form.add("price", "450002");
      
      String callResult = getTarget()
         .type(MediaType.APPLICATION_XML)
         .post(String.class, form);
   
      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testAddCar, Result: " + result );
   }
   //Test: Delete Car of id 2
   //Test: Check if result is success XML.
   private void testDeleteCar(){
      String callResult = getTarget()
         .path("2")
         .type(MediaType.APPLICATION_XML)
         .delete(String.class);

      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testDeleteCar, Result: " + result );
   }
}