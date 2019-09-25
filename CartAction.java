package com.internousdev.olive.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.olive.dao.CartInfoDAO;
import com.internousdev.olive.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	private ArrayList<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();

	public String execute(){

		if(!session.containsKey("tempUserId") && !session.containsKey("userId")){
			return "sessionTimeout";
		}
		String userId = null;
		CartInfoDAO dao = new CartInfoDAO();
		int logined = Integer.parseInt(session.get("logined").toString());
		if(logined == 1){
			userId = session.get("userId").toString();
		}else{
			userId = session.get("tempUserId").toString();
		}
		cartInfoDTOList = dao.getCartInfoDTOList(userId);
		int cartTotalPrice = 0;
		for(CartInfoDTO dto : cartInfoDTOList){
			cartTotalPrice += dto.getTotalPrice();
		}
		session.put("cartTotalPrice", cartTotalPrice);
		return SUCCESS;
	}
	public ArrayList<CartInfoDTO> getCartInfoDTOList(){
		return this.cartInfoDTOList;
	}
	public void setCartInfoDTOList(ArrayList<CartInfoDTO> cartInfoDTOList){
		this.cartInfoDTOList = cartInfoDTOList;
	}
	public Map<String,Object> getSession(){
		return this.session;
	}
	public void setSession(Map<String,Object> session){
		this.session = session;
	}
}
