package com.profacile.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.profacile.model.Admin;
import com.profacile.model.Coach;
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
	
	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public String Contacthome() {
		
		return "website/contact";
	}
 
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String login() {

		return "admin/login";
	}
	@RequestMapping(value="/admin", method=RequestMethod.POST,params = {"username", "password"})
	public String getForm(WebRequest request,Model model,@RequestParam(value = "username") String username,@RequestParam(value = "password") String password) {
		
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		if((admin.isUser())){
			
			//connexion reuissis
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
		System.out.println("Bienvenue sur mon site web"+ admin.getUsername());
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
		//verification des anciens pass

		
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
	
	
	
/****
 * 
 * 
 * Eleve controller
 * 
 * 
 * 
 * 	
 */
	
	
	
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
			ArrayList<Coach> a = offre.coachOffre(l);
			for(int i=0;i<a.size();i++) {
				Coach ligne = (Coach)a.get(i);
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
		ArrayList<Postuler> mescours = post.lesCoachs(eleve.getId());
		
		
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
		ArrayList<Postuler> mescours = post.lesCoachs(eleve.getId());
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
		//liste des enseignants/coach
		Eleve eleve = (Eleve)request.getAttribute("eleve", WebRequest.SCOPE_SESSION);
		ArrayList<Postuler> listeenseignant = p.lesCoachs(eleve.getId()); 
		for(int i=0;i<listeenseignant.size();i++) {
			Postuler ligne = (Postuler)listeenseignant.get(i);
			
			System.out.println(ligne.getCoach().getNom());
			System.out.println(ligne.getOffre().getCours().getIntitule());
			
		} 
		//System.out.println("La taille est ok "+listeenseignant.size());
		model.addAttribute("lesenseigants", listeenseignant);
		
		return "eleve/enseignant";
	}
	
	@RequestMapping(value="/student", method=RequestMethod.POST,params = {"email", "motpasse"})
	public String getFormLoginEleve(WebRequest request,Model model,@RequestParam(value = "email") String email,@RequestParam(value = "motpasse") String motpasse) {
		if(request.getAttribute("eleve", WebRequest.SCOPE_SESSION)!=null) {
			return "redirect:/student/calendrier";
		}
		Eleve eleve = new Eleve();
		eleve.setEmail(email);
		eleve.setMotpasse(motpasse);
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
	public String getFormCompte(WebRequest request,Model model,@RequestParam(value = "nom") String nom,@RequestParam(value = "prenom") String prenom,@RequestParam(value = "classe") String classe,@RequestParam(value = "adresse") String adresse,@RequestParam(value = "ville") String ville,@RequestParam(value = "pays") String pays,@RequestParam(value = "telephone") String telephone) {
		Eleve elevess = (Eleve)request.getAttribute("eleve",WebRequest.SCOPE_SESSION);
		String email = elevess.getEmail();	
		String response;
		Eleve eleve = new Eleve();
		eleve.setNom(nom);
		eleve.setPrenom(prenom);
		eleve.setClasse(classe);
		eleve.setTelephone(telephone);
		eleve.setAdresse(adresse);
		eleve.setVille(ville);
		eleve.setPays(pays);
		
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

	@RequestMapping(value="/student/register", method=RequestMethod.POST,params = {"nom", "prenom","email","classe","motpasse"})
	public String getFormRegsiter(WebRequest request,Model model,@RequestParam(value = "nom") String nom,@RequestParam(value = "prenom") String prenom,@RequestParam(value = "email") String email,@RequestParam(value = "classe") String classe,@RequestParam(value = "motpasse") String motpasse) {
		

		String response;
		Eleve eleve = new Eleve();
		eleve.setNom(nom);
		eleve.setPrenom(prenom);
		eleve.setEmail(email);
		eleve.setClasse(classe);
		eleve.setMotpasse(motpasse);
		
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
	
	
	
	/******
	 * 
	 * 
	 * 
	 * Coach
	 * 
	 * 
	 *  /
	 */
	
	@RequestMapping(value="/coach", method=RequestMethod.GET)
	public String loginCoach(WebRequest request) {
		
		if(request.getAttribute("coach", WebRequest.SCOPE_SESSION)==null) {
			return "coach/login";
		}else {
			return "redirect:/coach/calendrier";
		}
		
	}	
	
	@RequestMapping(value="/coach/register1", method=RequestMethod.GET)
	public String RegisterCoach1(WebRequest request) {
		if(request.getAttribute("coach", WebRequest.SCOPE_SESSION)!=null) {
			return "redirect:/coach/calendrier";
		}
		
		return "coach/register";
	}
	
	@RequestMapping(value="/coach/register2", method=RequestMethod.GET)
	public String RegisterCoach2(Model model,WebRequest request) {
		
		Coach coach = new Coach();
		int[] tab = coach.listeCoursTout();
		String[] tabintitule = new String[coach.nombreTousCours()];
		for(int i=0;i<tab.length;i++) {
			
			tabintitule[i] = coach.getIntitule(tab[i]);
		}
		
		model.addAttribute("listematiereID", tab);
		model.addAttribute("listematiereIntitule", tabintitule);
		
		return "coach/register2";
	}
	
	@RequestMapping(value="/coach/register1", method=RequestMethod.POST,params = {"nom", "prenom","email","motpasse"})
	public String getFormRegsiter1(WebRequest request,Model model,@RequestParam(value = "nom") String nom,@RequestParam(value = "prenom") String prenom,@RequestParam(value = "email") String email,@RequestParam(value = "motpasse") String motpasse) {
		

		String response = "false";
		Coach coach = new Coach();
		coach.setNom(nom);
		coach.setPrenom(prenom);
		coach.setEmail(email);
		coach.setMotpasse(motpasse);
		if(coach.isFreeEmail()) {
			request.setAttribute("coach", coach, WebRequest.SCOPE_SESSION);
			return "redirect:/coach/register2";
		}else {
			response = "true";
			
			model.addAttribute("error", response);
			return "/coach/register";
		}
			
	}
	
	@RequestMapping(value="/coach/register2", method=RequestMethod.POST,params = {"matieres","montant","apropos"})
	public String getFormRegsiter2(WebRequest request,Model model,@RequestParam(value = "matieres", required=false) String matieres,@RequestParam(value = "montant", required=false) String montant,@RequestParam(value = "apropos", required=false) String apropos) {
		

		String response;
		
	
		
		// enregistrement info coach
	Coach coach = 	(Coach)request.getAttribute("coach", WebRequest.SCOPE_SESSION);
		String[] split = matieres.split(",");
		
		if(coach.save()) {
			
			for(int i =0; i<split.length;i++) {
				//System.out.println(x);
				int idmatiere = Integer.parseInt(split[i]);
				System.out.println(idmatiere);
				coach.saveMatiere(coach.getEmail(),idmatiere);
			}	
			
			//Les updates
			
			coach.updateMontant(montant);
			coach.updateDescription(apropos);
			coach.dateFromCoach(coach.getEmail());
			request.setAttribute("coach", coach, WebRequest.SCOPE_SESSION);
		
			
			return "redirect:/coach/calendrier";
		}else {
			
			return "redirect:/coach/register1?p=error";
		}
		
			
	}
	
	
	@RequestMapping(value="/coach/calendrier", method=RequestMethod.GET)
	public String calendrierCoach(WebRequest request,Model model) {
		if(request.getAttribute("coach", WebRequest.SCOPE_SESSION)==null) {
			return "coach/login";
		}
		// Offre les cours etudiants
		Coach coach = (Coach)request.getAttribute("coach", WebRequest.SCOPE_SESSION);
		Postuler post = new Postuler();
		ArrayList<Postuler> mescours = post.lesEleves(coach.getEmail());
		
		model.addAttribute("mescours",mescours);
		model.addAttribute("nom",coach.getNom());
		model.addAttribute("prenom",coach.getPrenom());
		return "coach/tb";
	}
	
	@RequestMapping(value="/coach/meseleves", method=RequestMethod.GET)
	public String elevesCoach(WebRequest request,Model model) {
		if(request.getAttribute("coach", WebRequest.SCOPE_SESSION)==null) {
			return "coach/login";
		}
		// Offre les cours etudiants
		Coach coach = (Coach)request.getAttribute("coach", WebRequest.SCOPE_SESSION);
		Postuler post = new Postuler();
		ArrayList<Postuler> mescours = post.lesEleves(coach.getEmail());
		
		model.addAttribute("mescours",mescours);
		model.addAttribute("nom",coach.getNom());
		model.addAttribute("prenom",coach.getPrenom());
		return "coach/meseleves";
	}
	
	@RequestMapping(value="/coach/postuler", method=RequestMethod.GET,params = {"l"})
	public String Postuler(Model model,WebRequest request,@RequestParam(value = "l") int l) {
		
		if(request.getAttribute("coach", WebRequest.SCOPE_SESSION)==null) {
			return "coach/login";
		}else {
			
			Coach coach = (Coach)request.getAttribute("coach", WebRequest.SCOPE_SESSION);

			Offre offre = new Offre();
			if(offre.postuler(l, coach.getEmail())) {
				return "redirect:/coach/offres?is=true";
			}else {
				return "redirect:/coach/offres?is=false";
			}
			
			
		}
		
	}
	
	
	@RequestMapping(value="/coach/offrepost", method=RequestMethod.GET)
	public String offreCoachPostuler(WebRequest request,Model model) {
		if(request.getAttribute("coach", WebRequest.SCOPE_SESSION)==null) {
			return "coach/login";
		}
		
		Coach coach = (Coach)request.getAttribute("coach", WebRequest.SCOPE_SESSION);
		Offre offre = new Offre();
		ArrayList<Offre> lesoffres = offre.listeOffreCoachPostuler(coach.getEmail());
		int size = lesoffres.size();
		model.addAttribute("lesoffres", lesoffres);
		model.addAttribute("size", size);
		return "coach/offrepostuler";
	}
	
	
	@RequestMapping(value="/coach/offres", method=RequestMethod.GET)
	public String offreCoach(WebRequest request,Model model,@RequestParam(value = "is", required=false) String is) {
		if(request.getAttribute("coach", WebRequest.SCOPE_SESSION)==null) {
			return "coach/login";
		}
		
		Coach coach = (Coach)request.getAttribute("coach", WebRequest.SCOPE_SESSION);
		Offre offre = new Offre();
		ArrayList<Offre> lesoffres = offre.listeOffreCoach(coach.getEmail());
		int size = lesoffres.size();
		model.addAttribute("lesoffres", lesoffres);
		model.addAttribute("size", size);
		model.addAttribute("is", is);
		return "coach/offre";
	}
	
	
	
	@RequestMapping(value="/coach/logout", method=RequestMethod.GET)
	public String LogoutCoach(WebRequest request) {
		request.removeAttribute("coach", WebRequest.SCOPE_SESSION);
		return "redirect:/coach";
	}
	
	@RequestMapping(value="/coach", method=RequestMethod.POST,params = {"email", "motpasse"})
	public String getFormLoginCoach(WebRequest request,Model model,@RequestParam(value = "email") String email,@RequestParam(value = "motpasse") String motpasse) {
		if(request.getAttribute("coach", WebRequest.SCOPE_SESSION)!=null) {
			return "redirect:/coach/calendrier";
		}
		Coach coach = new Coach();
		coach.setEmail(email);
		coach.setMotpasse(motpasse);
		if((coach.isUser())){
			
			//connexion ok
			//model.addAttribute(username);
			coach.dateFromCoach(email);
			request.setAttribute("coach", coach, WebRequest.SCOPE_SESSION);
			return "redirect:/coach/calendrier";
		}else {
			// echec de connexion
			String error = "true";
			model.addAttribute("error",error);
			return "coach/login";
			
		}
		
	}
	
	@RequestMapping(value="/coach/compte", method=RequestMethod.POST,params = {"nom", "prenom","montant","adresse","ville","pays","telephone","description","cv"})
	public String getFormCompteCoach(WebRequest request,Model model,@RequestParam(value = "nom") String nom,@RequestParam(value = "prenom") String prenom,@RequestParam(value = "adresse") String adresse,@RequestParam(value = "ville") String ville,@RequestParam(value = "pays") String pays,@RequestParam(value = "telephone",required=false) String telephone,@RequestParam(value = "montant") String montant,@RequestParam(value = "description",required=false) String description,@RequestParam(value = "cv", required=false) String cv) {
		Coach coachs = (Coach)request.getAttribute("coach",WebRequest.SCOPE_SESSION);
		String email = coachs.getEmail();	
		String response;
		Coach coach = new Coach();
		coach.setNom(nom);
		coach.setPrenom(prenom);
		coach.setTelephone(telephone);
		coach.setAdresse(adresse);
		coach.setVille(ville);
		coach.setPays(pays);
		coach.setCv(cv);
		coach.setDescription(description);
		coach.setMontant(montant);
		
		int[] tab = coachs.listeCours(email);
		String[] tabintitule = new String[coachs.nombreCours(email)];
		//int[] tab2 = coach.listeCoursTout2();
		for(int i=0;i<tab.length;i++) {
			
			tabintitule[i] = coachs.getIntitule(tab[i]);
			System.out.println(tabintitule[i]);
		}
		
		model.addAttribute("listecours",tab);
		model.addAttribute("listecours2",tabintitule);
		
		if(coach.update(email)) {
			request.setAttribute("coach",coach , WebRequest.SCOPE_SESSION);
			response = "0";
			model.addAttribute("error",response);
			return "/coach/parametre";
		}else {
			response = "1";
			model.addAttribute("error",response);
			return "/coach/parametre";
		}
			
	}
	
	@RequestMapping(value="/coach/compte", method=RequestMethod.GET)
	public String ParametreCoach(Model model,WebRequest request) {
		if(request.getAttribute("coach", WebRequest.SCOPE_SESSION)==null) {
			return "coach/login";
		}
		
		Coach coach = (Coach)request.getAttribute("coach",WebRequest.SCOPE_SESSION);
		coach.dateFromCoach(coach.getEmail());
		
		int[] tab = coach.listeCours(coach.getEmail());
		String[] tabintitule = new String[coach.nombreCours(coach.getEmail())];
		//int[] tab2 = coach.listeCoursTout2();
		for(int i=0;i<tab.length;i++) {
			
			tabintitule[i] = coach.getIntitule(tab[i]);
			System.out.println(tabintitule[i]);
		}
		
		model.addAttribute("coach",coach);
		model.addAttribute("listecours",tab);
		model.addAttribute("listecours2",tabintitule);
		
	
		return "coach/parametre";
	}
	
}
