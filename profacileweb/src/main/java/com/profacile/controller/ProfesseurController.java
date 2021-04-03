package com.profacile.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.profacile.model.Professeur;
import com.profacile.model.Offre;
import com.profacile.model.Postuler;

@Controller
public class ProfesseurController {

	@RequestMapping(value="/professeur", method=RequestMethod.GET)
	public String loginProfesseur(WebRequest request) {
		
		if(request.getAttribute("professeur", WebRequest.SCOPE_SESSION)==null) {
			return "professeur/login";
		}else {
			return "redirect:/professeur/calendrier";
		}
		
	}	
	
	@RequestMapping(value="/professeur/register1", method=RequestMethod.GET)
	public String RegisterProfesseur1(WebRequest request) {
		if(request.getAttribute("professeur", WebRequest.SCOPE_SESSION)!=null) {
			return "redirect:/professeur/calendrier";
		}
		
		return "professeur/register";
	}
	
	@RequestMapping(value="/professeur/register2", method=RequestMethod.GET)
	public String RegisterProfesseur2(Model model,WebRequest request) {
		
		Professeur professeur = new Professeur();
		int[] tab = professeur.listeCoursTout();
		String[] tabintitule = new String[professeur.nombreTousCours()];
		for(int i=0;i<tab.length;i++) {
			
			tabintitule[i] = professeur.getIntitule(tab[i]);
		}
		
		model.addAttribute("listematiereID", tab);
		model.addAttribute("listematiereIntitule", tabintitule);
		
		return "professeur/register2";
	}
	
	@RequestMapping(value="/professeur/register1", method=RequestMethod.POST,params = {"nom", "prenom","email","password"})
	public String getFormRegsiter1(WebRequest request,Model model,@RequestParam(value = "nom") String nom,@RequestParam(value = "prenom") String prenom,@RequestParam(value = "email") String email,@RequestParam(value = "password") String password) {
		

		String response = "false";
		Professeur professeur = new Professeur();
		professeur.setNom(nom);
		professeur.setPrenom(prenom);
		professeur.setEmail(email);
		professeur.setPassword(password);
		if(professeur.isFreeEmail()) {
			request.setAttribute("professeur", professeur, WebRequest.SCOPE_SESSION);
			return "redirect:/professeur/register2";
		}else {
			response = "true";
			
			model.addAttribute("error", response);
			return "/professeur/register";
		}
			
	}
	
	@RequestMapping(value="/professeur/register2", method=RequestMethod.POST,params = {"matieres","montant","apropos"})
	public String getFormRegsiter2(WebRequest request,Model model,@RequestParam(value = "matieres", required=false) String matieres,@RequestParam(value = "montant", required=false) String montant,@RequestParam(value = "apropos", required=false) String apropos) {
		

		String response;
		
	
		
		// enregistrement info professeur
	Professeur professeur = 	(Professeur)request.getAttribute("professeur", WebRequest.SCOPE_SESSION);
		String[] split = matieres.split(",");
		
		if(professeur.save()) {
			
			for(int i =0; i<split.length;i++) {
				//System.out.println(x);
				int idmatiere = Integer.parseInt(split[i]);
				System.out.println(idmatiere);
				professeur.saveMatiere(professeur.getEmail(),idmatiere);
			}	
			
			//Les updates
			
			professeur.updateMontant(montant);
			professeur.updateDescription(apropos);
			professeur.dateFromProfesseur(professeur.getEmail());
			request.setAttribute("professeur", professeur, WebRequest.SCOPE_SESSION);
		
			
			return "redirect:/professeur/calendrier";
		}else {
			
			return "redirect:/professeur/register1?p=error";
		}
		
			
	}
	
	
	@RequestMapping(value="/professeur/calendrier", method=RequestMethod.GET)
	public String calendrierProfesseur(WebRequest request,Model model) {
		if(request.getAttribute("professeur", WebRequest.SCOPE_SESSION)==null) {
			return "professeur/login";
		}
		// Offre les cours etudiants
		Professeur professeur = (Professeur)request.getAttribute("professeur", WebRequest.SCOPE_SESSION);
		Postuler post = new Postuler();
		ArrayList<Postuler> mescours = post.lesEleves(professeur.getEmail());
		
		model.addAttribute("mescours",mescours);
		model.addAttribute("nom",professeur.getNom());
		model.addAttribute("prenom",professeur.getPrenom());
		return "professeur/tb";
	}
	
	@RequestMapping(value="/professeur/meseleves", method=RequestMethod.GET)
	public String elevesProfesseur(WebRequest request,Model model) {
		if(request.getAttribute("professeur", WebRequest.SCOPE_SESSION)==null) {
			return "professeur/login";
		}
		// Offre les cours etudiants
		Professeur professeur = (Professeur)request.getAttribute("professeur", WebRequest.SCOPE_SESSION);
		Postuler post = new Postuler();
		ArrayList<Postuler> mescours = post.lesEleves(professeur.getEmail());
		
		model.addAttribute("mescours",mescours);
		model.addAttribute("nom",professeur.getNom());
		model.addAttribute("prenom",professeur.getPrenom());
		return "professeur/meseleves";
	}
	
