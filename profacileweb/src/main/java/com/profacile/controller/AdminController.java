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
public class AdminController {
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String login() {

		return "admin/login";
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.POST,params = {"username", "password"})
	public String getForm(WebRequest request,Model model,@RequestParam(value = "username") String username,@RequestParam(value = "password") String password) {
		
		Admin admin = new Admin();
		admin.setLogin(username);
		admin.setPassword(password);
		if((admin.isUser())){
			
			//connexion reuissie
			//model.addAttribute(username);
			request.setAttribute("admin", admin, WebRequest.SCOPE_SESSION);
			return "redirect:/admin/tb";
		}else {
			// echec de connexion
			String error = "true";
			model.addAttribute("error",error);
			return "admin/login";
			
		}
		
	}
	
	@RequestMapping(value="/admin/eleve", method=RequestMethod.GET)
	public String Eleve() {
		System.out.println("Bienvenue sur mon site web");
		return "admin/eleve";
	}
	
	@RequestMapping(value="/admin/enseignant", method=RequestMethod.GET)
	public String Enseignant() {
		System.out.println("Bienvenue sur mon site web");
		return "admin/enseignant";
	}
	
	@RequestMapping(value="/admin/tb", method=RequestMethod.GET)
	public String Tableaubord(WebRequest request) {
		
		if(request.getAttribute("admin", WebRequest.SCOPE_SESSION)==null) {
			return "admin/login";
			
		}
		
		Admin admin = (Admin)request.getAttribute("admin", WebRequest.SCOPE_SESSION);
		System.out.println("Bienvenue sur mon site web"+ admin.getLogin());
		return "admin/tb";
	}
	
	@RequestMapping(value="/admin/parametre", method=RequestMethod.GET)
	public String Parametre() {
		System.out.println("Bienvenue sur mon site web");
		return "admin/parametre";
	}
	
	@RequestMapping(value="admin/parametre", method=RequestMethod.POST,params = {"passwordold", "passwordnew1","passwordnew2"})
	public String getFormParemeters(WebRequest request,Model model,@RequestParam(value = "passwordold") String passwordold,@RequestParam(value = "passwordnew1") String passwordnew1,@RequestParam(value = "passwordnew2") String passwordnew2) {
		
		String response;
		Admin admin = new Admin();
		
		//verification des anciens mot de passe
		
		if(passwordnew1.equals(passwordnew2)) {
			
			//ok
			
			String truepassword = admin.getPassword(1);
			
			if(truepassword.equals(passwordold)) {
				//tout est ok
				response = "0";
				admin.update(1,passwordnew1); 
				
			}else {
				
				//ancien mot de passe erroné
				response = "2";		
			}
			
		}else {
			//les nouveaux mots de passe ne correspondent pas
			response = "1";
		}
		
		model.addAttribute("response",response);
		return "admin/parametre";
		
	}
	
	@RequestMapping(value="/admin/logout", method=RequestMethod.GET)
	public String Logout(WebRequest request) {
		request.removeAttribute("admin", WebRequest.SCOPE_SESSION);
		return "redirect:/admin";
	}
	
}
