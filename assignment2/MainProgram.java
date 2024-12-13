package assignment2;

import peasy.PeasyCam;
import processing.core.PApplet;
import java.awt.Color;

import assignment2.Controller.ControllerAutomat;
import assignment2.Model.ModelAutomat;
import assignment2.View.ViewAutomat;

/*
 * Please put pattern = 0 for the example pattern or 1 to 3 for the patterns 1 to 3 of Tasks part.
 * Please put generation = 0 for the initial pattern and increase for the later evolutions so on.
 */
public class MainProgram extends PApplet {
	
	private PeasyCam cam;
	private static int rows;
	private static int columns;
	private static ControllerAutomat[][] automat;
	private static ViewAutomat[][] view;
	private static ModelAutomat[][] model;
	private static Boolean[][] status;
	private static Boolean[][] lastStatus;
	private static int generation = 25;
	private static int pattern = 3;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(MainProgram.class,args);
		if(pattern==0) {
			rows = 11;
			columns = 15;
		}
		else {
			rows = 40;
			columns = 40;
		}
		status = new Boolean[rows][columns];
		lastStatus = new Boolean[rows][columns];
		view = new ViewAutomat[rows][columns];	
		model = new ModelAutomat[rows][columns];
		automat = new ControllerAutomat[rows][columns];
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				view[i][j] = new ViewAutomat();	
				model[i][j] = new ModelAutomat();
				automat[i][j] = new ControllerAutomat(model[i][j], view[i][j]);	
			}
				
		}
	}
	
	
	public void settings() {
		this.size(800,600,P3D);
	}

	
	public void setup() {
		if(pattern == 0)
			cam = new PeasyCam(this,0,0,0,100);
		else
			cam = new PeasyCam(this,150,145,250,100);
		int posx = -85;
		int posy = 45;
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				int increment = 10;
				posx = posx+increment;
				lastStatus[i][j] = false;
				status[i][j] = false;
				model[i][j] = automat[i][j].createAutomat(this,10,10,posx,posy,Color.GRAY.getRGB(),lastStatus[i][j],status[i][j]);
			}
			posx = -85;
			posy = posy - 10;
		}
		
		if(pattern == 0) {			
		
			ControllerAutomat[] automatsToIterate = new ControllerAutomat[5];
			automatsToIterate[0] = automat[3][1];
			automatsToIterate[1] = automat[4][2];
			automatsToIterate[2] = automat[5][2];
			automatsToIterate[3] = automat[5][1];
			automatsToIterate[4] = automat[5][0];
			
			lastStatus[3][1] = true;
			automat[3][1].setInitialLivingStatus(lastStatus[3][1]);
			
			lastStatus[4][2] = true;
			automat[4][2].setInitialLivingStatus(lastStatus[4][2]);
			
			lastStatus[5][2] = true;
			automat[5][2].setInitialLivingStatus(lastStatus[5][2]);
			
			lastStatus[5][1] = true;
			automat[5][1].setInitialLivingStatus(lastStatus[5][1]);
			
			lastStatus[5][0] = true;
			automat[5][0].setInitialLivingStatus(lastStatus[5][0]);
			
			runEvolution(generation, automatsToIterate,5, 0);
		
		}
		else if (pattern == 1) {
			ControllerAutomat[] automatsToIterate = new ControllerAutomat[4];
		
			automatsToIterate[0] = automat[15][17];
			automatsToIterate[1] = automat[16][17];
			automatsToIterate[2] = automat[15][18];
			automatsToIterate[3] = automat[16][18];
			
			lastStatus[15][17] = true;
			automat[15][17].setInitialLivingStatus(lastStatus[15][17]);
			
			lastStatus[16][17] = true;
			automat[16][17].setInitialLivingStatus(lastStatus[16][17]);
			
			lastStatus[15][18] = true;
			automat[15][18].setInitialLivingStatus(lastStatus[15][18]);
			
			lastStatus[16][18] = true;
			automat[16][18].setInitialLivingStatus(lastStatus[16][18]);
			
			
			runEvolution(generation, automatsToIterate, 4, pattern);
		}
		else if (pattern == 2) {
			ControllerAutomat[] automatsToIterate = new ControllerAutomat[9];
			
			automatsToIterate[0] = automat[14][14];
			automatsToIterate[1] = automat[13][15];
			automatsToIterate[2] = automat[16][14];
			automatsToIterate[3] = automat[13][16];
			automatsToIterate[4] = automat[13][17];
			automatsToIterate[5] = automat[13][18];
			automatsToIterate[6] = automat[14][18];
			automatsToIterate[7] = automat[15][18];
			automatsToIterate[8] = automat[16][17];
			
			
			lastStatus[14][14] = true;
			automat[14][14].setInitialLivingStatus(lastStatus[14][14]);
			
			lastStatus[13][15] = true;
			automat[13][15].setInitialLivingStatus(lastStatus[13][15]);
		
			lastStatus[16][14] = true;
			automat[16][14].setInitialLivingStatus(lastStatus[16][14]);
			
			lastStatus[13][16] = true;
			automat[13][16].setInitialLivingStatus(lastStatus[13][16]);
			
			lastStatus[13][17] = true;
			automat[13][17].setInitialLivingStatus(lastStatus[13][17]);
			
			lastStatus[13][18] = true;
			automat[13][18].setInitialLivingStatus(lastStatus[13][18]);
			
			lastStatus[14][18] = true;
			automat[14][18].setInitialLivingStatus(lastStatus[14][18]);
			
			lastStatus[15][18] = true;
			automat[15][18].setInitialLivingStatus(lastStatus[15][18]);
			
			lastStatus[16][17] = true;
			automat[16][17].setInitialLivingStatus(lastStatus[16][17]);
			
			runEvolution(generation, automatsToIterate, 9, pattern);
		
		}
		else if (pattern == 3) {
			ControllerAutomat[] automatsToIterate = new ControllerAutomat[9];
			
			automatsToIterate[0] = automat[20][15];
			automatsToIterate[1] = automat[21][15];
			automatsToIterate[2] = automat[20][16];
			automatsToIterate[3] = automat[21][16];
			automatsToIterate[4] = automat[17][16];
			automatsToIterate[5] = automat[16][16];
			automatsToIterate[6] = automat[15][17];
			automatsToIterate[7] = automat[17][17];
			automatsToIterate[8] = automat[17][18];
			
			lastStatus[20][15] = true;
			automat[20][15].setInitialLivingStatus(lastStatus[20][15]);
			
			lastStatus[21][15] = true;
			automat[21][15].setInitialLivingStatus(lastStatus[21][15]);
			
			lastStatus[20][16] = true;
			automat[20][16].setInitialLivingStatus(lastStatus[20][16]);
			
			lastStatus[21][16] = true;
			automat[21][16].setInitialLivingStatus(lastStatus[21][16]);
			
			lastStatus[17][16] = true;
			automat[17][16].setInitialLivingStatus(lastStatus[17][16]);
			
			lastStatus[16][16] = true;
			automat[16][16].setInitialLivingStatus(lastStatus[16][16]);
			
			lastStatus[15][17] = true;
			automat[15][17].setInitialLivingStatus(lastStatus[15][17]);
			
			lastStatus[17][17] = true;
			automat[17][17].setInitialLivingStatus(lastStatus[17][17]);
			
			lastStatus[17][18] = true;
			automat[17][18].setInitialLivingStatus(lastStatus[17][18]);
			
			runEvolution(generation, automatsToIterate, 9, pattern);
		}
		
	}
	
	
	
	
	
	public void draw() {
		this.background(0);	
		int posx = -85;
		int posy = 45;
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				int increment = 10;
				posx = posx+increment;
				view[i][j].viewAutomat(model[i][j]);				
			}
			posx=-85;
			posy = posy - 10;
		}
		drawEvolution();
	}
	
	
	public void runEvolution(int generation, ControllerAutomat[] automatsToIterate, int indexes, int pattern ) {
		for(int iterator=0;iterator<generation;iterator++) {
			for(int x=0;x<indexes;x++) {
				for(int i=0;i<rows;i++) {
					for(int j=0;j<columns;j++) {
						if(automatsToIterate[x]==automat[i][j]) {
							automatsToIterate[x].startEvolution(model, lastStatus, status, automat,i,j, rows, columns);
						}
					}
				}
			}

			automatsToIterate = null;
			automatsToIterate = new ControllerAutomat[indexes];
			
			for(int i=0;i<rows;i++) {
				for(int j=0;j<columns;j++) {
					for(int x=0;x<indexes;x++) {
						if(automat[i][j].checkOldStatusAlive()) {
							automatsToIterate[x] = automat[i][j];
						}
					}
				}
			}
				
			for(int i=0;i<rows;i++) {
				for(int j=0;j<columns;j++) {
					lastStatus[i][j] = false;
					automat[i][j].refineLivingStatus(status[i][j]);
				}
			}

		}
	}
	
	
	public void drawEvolution() {
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				if(pattern==0) {
					if(generation==0) {
						if(lastStatus[i][j]!=null) {
							if(lastStatus[i][j]) 
								view[i][j].viewGetAlive(model[i][j]);
							else 
								view[i][j].viewStayDead(model[i][j]);					
						}
					}
					else {
						if(status[i][j]!=null) {
							if(status[i][j]) 
								view[i][j].viewGetAlive(model[i][j]);
							else 
								view[i][j].viewStayDead(model[i][j]);
						}
					}
						
				}
				else {
					if(generation==0) {
						if(lastStatus[i][j]!=null) {
							if(lastStatus[i][j]) 
								view[i][j].viewGetAlive(model[i][j]);
							else 
								view[i][j].viewStayDead(model[i][j]);	
						}
					}
				
					else {
						if(status[i][j]!=null) {
							if(status[i][j]) 
								view[i][j].viewGetAlive(model[i][j]);
							else 
								view[i][j].viewStayDead(model[i][j]);
							
						}
						
					}
				}
			}
		}	
	}
	
	
}
