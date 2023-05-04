package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Planet {
	
	private int id;
	
	private String name;
	
	private double mass;
	
	private List<Moon> moonList = new ArrayList<>();
	
	
	
	public Planet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Planet(int id, String name, double mass) {
		super();
		this.id = id;
		this.name = name;
		this.mass = mass;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public List<Moon> getMoonList() {
		return moonList;
	}
	public void setMoonList(List<Moon> moonList) {
		this.moonList = moonList;
	}
	
	public void addMoon(Moon m) {
		this.moonList.add(m);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, mass, moonList, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		return id == other.id && Double.doubleToLongBits(mass) == Double.doubleToLongBits(other.mass)
				&& Objects.equals(moonList, other.moonList) && Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return "Planet [id=" + id + ", name=" + name + ", mass=" + mass + ", moonList=" + moonList + "]";
	}
	
	
	
	
	

}
