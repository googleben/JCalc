package com.ben.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class OpButton extends Button {
	
	public String o;
	public Operator op;
	public CalcUI c;
	
	public OpButton(String o, String text, CalcUI c) {
		this.o = o;
		this.op = new Operator(o);
		this.c = c;
		this.setText(text);
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (c.displayText.equals("0")) c.changeDisplay(true, "-");
				else {
					c.op = op;
					c.opOne = c.num();
					c.changeDisplay(true, 0);
				}
			}
		});
	}
	
}
