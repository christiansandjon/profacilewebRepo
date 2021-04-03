package com.profacile.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.profacile.model.Professeur;
import com.profacile.model.Cours;
import com.profacile.model.Eleve;
import com.profacile.model.Offre;
import com.profacile.model.Postuler;

@Controller
public class EleveController {

	
	@RequestMapping(value="/student", method=RequestMethod.GET)
	public String loginEleve(WebRequest request) {
		
		if(request.getAttribute("eleve", WebRequest.SCOPE_SESSION)==null) {
			return "eleve/login";
		}else {
			return "redirect:/student/calendrier";
		}
		
	}
	
	@RequestMapping(value="student/valideroffre", method=RequestMethod.GET,params = {"l","email"})
	public String validerOffreEleve(WebRequest request,@RequestParam(value = "l") int l,@RequestParam(value = "email") String email) {
		
		if(request.getAttribute("eleve", WebRequest.SCOPE_SESSION)==null) {
			return "eleve/login";
		}else {
			
			
			Offre offre = new Offre();
			if(offre.validerOffre(l, email)) {
				
				return"redirect:/student/enseignant?ok";
			}else {
				return"redirect:/student/mescours?nn";
			}
			
			
			
		}
		
	}
	
	@RequestMapping(value="/student/voiroffre", method=RequestMethod.GET,params = {"l"})
	public String voirOffreEleve(Model model,WebRequest request,@RequestParam(value = "l") int l) {
		
		if(request.getAttribute("eleve", WebRequest.SCOPE_SESSION)==null) {
			return "eleve/login";
		}else {
			
			
			Offre offre = new Offre();
			offre.setId(l);
			offre.setOffre();
			ArrayList<Professeur> a = offre.professeurOffre(l);
			for(int i=0;i<a.size();i++) {
				Professeur ligne = (Professeur)a.get(i);
				System.out.println(ligne.getNom());
				System.out.println(ligne.getPrenom());
			}
			
			model.addAttribute("offre", offre);
			model.addAttribute("postulant", a);
			return "eleve/voiroffre";
		}
		
	}
	
	@RequestMapping(value="/student/calendrier", method=RequestMethod.GET)
	public String calendrierEleve(WebRequest request,Model model) {
		if(request.getAttribute("eleve", WebRequest.SCOPE_SESSION)==null) {
			return "eleve/login";
		}
		Eleve eleve = (Eleve)request.getAttribute("eleve", WebRequest.SCOPE_SESSION);
		
		Cours cours = new Cours();
		int[] tab = cours.listeCoursTout();
		String[] tabintitule = new String[cours.nombreTousCours()];
		for(int i=0;i<tab.length;i++) {
			
			tabintitule[i] = cours.getIntitule(tab[i]);
		}
		
		model.addAttribute("listematiereID", tab);
		model.addAttribute("listematiereIntitule", tabintitule);
		
	
		Postuler post = new Postuler();
		
		// Offre les cours etudiants
		ArrayList<Postuler> mescours = post.lesProfesseurs(eleve.getId());
		
		
		model.addAttribute("mescours",mescours);
		model.addAttribute("nom",eleve.getNom());
		model.addAttribute("prenom",eleve.getPrenom());
		return "eleve/tb";
	}
	
	@RequestMapping(value="/student/mescours", method=RequestMethod.GET)
	public String coursEleve(Model model,WebRequest request) {
		if(request.getAttribute("eleve", WebRequest.SCOPE_SESSION)==null) {
			return "eleve/login";
		}
		
		Eleve eleve = (Eleve)request.getAttribute("eleve", WebRequest.SCOPE_SESSION);
		
		Offre offre = new Offre();
		Postuler post = new Postuler();
		
		// Offre terminer
		ArrayList<Postuler> mescours = post.lesProfesseurs(eleve.getId());
		//offre.setEleve(eleve);
		ArrayList<Offre> lesoffres = offre.listeOffre(eleve.getId());
		model.addAttribute("mescours",mescours);
		model.addAttribute("lesoffres",lesoffres);
		
		return "eleve/cours";
	}
	
	@RequestMapping(value="/student/enseignant", method=RequestMethod.GET)
	public String enseignantEleve(Model model,WebRequest request) {
		if(request.getAttribute("eleve", WebRequest.SCOPE_SESSION)==null) {
			return "eleve/login";
		}
		
		Postuler p = new Postuler();
		//liste des enseignants/professeur
		Eleve eleve = (Eleve)request.getAttribute("eleve", WebRequest.SCOPE_SESSION);
		ArrayList<Postuler> listeenseignant = p.lesProfesseurs(eleve.getId());
		for(int i=0;i<listeenseignant.size();i++) {
			Postuler ligne = (Postuler)listeenseignant.get(i);
			
			System.out.println(ligne.getProfesseur().getNom());
			System.out.println(ligne.getOffre().getCours().getIntitule());
			
		} 
		//System.out.println("La taille est ok "+listeenseignant.size());
		model.addAttribute("lesenseigants", listeenseignant);
		
		return "eleve/enseignant";
	}
	
