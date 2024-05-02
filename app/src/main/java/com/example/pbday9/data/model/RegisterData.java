package com.example.pbday9.data.model;

import com.google.gson.annotations.SerializedName;

public class RegisterData {

	@SerializedName("name")
	private String name;

	@SerializedName("username")
	private String username;

	public String getName(){
		return name;
	}

	public String getUsername(){
		return username;
	}
}