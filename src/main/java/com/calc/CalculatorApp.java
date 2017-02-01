package com.calc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class CalculatorApp {

	public static void main(String a[]) {
		new CalculatorApp().start();
	}

	/**
	 * This is the starting point of the program. It receives input from the
	 * command line and process them further and sends to calculate function. At
	 * the end this method displays the calculated result.
	 */
	public void start() {
		Scanner scanner = new Scanner(System.in);

		
		System.out.println("   Please enter values to calculate :");
		

		String line = scanner.nextLine();

		
		System.out.println("   Entered Value : " + line);
		

		List<String> postfixString = CalculatorApp.convertInfixToPostfix(line);
		SimpleCalculator calculator = SimpleCalculator.getInstance();
		calculator.setResult(new BigDecimal(0));

		calculate(postfixString, calculator);

		System.out.println("***");
		System.out.println("   Result is " + calculator.getResult());
		System.out.println("***");
		scanner.close();
	}

	/**
	 * This method keeps a stack to process post-fix version of the input
	 * 
	 * @param Cal
	 * 
	 * @param postFix
	 */
	public void calculate(List<String> postFix, SimpleCalculator cal) {

		Stack<BigDecimal> stack = new Stack<BigDecimal>();

		for (int i = 0; i < postFix.size(); i++) {

			String next = postFix.get(i);

			if (next.equals("+") || next.equals("-") || next.equals("*") || next.equals("/")) {
				ArithmeticExpressionCalculator cmd = new ArithmeticExpressionCalculator(next.charAt(0), stack.pop(), stack.pop(), cal);
				cmd.calculate();
				stack.push(cal.getResult());
			} else {
				stack.push(new BigDecimal(next.trim()));
			}
		}
	}

	/**
	 * Infix to post-fix conversion for calculation.
	 * 
	 * @param input
	 */
	public static List<String> convertInfixToPostfix(String input) {

		int priority = 0;
		String postfixBuffer = "";
		Stack<Character> stack = new Stack<Character>();
		List<String> postfixArray = new ArrayList<String>();

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

				if (postfixBuffer.length() > 0) {
					postfixArray.add(postfixBuffer);
				}
				postfixBuffer = "";
				// check the precedence
				if (stack.size() <= 0)
					stack.push(ch);
				else {
					Character chTop = (Character) stack.peek();
					if (chTop == '*' || chTop == '/')
						priority = 1;
					else
						priority = 0;
					if (priority == 1) {
						if (ch == '+' || ch == '-') {
							postfixArray.add(String.valueOf(stack.pop()));
							i--;
						} else {
							postfixArray.add(String.valueOf(stack.pop()));
							i--;
						}
					} else {
						if (ch == '+' || ch == '-') {
							postfixArray.add(String.valueOf(stack.pop()));
							stack.push(ch);
						} else
							stack.push(ch);
					}
				}
			} else {
				postfixBuffer += ch;
			}
		}

		postfixArray.add(postfixBuffer);
		int len = stack.size();
		for (int j = 0; j < len; j++)
			postfixArray.add(stack.pop().toString());
		return postfixArray;
	}
}