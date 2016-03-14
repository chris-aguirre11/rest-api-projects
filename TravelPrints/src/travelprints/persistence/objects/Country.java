package travelprints.persistence.objects;

import java.io.Serializable;

public class Country implements Serializable {
	
	private String countryName;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