	@RequestMapping(value="/professeur/postuler", method=RequestMethod.GET,params = {"l"})
	public String Postuler(Model model,WebRequest request,@RequestParam(value = "l") int l) {
		
		if(request.getAttribute("professeur", WebRequest.SCOPE_SESSION)==null) {
			return "professeur/login";
		}else {
			
			Professeur professeur = (Professeur)request.getAttribute("professeur", WebRequest.SCOPE_SESSION);

			Offre offre = new Offre();
			if(offre.postuler(l, professeur.getEmail())) {
				return "redirect:/professeur/offres?is=true";
			}else {
				return "redirect:/professeur/offres?is=false";
			}
			
			
		}
		
	}
	
	
	@RequestMapping(value="/professeur/offrepost", method=RequestMethod.GET)
	public String offreProfesseurPostuler(WebRequest request,Model model) {
		if(request.getAttribute("professeur", WebRequest.SCOPE_SESSION)==null) {
			return "professeur/login";
		}
		
		Professeur professeur = (Professeur)request.getAttribute("professeur", WebRequest.SCOPE_SESSION);
		Offre offre = new Offre();
		ArrayList<Offre> lesoffres = offre.listeOffreProfesseurPostuler(professeur.getEmail());
		int size = lesoffres.size();
		model.addAttribute("lesoffres", lesoffres);
		model.addAttribute("size", size);
		return "professeur/offrepostuler";
	}
	
	
	@RequestMapping(value="/professeur/offres", method=RequestMethod.GET)
	public String offreProfesseur(WebRequest request,Model model,@RequestParam(value = "is", required=false) String is) {
		if(request.getAttribute("professeur", WebRequest.SCOPE_SESSION)==null) {
			return "professeur/login";
		}
		
		Professeur professeur = (Professeur)request.getAttribute("professeur", WebRequest.SCOPE_SESSION);
		Offre offre = new Offre();
		ArrayList<Offre> lesoffres = offre.listeOffreProfesseur(professeur.getEmail());
		int size = lesoffres.size();
		model.addAttribute("lesoffres", lesoffres);
		model.addAttribute("size", size);
		model.addAttribute("is", is);
		return "professeur/offre";
	}
	
	
	
	@RequestMapping(value="/professeur/logout", method=RequestMethod.GET)
	public String LogoutProfesseur(WebRequest request) {
		request.removeAttribute("professeur", WebRequest.SCOPE_SESSION);
		return "redirect:/professeur";
	}
	
	@RequestMapping(value="/professeur", method=RequestMethod.POST,params = {"email", "password"})
	public String getFormLoginProfesseur(WebRequest request,Model model,@RequestParam(value = "email") String email,@RequestParam(value = "password") String password) {
		if(request.getAttribute("professeur", WebRequest.SCOPE_SESSION)!=null) {
			return "redirect:/professeur/calendrier";
		}
		Professeur professeur = new Professeur();
		professeur.setEmail(email);
		professeur.setPassword(password);
		if((professeur.isUser())){
			
			//connexion ok
			//model.addAttribute(username);
			professeur.dateFromProfesseur(email);
			request.setAttribute("professeur", professeur, WebRequest.SCOPE_SESSION);
			return "redirect:/professeur/calendrier";
		}else {
			// echec de connexion
			String error = "true";
			model.addAttribute("error",error);
			return "professeur/login";
			
		}
		
	}
	
	@RequestMapping(value="/professeur/compte", method=RequestMethod.POST,params = {"nom", "prenom","montant","adresse","ville","pays","telephone","description","cv"})
	public String getFormCompteProfesseur(WebRequest request,Model model,@RequestParam(value = "nom") String nom,@RequestParam(value = "prenom") String prenom,@RequestParam(value = "adresse") String adresse,@RequestParam(value = "ville") String ville,@RequestParam(value = "pays") String pays,@RequestParam(value = "telephone",required=false) String telephone,@RequestParam(value = "montant") String montant,@RequestParam(value = "description",required=false) String description,@RequestParam(value = "cv", required=false) String cv) {
		Professeur professeurs = (Professeur)request.getAttribute("professeur",WebRequest.SCOPE_SESSION);
		String email = professeurs.getEmail();
		String response;
		Professeur professeur = new Professeur();
		professeur.setNom(nom);
		professeur.setPrenom(prenom);
		professeur.setTelephone(telephone);
		professeur.setAdresse(adresse);
		professeur.setVille(ville);
		professeur.setPays(pays);
		professeur.setCv(cv);
		professeur.setDescription(description);
		professeur.setMontant(montant);
		
		int[] tab = professeurs.listeCours(email);
		String[] tabintitule = new String[professeurs.nombreCours(email)];
		//int[] tab2 = professeur.listeCoursTout2();
		for(int i=0;i<tab.length;i++) {
			
			tabintitule[i] = professeurs.getIntitule(tab[i]);
			System.out.println(tabintitule[i]);
		}
		
		model.addAttribute("listecours",tab);
		model.addAttribute("listecours2",tabintitule);
		
		if(professeur.update(email)) {
			request.setAttribute("professeur",professeur , WebRequest.SCOPE_SESSION);
			response = "0";
			model.addAttribute("error",response);
			return "/professeur/parametre";
		}else {
			response = "1";
			model.addAttribute("error",response);
			return "/professeur/parametre";
		}
			
	}
	
	@RequestMapping(value="/professeur/compte", method=RequestMethod.GET)
	public String ParametreProfesseur(Model model,WebRequest request) {
		if(request.getAttribute("professeur", WebRequest.SCOPE_SESSION)==null) {
			return "professeur/login";
		}
		
		Professeur professeur = (Professeur)request.getAttribute("professeur",WebRequest.SCOPE_SESSION);
		professeur.dateFromProfesseur(professeur.getEmail());
		
		int[] tab = professeur.listeCours(professeur.getEmail());
		String[] tabintitule = new String[professeur.nombreCours(professeur.getEmail())];
		//int[] tab2 = professeur.listeCoursTout2();
		for(int i=0;i<tab.length;i++) {
			
			tabintitule[i] = professeur.getIntitule(tab[i]);
			System.out.println(tabintitule[i]);
		}
		
		model.addAttribute("professeur",professeur);
		model.addAttribute("listecours",tab);
		model.addAttribute("listecours2",tabintitule);
		
	
		return "professeur/parametre";
	}
}
