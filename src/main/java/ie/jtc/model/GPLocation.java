package ie.jtc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GPLocation extends Location {


	@JsonProperty
	private String id;
	@JsonProperty
	private String name;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String toString(){
		return getId()+" "+getName();
	}
}
