package fr.soat.soadle.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author hakim
 *
 */
@Entity
public class Participant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3308838324770542811L;

	@Id
	private String id;

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

}
