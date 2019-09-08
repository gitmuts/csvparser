package com.challange.csvparser.model;

import java.util.Date;

import javax.validation.constraints.Min;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class User {

	
	
	public User(String firstName, String lastName, Date dateOfBirth, String postalAddress, String nationalId,
			String gender,int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.postalAddress = postalAddress;
		this.nationalId = nationalId;
		this.gender = gender;
		this.age = age;
	}

	@CsvBindByName(column="first_name")
	private String firstName;
	
	@CsvBindByName(column="last_name")
	private String lastName;
	
	@CsvDate(value = "yyyy-MM-dd")
	@CsvBindByName(column="date_of_birth")
	private Date dateOfBirth;
	
	@CsvBindByName(column="postal_address")
	private String postalAddress;
	
	@CsvBindByName(column="national_id")
	private String nationalId;
	
	@CsvBindByName(column="gender")
	private String gender;
	
	@CsvBindByName(column="age")
	private int age;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
