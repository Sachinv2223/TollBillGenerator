package com.cts.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*This is the controller class for error mappings*/
@Controller
public class ExceptionController implements ErrorController {

	private final Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model map) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
		log.info("Http Status Code >> " + statusCode);
		log.info("Exception >> " + exception);

		map.addAttribute("statusCode", statusCode);
		if (statusCode == 404) {
			String exceptionValue = "The page you r looking is removed or temporarly unavailable";
			map.addAttribute("exception", exceptionValue);
			return "page404";
		} else {
			map.addAttribute("exception", exception);
			return "page500";
		}
	}

	public String getErrorPath() {
		return "/error";
	}

}
