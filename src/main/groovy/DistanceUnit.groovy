

/**
 * Units used in race/event distances.
 * 
 * @author Ryan Padilla
 *
 */
enum DistanceUnit {
	MILLIMETER('1', 1000000L, 'mm'),
	CENTIMETER('2', 10000000L, 'cm'),
	METER('3', 1000000000L, 'm'),
	KILOMETER('4', 1000000000000L, 'km'),
	INCH('101', 25400000L, 'in'),
	FOOT('102', 304800000L, 'ft'),
	YARD('103', 914400000L, 'yd'),
	MILE('104', 1609344000000L, 'mi')
	
	/**
	 * Store the abbreviation of the unit used.
	 * @param id abbreviation of the unit used.
	 */
	private DistanceUnit(String id, Long nmPerUnit, String abr){
		this.id = Byte.parseByte(id)
		this.nmPerUnit = new BigDecimal(nmPerUnit)
		this.abr = abr
	}
	/**
	 * Abbreviation of the unit used
	 */
	final Byte id
	final BigDecimal nmPerUnit
	final String abr
	
	/**
	 * Gets the DistanceUnit associated with unit abbreviation passed in.
	 * @param id abbreviation of the unit used
	 * @return unit associated with the parameter, id
	 */
	static DistanceUnit byId(Byte id){
		values().find { it.id == id }
	}
}
