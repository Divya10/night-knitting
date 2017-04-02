package org.night.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.night.models.EventDetails;
import org.night.models.StudentDet;
import org.night.models.forum;
import org.night.models.forumans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForumController {
	@Autowired
	private SessionFactory sessionFactory;
	private StudentDet student;
	
	
	@RequestMapping(value = "/forum", method = RequestMethod.GET)
	public ModelAndView indexpage(HttpSession httpSession) {

		ModelAndView model = new ModelAndView("forum");
		String admno = (String) httpSession.getAttribute("SESSION_admno");
		model.addObject("id",admno);
		return model;
    }
	
	
	@RequestMapping(value = "/addques", method = RequestMethod.POST)
	public ModelAndView addques(@ModelAttribute("question") org.night.models.forum question,HttpSession httpSession) {
		Session session = sessionFactory.openSession();
		ModelAndView model = new ModelAndView("forum");
		if (session.get(forum.class, question.getQuesid()) == null) {
			session.beginTransaction();
			String admno = (String) httpSession.getAttribute("SESSION_admno");
			question.setStudentid(admno);
			session.save(question);
			session.getTransaction().commit();
			session.close();
			System.out.println(question.getQuesid());
			model.addObject("invalid", "Successfully added question");

		} else

			model.addObject("invalid", "This question is alredy added");

		return model;

	}

	
	/**
	 * controller to answer question
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/answer", method = RequestMethod.GET)
	public ModelAndView dash(HttpSession httpSession) {

	Session session = sessionFactory.openSession();
		ModelAndView model;
		model = new ModelAndView("forumans");
		
		List<forum> ans = session.createCriteria(forum.class).list();
		model.addObject("notice", ans);
		return model;
		
}


	@RequestMapping(value = "/ans", method = RequestMethod.GET)
	public ModelAndView ques(HttpSession httpSession, @RequestParam Map<String, String> requestParams)
			throws IOException, JSONException {
		//String name = (String) httpSession.getAttribute("SESSION_name");
		String admno = (String) httpSession.getAttribute("SESSION_admno");
		ModelAndView model;
		if (admno != null) {
			model = new ModelAndView("ansdet");
			String id = "";
		    String que="";
			id = requestParams.get("id");
			Session session = sessionFactory.openSession();
			forum a = (forum) session.get(forum.class, Integer.parseInt(id));
			session.close();
			que=a.getQues();
			model.addObject("quesid", id);
			model.addObject("que",que);
			
		} else {
			model = new ModelAndView("index");
			model.addObject("invalid", "log in first to continue");
		}

		return model;

	}
	@RequestMapping(value = "/addans", method = RequestMethod.POST)
	public ModelAndView ad(@ModelAttribute("question") org.night.models.forumans question) {
		Session session = sessionFactory.openSession();
		ModelAndView model = new ModelAndView("forum");
		session.get(forum.class, question.getQuesid());
			session.beginTransaction();
			session.save(question);
			session.getTransaction().commit();
			session.close();
			System.out.println(question.getQuesid());
			model.addObject("invalid", "Successfully added answer");

		

		return model;

	}
	
	
	@RequestMapping(value = "/allans", method = RequestMethod.GET)
	public ModelAndView dnghjgj(HttpSession httpSession) {

	Session session = sessionFactory.openSession();
		ModelAndView model;
		model = new ModelAndView("View");
		
		List<forumans> ans = session.createCriteria(forumans.class).list();
		List<forum> ques = session.createCriteria(forum.class).list();
		model.addObject("ans", ans);
		model.addObject("ques",ques);
		return model;
		
}
	
}
