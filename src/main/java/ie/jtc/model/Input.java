package ie.jtc.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class Input {

	private String outputFile;
	private String inputFile;
	private int connections;
	private int distanceKm;
	private String doctorId; 
	private float degreesToSearch;
	public String getInputFile() {
		return inputFile;
	}
	public void setInputFile(String fileName) {
		this.inputFile = fileName;
	}
	public String getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	public int getConnections() {
		return connections;
	}
	public void setConnections(int connections) {
		this.connections = connections;
	}
	public int getDistanceKm() {
		return distanceKm;
	}
	public void setDistanceKm(int distanceKm) {
		this.distanceKm = distanceKm;
	}
	public float getDegreesToSearch() {
		return degreesToSearch;
	}
	public void setDegreesToSearch(float degreesToSearch) {
		this.degreesToSearch = degreesToSearch;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

}
