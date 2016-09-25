package ie.jtc.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class Input {

	private String outputFile;
	private String inputFile;
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

}
