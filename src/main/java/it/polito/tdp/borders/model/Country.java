package it.polito.tdp.borders.model;

public class Country implements Comparable<Country> {
	
	private String stateAbb;
	private Integer cCode;
	private String stateNme;
	private Integer statiConfinanti;
	
	public Country(String stateAbb, Integer cCode, String stateNme) {
		super();
		this.stateAbb = stateAbb;
		this.cCode = cCode;
		this.stateNme = stateNme;
		this.statiConfinanti = 0;
	}

	public String getStateAbb() {
		return stateAbb;
	}

	public Integer getcCode() {
		return cCode;
	}

	public String getStateNme() {
		return stateNme;
	}
	
	public Integer getStatiConfinanti() {
		return statiConfinanti;
	}

	public void setStatiConfinanti(Integer statiConfinanti) {
		this.statiConfinanti = statiConfinanti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cCode == null) ? 0 : cCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (cCode == null) {
			if (other.cCode != null)
				return false;
		} else if (!cCode.equals(other.cCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return stateNme;
	}


	@Override
	public int compareTo(Country o) {
		return this.stateNme.compareTo(o.stateNme);
	}
	
	
	
	
	
	

}
