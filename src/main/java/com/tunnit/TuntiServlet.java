package com.tunnit;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beans.Henkilot;
import com.beans.Tunnit;
import com.dao.tuntiDAO;

@WebServlet("/TuntiServlet")
public class TuntiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		
		String tunnit = request.getParameter("tunnit");
		if(tunnit != null){
		String kuvaus = request.getParameter("kuvaus");
		String henkilo_id = request.getParameter("henkilo_id");
		
		Henkilot henkilo = new Henkilot();
		henkilo.setId(Integer.parseInt(henkilo_id));
		Tunnit tunnin_tiedot = new Tunnit();		
		tunnin_tiedot.setKuvaus(kuvaus);
		tunnin_tiedot.setTunnit(Integer.parseInt(tunnit));
		henkilo.addTunti(tunnin_tiedot);
		tuntiDAO tDAO = (tuntiDAO) context.getBean("daoLuokka");
		tDAO.talleta(henkilo);
		}
		
		tuntiDAO tDAO = (tuntiDAO) context.getBean("daoLuokka");
		List<Henkilot> henkilot = tDAO.haeTunnit();
		request.setAttribute("henkilot", henkilot);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}