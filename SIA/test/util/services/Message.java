package util.services;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = -6974247512275958592L;
	
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String toString(){
		return text;
	}
}