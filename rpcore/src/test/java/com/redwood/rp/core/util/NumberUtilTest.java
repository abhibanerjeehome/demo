package com.redwood.rp.core.util;

/**
 *=====================================================================
 * ACP Number Utility Test Suite 
 * 
 *
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.redwood.rp.core.util.NumberUtil;
import com.redwood.rp.core.util.NumberUtil.TemperatureUnit;

public class NumberUtilTest {

	@Test
	public void testIsNullIDWithNull() {
		assertEquals(true, NumberUtil.isNullID(null));
	}
	
	@Test
	public void testIsNullIDWithNegativeValue() {
		assertEquals(true, NumberUtil.isNullID(new Long(-1)));
	}

	@Test
	public void testIsNullIDWithValidValue() {
		assertEquals(false, NumberUtil.isNullID(new Long(1)));
	}

	@Test
	public void testIsNotNullIDWithNull() {
		assertEquals(false, NumberUtil.isNotNullID(null));
	}
	
	@Test
	public void testIsNotNullIDWithValidValue() {
		assertEquals(true, NumberUtil.isNotNullID(new Long(1)));
	}

	@Test
	public void testIsNullVersionWithNull() {
		assertEquals(true, NumberUtil.isNullVersion(null));
	}
	
	@Test
	public void testIsNullVersionWithNegativeValue() {
		assertEquals(true, NumberUtil.isNullVersion(new Integer(-1)));
	}
	
	@Test
	public void testIsNullVersionWithValidValue() {
		assertEquals(false, NumberUtil.isNullVersion(new Integer(1)));
	}

	@Test
	public void testIsNotNullVersionWithNull() {
		assertEquals(false, NumberUtil.isNotNullVersion(null));
	}
	
	@Test
	public void testIsNotNullVersionWithValidValue() {
		assertEquals(true, NumberUtil.isNotNullVersion(new Integer(1)));
	}

	@Test
	public void testCheckNullWithNullInput() {
		BigDecimal output = NumberUtil.checkNull(null);
		assertEquals(new BigDecimal(0), output);
	}
	
	@Test
	public void testCheckNullWithValidInput() {
		BigDecimal input = new BigDecimal(1);
		BigDecimal output = NumberUtil.checkNull(input);
		assertEquals(input, output);
	}

	@Test
	public void testConvertDoubleWithNullInput() {
		Double output = NumberUtil.convertDouble(null);
		assertEquals(new Double(0.0), output);
	}
	
	@Test
	public void testConvertDoubleWithValidInput() {
		BigDecimal input = new BigDecimal(1);
		Double output = NumberUtil.convertDouble(input);
		assertEquals(new Double(input.doubleValue()), output);
	}

	@Test
	public void testConvertIntegerFromLongWithValidInput() {
		Long input = new Long(1);
		Integer output = NumberUtil.convertInteger(input);
		assertEquals(input.intValue(), output.intValue());
	}

	@Test
	public void testConvertIntegerFromBigDecimalWithValidInput() {
		BigDecimal input = new BigDecimal(1);
		Integer output = NumberUtil.convertInteger(input);
		assertEquals(input.intValue(), output.intValue());
	}

	@Test
	public void testConvertBigDecmalFromDoubleWithValidInput() {
		Double input = new Double(3.00);
		BigDecimal output = NumberUtil.convertBigDecmal(input);
		assertEquals(input, new Double(output.doubleValue()));
	}

	@Test
	public void testConvertBigDecmalFromIntegerWithValidInput() {
		Integer input = new Integer(12);
		BigDecimal output = NumberUtil.convertBigDecmal(input);
		assertEquals(input.intValue(), output.intValue());
	}

	@Test
	public void testConvertBigDecmalFromStringWithInvalidInput() {
		BigDecimal output = NumberUtil.convertBigDecmal("ABC");
		assertEquals(new BigDecimal(0), output);
	}
	
	@Test
	public void testConvertBigDecmalFromStringWithValidInput() {
		String input = "123";
		BigDecimal output = NumberUtil.convertBigDecmal(input);
		assertEquals(Integer.parseInt(input), output.intValue());
	}

	@Test
	public void testConvertTemperatureWithNullSourceAndDestUnits() {
		double input = 56.34;
		double output = NumberUtil.convertTemperature(input, null, null);
		assertEquals(new Double(input), new Double(output));
	}
	
	@Test
	public void testConvertTemperatureFromKelvinToRankine() {
		double input = 45.00;
		double output = NumberUtil.convertTemperature(input, TemperatureUnit.Kelvin, TemperatureUnit.Rankine);
		assertEquals(new Double(81.0), new Double(output));
	}
	
	@Test
	public void testConvertTemperatureFromKelvinToFahrenheit() {
		double input = 45.00;
		double output = NumberUtil.convertTemperature(input, TemperatureUnit.Kelvin, TemperatureUnit.Fahrenheit);
		assertEquals(new Double(-378.7), new Double(output));
	}
	
	@Test
	public void testConvertTemperatureFromKelvinToCelsius() {
		double input = 45.00;
		double output = NumberUtil.convertTemperature(input, TemperatureUnit.Kelvin, TemperatureUnit.Celsius);
		assertEquals(new Double(-228.1), new Double(output));
	}
	
	@Test
	public void testConvertTemperatureFromFahrenheitToKelvin() {
		double input = 45.00;
		double output = NumberUtil.convertTemperature(input, TemperatureUnit.Fahrenheit, TemperatureUnit.Kelvin);
		assertEquals(new Double(280.4), new Double(output));
	}
	
	@Test
	public void testConvertTemperatureFromFahrenheitToRankine() {
		double input = 45.00;
		double output = NumberUtil.convertTemperature(input, TemperatureUnit.Fahrenheit, TemperatureUnit.Rankine);
		assertEquals(new Double(504.7), new Double(output));
	}
	
	@Test
	public void testConvertTemperatureFromFahrenheitToCelsius() {
		double input = 45.00;
		double output = NumberUtil.convertTemperature(input, TemperatureUnit.Fahrenheit, TemperatureUnit.Celsius);
		assertEquals(new Double(7.2), new Double(output));
	}
	
	@Test
	public void testConvertTemperatureFromRankineToKelvin() {
		double input = 45.00;
		double output = NumberUtil.convertTemperature(input, TemperatureUnit.Rankine, TemperatureUnit.Kelvin);
		assertEquals(new Double(25.0), new Double(output));
	}
	
	@Test
	public void testConvertTemperatureFromRankineToFahrenheit() {
		double input = 45.00;
		double output = NumberUtil.convertTemperature(input, TemperatureUnit.Rankine, TemperatureUnit.Fahrenheit);
		assertEquals(new Double(-414.7), new Double(output));
	}
	
	@Test
	public void testConvertTemperatureFromRankineToCelsius() {
		double input = 45.00;
		double output = NumberUtil.convertTemperature(input, TemperatureUnit.Rankine, TemperatureUnit.Celsius);
		assertEquals(new Double(-248.1), new Double(output));
	}
	
	@Test
	public void testConvertTemperatureFromCelsiusToKelvin() {
		double input = 45.00;
		double output = NumberUtil.convertTemperature(input, TemperatureUnit.Celsius, TemperatureUnit.Kelvin);
		assertEquals(new Double(318.2), new Double(output));
	}
	
	@Test
	public void testConvertTemperatureFromCelsiusToRankine() {
		double input = 45.00;
		double output = NumberUtil.convertTemperature(input, TemperatureUnit.Celsius, TemperatureUnit.Rankine);
		assertEquals(new Double(572.7), new Double(output));
	}
	
	@Test
	public void testConvertTemperatureFromCelsiusToFahrenheit() {
		double input = 45.00;
		double output = NumberUtil.convertTemperature(input, TemperatureUnit.Celsius, TemperatureUnit.Fahrenheit);
		assertEquals(new Double(113.0), new Double(output));
	}

	@Test
	public void testParseStringToLongWithNullInput() {
		assertEquals(new Long(0), NumberUtil.parseStringToLong(null));
	}
	
	@Test
	public void testParseStringToLongWithEmptyString() {
		assertEquals(new Long(0), NumberUtil.parseStringToLong(""));
	}

	@Test
	public void testParseStringToLongWithInvalidInput() {
		assertEquals(new Long(0), NumberUtil.parseStringToLong("ABC"));
	}
	
	@Test
	public void testParseStringToLongWithValidInput() {
		String input = "123";
		assertEquals(Long.parseLong(input), NumberUtil.parseStringToLong(input).longValue());
	}
}
