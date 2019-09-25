package com.internousdev.olive.dto;

import java.util.Date;

public class CartInfoDTO {

	private int id;
	private String userId;
	private int productId;
	private int price;
	private int productCount;

	private String productName;
	private String productNameKana;
	private String productDescription;
	private String imageFilePath;
	private String imageFileName;
	private String releaseCompany;
	private int totalPrice;
	private int cartTotalPrice;

	private Date releaseDate;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}

	public int getProductId(){
		return this.productId;
	}
	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getPrice(){
		return this.price;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getProductCount(){
		return this.productCount;
	}
	public void setProductCount(int productCount){
		this.productCount = productCount;
	}

	public String getProductName(){
		return this.productName;
	}
	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductNameKana(){
		return this.productNameKana;
	}
	public void setProductNameKana(String productNameKana){
		this.productNameKana = productNameKana;
	}

	public String getProductDescription(){
		return this.productDescription;
	}

	public void setProductDescription(String productDescription){
		this.productDescription = productDescription;
	}

	public String getImageFilePath(){
		return this.imageFilePath;
	}
	public void setImageFilePath(String imageFilePath){
		this.imageFilePath = imageFilePath;
	}

	public String getImageFileName(){
		return this.imageFileName;
	}
	public void setImageFileName(String imageFileName){
		this.imageFileName = imageFileName;
	}

	public String getReleaseCompany(){
		return this.releaseCompany;
	}
	public void setReleaseCompany(String releaseCompany){
		this.releaseCompany = releaseCompany;
	}

	public int getTotalPrice(){
		return this.totalPrice;
	}
	public void setTotalPrice(int totalPrice){
		this.totalPrice = totalPrice;
	}

	public int getCartTotalPrice(){
		return this.cartTotalPrice;
	}
	public void setCartTotalPrice(int cartTotalPrice){
		this.cartTotalPrice = cartTotalPrice;
	}

	public Date getReleaseDate(){
		return this.releaseDate;
	}
	public void setReleaseDate(Date releaseDate){
		this.releaseDate = releaseDate;
	}

}
