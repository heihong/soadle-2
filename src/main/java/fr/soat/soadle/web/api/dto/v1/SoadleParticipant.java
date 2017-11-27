package fr.soat.soadle.web.api.dto.v1;

/**
 * @author hakim
 *
 */
public class SoadleParticipant implements Comparable<SoadleParticipant> {
	
	private String id;

	private String name;
	
	private Boolean preference;

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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoadleParticipant other = (SoadleParticipant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Boolean getPreference() {
		return preference;
	}

	public void setPreference(Boolean preference) {
		this.preference = preference;
	}

	@Override
	public int compareTo(SoadleParticipant arg0) {
		if(arg0 == null) return -1;
		
		int compare = this.name.compareTo(arg0.getName());
		
		if(compare != 0)  return compare;
		
		if(this.id == null && arg0.getId() != null)
		{
			return 1;
		} else if(this.id != null && arg0.getId() == null)
		{
			return -1;
		} else if(this.id == null && arg0.getId() == null)
		{
			return 0;
		} 
		
		return this.id.compareTo(arg0.getId());		
	}
	
	

}
