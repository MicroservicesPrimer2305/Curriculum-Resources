package com.example.demo.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Moon {
	
	@Id
	@Column 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column 
	private String name;
	
	@Column
	private String myPlanet;
	
	

	public Moon(int id, String name, String myPlanet) {
		super();
		this.id = id;
		this.name = name;
		this.myPlanet = myPlanet;
	}

	public Moon() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getMyPlanet() {
		return myPlanet;
	}

	public void setMyPlanet(String myPlanet) {
		this.myPlanet = myPlanet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, myPlanet, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moon other = (Moon) obj;
		return id == other.id && Objects.equals(myPlanet, other.myPlanet) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Moon [id=" + id + ", name=" + name + ", myPlanet=" + myPlanet + "]";
	}
	
	
	

}
