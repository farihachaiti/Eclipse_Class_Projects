package assignment2.View;

import assignment2.Model.ModelAutomat;

public class ViewAutomat {

	
	public ViewAutomat() {
			
		}
	
	public void viewAutomat(ModelAutomat model) {
		model.display();
	
	}
	
	public void viewStayDead(ModelAutomat model) {
		model.stayDead();
	}
	
	public void viewGetAlive(ModelAutomat model) {
		model.getAlive();
	}
}
