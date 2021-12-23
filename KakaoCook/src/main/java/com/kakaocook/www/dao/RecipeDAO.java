package com.kakaocook.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.kakaocook.www.dto.RecipeDTO;

public class RecipeDAO {
	private static RecipeDAO rDao = new RecipeDAO();
	private DataSource dataSource;
	private int sizeOfPage = 12;
	
	private RecipeDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/myPortfolio");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static RecipeDAO getRDao() {
		return rDao;
	}
	public RecipeDTO getRDto(RecipeDTO rDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from recipeboard where no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rDto.getNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rDto.setNo(rs.getInt("no"));
				rDto.setId(rs.getString("id"));
				rDto.setName(rs.getString("name"));
				rDto.setTitle(rs.getString("title"));
				rDto.setRecipe_type(rs.getString("recipe_type"));
				rDto.setFood_type(rs.getString("food_type"));
				rDto.setIngredient_type(rs.getString("ingredient_type"));
				rDto.setMethod_type(rs.getString("method_type"));
				rDto.setRecipe_thumbnail_image(rs.getString("recipe_thumbnail_image"));
				rDto.setIngredient_name(rs.getString("ingredient_name"));
				rDto.setIngredient_amount(rs.getString("ingredient_amount"));
				rDto.setRecipe_image(rs.getString("recipe_image"));
				rDto.setrCnt(rs.getInt("rCnt"));
				rDto.setBookmarkCnt(rs.getInt("bookmarkCnt"));
				rDto.setGradeAverage(getAveagegrade(rs.getInt("gradeNum"), rs.getInt("gradeCnt"), rs.getInt("no")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return rDto;
	}
	public RecipeDTO getRDto(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RecipeDTO rDto = new RecipeDTO();
		
		String sql = "select * from recipeboard where no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rDto.setNo(rs.getInt("no"));
				rDto.setId(rs.getString("id"));
				rDto.setName(rs.getString("name"));
				rDto.setTitle(rs.getString("title"));
				rDto.setRecipe_type(rs.getString("recipe_type"));
				rDto.setFood_type(rs.getString("food_type"));
				rDto.setIngredient_type(rs.getString("ingredient_type"));
				rDto.setMethod_type(rs.getString("method_type"));
				rDto.setRecipe_thumbnail_image(rs.getString("recipe_thumbnail_image"));
				rDto.setIngredient_name(rs.getString("ingredient_name"));
				rDto.setIngredient_amount(rs.getString("ingredient_amount"));
				rDto.setRecipe_image(rs.getString("recipe_image"));
				rDto.setrCnt(rs.getInt("rCnt"));
				rDto.setBookmarkCnt(rs.getInt("bookmarkCnt"));
				rDto.setGradeAverage(getAveagegrade(rs.getInt("gradeNum"), rs.getInt("gradeCnt"), rs.getInt("no")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return rDto;
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
	public ArrayList<RecipeDTO> listDAO(int curPage, String sortWay){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RecipeDTO> list = new ArrayList<RecipeDTO>();
		
		String sql1 = "select * from recipeboard order by no desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql2 = "select * from recipeboard order by rCnt desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql3 = "select * from recipeboard order by bookmarkCnt desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql4 = "select * from recipeboard order by name limit "+curPage*sizeOfPage+", "+sizeOfPage;
		try {
			if(sortWay.equals("latestSort")) {
				pstmt = conn.prepareStatement(sql1);
			} else if(sortWay.equals("rCntSort")) {
				pstmt = conn.prepareStatement(sql2);
			} else if(sortWay.equals("bookmarkCntSort")) {
				pstmt = conn.prepareStatement(sql3);
			} else if(sortWay.equals("alphabeticalSort")) {
				pstmt = conn.prepareStatement(sql4);
			}
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
				rDto.setRecipe_thumbnail_image(rs.getString("recipe_thumbnail_image"));
				rDto.setIngredient_name(rs.getString("ingredient_name"));
				rDto.setIngredient_amount(rs.getString("ingredient_amount"));
				rDto.setRecipe_image(rs.getString("recipe_image"));
				rDto.setrCnt(rs.getInt("rCnt"));
				rDto.setBookmarkCnt(rs.getInt("bookmarkCnt"));
				rDto.setGradeAverage(getAveagegrade(rs.getInt("gradeNum"), rs.getInt("gradeCnt"), rs.getInt("no")));
				list.add(rDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public ArrayList<RecipeDTO> listDAO(int curPage, String sortWay, String search_contents){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RecipeDTO> list = new ArrayList<RecipeDTO>();
		
		String sql1 = "select * from recipeboard where name like ? or title like ? order by no desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql2 = "select * from recipeboard where name like ? or title like ? order by rCnt desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql3 = "select * from recipeboard where name like ? or title like ? order by bookmarkCnt desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql4 = "select * from recipeboard where name like ? or title like ? order by name limit "+curPage*sizeOfPage+", "+sizeOfPage;
		
		try {
			if(sortWay.equals("latestSort")) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, "%"+search_contents+"%");
				pstmt.setString(2, "%"+search_contents+"%");
			} else if(sortWay.equals("rCntSort")) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, "%"+search_contents+"%");
				pstmt.setString(2, "%"+search_contents+"%");
			} else if(sortWay.equals("bookmarkCntSort")) {
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, "%"+search_contents+"%");
				pstmt.setString(2, "%"+search_contents+"%");
			} else if(sortWay.equals("alphabeticalSort")) {
				pstmt = conn.prepareStatement(sql4);
				pstmt.setString(1, "%"+search_contents+"%");
				pstmt.setString(2, "%"+search_contents+"%");
			}
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
				rDto.setRecipe_thumbnail_image(rs.getString("recipe_thumbnail_image"));
				rDto.setIngredient_name(rs.getString("ingredient_name"));
				rDto.setIngredient_amount(rs.getString("ingredient_amount"));
				rDto.setRecipe_image(rs.getString("recipe_image"));
				rDto.setrCnt(rs.getInt("rCnt"));
				rDto.setBookmarkCnt(rs.getInt("bookmarkCnt"));
				rDto.setGradeAverage(getAveagegrade(rs.getInt("gradeNum"), rs.getInt("gradeCnt"), rs.getInt("no")));
				list.add(rDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public ArrayList<RecipeDTO> top_listDAO(){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RecipeDTO> list = new ArrayList<RecipeDTO>();
		
		String sql = "select * from recipeboard order by rCnt desc limit 10";
		
		try {
			pstmt = conn.prepareStatement(sql);
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
				rDto.setBookmarkCnt(rs.getInt("bookmarkCnt"));
				rDto.setGradeAverage(getAveagegrade(rs.getInt("gradeNum"), rs.getInt("gradeCnt"), rs.getInt("no")));
				list.add(rDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public ArrayList<RecipeDTO> ran_listDAO(){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RecipeDTO> list = new ArrayList<RecipeDTO>();
		
		String sql = "select * from recipeboard order by rand() limit 10";
		
		try {
			pstmt = conn.prepareStatement(sql);
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
				rDto.setBookmarkCnt(rs.getInt("bookmarkCnt"));
				rDto.setGradeAverage(getAveagegrade(rs.getInt("gradeNum"), rs.getInt("gradeCnt"), rs.getInt("no")));
				list.add(rDto);
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
		
		String sql = "select count(*) from recipeboard";
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
	public ArrayList<RecipeDTO> searchDAO(String search_contents) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RecipeDTO> list = new ArrayList<RecipeDTO>();
		
		String sql = "select * from recipeboard where title like ? or name like ? or ingredient_name like ? order by no desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search_contents+"%");
			pstmt.setString(2, "%"+search_contents+"%");
			pstmt.setString(3, "%"+search_contents+"%");
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
				rDto.setRecipe_thumbnail_image(rs.getString("recipe_thumbnail_image"));
				rDto.setIngredient_name(rs.getString("ingredient_name"));
				rDto.setIngredient_amount(rs.getString("ingredient_amount"));
				rDto.setRecipe_image(rs.getString("recipe_image"));
				rDto.setrCnt(rs.getInt("rCnt"));
				rDto.setBookmarkCnt(rs.getInt("bookmarkCnt"));
				rDto.setGradeAverage(getAveagegrade(rs.getInt("gradeNum"), rs.getInt("gradeCnt"), rs.getInt("no")));
				list.add(rDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public ArrayList<RecipeDTO> categoryDAO(String r_type, String f_type, String i_type, String m_type, int curPage) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RecipeDTO> list = new ArrayList<RecipeDTO>();
		
		String sql = "select * from recipeboard where "
				+ "recipe_type like ? and food_type like ? and ingredient_type like ? and method_type like ? "
				+ "order by no desc limit "+curPage*sizeOfPage+", "+sizeOfPage;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+r_type+"%");
			pstmt.setString(2, "%"+f_type+"%");
			pstmt.setString(3, "%"+i_type+"%");
			pstmt.setString(4, "%"+m_type+"%");
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
				rDto.setRecipe_thumbnail_image(rs.getString("recipe_thumbnail_image"));
				rDto.setIngredient_name(rs.getString("ingredient_name"));
				rDto.setIngredient_amount(rs.getString("ingredient_amount"));
				rDto.setRecipe_image(rs.getString("recipe_image"));
				rDto.setrCnt(rs.getInt("rCnt"));
				rDto.setBookmarkCnt(rs.getInt("bookmarkCnt"));
				rDto.setGradeAverage(getAveagegrade(rs.getInt("gradeNum"), rs.getInt("gradeCnt"), rs.getInt("no")));
				list.add(rDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public void insertDAO(RecipeDTO rDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "insert into recipeboard(id, name, title, recipe_type, food_type, ingredient_type, method_type,"
				+ "recipe_thumbnail_image, ingredient_name, ingredient_amount, recipe_description, recipe_image)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rDto.getId());
			pstmt.setString(2, rDto.getName());
			pstmt.setString(3, rDto.getTitle());
			pstmt.setString(4, rDto.getRecipe_type());
			pstmt.setString(5, rDto.getFood_type());
			pstmt.setString(6, rDto.getIngredient_type());
			pstmt.setString(7, rDto.getMethod_type());
			pstmt.setString(8, rDto.getRecipe_thumbnail_image());
			pstmt.setString(9, rDto.getIngredient_name());
			pstmt.setString(10, rDto.getIngredient_amount());
			pstmt.setString(11, rDto.getRecipe_description());
			pstmt.setString(12, rDto.getRecipe_image());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public RecipeDTO viewDAO(RecipeDTO rDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update recipeboard set rCnt=rCnt+1 where no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rDto.getNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
		
		rDto = getRDto(rDto);
		return rDto;
	}
	public ArrayList<String> getIngredientName(RecipeDTO rDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> ingredient_name_list = new ArrayList<String>();
		
		String ingredient_name_str = "";
		String sql = "select ingredient_name from recipeboard where no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rDto.getNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ingredient_name_str = rs.getString(1);
			}
			StringTokenizer tokenizer = new StringTokenizer(ingredient_name_str,"||");
			while(tokenizer.hasMoreTokens()) {
				ingredient_name_list.add(tokenizer.nextToken());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return ingredient_name_list;
	}
	public ArrayList<String> getIngredientAmount(RecipeDTO rDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> ingredient_amount_list = new ArrayList<String>();
		
		String ingredient_amount_str = "";
		String sql = "select ingredient_amount from recipeboard where no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rDto.getNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ingredient_amount_str = rs.getString(1);
			}
			StringTokenizer tokenizer = new StringTokenizer(ingredient_amount_str,"||");
			while(tokenizer.hasMoreTokens()) {
				ingredient_amount_list.add(tokenizer.nextToken());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return ingredient_amount_list;
	}
	public ArrayList<String> getRecipeDescription(RecipeDTO rDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> recipe_description_list = new ArrayList<String>();
		
		String description_str = "";
		String sql = "select recipe_description from recipeboard where no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rDto.getNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				description_str = rs.getString(1);
			}
			StringTokenizer tokenizer = new StringTokenizer(description_str,"||");
			while(tokenizer.hasMoreTokens()) {
				recipe_description_list.add(tokenizer.nextToken());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return recipe_description_list;
	}
	public ArrayList<String> getImgPath(RecipeDTO rDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> imgPath_list = new ArrayList<String>();
		
		String path_str = "";
		String sql = "select recipe_image from recipeboard where no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rDto.getNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				path_str = rs.getString(1);
			}
			StringTokenizer tokenizer = new StringTokenizer(path_str,"||");
			while(tokenizer.hasMoreTokens()) {
		    	imgPath_list.add(tokenizer.nextToken());
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return imgPath_list;
	}
	public RecipeDTO modifyDAO(RecipeDTO rDto) {
		rDto = getRDto(rDto);
		return rDto;
	}
	public void modifyOKDAO(RecipeDTO rDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update recipeboard set name=?, title=?,"
				+ "recipe_type=?, food_type=?, ingredient_type=?, method_type=?,"
				+ "recipe_thumbnail_image=?, ingredient_name=?, ingredient_amount=?,"
				+ "recipe_description=?, recipe_image=? where no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rDto.getName());
			pstmt.setString(2, rDto.getTitle());
			pstmt.setString(3, rDto.getRecipe_type());
			pstmt.setString(4, rDto.getFood_type());
			pstmt.setString(5, rDto.getIngredient_type());
			pstmt.setString(6, rDto.getMethod_type());
			pstmt.setString(7, rDto.getRecipe_thumbnail_image());
			pstmt.setString(8, rDto.getIngredient_name());
			pstmt.setString(9, rDto.getIngredient_amount());
			pstmt.setString(10, rDto.getRecipe_description());
			pstmt.setString(11, rDto.getRecipe_image());
			pstmt.setInt(12, rDto.getNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void deleteDAO(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "delete from recipeboard where no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void gradeOKDAO(RecipeDTO rDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update recipeboard set gradeNum = gradeNum + ?, gradeCnt = gradeCnt + 1 where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rDto.getGradeNum());
			pstmt.setInt(2, rDto.getNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public int getAveagegrade(int gradeNum, int gradeCnt, int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int average = 0;
		String sql = "select (? / ?) from recipeboard where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gradeNum);
			pstmt.setInt(2, gradeCnt);
			pstmt.setInt(3, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				average = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ArithmeticException e){
			e.printStackTrace();
			return 0;
		} finally {
			close(pstmt, conn);
		}
		return average;
	}
	public ArrayList<RecipeDTO> getRecipeChart(){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RecipeDTO> chart_list = new ArrayList<RecipeDTO>();
		ArrayList<String> i_column = getIngredientColumn();
		ArrayList<String> m_column = getMethodColumn();
		
		String sql = "select rCnt, bookmarkCnt, ingredient_type, method_type from recipeboard "
				+ "where ingredient_type = ? and method_type = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			for(int i=0;i<i_column.size();i++) {
				pstmt.setString(1, i_column.get(i));
				for(int j=0;j<m_column.size();j++) {
					pstmt.setString(2, m_column.get(j));
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						RecipeDTO rDto = new RecipeDTO();
						if(rs.getInt("rCnt") == 0) {
							rDto.setrCnt(1);
						} else {
							rDto.setrCnt(rs.getInt("rCnt"));
						}
						if(rs.getInt("bookmarkCnt") == 0) {
							rDto.setBookmarkCnt(1);
						} else {
							rDto.setBookmarkCnt(rs.getInt("bookmarkCnt"));
						}
						String i_type = rs.getString("ingredient_type");
						if(i_type.equals("meat_dish")) {
							rDto.setIngredient_type("육류");
						} else if(i_type.equals("seafood_dish")) {
							rDto.setIngredient_type("해물류");
						} else if(i_type.equals("vegetable_dish")) {
							rDto.setIngredient_type("채소류");
						} else if(i_type.equals("processed_dish")) {
							rDto.setIngredient_type("가공식품류");
						} else if(i_type.equals("grain_dish")) {
							rDto.setIngredient_type("곡류");
						} else if(i_type.equals("etc_ingredient_dish")) {
							rDto.setIngredient_type("기타 재료");
						}
						String m_type = rs.getString("method_type");
						if(m_type.equals("roast_dish")) {
							rDto.setMethod_type("볶음");
						} else if(m_type.equals("grill_dish")) {
							rDto.setMethod_type("구이");
						} else if(m_type.equals("boil_dish")) {
							rDto.setMethod_type("끓이기");
						} else if(m_type.equals("boil_down_dish")) {
							rDto.setMethod_type("조림");
						} else if(m_type.equals("steam_dish")) {
							rDto.setMethod_type("찜");
						} else if(m_type.equals("etc_method_dish")) {
							rDto.setMethod_type("기타 조리법");
						}
						chart_list.add(rDto);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return chart_list;
	}
	public ArrayList<String> getIngredientColumn(){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> i_column = new ArrayList<String>();
		
		String sql = "select distinct ingredient_type from recipeboard";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				i_column.add(rs.getString("ingredient_type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return i_column;
	}
	public ArrayList<String> getMethodColumn(){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> m_column = new ArrayList<String>();
		
		String sql = "select distinct method_type from recipeboard";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				m_column.add(rs.getString("method_type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return m_column;
	}
}
