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

public class GroceryDAO {
	public static GroceryDAO gDao = new GroceryDAO();
	private DataSource dataSource;
	private int sizeOfPage = 12;
	
	private GroceryDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/myPortfolio");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static GroceryDAO getGDao() {
		return gDao;
	}
	public GroceryDTO getGDto(GroceryDTO gDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from groceryboard where code = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gDto.getCode());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				gDto.setNo(rs.getInt("no"));
				gDto.setName(rs.getString("name"));
				gDto.setCode(rs.getString("code"));
				gDto.setType(rs.getString("type"));
				gDto.setPackage_amount(rs.getInt("package_amount"));
				gDto.setPackage_unit(rs.getString("package_unit"));
				gDto.setTotal_amount(rs.getInt("total_amount"));
				gDto.setShelf_life(rs.getString("shelf_life"));
				gDto.setStorage_method(rs.getString("storage_method"));
				gDto.setInsertCnt(rs.getInt("insertCnt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return gDto;
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
	public ArrayList<GroceryDTO> listDAO(String grocery_type, int curPage, String sortWay){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<GroceryDTO> list = new ArrayList<GroceryDTO>();
		
		String sql1 = "select * from groceryboard where type like ? order by no desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql2 = "select * from groceryboard where type like ? order by insertCnt desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql3 = "select * from groceryboard where type like ? order by shelf_life limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql4 = "select * from groceryboard where type like ? order by name limit "+curPage*sizeOfPage+", "+sizeOfPage;
		if(grocery_type.equals("all")) {
			sql1 = "select * from groceryboard order by no desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
			sql2 = "select * from groceryboard order by insertCnt desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
			sql3 = "select * from groceryboard order by shelf_life limit "+curPage*sizeOfPage+", "+sizeOfPage;
			sql4 = "select * from groceryboard order by name limit "+curPage*sizeOfPage+", "+sizeOfPage;
		}
		
		try {
			if(sortWay.equals("latestSort")) {
				pstmt = conn.prepareStatement(sql1);
				if(!(grocery_type.equals("all"))) {
					pstmt.setString(1, "%"+grocery_type+"%");
				}
			} else if(sortWay.equals("insertCnt")) {
				pstmt = conn.prepareStatement(sql2);
				if(!(grocery_type.equals("all"))) {
					pstmt.setString(1, "%"+grocery_type+"%");
				}
			} else if(sortWay.equals("shelflifeSort")) {
				pstmt = conn.prepareStatement(sql3);
				if(!(grocery_type.equals("all"))) {
					pstmt.setString(1, "%"+grocery_type+"%");
				}
			} else if(sortWay.equals("alphabeticalSort")) {
				pstmt = conn.prepareStatement(sql4);
				if(!(grocery_type.equals("all"))) {
					pstmt.setString(1, "%"+grocery_type+"%");
				}
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GroceryDTO gDto = new GroceryDTO();
				gDto.setNo(rs.getInt("no"));
				gDto.setName(rs.getString("name"));
				gDto.setCode(rs.getString("code"));
				gDto.setType(rs.getString("type"));
				gDto.setPackage_amount(rs.getInt("package_amount"));
				gDto.setPackage_unit(rs.getString("package_unit"));
				gDto.setTotal_amount(rs.getInt("total_amount"));
				gDto.setShelf_life(rs.getString("shelf_life"));
				gDto.setRest_of_shelf_life(getRestOfShelfLife(rs.getString("code"), rs.getString("shelf_life")));
				gDto.setStorage_method(rs.getString("storage_method"));
				gDto.setInsertCnt(rs.getInt("insertCnt"));
				list.add(gDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public ArrayList<GroceryDTO> listDAO(String grocery_type, int curPage, String sortWay, String search_contents){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<GroceryDTO> list = new ArrayList<GroceryDTO>();
		
		String sql1 = "select * from groceryboard where type like ? and name like ? order by no desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql2 = "select * from groceryboard where type like ? and name like ? order by insertCnt desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql3 = "select * from groceryboard where type like ? and name like ? order by shelf_life limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql4 = "select * from groceryboard where type like ? and name like ? order by name limit "+curPage*sizeOfPage+", "+sizeOfPage;
		if(grocery_type.equals("all")) {
			sql1 = "select * from groceryboard where name like ? order by no desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
			sql2 = "select * from groceryboard where name like ? order by insertCnt desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
			sql3 = "select * from groceryboard where name like ? order by shelf_life limit "+curPage*sizeOfPage+", "+sizeOfPage;
			sql4 = "select * from groceryboard where name like ? order by name limit "+curPage*sizeOfPage+", "+sizeOfPage;
		}
		
		try {
			if(sortWay.equals("latestSort")) {
				pstmt = conn.prepareStatement(sql1);
				if(!(grocery_type.equals("all"))) {
					pstmt.setString(1, "%"+grocery_type+"%");
					pstmt.setString(2, "%"+search_contents+"%");
				} else {
					pstmt.setString(1, "%"+search_contents+"%");
				}
			} else if(sortWay.equals("insertCnt")) {
				pstmt = conn.prepareStatement(sql2);
				if(!(grocery_type.equals("all"))) {
					pstmt.setString(1, "%"+grocery_type+"%");
					pstmt.setString(2, "%"+search_contents+"%");
				} else {
					pstmt.setString(1, "%"+search_contents+"%");
				}
			} else if(sortWay.equals("shelflifeSort")) {
				pstmt = conn.prepareStatement(sql3);
				if(!(grocery_type.equals("all"))) {
					pstmt.setString(1, "%"+grocery_type+"%");
					pstmt.setString(2, "%"+search_contents+"%");
				} else {
					pstmt.setString(1, "%"+search_contents+"%");
				}
			} else if(sortWay.equals("alphabeticalSort")) {
				pstmt = conn.prepareStatement(sql4);
				if(!(grocery_type.equals("all"))) {
					pstmt.setString(1, "%"+grocery_type+"%");
					pstmt.setString(2, "%"+search_contents+"%");
				} else {
					pstmt.setString(1, "%"+search_contents+"%");
				}
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GroceryDTO gDto = new GroceryDTO();
				gDto.setNo(rs.getInt("no"));
				gDto.setName(rs.getString("name"));
				gDto.setCode(rs.getString("code"));
				gDto.setType(rs.getString("type"));
				gDto.setPackage_amount(rs.getInt("package_amount"));
				gDto.setPackage_unit(rs.getString("package_unit"));
				gDto.setTotal_amount(rs.getInt("total_amount"));
				gDto.setShelf_life(rs.getString("shelf_life"));
				gDto.setRest_of_shelf_life(getRestOfShelfLife(rs.getString("code"), rs.getString("shelf_life")));
				gDto.setStorage_method(rs.getString("storage_method"));
				gDto.setInsertCnt(rs.getInt("insertCnt"));
				list.add(gDto);
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
		
		String sql = "select count(*) from groceryboard";
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
	public int getRestOfShelfLife(String code, String shelf_life) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int restOfShelfLife = 0;
		
		String sql = "select (DATEDIFF(?, CURDATE())) from groceryboard where code = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shelf_life);
			pstmt.setString(2, code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				restOfShelfLife = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return restOfShelfLife;
	}
	public ArrayList<GroceryDTO> getOutOfShelfLifeList() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<GroceryDTO> list = new ArrayList<GroceryDTO>();
		
		String sql = "select * from groceryboard where (DATEDIFF(shelf_life, CURDATE())) <= 0";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GroceryDTO gDto = new GroceryDTO();
				gDto.setNo(rs.getInt("no"));
				gDto.setName(rs.getString("name"));
				gDto.setCode(rs.getString("code"));
				gDto.setType(rs.getString("type"));
				gDto.setPackage_amount(rs.getInt("package_amount"));
				gDto.setPackage_unit(rs.getString("package_unit"));
				gDto.setTotal_amount(rs.getInt("total_amount"));
				gDto.setShelf_life(rs.getString("shelf_life"));
				gDto.setRest_of_shelf_life(getRestOfShelfLife(rs.getString("code"), rs.getString("shelf_life")));
				gDto.setStorage_method(rs.getString("storage_method"));
				gDto.setInsertCnt(rs.getInt("insertCnt"));
				list.add(gDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public ArrayList<GroceryDTO> getSoldoutList() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<GroceryDTO> list = new ArrayList<GroceryDTO>();
		
		String sql = "select * from groceryboard where total_amount <= 0";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				GroceryDTO gDto = new GroceryDTO();
				gDto.setNo(rs.getInt("no"));
				gDto.setName(rs.getString("name"));
				gDto.setCode(rs.getString("code"));
				gDto.setType(rs.getString("type"));
				gDto.setPackage_amount(rs.getInt("package_amount"));
				gDto.setPackage_unit(rs.getString("package_unit"));
				gDto.setTotal_amount(rs.getInt("total_amount"));
				gDto.setShelf_life(rs.getString("shelf_life"));
				gDto.setRest_of_shelf_life(getRestOfShelfLife(rs.getString("code"), rs.getString("shelf_life")));
				gDto.setStorage_method(rs.getString("storage_method"));
				gDto.setInsertCnt(rs.getInt("insertCnt"));
				list.add(gDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public ArrayList<GroceryDTO> searchDAO(String search_contents){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<GroceryDTO> list = new ArrayList<GroceryDTO>();
		
		String sql = "select * from groceryboard where name like ? order by no desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search_contents+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GroceryDTO gDto = new GroceryDTO();
				gDto.setNo(rs.getInt("no"));
				gDto.setName(rs.getString("name"));
				gDto.setCode(rs.getString("code"));
				gDto.setType(rs.getString("type"));
				gDto.setPackage_amount(rs.getInt("package_amount"));
				gDto.setPackage_unit(rs.getString("package_unit"));
				gDto.setTotal_amount(rs.getInt("total_amount"));
				gDto.setShelf_life(rs.getString("shelf_life"));
				gDto.setRest_of_shelf_life(getRestOfShelfLife(rs.getString("code"), rs.getString("shelf_life")));
				gDto.setStorage_method(rs.getString("storage_method"));
				gDto.setInsertCnt(rs.getInt("insertCnt"));
				list.add(gDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public void insertDAO(GroceryDTO gDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "insert into groceryboard(name, code, type, package_amount, package_unit, "
				+ "total_amount, shelf_life, storage_method) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gDto.getName());
			pstmt.setString(2, gDto.getCode());
			pstmt.setString(3, gDto.getType());
			pstmt.setInt(4, gDto.getPackage_amount());
			pstmt.setString(5, gDto.getPackage_unit());
			pstmt.setInt(6, gDto.getTotal_amount());
			pstmt.setString(7, gDto.getShelf_life());
			pstmt.setString(8, gDto.getStorage_method());
			pstmt.executeUpdate();
			setCodeNum(gDto);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void setCodeNum(GroceryDTO gDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int maxNo = 0;
		String sql = "select max(no) from groceryboard where type = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gDto.getType());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				maxNo = rs.getInt(1);
				setFinalCode(gDto, maxNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
	}
	public void setFinalCode(GroceryDTO gDto, int maxNo) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update groceryboard set code = ? where name = ?";
		String finalCode = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(maxNo < 10) {
				finalCode = gDto.getCode() + ("0"+maxNo);
			} else {
				finalCode = gDto.getCode() + maxNo;
			}
			pstmt.setString(1, finalCode);
			pstmt.setString(2, gDto.getName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public GroceryDTO viewDAO(GroceryDTO gDto) {
		gDto = getGDto(gDto);
		return gDto;
	}
	public GroceryDTO modifyDAO(GroceryDTO gDto) {
		gDto = getGDto(gDto);
		return gDto;
	}
	public void modifyOKDAO(GroceryDTO gDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update groceryboard set name = ?, type = ?, "
				+ "package_amount = ?, package_unit = ?, total_amount = ?, "
				+ "shelf_life = ?, storage_method = ? where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gDto.getName());
			pstmt.setString(2, gDto.getType());
			pstmt.setInt(3, gDto.getPackage_amount());
			pstmt.setString(4, gDto.getPackage_unit());
			pstmt.setInt(5, gDto.getTotal_amount());
			pstmt.setString(6, gDto.getShelf_life());
			pstmt.setString(7, gDto.getStorage_method());
			pstmt.setInt(8, gDto.getNo());
			pstmt.executeUpdate();
			setCodeNum(gDto);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void modifyCode(GroceryDTO gDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update groceryboard set code = ? where no = ?";
		String finalCode = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(gDto.getNo() < 10) {
				finalCode = gDto.getType() + ("0"+gDto.getNo());
			} else {
				finalCode = gDto.getType() + gDto.getNo();
			}
			pstmt.setString(1, finalCode);
			pstmt.setInt(2, gDto.getNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void deleteOKDAO(String code) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "delete from groceryboard where code = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
}
