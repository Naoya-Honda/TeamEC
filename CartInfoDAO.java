package com.internousdev.olive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.olive.dto.CartInfoDTO;
import com.internousdev.olive.util.DBConnector;

public class CartInfoDAO {

	//Listに格納
	public ArrayList<CartInfoDTO> getCartInfoDTOList(String userId){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();

		String sql = "SELECT "
				+ "ci.id, "
				+ "ci.user_id, "
				+ "ci.product_id, "
				+ "ci.product_count, "
				+ "pi.product_name, "
				+ "pi.product_name_kana, "
				+ "pi.product_description, "
				+ "pi.price, "
				+ "pi.image_file_path, "
				+ "pi.image_file_name, "
				+ "pi.release_date, "
				+ "pi.release_company, "
				+ "(ci.product_count * pi.price) AS totalprice, "
				+ "ci.regist_date as regist_date, "
				+ "ci.update_date as update_date "
				+ "FROM cart_info ci "
				+ "LEFT JOIN product_info pi "
				+ "ON ci.product_id = pi.product_id "
				+ "WHERE ci.user_id = ? "
				+ "ORDER BY update_date DESC,regist_date DESC";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				CartInfoDTO dto = new CartInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getString("user_id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductCount(rs.getInt("product_count"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setTotalPrice(rs.getInt("totalprice"));
				cartInfoDTOList.add(dto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return cartInfoDTOList;
	}

	//商品をカートに登録
	public int regist(String userId,int productId,int productCount){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int count = 0;
		String sql = "INSERT INTO cart_info(user_id,product_id,product_count,regist_date,update_date) "
				+ "VALUES(?,?,?,NOW(),NOW())";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, productCount);

			count = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}

	//カートにすでに存在する商品の購入個数を増やす
	public int updateCartInfo(String userId,int productId,int productCount){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "UPDATE cart_info SET product_count = (product_count + ?), update_date = NOW() "
				+ "WHERE user_id = ? AND product_id = ?";

		int result = 0;

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, productCount);
			ps.setString(2, userId);
			ps.setInt(3, productId);

			result = ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return result;
	}

	//カートに存在している商品を削除
	public int delete(String userId,String productId){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		int count = 0;

		String sql = "DELETE FROM cart_info WHERE user_id = ? AND product_id = ?";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, productId);

			count = ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}

	//カート情報を削除
	public int allDelete(String userId){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		int count = 0;

		String sql = "DELETE FROM cart_info WHERE user_id = ?";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);

			count = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}

	//引数として受け取ったuserIdのユーザーが、productIdの商品のカートに入れた情報が存在するかを判別
	//true:存在する　false:存在しない
	public boolean checkExistsCart(String userId,int productId){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "SELECT COUNT(id) AS COUNT FROM cart_info WHERE user_id = ? AND product_id = ?";

		boolean result = false;

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				if(rs.getInt("COUNT") > 0){
					result = true;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return result;
	}

	//tempUserIdからカート情報をuserIdに紐づける
	public int linkUserId(String userId,String tempUserId,int productId){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		int count = 0;

		String sql = "UPDATE cart_info SET user_id = ?, update_date = NOW() WHERE user_id = ? AND product_id = ?";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, tempUserId);
			ps.setInt(3, productId);

			count = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}

}
