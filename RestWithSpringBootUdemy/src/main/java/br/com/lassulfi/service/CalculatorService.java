package br.com.lassulfi.service;

import org.springframework.stereotype.Service;

import br.com.lassulfi.exceptions.UnsuportedMathOperationException;
import br.com.lassulfi.utils.CalculatorUtils;

@Service
public class CalculatorService {

	public Double sum(String numberOne, String numberTwo) {
		
		if (!CalculatorUtils.isNumeric(numberOne) || !CalculatorUtils.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Invalid numeric type. Please, set a numeric value.");
		}
		
		Double sum = CalculatorUtils.convertToDouble(numberOne) + CalculatorUtils.convertToDouble(numberTwo);
		
		return sum;
	}
	
	public Double subtract(String numberOne, String numberTwo) {
		if (!CalculatorUtils.isNumeric(numberOne) || !CalculatorUtils.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Invalid numeric type. Please, set a numeric value.");
		}

		Double subtraction = CalculatorUtils.convertToDouble(numberOne) - CalculatorUtils.convertToDouble(numberTwo);

		return subtraction;
	}
	
	public Double multiplication(String numberOne, String numberTwo) {
		if (!CalculatorUtils.isNumeric(numberOne) || !CalculatorUtils.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Invalid numeric type. Please, set a numeric value.");
		}

		Double multiplication = CalculatorUtils.convertToDouble(numberOne) * CalculatorUtils.convertToDouble(numberTwo);

		return multiplication;
	}
	
	public Double division(String numberOne, String numberTwo) {
		if (!CalculatorUtils.isNumeric(numberOne) || !CalculatorUtils.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Invalid numeric type. Please, set a numeric value.");
		}
		
		if(CalculatorUtils.convertToDouble(numberTwo) == 0D) {
			throw new UnsuportedMathOperationException("Cannot divide by zero");
		}

		Double division = CalculatorUtils.convertToDouble(numberOne) / CalculatorUtils.convertToDouble(numberTwo);
		
		return division;
	}
	
	public Double average(String numberOne, String numberTwo) {
		if (!CalculatorUtils.isNumeric(numberOne) || !CalculatorUtils.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Invalid numeric type. Please, set a numeric value.");
		}
		
		Double avg = (CalculatorUtils.convertToDouble(numberTwo) + CalculatorUtils.convertToDouble(numberTwo)) / 2;
		
		return avg;
	}
	
	public Double squareRoot(String number) {
		if (!CalculatorUtils.isNumeric(number)) {
			throw new UnsuportedMathOperationException("Invalid numeric type. Please, set a numeric value.");
		}
		
		Double convertedNumber = CalculatorUtils.convertToDouble(number);
		
		return Math.sqrt(convertedNumber);
	}
}
