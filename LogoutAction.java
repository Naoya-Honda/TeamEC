package com.internousdev.olive.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.olive.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;

	public String execute(){
		UserInfoDAO dao = new UserInfoDAO();
		String userId = session.get("userId").toString();
		boolean keepId = Boolean.valueOf(String.valueOf(session.get("keepId")));

		int count = dao.logout(userId);
		if(count > 0){
			session.clear();
			if(keepId){
				session.put("keepId", keepId);
				session.put("userId", userId);
			}
			session.put("logined", 0);
		}
		return SUCCESS;
	}

	public Map<String,Object> getSession(){
		return this.session;
	}
	public void setSession(Map<String,Object> session){
		this.session = session;
	}

}
