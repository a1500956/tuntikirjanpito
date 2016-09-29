package com.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.Henkilot;
import com.beans.HenkilotImpl;
import com.beans.Tunnit;
import com.dao.TuntiDAO;


@Controller
@RequestMapping (value="")
public class TuntikirjausController {
	
	@Inject
	private TuntiDAO dao;
	
	public TuntiDAO getDao() {
		return dao;
	}

	public void setDao(TuntiDAO dao) {
		this.dao = dao;
	}
	
	//Tuntien listaus ja formi
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getView(Model model){
		List<HenkilotImpl> henkilot = dao.haeTunnit();
		model.addAttribute("henkilot", henkilot);
		HenkilotImpl tyhjaHenkilo = new HenkilotImpl();
		Tunnit  tyhjatTunnit = new Tunnit();
		model.addAttribute("henkilo", tyhjaHenkilo);
		model.addAttribute("tunnit", tyhjatTunnit);
		System.out.println("halojaa");
		return "index";
	}
	// K�ytt�j�n tuntien lis�ys
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String create( @ModelAttribute(value="henkilo") HenkilotImpl henkilot){
		dao.talleta(henkilot);
		return "redirect:/";
	}
	
	
//	@RequestMapping(value="/", method=RequestMethod.GET)
//	public String getCreateForm(Model model) {	
//		Henkilot tyhjaHenkilo = new HenkilotImpl();
//		tyhjaHenkilo.setEtunimi("oletusetunimi");
//		
//		model.addAttribute("henkilo", tyhjaHenkilo);
//		return "index	";
//	}
	

//	//FORMIN TEKEMINEN
//	@RequestMapping(value="uusi", method=RequestMethod.GET)
//	public String getCreateForm(Model model) {
//		Henkilot tyhjaHenkilo = new Henkilot();
//		tyhjaHenkilo.setEtunimi("oletusetunimi");
//		
//		model.addAttribute("henkilo", tyhjaHenkilo);
//		return "henk/createForm";
//	}
	
	

}
