package org.night.models;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Societies_Det {

	

		
		@Id
		private String socid;
		private String society_name;
		private String about;
		private String dept;
		
		public String getSocid() {
			return socid;
		}
		public void setSocid(String socid) {
			this.socid = socid;
		}
		public String getSociety_name() {
			return society_name;
		}
		public void setSociety_name(String society_name) {
			this.society_name = society_name;
		}
		public String getAbout() {
			return about;
		}
		public void setAbout(String about) {
			this.about = about;
		}
		public String getDept() {
			return dept;
		}
		public void setDept(String dept) {
			this.dept = dept;
		}
		
		
		
		

	}


