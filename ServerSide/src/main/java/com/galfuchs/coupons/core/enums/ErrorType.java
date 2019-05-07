package com.galfuchs.coupons.core.enums;

public enum ErrorType {
	
	COMPANY_EXISTS(808, "This company already exists."),
	COMPANY_DOES_NOT_EXIST(808, "This company does not exist."),
	NO_COMPANIES_EXIST(808, "There are no companies in the database"),
	COUPON_EXISTS(808, "A coupon with this title already exists."),
	COUPON_DOES_NOT_EXIST(808, "This coupon does not exist."),
	COUPON_END_DATE_INVALID(808, "Coupon's end date already occured"),
	CUSTOMER_EXISTS(808, "This customer already exists."),
	CUSTOMER_DOES_NOT_EXIST(808, "This customer does not exist"),
	INVALID_EMAIL(808, "The e-mail address is invalid"),
	INVALID_PASSWORD(808, "The password is invalid"),
	CUSTOMER_ALREADY_OWNS_COUPON(808, "The customer already owns this coupon"),
	LOGIN_ERROR(808, "Email or password incorrect"),
	COUPON_NOT_IN_STOCK(808, "The coupon is not in stock"),
	COUPON_AMOUNT_INVALID(808, "Cannot set negative amount"),
	COUPON_PRICE_INVALID(808, "Cannot set negative price"),
	SYSTEM_ERROR(808);
	
	private int internalErrorCode;
	private String message;
	
	ErrorType(int errorCode, String message) {
		this.internalErrorCode = errorCode;
		this.message = message;
	}
	
	ErrorType(int errorCode) {
		this.internalErrorCode = errorCode;
	}

	public int getInternalErrorCode() {
		return internalErrorCode;
	}

	public String getInternalMessage() {
		return message;
	}

}
