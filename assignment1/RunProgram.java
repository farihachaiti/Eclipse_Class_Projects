package assignment1;

import org.mariuszgromada.math.mxparser.Function;

import processing.core.PApplet;

//This is the test class to plot the figures


public class RunProgram extends PApplet {
	
	private FunctionPlotter1D plotter;
	private Function myFunction;
	private double a1 = 124138/40000; //Matriculation Number = 124138
	private double t = 20d; //20 seconds
	private double f1 = 0.5; //2Hz, so f1=1/2 seconds
	private double actualResult = 620.69; //Analytical Integral by hand Result

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(RunProgram.class, args);
		
	}

	
	public void settings() {
		this.size(800,600,P3D);
	}
	
	
	public void setup() {
		myFunction = new Function("P(t)="+a1+"*t+sin(2*"+Math.PI+"*"+f1+"*t)");	
		plotter = new FunctionPlotter1D(myFunction, 0d, t);
		Algorithm algorithm = new Algorithm(200,plotter,myFunction); // Number of Grid Points= 10 grid points = 1 secs. so, 200 grid points as t = 20secs
		algorithm.calculateAlgorithm(1); //put any value between 0 to 3 as parameter. Other values will throw an exception!
		
		System.out.println("Actual Result: "+actualResult);
		System.out.println("Numerical Integration Result: "+myFunction.calculate()*10); //10 grid points = 1 secs
		System.out.println("Absolute Error: "+Math.round(this.getAbsoluteError()));
		System.out.println("Relative Error: "+Math.round(this.getRelativeError())+"%");
	}
	
	
	public void draw() {
		this.background(0);
		plotter.plot(this);
	}
	
	
	public double getAbsoluteError() {
		return (actualResult-myFunction.calculate()*10); //10 grid points = 1 secs
	}
	
	public double getRelativeError() {
		return ((actualResult-myFunction.calculate()*10)/actualResult)*100; //10 grid points = 1 secs
	}
	
}
