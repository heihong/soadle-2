package fr.soat.soadle.model;

public enum EnumOrigine {

	SOADLE  ("S"),
	DOODLE ("D")
	;
		   
	private String origine = "";
		   
	EnumOrigine(String origine){
	    this.origine = origine;
	 }
		   
	 public String toString(){
	    return origine;
	 }
}
