package ro.usv.rf.entities;

public class Distances
{
	private double euclidianDistance;
	private double cebasevDistance;
	private double cityBlockDistance;
	private double mahalanobisDistance;
	
	public double getEuclidianDistance() {
		return euclidianDistance;
	}
	public void setEuclidianDistance(double euclidianDistance) {
		this.euclidianDistance = euclidianDistance;
	}
	public double getCebasevDistance() {
		return cebasevDistance;
	}
	public void setCebasevDistance(double cebasevDistance) {
		this.cebasevDistance = cebasevDistance;
	}
	public double getCityBlockDistance() {
		return cityBlockDistance;
	}
	public void setCityBlockDistance(double cityBlockDistance) {
		this.cityBlockDistance = cityBlockDistance;
	}
	public double getMahalanobisDistance() {
		return mahalanobisDistance;
	}
	public void setMahalanobisDistance(double mahalanobisDistance) {
		this.mahalanobisDistance = mahalanobisDistance;
	}	
}
