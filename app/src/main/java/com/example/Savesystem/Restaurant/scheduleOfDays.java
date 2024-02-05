package Restaurant;

public class scheduleOfDays {

	private dayInterval Interval;
	private daysCycle cycle;

	public dayInterval getInterval() {
		return Interval;
	}

	/**
	 * 
	 * @param Interval
	 */
	public void setInterval(dayInterval Interval) {
		this.Interval = Interval;
	}

	public daysCycle getCycle() {
		return this.cycle;
	}

	/**
	 * 
	 * @param cycle
	 */
	public void setCycle(daysCycle cycle) {
		this.cycle = cycle;
	}

	/**
	 * 
	 * @param Interval
	 * @param cycle
	 */
	public scheduleOfDays(dayInterval Interval, daysCycle cycle) {
		this.Interval = Interval;
		this.cycle = cycle;
	}

}