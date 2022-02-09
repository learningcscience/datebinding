

import grails.validation.Validateable
import org.apache.commons.lang.builder.HashCodeBuilder

//@Validateable

class Distance implements Serializable, Validateable{
	
	public Distance(){}
	
	public Distance(BigDecimal magnitude, DistanceUnit unit){
		this.set(magnitude, unit)
	}
	/**
	 * Magnitude of this distance in nanometers
	 */
	Long nanometers
	/**
	 * Unit the distance will be displayed in
	 */
	DistanceUnit unit
	
	static transients = ['magnitude']
	
	static constraints = {
		nanometers min: 0L
		unit()
	}

	static mapping = {
		unit enumType: 'string'
	}
	
	/**
	 * @return the distance in the stored units
	 */
	BigDecimal getMagnitude(){
		if (this.nanometers != null && this.unit != null){
			return (new BigDecimal(this.nanometers) / unit.nmPerUnit)
		}
		else return null
	}
	
	void set(String magnitude, String unit) throws NumberFormatException, IllegalArgumentException{
		this.set(new BigDecimal(magnitude), DistanceUnit.valueOf(unit))
	}
	
	/**
	 * @param magnitude magnitude of this distance in the passed in units
	 * @param unit units of the passed in magnitude
	 */
	void set(BigDecimal magnitude, DistanceUnit unit){
		if (magnitude == null || unit == null) return
		if ((magnitude * new BigDecimal(unit.nmPerUnit)) > new BigDecimal(Long.MAX_VALUE)){
			throw new IllegalArgumentException('Number overflow detected. The distance will be incorrect.')
		}
		this.unit = unit
		this.nanometers = (magnitude * new BigDecimal(this.unit.nmPerUnit)).longValue()
	}
	
	boolean equals(Object other){
		if (!Distance.isInstance(other)) return false
		
		Distance o = (Distance) other
		return (this.nanometers == o.nanometers && this.unit == o.unit)
	}
	
	int hashCode(){
		HashCodeBuilder builder = new HashCodeBuilder(123073,44231)
		if (this.nanometers != null) builder.append(this.nanometers)
		if (this.unit) builder.append(this.unit)
		return builder.toHashCode()
	}
	
	public String toString(){
		"${this.getMagnitude()} ${this.unit}"
	}
}
