package com.internousdev.olive.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.olive.dao.CartInfoDAO;
import com.internousdev.olive.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class AddCartAction extends ActionSupport implements SessionAware{

	private int productId;
	private int productCount;
	private ArrayList<CartInfoDTO> cartInfoDTOList;
	private Map<String,Object> session;

	public String execute(){

		if(!session.containsKey("tempUserId") && !session.containsKey("userId")){
			return "sessionTimeout";
		}

		if(productCount < 1 || productCount > 5){
			return ERROR;
		}

		String result = ERROR;
		String userId = null;

		int logined = Integer.parseInt(session.get("logined").toString());
		if(logined == 1){
			userId = session.get("userId").toString();
		}else{
			userId = session.get("tempUserId").toString();
		}

		//カートに商品を新規登録もしくは更新
		CartInfoDAO dao = new CartInfoDAO();
		int count = 0;

		//追加する商品と同じものがあるかチェック
		if(dao.checkExistsCart(userId,productId)){
			//商品が存在する場合は更新
			count = dao.updateCartInfo(userId, productId, productCount);

		}else{

			//商品が存在しない場合は登録
			count = dao.regist(userId, productId, productCount);
		}

		if(count > 0){
			cartInfoDTOList = dao.getCartInfoDTOList(userId);
			int cartTotalPrice = 0;
			for(CartInfoDTO dto : cartInfoDTOList){
				cartTotalPrice += dto.getTotalPrice();
			}
			session.put("cartTotalPrice", cartTotalPrice);

			result = SUCCESS;
		}
		return result;

	}

	public int getProductId(){
		return this.productId;
	}
	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductCount(){
		return this.productCount;
	}
	public void setProductCount(int productCount){
		this.productCount = productCount;
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
