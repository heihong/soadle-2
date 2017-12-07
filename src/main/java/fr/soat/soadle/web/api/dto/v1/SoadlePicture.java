package fr.soat.soadle.web.api.dto.v1;

public class SoadlePicture {
	
	private Long id;

	private String url;

	private String mettnigId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMettnigId() {
		return mettnigId;
	}

	public void setMettnigId(String mettnigId) {
		this.mettnigId = mettnigId;
	}
	

}
