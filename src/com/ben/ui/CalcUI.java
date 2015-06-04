package com.ben.ui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CalcUI extends Application {
	
	public static String BGColor;
	
	public static void main(String[] args) {
		launch();
		BGColor = args.length >= 1 ? args[0] : "#000";
	}
	
	public String displayText;
	public GridPane grid;
	public TextField scr;
	public Operator op;
	public double opOne = 0;
	public double opTwo = 0;
	public Timer t;
	
	public void start(Stage primaryStage) {
		
		displayText = "0";
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(2);
		grid.setVgap(10);
		grid.setPadding(new Insets(2,0,2,0));
		grid.setStyle("-fx-background-color:#fff;");
		//grid.setGridLinesVisible(true);
		
		scr = new TextField("0");
		scr.setAlignment(Pos.CENTER_RIGHT);
		/*scr.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				String s = scr.getText();
				changeDisplay(true, s);
				System.out.println(s);
			}
		});*/
		grid.add(scr,0,0);
		grid.setColumnSpan(scr,5);
		
		NumButton one = new NumButton(1, this);
		NumButton two = new NumButton(2, this);
		NumButton three = new NumButton(3, this);
		NumButton four = new NumButton(4, this);
		NumButton five = new NumButton(5, this);
		NumButton six = new NumButton(6, this);
		NumButton seven = new NumButton(7, this);
		NumButton eight = new NumButton(8, this);
		NumButton nine = new NumButton(9, this);
		NumButton zero = new NumButton(0, this);
		grid.add(one, 0, 1);
		grid.add(two, 1, 1);
		grid.add(three, 2, 1);
		grid.add(four, 0, 2);
		grid.add(five, 1, 2);
		grid.add(six, 2, 2);
		grid.add(seven, 0, 3);
		grid.add(eight, 1, 3);
		grid.add(nine, 2, 3);
		grid.add(zero, 0, 4); zero.setMinWidth(50); grid.setColumnSpan(zero, 2);
		
		OpButton add = new OpButton("add", "+", this);
		OpButton sub = new OpButton("sub", "-", this);
		OpButton mul = new OpButton("mul", "*", this);
		OpButton div = new OpButton("div", "/", this);
		grid.add(add, 3, 1);
		grid.add(sub, 3, 2); sub.setMinWidth(24);
		grid.add(mul, 3, 3); mul.setMinWidth(24);
		grid.add(div, 3, 4); div.setMinWidth(24);
		
		Button sqrt = new Button();
		sqrt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				changeDisplay(true, Math.sqrt(num()));
			}
		});
		sqrt.setText("√");
		grid.add(sqrt, 4, 3);
		Button sqr = new Button();
		sqr.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				changeDisplay(true, Math.pow(num(), 2));
			}
		});
		sqr.setText("x\u00B2");
		sqr.setFont(Font.font("Arial"));
		grid.add(sqr, 4, 2);
		
		Button eq = new Button();
		eq.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				doOp(); 
			}
		});
		grid.add(eq, 4, 4);
		eq.setText("=");
		Button cl = new Button();
		cl.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				clear(); 
			}
		});
		grid.add(cl, 4, 1);
		cl.setText("cl");
		Button dec = new Button();
		dec.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changeDisplay(false, ".");
			}
		});
		grid.add(dec, 2, 4);
		dec.setText("."); dec.setMinWidth(24);
		
		Scene scene = new Scene(grid,160,180);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Calculator");
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        	@Override
        	public void handle(WindowEvent e) {
        		System.exit(0);
        	}
        });
        
        t = new Timer();
        TimerTask tAction = new TimerTask() {
        	@Override
        	public void run() {
        		Platform.runLater(new Runnable() {
		            public void run() {
		                redraw();
		            }
		        });
        	}
        };
        t.schedule(tAction, 1, 100);
		
	}
	
	public void redraw() {
		scr.setText(displayText);
	}
	
	public void changeDisplay(boolean set, double n) {
		if (n%1.0<=0.00000001) changeDisplay(set, ""+(int)n);
		else if (set || displayText.equals("0")) displayText=""+n; 
		else displayText = displayText+n;
	}
	
	public void changeDisplay(boolean set, String s) {
		if (set || displayText.equals("0")) displayText=s; 
		else displayText = displayText+s;
		//System.out.println("set");
	}
	
	public double num() {
		return Double.parseDouble(displayText);
	}
	
	public void doOp() {
		double n = op.op(opOne, num());
		String s = ""+n;
		if (n%1.0==0) s=""+((int)n);
		displayText = s;
		opOne = 0;
	}
	
	public void clear() {
		displayText = "0";
		opOne = 0;
		opTwo = 0;
	}
	
	public void waitDraw(int time) {
		try {t.wait(time);} catch (InterruptedException e) {e.printStackTrace();}
	}
	
}
