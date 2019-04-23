package ro.usv.rf;

public class Distance implements Comparable<Distance>{
	public Distance(double distance, int position) {
		this.distance = distance;
		this.position = position;
	}
	public double distance;
	public int position;
	
	@Override
	public int compareTo(Distance o) {
		if(this.distance > o.distance) return 1;
		else if(this.distance < o.distance) return -1;
		return 1;
	}
}
