package com.kakaocook.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.kakaocook.www.dto.FreezerDTO;
import com.kakaocook.www.dto.GroceryDTO;
import com.kakaocook.www.dto.RecipeDTO;

public class FreezerDAO {
	private static FreezerDAO fDao = new FreezerDAO();
	private DataSource dataSource;
	private int sizeOfPage = 12;
	
	private FreezerDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/myPortfolio");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static FreezerDAO getFDao() {
		return fDao;
	}
	public ArrayList<FreezerDTO> getFDto(String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FreezerDTO> list = new ArrayList<FreezerDTO>();
		
		String sql = "select * from freezerboard where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FreezerDTO fDto = new FreezerDTO();
				fDto.setNo(rs.getInt("no"));
				fDto.setName(rs.getString("name"));
				fDto.setCode(rs.getString("code"));
				fDto.setType(rs.getString("type"));
				fDto.setPackage_amount(rs.getInt("package_amount"));
				fDto.setPackage_unit(rs.getString("package_unit"));
				fDto.setTotal_amount(rs.getInt("total_amount"));
				fDto.setNumOfPurchase(rs.getInt("numOfPurchase"));
				fDto.setPurchase_date(rs.getString("purchase_date"));
				fDto.setShelf_life(rs.getString("shelf_life"));
				fDto.setStorage_method(rs.getString("storage_method"));
				list.add(fDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public FreezerDTO getFDtoFromID(String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreezerDTO fDto = new FreezerDTO();
		
		String sql = "select * from freezerboard where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				fDto.setNo(rs.getInt("no"));
				fDto.setName(rs.getString("name"));
				fDto.setCode(rs.getString("code"));
				fDto.setType(rs.getString("type"));
				fDto.setPackage_amount(rs.getInt("package_amount"));
				fDto.setPackage_unit(rs.getString("package_unit"));
				fDto.setTotal_amount(rs.getInt("total_amount"));
				fDto.setNumOfPurchase(rs.getInt("numOfPurchase"));
				fDto.setPurchase_date(rs.getString("purchase_date"));
				fDto.setShelf_life(rs.getString("shelf_life"));
				fDto.setStorage_method(rs.getString("storage_method"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return fDto;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void close(PreparedStatement pstmt, Connection conn) {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<FreezerDTO> listDAO(String id, String freezer_type, int curPage, String sortWay){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FreezerDTO> list = new ArrayList<FreezerDTO>();
		
		String sql1 = "select * from freezerboard where id =? and type like ? order by purchase_date desc, no desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql2 = "select * from freezerboard where id =? and type like ? order by shelf_life limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql3 = "select * from freezerboard where id =? and type like ? order by name limit "+curPage*sizeOfPage+", "+sizeOfPage;
		if(freezer_type.equals("all")) {
			sql1 = "select * from freezerboard where id =? order by purchase_date desc, no desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
			sql2 = "select * from freezerboard where id =? order by shelf_life limit "+curPage*sizeOfPage+", "+sizeOfPage;
			sql3 = "select * from freezerboard where id =? order by name limit "+curPage*sizeOfPage+", "+sizeOfPage;
		}
		
		try {
			if(sortWay.equals("latestSort")) {
				pstmt = conn.prepareStatement(sql1);
				if(!(freezer_type.equals("all"))) {
					pstmt.setString(1, id);
					pstmt.setString(2, "%"+freezer_type+"%");
				} else {
					pstmt.setString(1, id);
				}
			} else if(sortWay.equals("lifeSort")) {
				pstmt = conn.prepareStatement(sql2);
				if(!(freezer_type.equals("all"))) {
					pstmt.setString(1, id);
					pstmt.setString(2, "%"+freezer_type+"%");
				} else {
					pstmt.setString(1, id);
				}
			} else if(sortWay.equals("alphabeticalSort")) {
				pstmt = conn.prepareStatement(sql3);
				if(!(freezer_type.equals("all"))) {
					pstmt.setString(1, id);
					pstmt.setString(2, "%"+freezer_type+"%");
				} else {
					pstmt.setString(1, id);
				}
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FreezerDTO fDto = new FreezerDTO();
				fDto.setNo(rs.getInt("no"));
				fDto.setName(rs.getString("name"));
				fDto.setCode(rs.getString("code"));
				fDto.setType(rs.getString("type"));
				fDto.setPackage_amount(rs.getInt("package_amount"));
				fDto.setPackage_unit(rs.getString("package_unit"));
				fDto.setTotal_amount(rs.getInt("total_amount"));
				fDto.setNumOfPurchase(rs.getInt("numOfPurchase"));
				fDto.setPurchase_date(rs.getString("purchase_date"));
				fDto.setShelf_life(rs.getString("shelf_life"));
				fDto.setStorage_method(rs.getString("storage_method"));
				list.add(fDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public int getTotalPage() {
		int listSize = getListSize();
		if(listSize == 0) {
			return 0;
		}
		int totalPage = listSize/sizeOfPage;
		
		if(listSize%sizeOfPage == 0) {
			totalPage--;
		}
		return totalPage;
	}
	public int getListSize() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from freezerboard";
		int listSize = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listSize = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return listSize;
	}
	public void insertDAO(FreezerDTO fDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		GroceryDTO gDto = getRestOfInfo(fDto);
		
		String sql = "insert into freezerboard(id, no, name, code, type, package_amount, package_unit, "
				+ "total_amount, numOfPurchase, purchase_date, shelf_life, storage_method) "
				+ "value(?, ?, ?, ?, ?, ?, ?, ?, ?, CURDATE(), ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fDto.getId());
			pstmt.setInt(2, gDto.getNo());
			pstmt.setString(3, gDto.getName());
			pstmt.setString(4, fDto.getCode());
			pstmt.setString(5, gDto.getType());
			pstmt.setInt(6, (gDto.getPackage_amount()*fDto.getNumOfPurchase()));
			pstmt.setString(7, gDto.getPackage_unit());
			pstmt.setInt(8, gDto.getTotal_amount());
			pstmt.setInt(9, fDto.getNumOfPurchase());
			pstmt.setString(10, gDto.getShelf_life());
			pstmt.setString(11, gDto.getStorage_method());
			pstmt.executeUpdate();
			subFromGroceyboard(fDto);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void subFromGroceyboard(FreezerDTO fDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update groceryboard set total_amount = (total_amount - ?) where code = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fDto.getNumOfPurchase());
			pstmt.setString(2, fDto.getCode());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public GroceryDTO getRestOfInfo(FreezerDTO fDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GroceryDTO gDto = new GroceryDTO();
		
		String sql = "select * from groceryboard where code = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fDto.getCode());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				gDto.setNo(rs.getInt("no"));
				gDto.setName(rs.getString("name"));
				gDto.setType(rs.getString("type"));
				gDto.setPackage_amount(rs.getInt("package_amount"));
				gDto.setPackage_unit(rs.getString("package_unit"));
				gDto.setTotal_amount(rs.getInt("total_amount"));
				gDto.setShelf_life(rs.getString("shelf_life"));
				gDto.setStorage_method(rs.getString("storage_method"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return gDto;
	}
	public ArrayList<FreezerDTO> getFreezerlist(String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FreezerDTO> list = new ArrayList<FreezerDTO>();
		
		String sql = "select name from freezerboard where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FreezerDTO fDto = new FreezerDTO();
				fDto.setName(rs.getString(1));
				list.add(fDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public ArrayList<RecipeDTO> possible_listDAO(ArrayList<FreezerDTO> f_list){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RecipeDTO> r_list = new ArrayList<RecipeDTO>();
		RecipeDAO rDao = RecipeDAO.getRDao();
		
		String sql = "select * from recipeboard where ingredient_name like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			for(int i=0;i<f_list.size();i++) {
				pstmt.setString(1, "%"+(f_list.get(i)).getName()+"%");
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					RecipeDTO rDto = new RecipeDTO();
					rDto.setNo(rs.getInt("no"));
					rDto.setId(rs.getString("id"));
					rDto.setName(rs.getString("name"));
					rDto.setTitle(rs.getString("title"));
					rDto.setRecipe_type(rs.getString("recipe_type"));
					rDto.setFood_type(rs.getString("food_type"));
					rDto.setIngredient_type(rs.getString("ingredient_type"));
					rDto.setMethod_type(rs.getString("method_type"));
					rDto.setRecipe_thumbnail_image((rs.getString("recipe_thumbnail_image")).substring(3));
					rDto.setIngredient_name(rs.getString("ingredient_name"));
					rDto.setIngredient_amount(rs.getString("ingredient_amount"));
					rDto.setRecipe_image(rs.getString("recipe_image"));
					rDto.setrCnt(rs.getInt("rCnt"));
					rDto.setGradeAverage(rDao.getAveagegrade(rs.getInt("gradeNum"), rs.getInt("gradeCnt"), rs.getInt("no")));
					r_list.add(rDto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return r_list;
	}
	public ArrayList<FreezerDTO> getRestOfShelfLife(ArrayList<FreezerDTO> f_list, String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FreezerDTO> s_list = new ArrayList<FreezerDTO>();
		
		String sql = "select (DATEDIFF(?, CURDATE())), no "
				+ "from freezerboard where id = ? and code = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, id);
			for(int i=0;i<f_list.size();i++) {
				pstmt.setString(1, (f_list.get(i)).getShelf_life());
				pstmt.setString(3, (f_list.get(i)).getCode());
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					FreezerDTO fDto = new FreezerDTO();
					fDto.setNo(rs.getInt(2));
					fDto.setId(id);
					fDto.setName((f_list.get(i)).getName());
					fDto.setCode((f_list.get(i)).getCode());
					fDto.setRest_of_shelf_life(Integer.valueOf(rs.getString(1)));
					s_list.add(fDto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		Collections.sort(s_list);
		return s_list;
	}
	public int getNumOfFreezerList(String id, String freezer_type) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numOfFreezerList = 0;
		
		String sql1 = "select count(*) from freezerboard where id = ?";
		String sql2 = "select count(*) from freezerboard where id = ? and type like ?";
		
		try {
			if((freezer_type == null)||(freezer_type.equals(""))||(freezer_type.equals("all"))) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, id);
			} else {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, id);
				pstmt.setString(2, freezer_type);
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				numOfFreezerList = rs.getInt(1); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return numOfFreezerList;
	}
	public void deleteDAO(FreezerDTO fDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "delete from freezerboard where id = ? and no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fDto.getId());
			pstmt.setInt(2, fDto.getNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void modifyDAO(FreezerDTO fDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update freezerboard set package_amount = ? where id = ? and no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fDto.getPackage_amount());
			pstmt.setString(2, fDto.getId());
			pstmt.setInt(3, fDto.getNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void useRecipeDAO(RecipeDTO rDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update freezerboard set package_amount = ? where id = ? and no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
}
