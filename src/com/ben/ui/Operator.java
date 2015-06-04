package com.ben.ui;

public class Operator {
	
	public String o;
	
	public Operator(String o) {
		this.o = o;
	}
	
	public double op(double op1, double op2) {
		switch(o) {
			case "add": return op1+op2;
			case "sub": return op1-op2;
			case "mul": return op1*op2;
			case "div": return op1/op2;
		}
		return 0;
	}
	
}
