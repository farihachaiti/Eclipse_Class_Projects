package assignment2.Model;

import processing.core.PApplet;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelAutomat {

	private PApplet app;
	private float w, h, posx, posy;
	private int col;
	private Boolean status, lastStatus;
	private List<ModelAutomat> aliveNeighbours = new ArrayList<ModelAutomat>();
	private List<ModelAutomat> allNeighbours = new ArrayList<ModelAutomat>();
	
	public ModelAutomat() {
		
	}
	
	public ModelAutomat(PApplet app,float w, float h, float posx, float posy, int col,Boolean status,Boolean lastStatus) {
		this.app = app;
		this.w  = w;
		this.h = h;
		this.posx = posx;
		this.posy = posy;
		this.col = col;
		this.status = status;
		this.lastStatus = lastStatus;
	}
	
	public void display() {
		app.fill(col);
		app.rect(posx,-posy,w,-h);
	}
	
	public void getAlive() {
		app.fill(Color.YELLOW.getRGB());
		app.rect(posx, -posy, w, -h);		
	}
	
	public void setInitialLivingStatus(Boolean lastStatus) {
		this.lastStatus = lastStatus;
	}
	
	public void setCurrentLivingStatus(Boolean status) {
		this.status = status;
	}
	
	public void refineLivingStatus(Boolean status) {
		this.lastStatus = status;
	}
	
	
	public void stayDead() {
		app.fill(Color.GRAY.getRGB());
		app.rect(posx, -posy, w, -h);
		
	}
	
	
	public Boolean getAutomatStatus() {
		return this.status;
	}
	
	public Boolean getAutomatLastStatus() {
		return this.lastStatus;
	}
	
	
	public Boolean isAlive() {
		if(this.lastStatus==true)
			return true;
		else
			return false;
	}
	
	public int aliveNeighbourCount() {
		return this.aliveNeighbours.size();
	}
	
	public void addAliveNeighbourList(ModelAutomat ifAliveNeighbour) {
		this.aliveNeighbours.add(ifAliveNeighbour);
	
	}
	
	public void addAllNeighbourList(ModelAutomat ifIsNeighbour) {
		this.allNeighbours.add(ifIsNeighbour);
	}
	
	public List<ModelAutomat> getNeighbours(ModelAutomat[][] modelArr, int i, int j, int rows, int columns){
		this.allNeighbours = this.neighbourStatus(modelArr,i,j, rows, columns);
		return this.allNeighbours;
	}
	
	public int neighbourCount() {
		return this.allNeighbours.size();
	}
	
	
	public List<ModelAutomat> checkAliveNeighbour(ModelAutomat[][] modelArr,int i, int j, int rows, int columns) {	
		ModelAutomat result = new ModelAutomat();
		for(int k=0;k<rows;k++) {
			for(int l=0;l<columns;l++) {
				if(k==i && l==j-1) {
					result = modelArr[i][j-1];
					this.addAllNeighbourList(result);
					if(result.isAlive()) 
						this.addAliveNeighbourList(result);
				}
								
				if ((k==(i-1)) && (l==(j-1))) {
					result = modelArr[i-1][j-1];
					this.addAllNeighbourList(result);
					if(result.isAlive()) 
						this.addAliveNeighbourList(result);
				}
				if(k==i-1 && l==j) {
					result = modelArr[i-1][j];
					this.addAllNeighbourList(result);
					if(result.isAlive()) 
						this.addAliveNeighbourList(result);
				}
				if(k==i-1 && l==j+1) 	{
					result = modelArr[i-1][j+1];
					this.addAllNeighbourList(result);
					if(result.isAlive()) 
						this.addAliveNeighbourList(result);
				}
				
				if(k==i && l==j+1) {
					result = modelArr[i][j+1];
					this.addAllNeighbourList(result);
					if(result.isAlive()) 
						this.addAliveNeighbourList(result);
				}
				
				if(k==i+1 && l==j+1) {
					result = modelArr[i+1][j+1];
					this.addAllNeighbourList(result);
					if(result.isAlive()) 
						this.addAliveNeighbourList(result);
				}	
								
				if(k==i+1 && l==j) {
					result = modelArr[i+1][j];
					this.addAllNeighbourList(result);
					if(result.isAlive()) 
						this.addAliveNeighbourList(result);
				}
				
				if(k==i+1 && l==j-1) {
					result = modelArr[i+1][j-1];
					this.addAllNeighbourList(result);
					if(result.isAlive()) 
						this.addAliveNeighbourList(result);
				}		
		
			}
		}
		return this.aliveNeighbours;
		
	}
	

	public List<ModelAutomat> checkNeighbour(ModelAutomat[][] modelArr,int i, int j, int rows, int columns) {	
		ModelAutomat result = new ModelAutomat();
		for(int k=0;k<rows;k++) {
			for(int l=0;l<columns;l++) {
				if(k==i && l==j-1) {
					result = modelArr[i][j-1];
					this.addAllNeighbourList(result);
				}							
				if ((k==(i-1)) && (l==(j-1))) {
					result = modelArr[i-1][j-1];
					this.addAllNeighbourList(result);
				}
				if(k==i-1 && l==j) {
					result = modelArr[i-1][j];
					this.addAllNeighbourList(result);
				}
				if(k==i-1 && l==j+1) 	{
					result = modelArr[i-1][j+1];
					this.addAllNeighbourList(result);
				}
				
				if(k==i && l==j+1) {
					result = modelArr[i][j+1];
					this.addAllNeighbourList(result);
				}
				
				if(k==i+1 && l==j+1) {
					result = modelArr[i+1][j+1];
					this.addAllNeighbourList(result);
				}	
								
				if(k==i+1 && l==j) {
					result = modelArr[i+1][j];
					this.addAllNeighbourList(result);
				}
				
				if(k==i+1 && l==j-1) {
					result = modelArr[i+1][j-1];
					this.addAllNeighbourList(result);
				}		
		
			}
		}

		return this.allNeighbours;
		
	}
	
	
	public List<ModelAutomat> neighbourStatus(ModelAutomat[][] modelArr,int i, int j, int rows, int columns) {
		this.allNeighbours.removeAll(allNeighbours);
		this.allNeighbours = this.checkNeighbour(modelArr,i,j, rows, columns);
		return this.allNeighbours;
	}
	
	
	
	public int aliveNeighbourStatus(ModelAutomat[][] modelArr,int i, int j, int rows, int columns) {
		this.aliveNeighbours.removeAll(aliveNeighbours);
		this.aliveNeighbours = this.checkAliveNeighbour(modelArr,i, j, rows, columns);	
		return this.aliveNeighbourCount();
	}
}
