package com.bus.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class RoundCarVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2141979242841210020L;
	
	private String id;
	
	private String name ;

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
	

}
