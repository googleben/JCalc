package com.ben.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class NumButton extends Button {
	
	public int n;
	
	public NumButton(int n, CalcUI c) {
		
		this.n = n;
		this.setText(""+n);
		
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				c.changeDisplay(false, n);
			}
		});
		
	}
	
}
