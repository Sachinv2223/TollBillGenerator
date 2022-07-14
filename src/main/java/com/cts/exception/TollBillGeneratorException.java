package com.cts.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// This is the exception class for Toll Bill Generator project 
@ControllerAdvice
public class TollBillGeneratorException extends Exception {
	private static final long serialVersionUID = -1105431869622052445L;

	/**
	 * 
	 */
	public TollBillGeneratorException() {
		super();
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		ModelAndView model = new ModelAndView();

		System.out.println("Exception");
		model.setViewName("indexUser");
		return model;
	}

}
