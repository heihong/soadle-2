package fr.soat.soadle.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectToGeson {
	
	
	private static Gson gson = new GsonBuilder().create();
	
	public static String toGeson(Object object)
	{
		return gson.toJson(object);
	}

}
