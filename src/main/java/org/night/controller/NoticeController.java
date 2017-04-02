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

@Controller
public class NoticeController {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public ModelAndView ques(HttpSession httpSession, @RequestParam Map<String, String> requestParams)
			throws IOException, JSONException {
		//String name = (String) httpSession.getAttribute("SESSION_name");
		String admno = (String) httpSession.getAttribute("SESSION_admno");
		ModelAndView model;
		if (admno != null) {
			model = new ModelAndView("notdetails");
			String id = "";
			String det="";
			String title="";
			id = requestParams.get("id");
			Session session = sessionFactory.openSession();
			EventDetails a = (EventDetails) session.get(EventDetails.class, Integer.parseInt(id));
			session.close();
			det=a.getNoticedet();
			title=a.getNoticetitle();

			model.addObject("quesid", id);
			model.addObject("det", det);
			model.addObject("title", title);
			System.out.println(det);
		} else {
			model = new ModelAndView("index");
			model.addObject("invalid", "log in first to continue");
		}

		return model;

	}
	
}
