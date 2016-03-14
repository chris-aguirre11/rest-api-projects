package travelprints.persistence.objects;

import java.io.Serializable;

public class State implements Serializable {
	
	private String stateName, countryName;

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
