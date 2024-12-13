package assignment1;

import java.awt.Color;
import java.util.ArrayList;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import grafica.GLayer;
import grafica.GPlot;
import grafica.GPoint;
import grafica.GPointsArray;
import processing.core.PApplet;

public class FunctionPlotter1D {
	private Function functionToPlot;
	private double start;
	private double end;
	private int numberOfGridPoints=500;
	private GPointsArray points;
	private GPlot plot;
	private ArrayList<LineSegmentOption> additionalLines;
	private ArrayList<PointOption> additionalPoints;


	public FunctionPlotter1D(Function f, double a, double b) {
		this.functionToPlot = f;
		this.start = a;
		this.end = b;
		this.additionalLines = new ArrayList<LineSegmentOption>();
		this.additionalPoints = new ArrayList<PointOption>();
		
	}

	public void setNumberOfGridPoints(int numberOfGridPoints) {
			this.numberOfGridPoints = numberOfGridPoints;
	}

	// calculate the function points
	public void calcPoints() {
		this.points = new GPointsArray(numberOfGridPoints);

		// initialize points and stepwidth
		double dt = (this.end - this.start) / (this.numberOfGridPoints - 1);

		// save function name for expression
		String functionName = this.functionToPlot.getFunctionName();

		// calculate points
		for (int i = 0; i < this.numberOfGridPoints; i++) {
			double t = this.start + i * dt;
			String sExpression = functionName + "(" + Double.toString(t) + ")";
			Expression functionArgument = new Expression(sExpression, this.functionToPlot);
			
			double ft = functionArgument.calculate();
	

			this.points.add((float) t, (float) ft);
		}

		// this.firstView = true;
	}

	/**
	 * plot the function
	 * 
	 * @param app
	 *            Processing window
	 */
	public void plot(PApplet app) {
		String layerId = "layer1";
		if (this.plot == null) {
			this.plot = new GPlot(app);
			this.plot.setPos(25, 25);
			this.plot.setDim((float) app.width - 150, (float) app.height - 150);
			this.plot.setTitleText(this.functionToPlot.getFunctionName());
			this.plot.getXAxis().setAxisLabelText("t");
			this.plot.getYAxis().setAxisLabelText("P(t)");
			this.plot.addLayer(layerId, this.points);
			this.plot.activatePanning();
			this.plot.activateZooming();
		
		}

		GLayer firstLayer = this.plot.getLayer(layerId);
		firstLayer.setPoints(this.points);
		firstLayer.setPointSize(0f);

		this.plot.defaultDraw();

		// draw additional lines
		this.plot.beginDraw();
		for (LineSegmentOption pointPair : this.additionalLines) {
			this.plot.drawLine(pointPair.pointA, pointPair.pointB, pointPair.lineColor.getRGB(), 1f);
		}

		for (PointOption option : this.additionalPoints) {
			this.plot.drawPoint(option.pointPosition, option.pointColor.getRGB(), option.pointDiameter);
		}

		this.plot.drawHorizontalLine(0);
		this.plot.drawVerticalLine(0);
		this.plot.endDraw();
	}

	
	public float getPointX(int pos) {
		return this.points.getX(pos);
	}
	
	public float getPointY(int pos) {
		return this.points.getY(pos);
	}
	
	
	
	/**
	 * add an additional line to the plot
	 */
	public void addLine(GPoint pointA, GPoint pointB, Color lineColor) {
		LineSegmentOption option = new LineSegmentOption(pointA, pointB, lineColor);
		this.additionalLines.add(option);
	}

	/**
	 * remove all additional lines
	 */
	public void clearLines() {
		this.additionalLines.clear();
	}

	public void addPoint(GPoint pointPosition, Color pointColor, float pointDiameter) {
		PointOption option = new PointOption(pointPosition, pointColor, pointDiameter);
		this.additionalPoints.add(option);
	}

	public void clearPoints() {
		this.additionalPoints.clear();
	}

	/**
	 * nested class for the representation of line segment elements
	 * 
	 * @author ripo9018
	 *
	 */
	private class LineSegmentOption {
		private GPoint pointA;
		private GPoint pointB;
		private Color lineColor;

		public LineSegmentOption(GPoint pointA, GPoint pointB, Color lineColor) {
			this.pointA = pointA;
			this.pointB = pointB;
			this.lineColor = lineColor;
		}
	}

	private class PointOption {
		private GPoint pointPosition;
		private Color pointColor;
		private float pointDiameter;

		public PointOption(GPoint pointPosition, Color pointColor, float pointDiameter) {
			this.pointPosition = pointPosition;
			this.pointColor = pointColor;
			this.pointDiameter = pointDiameter;
		}
	}

}
