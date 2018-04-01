package edu.usach.apicommons.errorhandling;

public interface IErrorCode {

	String getMessage(String detail);
	Integer getCode();
	String getCodeName();
}
