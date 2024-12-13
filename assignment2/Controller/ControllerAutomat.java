package assignment2.Controller;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import assignment2.Model.ModelAutomat;
import assignment2.View.ViewAutomat;
import processing.core.PApplet;

public class ControllerAutomat {

	private ModelAutomat model;
	private ViewAutomat view;
	
	
	public ControllerAutomat(ModelAutomat model,ViewAutomat view) {
		this.model = model;
		this.view = view;
	}
	
	
	public ModelAutomat createAutomat(PApplet app,float w,float h,float posx,float posy,int col,Boolean lastStatus, Boolean status) {
		model = new ModelAutomat(app,w,h,posx,posy,col,lastStatus,status);
		return model;
	}
	

	
	public ModelAutomat setInitialLivingStatus(Boolean lastStatus) {
		model.setInitialLivingStatus(lastStatus);
		return model;
	}
	
	
	public ModelAutomat setCurrentLivingStatus(Boolean status) {
		model.setCurrentLivingStatus(status);
		return model;
	}
	

	
	public ModelAutomat refineLivingStatus(Boolean status) {
		model.refineLivingStatus(status);
		return model;
	}
	
	
	public Boolean checkStatusAlive() {
		return model.getAutomatStatus();
	}
	
	
	public Boolean checkOldStatusAlive() {
		return model.getAutomatLastStatus();
	}
	
	public ModelAutomat[] getNeighbours(ModelAutomat[][] modelArr, int i, int j, int rows, int columns) {
		ModelAutomat[] neighbours = new ModelAutomat[model.getNeighbours(modelArr,i,j, rows, columns).size()];
		model.getNeighbours(modelArr,i,j, rows, columns).toArray(neighbours);
		return neighbours;
	}
	
	public int getAliveNeighbourCount(ModelAutomat[][] modelArr,int i, int j, int rows, int columns) {
		return model.aliveNeighbourStatus(modelArr, i, j, rows, columns);
		
	}
	
	public void checkNeighbour(ModelAutomat[][] modelArr,int i, int j, int rows, int columns) {
		model.neighbourStatus(modelArr, i ,j, rows, columns);
		
	}

	
	public void startEvolution(ModelAutomat[][] modelArr,Boolean[][] lastStatus, Boolean[][] status,ControllerAutomat[][] automat,int k, int l,int rows, int columns) {
		ModelAutomat[] neighbours = this.getNeighbours(modelArr,k,l, rows, columns);
		for(int x=0;x<neighbours.length;x++) {
			for(int i=0;i<rows;i++) {
				for(int j=0;j<columns;j++) {					
					automat[i][j].checkAround(i, j, modelArr, lastStatus, status, rows, columns);
				}
		
			}	
			
		}
		
	}
	
	public void checkAround(int i, int j, ModelAutomat[][] modelArr, Boolean[][] lastStatus, Boolean[][] status, int rows, int columns) {
		if(this.checkOldStatusAlive()) {					
			if((this.getAliveNeighbourCount(modelArr,i,j, rows, columns)<2) || (this.getAliveNeighbourCount(modelArr,i,j, rows, columns)>3)) {
				status[i][j] = false;
				this.setCurrentLivingStatus(status[i][j]);
				
			}		
			else if((this.getAliveNeighbourCount(modelArr,i,j, rows, columns)==2) || (this.getAliveNeighbourCount(modelArr,i,j, rows, columns)==3)) {
				status[i][j] = true;
				this.setCurrentLivingStatus(status[i][j]);

			}							
		}
		else {
			if(this.getAliveNeighbourCount(modelArr,i,j, rows, columns)==3) {
				status[i][j] = true;
				this.setCurrentLivingStatus(status[i][j]);

			}			
			else {
				status[i][j] = false;
				this.setCurrentLivingStatus(status[i][j]);

			}
		
		}
	}

	
}
