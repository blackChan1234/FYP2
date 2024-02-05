package Restaurant;

import java.util.ArrayList;
import java.util.Stack;

public class schedule {

	private ArrayList<scheduleOfDays> Interval;
	
	public schedule() {
		Interval = new ArrayList<scheduleOfDays>();

	}
	
	public ArrayList<scheduleOfDays> getInterval() {
		return Interval;
	}

	/**
	 * 
	 * @param Interval
	 */
	public void setInterval(ArrayList<scheduleOfDays> Interval) {
		this.Interval = Interval;
	}


}