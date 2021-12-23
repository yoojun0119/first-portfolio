package com.kakaocook.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.GroceryDAO;
import com.kakaocook.www.dao.MemberDAO;
import com.kakaocook.www.dao.RecipeDAO;
import com.kakaocook.www.dto.GroceryDTO;
import com.kakaocook.www.dto.MemberDTO;
import com.kakaocook.www.dto.RecipeDTO;

public class AMainCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = MemberDAO.getMDao();
		RecipeDAO rDao = RecipeDAO.getRDao();
		GroceryDAO gDao = GroceryDAO.getGDao();
		
		// 회원관리
		int m_curPage = 0;
		int m_totalPage = 0;
		String m_sortWay = "";
		String m_search_contents = "";
		ArrayList<MemberDTO> m_admin_list = new ArrayList<MemberDTO>();
		ArrayList<MemberDTO> m_user_list = new ArrayList<MemberDTO>();
		
		if(request.getParameter("m_curPage") != null) {
			m_curPage = Integer.valueOf(request.getParameter("m_curPage"));
		}
		if(request.getParameter("m_sortWay") != null) {
			m_sortWay = request.getParameter("m_sortWay");
		} else {
			m_sortWay = "nameSort";
		}
		m_search_contents = request.getParameter("m_search_contents");
		if(m_search_contents == null) {
			m_user_list = mDao.listDAO(m_curPage, m_sortWay, "N");
			request.setAttribute("m_listAll", false);
		} else {
			m_user_list = mDao.listDAO(m_curPage, m_sortWay, "N", m_search_contents);
			request.setAttribute("m_listAll", true);
		}
		
		m_totalPage = mDao.getTotalPage();
		m_admin_list = mDao.listDAO(m_curPage, m_sortWay, "Y");
		m_user_list = mDao.listDAO(m_curPage, m_sortWay, "N");
		request.setAttribute("m_sortWay", m_sortWay);
		request.setAttribute("m_totalPage", m_totalPage);
		request.setAttribute("m_admin_list", m_admin_list);
		request.setAttribute("m_user_list", m_user_list);
		request.setAttribute("rateOfMale", mDao.getRateOfSex("male"));
		request.setAttribute("rateOfFemale", mDao.getRateOfSex("female"));
		request.setAttribute("rateOf10th", mDao.getRateOfAge(10, 19));
		request.setAttribute("rateOf20th", mDao.getRateOfAge(20, 29));
		request.setAttribute("rateOf30th", mDao.getRateOfAge(30, 39));
		request.setAttribute("rateOf40th", mDao.getRateOfAge(40, 49));
		request.setAttribute("rateOf99th", mDao.getRateOfAge(50, 999));
		
		// 레시피
		int r_curPage = 0;
		int r_totalPage = 0;
		String r_sortWay = "";
		String r_search_contents = "";
		ArrayList<RecipeDTO> r_list = new ArrayList<RecipeDTO>();
		ArrayList<RecipeDTO> chart_list = new ArrayList<RecipeDTO>();
		
		if(request.getParameter("r_curPage") != null) {
			r_curPage = Integer.valueOf(request.getParameter("r_curPage"));
		}
		if(request.getParameter("r_sortWay") != null) {
			r_sortWay = request.getParameter("r_sortWay");
		} else {
			r_sortWay = "latestSort";
		}
		
		r_search_contents = request.getParameter("r_search_contents");
		if(r_search_contents == null) {
			r_list = rDao.listDAO(r_curPage, r_sortWay);
			request.setAttribute("r_listAll", false);
		} else {
			r_list = rDao.listDAO(r_curPage, r_sortWay, r_search_contents);
			request.setAttribute("r_listAll", true);
		}
		r_totalPage = rDao.getTotalPage();
		request.setAttribute("r_sortWay", r_sortWay);
		request.setAttribute("r_totalPage", r_totalPage);
		request.setAttribute("r_list", r_list);
		
		chart_list = rDao.getRecipeChart();
		request.setAttribute("chart_list", chart_list);
		
		// 식재료
		int g_curPage = 0;
		int g_totalPage = 0;
		String g_sortWay = "";
		String grocery_type = "";
		String g_search_contents = "";
		ArrayList<GroceryDTO> g_list = new ArrayList<GroceryDTO>();
		
		if(request.getParameter("grocery_type") != null) {
			grocery_type = request.getParameter("grocery_type");
		} else {
			grocery_type = "all";
		}
		if(request.getParameter("g_sortWay") != null) {
			g_sortWay = request.getParameter("g_sortWay");
		} else {
			g_sortWay = "latestSort";
		}
		if(request.getParameter("g_curPage") != null) {
			g_curPage = Integer.valueOf(request.getParameter("g_curPage"));
		}
		g_search_contents = request.getParameter("g_search_contents");
		if(g_search_contents == null) {
			g_list = gDao.listDAO(grocery_type, g_curPage, g_sortWay);
			request.setAttribute("g_listAll", false);
		} else {
			g_list = gDao.listDAO(grocery_type, r_curPage, g_sortWay, g_search_contents);
			request.setAttribute("g_listAll", true);
		}
		
		g_totalPage = gDao.getTotalPage();
		ArrayList<GroceryDTO> g_out_of_shelf_life_list = gDao.getOutOfShelfLifeList();
		ArrayList<GroceryDTO> g_soldout_list = gDao.getSoldoutList();
		request.setAttribute("g_list", g_list);
		request.setAttribute("g_out_of_shelf_life_list", g_out_of_shelf_life_list);
		request.setAttribute("g_soldout_list", g_soldout_list);
		request.setAttribute("g_totalPage", g_totalPage);
		request.setAttribute("g_sortWay", g_sortWay);
	}

}
