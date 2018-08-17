package com.org.enterprise.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.org.enterprise.model.Car;
import com.org.enterprise.model.MetaData;
import com.org.enterprise.model.Metrics;
import com.org.enterprise.model.Perdayrent;
import com.org.enterprise.utility.CarsParser;

public class CarTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, JSONException {
		List<Car> cars = getCarDetails();
		/*
		 * Question 1: Print all the blue Teslas received in the web service response.
		 * Also print the notes
		 */
		System.out.println("All Blue Tesla Cars: ");
		for (Car car : cars) {
			MetaData metaData = car.getMetadata();
			if (car.getMake().equals("Tesla") && metaData.getColor().equals("Blue")) {
				System.out.println(metaData.getColor());
				System.out.println(metaData.getNotes());

			}

		}

		/*
		 * Question 2: Return all cars which have the lowest per day rental cost for
		 * both cases: a. Price only b. Price after discounts
		 */
		List<Car> lowestRentalCars = getLowestRentalCars();
		System.out.println("Cars with lowest per day cost:  ");
		for (Car car : lowestRentalCars) {
			System.out.println(car.getMake() + ", " + car.getModel());
		}

		/*
		 * Question 3: Find the highest revenue generating car. year over year
		 * maintenance cost + depreciation is the total expense per car for the full
		 * year for the rental car company. The objective is to find those cars that
		 * produced the highest profit in the last year
		 */

		List<Car> highestProfitCar = getHightestProfitCar();
		System.out.println("Car with highest profit");
		for (Car car : highestProfitCar)
			System.out.println(car.getMake() + ", " + car.getModel());

	}

	private static List<Car> getCarDetails()
			throws FileNotFoundException, com.fasterxml.jackson.core.JsonParseException,
			com.fasterxml.jackson.databind.JsonMappingException, IOException, JSONException {
		JsonParser parser = new JsonParser();

		FileReader fr = new FileReader("git/RentalCarsProject/RentalCars/src/test/resources/cars.json");
		JsonElement obj = parser.parse(fr);
		JSONObject jsonObject = new JSONObject(obj.toString());
		Object carsObject = jsonObject.get("Cars");

		CarsParser carParser = new CarsParser();
		List<Car> cars;
		cars = carParser.getCarDetails(carsObject.toString());
		return cars;
	}

	/*
	 * Question 2: Return all cars which have the lowest per day rental cost for
	 * both cases: a. Price only b. Price after discounts
	 */

	private static List<Car> getLowestRentalCars()
			throws JsonParseException, JsonMappingException, FileNotFoundException, IOException, JSONException {
		List<Car> cars = getCarDetails();
		Perdayrent perDayRent = new Perdayrent();
		Perdayrent perDayRent1 = new Perdayrent();
		float price = 0.0f;
		float discount = 0.0f;
		float lowestPrice = 0.0f;
		float price1 = 0.0f;
		float discount1 = 0.0f;
		float lowestPrice1 = 0.0f;
		List<Car> lowestPriceCars = new ArrayList<>();
		for (Car car : cars) {
			if (lowestPriceCars.isEmpty()) {
				lowestPriceCars.add(car);
			} else {
				perDayRent1 = lowestPriceCars.get(0).getPerdayrent();
				price1 = perDayRent1.getPrice();
				discount1 = perDayRent1.getDiscount();
				lowestPrice1 = price1 - (price1 * discount1 / 100);

				perDayRent = car.getPerdayrent();
				price = perDayRent.getPrice();
				discount = perDayRent.getDiscount();
				lowestPrice = price - (price * discount / 100);

				if (lowestPrice1 > lowestPrice) {
					lowestPriceCars.remove(0);
					lowestPriceCars.add(car);
				} else if (lowestPrice1 == lowestPrice) {
					lowestPriceCars.add(car);
				}
			}
		}
		return lowestPriceCars;
	}

	/*
	 * Question 3: Find the highest revenue generating car. year over year
	 * maintenance cost + depreciation is the total expense per car for the full
	 * year for the rental car company. The objective is to find those cars that
	 * produced the highest profit in the last year
	 */

	private static List<Car> getHightestProfitCar()
			throws JsonParseException, JsonMappingException, FileNotFoundException, IOException, JSONException {
		List<Car> cars = getCarDetails();
		Metrics metrics = new Metrics();
		Metrics metrics1 = new Metrics();
		float maintenanceCost = 0.0f;
		float depreciation = 0.0f;
		float expenses = 0.0f;
		float maintenanceCost1 = 0.0f;
		float depreciation1 = 0.0f;
		float expenses1 = 0.0f;
		List<Car> highestProfitCar = new ArrayList<>();
		for (Car car : cars) {
			if (highestProfitCar.isEmpty()) {
				highestProfitCar.add(car);
			} else {
				metrics1 = highestProfitCar.get(0).getMetrics();
				maintenanceCost1 = metrics1.getYoymaintenancecost();
				depreciation1 = metrics1.getDepreciation();
				expenses1 = maintenanceCost1 + depreciation1;

				metrics = car.getMetrics();
				maintenanceCost = metrics.getYoymaintenancecost();
				depreciation = metrics.getDepreciation();
				expenses = maintenanceCost + depreciation;

				if (expenses1 > expenses) {
					highestProfitCar.remove(0);
					highestProfitCar.add(car);
				}
			}
		}
		return highestProfitCar;
	}

}