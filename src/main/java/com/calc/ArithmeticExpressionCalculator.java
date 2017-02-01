package com.calc;

import java.math.BigDecimal;

public class ArithmeticExpressionCalculator {

	private char operator;
	private BigDecimal leftOperand;
	private BigDecimal rightOperand;
	private SimpleCalculator calculator;

	public ArithmeticExpressionCalculator(char operator, BigDecimal leftOperand, BigDecimal rightOperand,
			SimpleCalculator calculator) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operator = operator;
		this.calculator = calculator;
	}

	/**
	 * This method invoke the three argument operation method that is only used
	 * for arithmetic calculations.
	 * 
	 * @param operator
	 * 
	 * @param leftOperand
	 * 
	 * @param rightOperand
	 */
	public void calculate() {
		calculator.operation(operator, leftOperand, rightOperand);
	}
}