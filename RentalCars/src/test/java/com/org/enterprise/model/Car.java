package com.org.enterprise.model;

public class Car {
	String make;
	String model;
	String vin;
	MetaData metadata;
	PerDayRent perdayrent;
	Rent rent;
	Metrics metrics;

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public MetaData getMetadata() {
		return metadata;
	}

	public void setMetadata(MetaData metadata) {
		this.metadata = metadata;
	}

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

	public Metrics getMetrics() {
		return metrics;
	}

	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}

	public PerDayRent getPerdayrent() {
		return perdayrent;
	}

	public void setPerdayrent(PerDayRent perdayrent) {
		this.perdayrent = perdayrent;
	}

}
