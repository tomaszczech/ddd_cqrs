package com.czecht.dddcqrs.crm.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.czecht.dddcqrs.ddd.annotations.domain.application.ValueObject;

@Embeddable
@Access(AccessType.FIELD)
@ValueObject
public class Address {

	@Column
	private String city;

	@Column
	private String street;

	@Column
	private String postCode;

	private Address() {
	}

	public static Address of(String city, String street, String postCode) {
		return new Address(city, street, postCode);
	}

	public String getMailAddress(){
		return new StringBuffer(city).append('\n').append(street).append(' ').append(postCode).toString();
	}

	public String getCity() {
		return city;
	}

	private void setCity(String city) {
		if(StringUtils.isEmpty(city)) {
			throw new IllegalArgumentException("City cannot be empty");
		}

		this.city = city;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equalObject = false;
		if(obj != null && this.getClass() == obj.getClass()) {
			Address anAddress = (Address)obj;
			equalObject = this.getStreet().equals(anAddress.getCity()) && this.getStreet().equals(anAddress.getStreet())
					&& this.getPostCode().equals(anAddress.getPostCode());
		}
		return equalObject;
	}

	@Override
	public int hashCode() {
		int hashCodeValue =
				(123142 * 432) + this.getCity().hashCode() + this.getStreet().hashCode() + this.getPostCode()
						.hashCode();
		return hashCodeValue;
	}



	public String getStreet() {
		return street;
	}

	public String getPostCode() {
		return postCode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	private Address(String city, String street, String postCode) {
		this.setCity(city);
		this.setStreet(street);
		this.setPostCode(postCode);
	}



	private void setStreet(String street) {
		if(StringUtils.isEmpty(street)) {
			throw new IllegalArgumentException("Street cannot be empty");
		}

		this.street = street;
	}

	private void setPostCode(String postCode) {
		if(StringUtils.isEmpty(postCode)) {
			throw new IllegalArgumentException("Street cannot be empty");
		}
		//todo check if postCode have valid format

		this.postCode = postCode;
	}
}
