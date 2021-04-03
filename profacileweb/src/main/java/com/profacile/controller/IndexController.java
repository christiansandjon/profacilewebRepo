package com.profacile.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.profacile.model.Admin;
import com.profacile.model.Professeur;
import com.profacile.model.Cours;
import com.profacile.model.Eleve;
import com.profacile.model.Offre;
import com.profacile.model.Postuler;

@Controller
public class IndexController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		
		return "website/home";
	}

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home2() {

		return "website/home";
	}
	
	@RequestMapping(value="/formules", method=RequestMethod.GET)
	public String Formules() {
		
		return "website/formules";
	}
	
	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public String Contacthome() {
		
		return "website/contact";
	}
	
}
