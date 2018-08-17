package com.org.enterprise.utility;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.enterprise.model.Car;

public class CarsParser {
	ObjectMapper objectMapper = new ObjectMapper();

	// maps json attr value
	public List<Car> getCarDetails(String json) throws JsonParseException, JsonMappingException, IOException {
		// json=jsonArray.getString(i);

		TypeReference<List<Car>> mapType = new TypeReference<List<Car>>() {
		};
		JsonNode rootNode = objectMapper.readTree(json);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<Car> cars = objectMapper.readValue(rootNode.toString(), mapType);

		// System.out.println("Gtin from Response" + itemValue.getGtin());
		return cars;
	}

}
