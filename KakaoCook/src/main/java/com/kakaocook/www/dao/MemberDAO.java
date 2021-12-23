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

import com.kakaocook.www.dto.MemberDTO;

public class MemberDAO {
	private static MemberDAO mDao = new MemberDAO();
	private DataSource dataSource;
	private int sizeOfPage = 10;
	
	private MemberDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/myPortfolio");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static MemberDAO getMDao() {
		return mDao;
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
	public ArrayList<MemberDTO> listDAO(int curPage, String sortWay, String userType){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		String sql1 = "select * from memberboard where granted = ? order by superGranted desc, granted desc, name limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql2 = "select * from memberboard where granted = ? order by superGranted desc, granted desc, id limit "+curPage*sizeOfPage+", "+sizeOfPage;
		
		try {
			if(sortWay.equals("nameSort")) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, userType);
			} else if(sortWay.equals("IDSort")) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, userType);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO mDto = new MemberDTO();
				mDto.setName(rs.getString("name"));
				mDto.setId(rs.getString("id"));
				mDto.setPw(rs.getString("pw"));
				mDto.setBirthYear(rs.getInt("birthYear"));
				mDto.setBirthMonth(rs.getInt("birthMonth"));
				mDto.setBirthDate(rs.getInt("birthDate"));
				mDto.setSex(rs.getString("sex"));
				mDto.setPostcode(rs.getString("postcode"));
				mDto.setRoadAddress(rs.getString("roadAddress"));
				mDto.setJibunAddress(rs.getString("jibunAddress"));
				mDto.setDetailAddress(rs.getString("detailAddress"));
				mDto.setExtraAddress(rs.getString("extraAddress"));
				mDto.setPhoneNumber(rs.getString("phoneNumber"));
				mDto.setEmail(rs.getString("email"));
				mDto.setGranted(rs.getString("granted"));
				mDto.setSuperGranted(rs.getString("superGranted"));
				list.add(mDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	public ArrayList<MemberDTO> listDAO(int curPage, String sortWay, String userType, String search_contents){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		String sql1 = "select * from memberboard where granted = ? and name like ? or id like ? order by superGranted desc, granted desc, name limit "+curPage*sizeOfPage+", "+sizeOfPage;
		String sql2 = "select * from memberboard where granted = ? and name like ? or id like ? order by superGranted desc, granted desc, id limit "+curPage*sizeOfPage+", "+sizeOfPage;
		
		try {
			if(sortWay.equals("nameSort")) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, userType);
				pstmt.setString(2, "%"+search_contents+"%");
				pstmt.setString(3, "%"+search_contents+"%");
			} else if(sortWay.equals("IDSort")) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, userType);
				pstmt.setString(2, "%"+search_contents+"%");
				pstmt.setString(3, "%"+search_contents+"%");
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO mDto = new MemberDTO();
				mDto.setName(rs.getString("name"));
				mDto.setId(rs.getString("id"));
				mDto.setPw(rs.getString("pw"));
				mDto.setBirthYear(rs.getInt("birthYear"));
				mDto.setBirthMonth(rs.getInt("birthMonth"));
				mDto.setBirthDate(rs.getInt("birthDate"));
				mDto.setSex(rs.getString("sex"));
				mDto.setPostcode(rs.getString("postcode"));
				mDto.setRoadAddress(rs.getString("roadAddress"));
				mDto.setJibunAddress(rs.getString("jibunAddress"));
				mDto.setDetailAddress(rs.getString("detailAddress"));
				mDto.setExtraAddress(rs.getString("extraAddress"));
				mDto.setPhoneNumber(rs.getString("phoneNumber"));
				mDto.setEmail(rs.getString("email"));
				mDto.setGranted(rs.getString("granted"));
				mDto.setSuperGranted(rs.getString("superGranted"));
				list.add(mDto);
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
		
		String sql = "select count(*) from memberboard";
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
	public float getNumOfUser() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		float numOfUser = 0;
		
		String sql = "select count(*) from memberboard where granted = 'N'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				numOfUser = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return numOfUser;
	}
	public float getRateOfSex(String sex) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		float rate = 0;
		float numOfUser = 0;
		
		String sql = "select count(*) from memberboard where granted = 'N' and sex = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sex);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				numOfUser = getNumOfUser();
				rate = (float)rs.getInt(1) / numOfUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return rate;
	}
	public float getRateOfAge(int beginAge, int endAge) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		float rate = 0;
		float numOfUser = 0;
		
		String sql = "select count(*) from memberboard where granted = 'N' and "
				+ "(? <= (DATE_FORMAT(CURDATE(), '%Y') - birthYear)) and "
				+ "((DATE_FORMAT(CURDATE(), '%Y') - birthYear)+1 <= ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginAge);
			pstmt.setInt(2, endAge);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				numOfUser = getNumOfUser();
				rate = (float)(Math.round(((float)rs.getInt(1) / numOfUser) * 100)) / 100;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return rate;
	}
	public MemberDTO loginDAO(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from memberboard where id=? and pw=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getId());
			pstmt.setString(2, mDto.getPw());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mDto.setName(rs.getString("name"));
				mDto.setId(rs.getString("id"));
				mDto.setPw(rs.getString("pw"));
				mDto.setBirthYear(rs.getInt("birthYear"));
				mDto.setBirthMonth(rs.getInt("birthMonth"));
				mDto.setBirthDate(rs.getInt("birthDate"));
				mDto.setSex(rs.getString("sex"));
				mDto.setPostcode(rs.getString("postcode"));
				mDto.setRoadAddress(rs.getString("roadAddress"));
				mDto.setJibunAddress(rs.getString("jibunAddress"));
				mDto.setDetailAddress(rs.getString("detailAddress"));
				mDto.setExtraAddress(rs.getString("extraAddress"));
				mDto.setPhoneNumber(rs.getString("phoneNumber"));
				mDto.setEmail(rs.getString("email"));
				mDto.setGranted(rs.getString("granted"));
			} else {
				mDto = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return mDto;
	}
	public void registerDAO(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "insert into memberboard(id, pw, name, birthYear, birthMonth, birthDate, sex,"
				+ "postcode, roadAddress, jibunAddress, detailAddress, extraAddress,"
				+ "phoneNumber, email)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getId());
			pstmt.setString(2, mDto.getPw());
			pstmt.setString(3, mDto.getName());
			pstmt.setInt(4, mDto.getBirthYear());
			pstmt.setInt(5, mDto.getBirthMonth());
			pstmt.setInt(6, mDto.getBirthDate());
			pstmt.setString(7, mDto.getSex());
			pstmt.setString(8, mDto.getPostcode());
			pstmt.setString(9, mDto.getRoadAddress());
			pstmt.setString(10, mDto.getJibunAddress());
			pstmt.setString(11, mDto.getDetailAddress());
			pstmt.setString(12, mDto.getExtraAddress());
			pstmt.setString(13, mDto.getPhoneNumber());
			pstmt.setString(14, mDto.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}		
	}
	public boolean duplicateIdCheck(String inputID){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sql = "select id from memberboard where id=?";
        boolean flag = false;

    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
        return flag;
    }
	public void modifyDAO(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;

		String sql = "update memberboard set pw=?, name= ?, birthYear=?, birthMonth=?, birthDate=?, sex=?,"
				+ "postcode=?, roadAddress=?, jibunAddress=?, detailAddress=?, extraAddress=?,"
				+ "phoneNumber=?, email=? where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getPw());
			pstmt.setString(2, mDto.getName());
			pstmt.setInt(3, mDto.getBirthYear());
			pstmt.setInt(4, mDto.getBirthMonth());
			pstmt.setInt(5, mDto.getBirthDate());
			pstmt.setString(6, mDto.getSex());
			pstmt.setString(7, mDto.getPostcode());
			pstmt.setString(8, mDto.getRoadAddress());
			pstmt.setString(9, mDto.getJibunAddress());
			pstmt.setString(10, mDto.getDetailAddress());
			pstmt.setString(11, mDto.getExtraAddress());
			pstmt.setString(12, mDto.getPhoneNumber());
			pstmt.setString(13, mDto.getEmail());
			pstmt.setString(14, mDto.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}	
	}
	public void deleteDAO(String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "delete from memberboard where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}	
	}
	public void chkBookmark(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		String sql = "select count(bookmark) from memberboard where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
				if(cnt == 0) {
					setBookmark(mDto);
				} else {
					updateBookmark(mDto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
	}
	public void setBookmark(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update memberboard set bookmark = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(mDto.getBookmark()));
			pstmt.setString(2, mDto.getId());
			pstmt.executeUpdate();
			addBookmarkCnt(mDto);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void addBookmarkCnt(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update recipeboard set bookmarkCnt = bookmarkCnt+1 where no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mDto.getBookmark());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void updateBookmark(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update memberboard set bookmark = concat(bookmark, (concat('||', ?))) where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(mDto.getBookmark()));
			pstmt.setString(2, mDto.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public ArrayList<Integer> getBookmark(String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String bookmarkList_str = "";
		ArrayList<Integer> bookmarkList = new ArrayList<Integer>();
		
		String sql = "select bookmark from memberboard where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bookmarkList_str = rs.getString(1);
			}
			StringTokenizer tokenizer = new StringTokenizer(bookmarkList_str,"||");
			while(tokenizer.hasMoreTokens()) {
				bookmarkList.add(Integer.valueOf(tokenizer.nextToken()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
		return bookmarkList;
	}
	public void deleteBookmarkDAO(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ArrayList<Integer> bookmarkList = getBookmark(mDto.getId());
		
		String bookmark_str = "";
		String sql = "update memberboard set bookmark = replace(bookmark, ?, '') where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "||"+(mDto.getBookmark()));
			pstmt.setString(2, mDto.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void chkGrade(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		String sql = "select count(assessGradeRecipe) from memberboard where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
				if(cnt == 0) {
					setAssessGradeRecipe(mDto);
				} else {
					updateAssessGradeRecipe(mDto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
	}
	public void setAssessGradeRecipe(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update memberboard set assessGradeRecipe = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(mDto.getAssessGradeRecipe()));
			pstmt.setString(2, mDto.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void updateAssessGradeRecipe(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update memberboard set assessGradeRecipe = concat(assessGradeRecipe, (concat('||', ?))) where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(mDto.getAssessGradeRecipe()));
			pstmt.setString(2, mDto.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public ArrayList<Integer> getAssessGradeRecipe(String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String assessGradeRecipe_str = "";
		ArrayList<Integer> assessGradeRecipe_list = new ArrayList<Integer>();
		
		String sql = "select assessGradeRecipe from memberboard where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				assessGradeRecipe_str = rs.getString(1);
			}
			StringTokenizer tokenizer = new StringTokenizer(assessGradeRecipe_str,"||");
			while(tokenizer.hasMoreTokens()) {
				assessGradeRecipe_list.add(Integer.valueOf(tokenizer.nextToken()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			return assessGradeRecipe_list;
		} finally {
			close(pstmt, conn);
		}
		return assessGradeRecipe_list;
	}
	public void chkGradeNum(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		String sql = "select count(assessGradeNum) from memberboard where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
				if(cnt == 0) {
					setAssessGradeNum(mDto);
				} else {
					updateAssessGradeNum(mDto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
	}
	public void setAssessGradeNum(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update memberboard set assessGradeNum = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(mDto.getAssessGradeNum()));
			pstmt.setString(2, mDto.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public void updateAssessGradeNum(MemberDTO mDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update memberboard set assessGradeNum = concat(assessGradeNum, (concat('||', ?))) where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(mDto.getAssessGradeNum()));
			pstmt.setString(2, mDto.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
	public ArrayList<Integer> getAssessGradeNum(String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String assessGradeNum_str = "";
		ArrayList<Integer> assessGradeNum_list = new ArrayList<Integer>();
		
		String sql = "select assessGradeNum from memberboard where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				assessGradeNum_str = rs.getString(1);
			}
			StringTokenizer tokenizer = new StringTokenizer(assessGradeNum_str,"||");
			while(tokenizer.hasMoreTokens()) {
				assessGradeNum_list.add(Integer.valueOf(tokenizer.nextToken()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			return assessGradeNum_list;
		} finally {
			close(pstmt, conn);
		}
		return assessGradeNum_list;
	}
	public void updateGrantedDAO(String id, String grantedSwitch) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update memberboard set granted = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, grantedSwitch);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}
}
