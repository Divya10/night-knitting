package org.night.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.night.models.EventDetails;
import org.night.models.Societies_Det;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * controller for society details and login
 * @author 
 *
 */
@Controller
public class SocietyController {
	@Autowired
	private SessionFactory sessionFactory;
	

	/**
	 * controller for seeing the societies details
	 * @param httpSession
	 * @return
	 */
	

	
	@RequestMapping(value = "/society", method = RequestMethod.GET)
		public ModelAndView society(HttpSession httpSession) {

		Session session = sessionFactory.openSession();
			ModelAndView model = new ModelAndView("society");
			List<Societies_Det> det = session.createCriteria(Societies_Det.class).list();
			model.addObject("details",det);
			return model;
			
}
	
	@RequestMapping(value = "/socdet", method = RequestMethod.GET)
	public ModelAndView ques(HttpSession httpSession, @RequestParam Map<String, String> requestParams)
			throws IOException, JSONException {
		//String name = (String) httpSession.getAttribute("SESSION_name");
		String admno = (String) httpSession.getAttribute("SESSION_admno");
		ModelAndView model;
		if (admno != null) {
			model = new ModelAndView("socdetails");
			String id = "";
			String det="";
			String title="";
			String dept="";
			id = requestParams.get("id");
			Session session = sessionFactory.openSession();
			Societies_Det a = (Societies_Det) session.get(Societies_Det.class,id);
			session.close();
			det=a.getAbout();
			title=a.getSociety_name();
			dept=a.getDept();
			

			model.addObject("quesid", id);
			model.addObject("det", det);
			model.addObject("name", title);
			model.addObject("dept",dept);
			System.out.println(det);
		} else {
			model = new ModelAndView("index");
			model.addObject("invalid", "log in first to continue");
		}

		return model;

	}

}
