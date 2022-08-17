package assignment1;

import org.mariuszgromada.math.mxparser.Function;

import grafica.GPoint;

import java.awt.Color;

//import grafica.GPlot;
import processing.core.PApplet;

public class RunProgram extends PApplet{
	
	private int numberOfGridPoints;


	Function myFunction;
	FunctionPlotter1D plotter;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(RunProgram.class, args);
	}
	
	public void settings() {
		this.size(800,600,P3D);
	}
	
	public void setup() {
		double a1 = 124138/40000; // Matriculation Number = 124138
		double f1 = 0.5; //2Hz, so f1=1/2 seconds
		myFunction = new Function("P(t)="+a1+"*t+sin(2*"+Math.PI+"*"+f1+"*t)");
		double t = 20d;
		numberOfGridPoints = 200;
		plotter = new FunctionPlotter1D(myFunction, 0d, t);
		plotter.setNumberOfGridPoints(numberOfGridPoints);
		plotter.calcPoints();
		this.checkAlgorithm(0);   //Put value 0 for the original function P(t) Or,
								  //Put value 1 to 3 for any of the 3 algorithms

		
	}

	public float setArea(float tx0_float) {
		return (float) this.myFunction.calculate(tx0_float);		
	}
	
	public float getMean(float ty0,float ty1) {
		return (ty0+ty1)/2;
	}
	
	public float getLinearInterpolation(float y0, float y1, int x, float x0, float x1 ) {
		return  y1+((x-x0)*((y1-y0)/(x1-x0)));
				
	}
	
	public void checkAlgorithm(int check) {
		if(check==1) {
			for (int i = 0; i < numberOfGridPoints; i++) {
				int tx0 = (int) plotter.getPointX(i);
				int ty0 = (int) plotter.getPointY(i);
				float tx0_float = plotter.getPointX(i);
				float ty0_float = plotter.getPointY(i);
				int y0 = (int) this.setArea(tx0);
				
				GPoint pointD = new GPoint(tx0_float, y0);
				GPoint pointA = new GPoint(tx0,y0);
				GPoint pointB = new GPoint(tx0_float, 0.0f);
				GPoint pointC = new GPoint(0,0);
				
			//	plotter.addPoint(pointA, Color.RED, 5.0f);
				plotter.addLine(pointC, pointA, Color.RED);
				plotter.addLine(pointD, pointB, Color.BLUE);
				System.out.println(tx0+","+ty0+","+y0);
			}
			
		}
		
		else if(check ==2) {

			Integer[] tx= new Integer[numberOfGridPoints];
			Integer[] ty = new Integer[numberOfGridPoints];
			Float[] tx_float = new Float[numberOfGridPoints];
			Float[] ty_float = new Float[numberOfGridPoints];
			Integer[] y = new Integer[numberOfGridPoints];
			Float[] yMean = new Float[numberOfGridPoints];
	
			for (int i = 0; i < numberOfGridPoints; i++) {			
				tx[i] = (int) plotter.getPointX(i);
				ty[i] = (int) plotter.getPointY(i);
				tx_float[i] = plotter.getPointX(i);
				ty_float[i] = plotter.getPointY(i);
				y[i] = (int) this.setArea(tx[i]);
			}
			
			
			int counter = 10;
			int iterator = 0;		
				for (int i = 0; i<numberOfGridPoints;i++) {
						for(int j=0; j<counter-iterator;j++) {
							if(counter>=numberOfGridPoints)
								break;
							System.out.println("i"+j);
							System.out.println("c"+counter);
							System.out.println("i"+iterator);
							System.out.println(j+iterator);
							yMean[j+iterator] = this.getMean(y[counter-1],y[counter]);
							
						}
						iterator= counter;
						if(counter<=numberOfGridPoints)
						counter = counter + 10;
					}					

			
			  	for (int i = numberOfGridPoints-10; i<numberOfGridPoints;i++) {		
					yMean[i] = this.getMean(y[numberOfGridPoints-2],y[numberOfGridPoints-1]);
						System.out.println(i+","+yMean[i]);
					}
			  	
		
			 for (int i = 0; i<numberOfGridPoints;i++) {
				GPoint pointD = new GPoint(tx_float[i], yMean[i]);
				GPoint pointA = new GPoint(tx[i],yMean[i]);
				GPoint pointB = new GPoint(tx_float[i], 0.0f);
				GPoint pointC = new GPoint(tx[i],yMean[i]);
				GPoint pointE = new GPoint(0,0);
				GPoint pointF = new GPoint(tx[i],y[i]);
			
			//	plotter.addPoint(pointA, Color.RED, 5.0f);
				plotter.addLine(pointE, pointF, Color.RED);
			//	plotter.addLine(pointA, pointC, Color.BLUE);
				plotter.addLine(pointB, pointD, Color.BLUE);	
	
			}	
			
		}
		else if(check == 3) {
			for (int i = 0; i < numberOfGridPoints-1; i++) {
				int x0 = (int) plotter.getPointX(i);
				//int ty0 = (int) plotter.getPointY(i);
				int x1 = (int) plotter.getPointX(i+1);
			//	int ty1 = (int) plotter.getPointY(i+1);
				float x0_float = plotter.getPointX(i);
				float y0_float = plotter.getPointY(i);
				int y0 = (int) this.setArea(x0);
				int y1 = (int) this.setArea(x1);
				int y = (int) this.getLinearInterpolation(y0, y1, i, x0, x1);
				
				GPoint pointD = new GPoint(x0_float, y);
				GPoint pointA = new GPoint(x1,y);
				GPoint pointB = new GPoint(x0_float, 0.0f);
				GPoint pointC = new GPoint(0,0);
				
			//	plotter.addPoint(pointA, Color.RED, 5.0f);
				plotter.addLine(pointC, pointA, Color.RED);
				plotter.addLine(pointD, pointB, Color.BLUE);
	//			System.out.println(tx0+","+ty0+","+y0);
			}
			
		}
		else {
			check = 0;
		}
			
	}

	public void draw() {
		this.background(0);
		plotter.plot(this);
	}
}
