package org.night.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.night.models.EventDetails;
import org.night.models.StudentDet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @author 
 *
 *controller for login and signup
 */
@Controller
public class LoginController {

	
	@Autowired
	private SessionFactory sessionFactory;
	private StudentDet student;
	
	/**
	 * controller for landing page
	 * @return
	 */
	
			@RequestMapping(value = "/index", method = RequestMethod.GET)
				public ModelAndView indexpage() {

					ModelAndView model = new ModelAndView("index");
					return model;
	}
			
			@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
			public ModelAndView dash(HttpSession httpSession) {

			Session session = sessionFactory.openSession();
				ModelAndView model;
				model = new ModelAndView("dashboard");
				model.addObject("name", student.getName());
				List<EventDetails> notice = session.createCriteria(EventDetails.class).list();
				model.addObject("notice", notice);
				return model;
				
		}
			
			
			/**
			 * controller for signup
			 * @return
			 */
			
			@RequestMapping(value = "/signup", method = RequestMethod.POST)
			public ModelAndView signup(@ModelAttribute("student") org.night.models.StudentDet student){
				Session session = sessionFactory.openSession();
				ModelAndView model = new ModelAndView("index");
				if (session.get(StudentDet.class, student.getAdmno()) == null) {
					
					session.beginTransaction();
					session.save(student);
					session.getTransaction().commit();
					model.addObject("invalid", "Successfully registered, login to proceed!");

				} else
					model.addObject("invalid", "This email is already registered.");
				session.close();
				return model;

			}
			
			/**
			 * controller for login
			 * @param httpSession
			 * @param emailid
			 * @param password
			 * @return
			 */

			@RequestMapping(value = "/login", method = RequestMethod.POST)
			public ModelAndView login(HttpSession httpSession, @RequestParam("id") String admno,
					@RequestParam("pass") String password) {
				ModelAndView model;
				Session session = sessionFactory.openSession();
				student = (StudentDet) session.get(StudentDet.class, admno);
				if (student != null) {
							if (student.getPassword().equals(password)) {
									httpSession.setAttribute("SESSION_admno",student.getAdmno());
									
									if((String) httpSession.getAttribute("SESSION_admno")!=null){
										model = new ModelAndView("dashboard");
										model.addObject("name", student.getName());
										List<EventDetails> notice = session.createCriteria(EventDetails.class).list();
										model.addObject("notice", notice);
						               }
									else{
										model = new ModelAndView("index");
										model.addObject("invalid", "LOG IN FIRST TO CONTINUE");
									}
					} else {
						model = new ModelAndView("index");
						model.addObject("invalid", "invalid details");
					}
				}

				else {
					model = new ModelAndView("index");
					model.addObject("norecord", "no record found");
				}
				session.close();
				return model;
			}
			
			/**
			 * controller for logout
			 * @param httpSession
			 * @return
			 */
			
			
			@RequestMapping(value = "/logout", method = RequestMethod.GET)
			public ModelAndView LogoutController(HttpSession httpSession) {
				httpSession.invalidate();
				ModelAndView model = new ModelAndView("index");
				model.addObject("invalid", "successfully logged out");
				return model;
			}
}
