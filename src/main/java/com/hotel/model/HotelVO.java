package com.hotel.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;

public class HotelVO implements java.io.Serializable {
	private Integer hotel_id;
	private String tax_id;
	private String password;
	private String name;
	private String city;
	private String district;
	private String address;
	private String phone_number;
	private String email;
	private Integer status;
	private Timestamp create_time;
	private String owner;
	private byte[] id_front;
	private byte[] id_back;
	private byte[] license;
	private String info_text;
	private Timestamp review_time;
	private Timestamp update_time;
	private String id_front_base64;
	private String id_back_base64;
	private String license_base64;
	public String getId_back_base64() {
		if (this.getId_back() != null) {
	        this.setId_back_base64(Base64.getEncoder().encodeToString(this.getId_back()));
	    }
		return id_back_base64;
	}
	public void setId_back_base64(String id_back_base64) {
		this.id_back_base64 = id_back_base64;
	}
	public String getLicense_base64() {
		if (this.getLicense() != null) {
	        this.setLicense_base64(Base64.getEncoder().encodeToString(this.getLicense()));
	    }
		return license_base64;
	}
	public void setLicense_base64(String license_base64) {
		this.license_base64 = license_base64;
	}
	public String getId_front_base64() {
		if (this.getId_front() != null) {
	        this.setId_front_base64(Base64.getEncoder().encodeToString(this.getId_front()));
	    }
		return id_front_base64;
	}
	public void setId_front_base64(String id_front_base64) {
		this.id_front_base64 = id_front_base64;
	}
	public Integer getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getTax_id() {
		return tax_id;
	}
	public void setTax_id(String tax_id) {
		this.tax_id = tax_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public byte[] getId_front() {
		return id_front;
	}
	public void setId_front(byte[] id_front) {
		this.id_front = id_front;
	}
	public byte[] getId_back() {
		return id_back;
	}
	public void setId_back(byte[] id_back) {
		this.id_back = id_back;
	}
	public byte[] getLicense() {
		return license;
	}
	public void setLicense(byte[] license) {
		this.license = license;
	}
	public String getInfo_text() {
		return info_text;
	}
	public void setInfo_text(String info_text) {
		this.info_text = info_text;
	}
	public Timestamp getReview_time() {
		return review_time;
	}
	public void setReview_time(Timestamp review_time) {
		this.review_time = review_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	@Override
	public String toString() {
		return "HotelVO [hotel_id=" + hotel_id + ", tax_id=" + tax_id + ", password=" + password + ", name=" + name
				+ ", city=" + city + ", district=" + district + ", address=" + address + ", phone_number="
				+ phone_number + ", email=" + email + ", status=" + status + ", create_time=" + create_time + ", owner="
				+ owner + ", id_front=" + Arrays.toString(id_front) + ", id_back=" + Arrays.toString(id_back)
				+ ", license=" + Arrays.toString(license) + ", info_text=" + info_text + ", review_time=" + review_time
				+ ", update_time=" + update_time + ", id_front_base64=" + id_front_base64 + "]";
	}
}