	@RequestMapping(value="/student", method=RequestMethod.POST,params = {"email", "password"})
	public String getFormLoginEleve(WebRequest request,Model model,@RequestParam(value = "email") String email,@RequestParam(value = "password") String password) {
		if(request.getAttribute("eleve", WebRequest.SCOPE_SESSION)!=null) {
			return "redirect:/student/calendrier";
		}
		Eleve eleve = new Eleve();
		eleve.setEmail(email);
		eleve.setPassword(password);
		if((eleve.isUser())){
			
			//connexion ok
			//model.addAttribute(username);
			eleve.dateFromEleve(email);
			request.setAttribute("eleve", eleve, WebRequest.SCOPE_SESSION);
			return "redirect:/student/calendrier";
		}else {
			// echec de connexion
			String error = "true";
			model.addAttribute("error",error);
			return "eleve/login";
			
		}
		
	}
	
	@RequestMapping(value="/student/compte", method=RequestMethod.GET)
	public String ParametreEleve(Model model,WebRequest request) {
		if(request.getAttribute("eleve", WebRequest.SCOPE_SESSION)==null) {
			return "eleve/login";
		}
		Eleve eleve = (Eleve)request.getAttribute("eleve",WebRequest.SCOPE_SESSION);
		System.out.println(eleve.getId());
		model.addAttribute("eleve",eleve);
	
		return "eleve/parametre";
	}
	
	@RequestMapping(value="/student/logout", method=RequestMethod.GET)
	public String LogoutEleve(WebRequest request) {
		request.removeAttribute("eleve", WebRequest.SCOPE_SESSION);
		return "redirect:/student";
	}
	
	@RequestMapping(value="/student/compte", method=RequestMethod.POST,params = {"nom", "prenom","classe","adresse","ville","pays","classe","telephone"})
	public String getFormCompte(WebRequest request,Model model,@RequestParam(value = "nom") String nom,@RequestParam(value = "prenom") String prenom,@RequestParam(value = "niveauScolaire") String niveauScolaire,@RequestParam(value = "adresse") String adresse,@RequestParam(value = "ville") String ville,@RequestParam(value = "pays") String pays,@RequestParam(value = "telephone") String telephone) {
		Eleve elevess = (Eleve)request.getAttribute("eleve",WebRequest.SCOPE_SESSION);
		String email = elevess.getEmail();	
		String response;
		Eleve eleve = new Eleve();
		eleve.setNom(nom);
		eleve.setPrenom(prenom);
		//eleve.setNiveauScolaire(niveauScolaire);
		eleve.setTelephone(telephone);
		//eleve.setAdresse(adresse);
		//eleve.setVille(ville);
		//eleve.setPays(pays);
		
		if(eleve.update(email)) {
			request.setAttribute("eleve", eleve, WebRequest.SCOPE_SESSION);
			response = "0";
			model.addAttribute("error",response);
			return "/eleve/parametre";
		}else {
			response = "1";
			model.addAttribute("error",response);
			return "/eleve/parametre";
		}
			
	}
	
	@RequestMapping(value="/student/register", method=RequestMethod.GET)
	public String RegisterEleve(WebRequest request) {
		if(request.getAttribute("eleve", WebRequest.SCOPE_SESSION)!=null) {
			return "redirect:/student/calendrier";
		}
		
		return "eleve/register";
	}

	@RequestMapping(value="/student/register", method=RequestMethod.POST,params = {"nom", "prenom","email","classe","password"})
	public String getFormRegsiter(WebRequest request,Model model,@RequestParam(value = "nom") String nom,@RequestParam(value = "prenom") String prenom,@RequestParam(value = "email") String email,@RequestParam(value = "niveauScolaire") String niveauScolaire,@RequestParam(value = "password") String password) {
		

		String response;
		Eleve eleve = new Eleve();
		eleve.setNom(nom);
		eleve.setPrenom(prenom);
		eleve.setEmail(email);
		//eleve.setNiveauScolaire(niveauScolaire);
		eleve.setPassword(password);
		
		if(eleve.save()) {
			request.setAttribute("eleve", eleve, WebRequest.SCOPE_SESSION);
			eleve.dateFromEleve(email);
			return "redirect:/student/calendrier?new";
		}else {
			response = "true";
			model.addAttribute("error",response);
			return "/eleve/register";
		}
			
	}
	
	@RequestMapping(value="/student/creeroffre", method=RequestMethod.POST,params = {"niveau", "matiere"})
	public String getFormRegisterOffre(WebRequest request,Model model,@RequestParam(value = "niveau") String niveau,@RequestParam(value = "matiere") int matiere) {
		
		String response;
		Offre offre = new Offre();
		Cours cours = new Cours();
		Eleve eleve =  (Eleve)request.getAttribute("eleve", WebRequest.SCOPE_SESSION);
		cours.setId(matiere);
		
		offre.setCours(cours);
		offre.setEleve(eleve);
		
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
		
		offre.setDatepub(currentTime);
		String datefin="0000-00-00 00:00:00";
		String status = "en cours";
		offre.setStatus(status);
		offre.setNiveau(niveau);
		offre.setDatefinoffre(datefin);
		if(offre.save()) {
			response = "true";
		}else {
			
			response = "false";
		}
		model.addAttribute("error",response);
	    return "/eleve/offre";	
	}
	
	
}
