package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String name;
	@NotBlank
	private String crushname;

	private String result;
	
	@ManyToOne
	@JoinColumn(name = "userid" , insertable = false , updatable = false)
	private Users user;
	private Integer userid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.replaceAll(" ", "");
	}

	public String getCrushname() {
		return crushname;
	}

	public void setCrushname(String crushname) {
		this.crushname = crushname.replaceAll(" ", "");
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", name=" + name + ", crushname=" + crushname + ", result=" + result + "]";
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	
	
}
