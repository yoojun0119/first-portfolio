package com.kakaocook.www.dto;

public class FreezerDTO implements Comparable<FreezerDTO>{
	private String id;
	private int no;
	private String name;
	private String code;
	private String type;
	private int package_amount;
	private String package_unit;
	private int total_amount;
	private int numOfPurchase;
	private String purchase_date;
	private String shelf_life;
	private int rest_of_shelf_life;
	private String storage_method;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPackage_amount() {
		return package_amount;
	}
	public void setPackage_amount(int package_amount) {
		this.package_amount = package_amount;
	}
	public String getPackage_unit() {
		return package_unit;
	}
	public void setPackage_unit(String package_unit) {
		this.package_unit = package_unit;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public int getNumOfPurchase() {
		return numOfPurchase;
	}
	public void setNumOfPurchase(int numOfPurchase) {
		this.numOfPurchase = numOfPurchase;
	}
	public String getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}
	public String getShelf_life() {
		return shelf_life;
	}
	public void setShelf_life(String shelf_life) {
		this.shelf_life = shelf_life;
	}
	public int getRest_of_shelf_life() {
		return rest_of_shelf_life;
	}
	public void setRest_of_shelf_life(int rest_of_shelf_life) {
		this.rest_of_shelf_life = rest_of_shelf_life;
	}
	public String getStorage_method() {
		return storage_method;
	}
	public void setStorage_method(String storage_method) {
		this.storage_method = storage_method;
	}
	
	@Override
	public int compareTo(FreezerDTO fDto) {
		if(this.rest_of_shelf_life < fDto.rest_of_shelf_life) {
			return -1;
		} else if(this.rest_of_shelf_life > fDto.rest_of_shelf_life){
			return 1;
		}
		return 0;
	}
}
