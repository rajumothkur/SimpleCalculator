package com.calc;

import java.math.BigDecimal;

public class SimpleCalculator {

	private static SimpleCalculator calculator;
	private BigDecimal result = new BigDecimal(0);

	private SimpleCalculator() {
	}

	public static SimpleCalculator getInstance() {
		if (calculator == null) {
			calculator = new SimpleCalculator();
		}
		return calculator;
	}

	/**
	 * This method calculate current value for any number of calculation
	 * operations. Currently following operations are supported +,-,*,/
	 * 
	 * @param operator
	 * 
	 * @param leftOperand
	 * 
	 * @param rightOperand
	 * 
	 */
	public void operation(char operator, BigDecimal leftOperand, BigDecimal rightOperand) {
		switch (operator) {
		case '+':
			result = leftOperand.add(rightOperand);
			break;
		case '-':
			result = rightOperand.subtract(leftOperand);
			break;
		case '/':
			result = rightOperand.divide(leftOperand);
			break;
		case '*':
			result = leftOperand.multiply(rightOperand);
			break;
		default:
			break;
		}
	}

	public BigDecimal getResult() {
		return result;
	}

	public void setResult(BigDecimal result) {
		this.result = result;
	}
}