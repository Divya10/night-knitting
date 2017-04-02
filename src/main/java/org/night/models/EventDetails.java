package org.night.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EventDetails {

	
	@Id @GeneratedValue
	private int not_id;
	private String noticedet;
	private String noticetitle;
	public String getNoticetitle() {
		return noticetitle;
	}
	public void setNoticetitle(String noticetitle) {
		this.noticetitle = noticetitle;
	}
	public int getNot_id() {
		return not_id;
	}
	public void setNot_id(int not_id) {
		this.not_id = not_id;
	}
	public String getNoticedet() {
		return noticedet;
	}
	public void setNoticedet(String noticedet) {
		this.noticedet = noticedet;
	}
	
}
