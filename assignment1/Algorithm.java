package assignment1;

import org.mariuszgromada.math.mxparser.Function;

//This class contains all 3 algorithms

import grafica.GPoint;

import java.awt.Color;

public class Algorithm{
	
	

	private Function myFunction;
	private FunctionPlotter1D plotter;
	private int numberOfGridPoints;
	
	
	public Algorithm(int  numberOfGridPoints, FunctionPlotter1D plotter, Function myFunction) {
		this.numberOfGridPoints = numberOfGridPoints;
		this.plotter = plotter;
		this.myFunction = myFunction;
		
	}
	
	
	public void calculateAlgorithm(int algorithm) {	 		
		plotter.setNumberOfGridPoints(numberOfGridPoints);
		try {
			this.checkAlgorithm(algorithm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public float getArea(float x) {
		return (float) this.myFunction.calculate(x);		
	}
	
	public float getMean(float y0,float y1) {
		return (y0+y1)/2;
	}
	
	public float getLinearInterpolation(float y0, float y1, int x, float x0, float x1 ) {
		return  y1+((x-x0)*((y1-y0)/(x1-x0)));
				
	}
	
	
	public void originalPlot() {
		plotter.calcPoints();
	}
	
	
	public void algorithmOne() {
		Integer[] x= new Integer[numberOfGridPoints];
		Float[] xFloat = new Float[numberOfGridPoints];
		Integer[] y = new Integer[numberOfGridPoints];
		
		for (int i = 0; i < numberOfGridPoints; i++) {
			x[i] = (int) plotter.getPointX(i);
			xFloat[i] = plotter.getPointX(i);
			y[i] = (int) this.getArea(x[i]);
			
			GPoint pointD = new GPoint(xFloat[i], y[i]);
			GPoint pointA = new GPoint(x[i],y[i]);
			GPoint pointB = new GPoint(xFloat[i], 0.0f);
			GPoint pointC = new GPoint(0,0);
			
			plotter.addLine(pointC, pointA, Color.RED);
			plotter.addLine(pointD, pointB, Color.BLUE);
		}
	}
	
	
	public void algorithmTwo() {
		Integer[] x= new Integer[numberOfGridPoints];
		Float[] xFloat = new Float[numberOfGridPoints];
		Integer[] y = new Integer[numberOfGridPoints];
		Float[] yMean = new Float[numberOfGridPoints];

		for (int i = 0; i < numberOfGridPoints; i++) {			
			x[i] = (int) plotter.getPointX(i);
			xFloat[i] = plotter.getPointX(i);
			y[i] = (int) this.getArea(x[i]);
		}
		
		yMean = sortYMeanArray(y);
	
		 for (int i = 0; i<numberOfGridPoints;i++) {
			GPoint pointD = new GPoint(xFloat[i], yMean[i]);
			GPoint pointB = new GPoint(xFloat[i], 0.0f);
			GPoint pointE = new GPoint(0,0);
			GPoint pointF = new GPoint(x[i],y[i]);
		
			plotter.addLine(pointE, pointF, Color.RED);
			plotter.addLine(pointB, pointD, Color.BLUE);	

		}	
	}
	
	
	public void algorithmThree() {
		Integer[] x = new Integer[numberOfGridPoints];
		Float[] xFloat = new Float[numberOfGridPoints];
		Integer[] y = new Integer[numberOfGridPoints];
		Integer[] Y = new Integer[numberOfGridPoints];
		for (int i = 0; i < numberOfGridPoints-1; i++) {
			x[i] = (int) plotter.getPointX(i);
			x[i+1] = (int) plotter.getPointX(i+1);
			xFloat[i] = plotter.getPointX(i);
			y[i] = (int) this.getArea(x[i]);
			y[i+1] = (int) this.getArea(x[i+1]);
			Y[i] = (int) this.getLinearInterpolation(y[i], y[i+1], i, x[i], x[i+1]);
			
			GPoint pointD = new GPoint(xFloat[i], Y[i]);
			GPoint pointA = new GPoint(x[i+1],Y[i]);
			GPoint pointB = new GPoint(xFloat[i], 0.0f);
			GPoint pointC = new GPoint(0,0);
			
			plotter.addLine(pointC, pointA, Color.RED);
			plotter.addLine(pointD, pointB, Color.BLUE);

		}
	}
	
	
	public Float[] sortYMeanArray(Integer[] y) {
		Float[] yMean = new Float[numberOfGridPoints];
		int counter = 10;
		int iterator = 0;		
			for (int i = 0; i<numberOfGridPoints;i++) {
					for(int j=0; j<counter-iterator;j++) {
						if(counter>=numberOfGridPoints)
							break;
						yMean[j+iterator] = this.getMean(y[counter-1],y[counter]);
						
					}
					iterator= counter;
					if(counter<=numberOfGridPoints)
					counter = counter + 10;
				}					

		
		  	for (int i = numberOfGridPoints-10; i<numberOfGridPoints;i++) {		
				yMean[i] = this.getMean(y[numberOfGridPoints-2],y[numberOfGridPoints-1]);
				}
		  	
		  	return yMean;
		  	
	}

	
	public void checkAlgorithm(int check) throws Exception {
		if (check == 0)
			this.originalPlot();
		else if(check==1) {
			plotter.calcPoints();
			this.algorithmOne();
			
		}
		
		else if(check ==2) {
			plotter.calcPoints();
			this.algorithmTwo();
			
		}
		else if(check == 3) {
			plotter.calcPoints();
			this.algorithmThree();
			
		}
		else {
			throw new Exception("OOps!!!:( \nPlease put 0 to plot the original function or between 1 to 3 to plot an algorithm!!!");
		}
			
	}
}
