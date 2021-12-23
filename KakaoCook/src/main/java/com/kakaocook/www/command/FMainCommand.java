package com.kakaocook.www.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kakaocook.www.dao.FreezerDAO;
import com.kakaocook.www.dao.GroceryDAO;
import com.kakaocook.www.dao.MemberDAO;
import com.kakaocook.www.dao.RecipeDAO;
import com.kakaocook.www.dto.FreezerDTO;
import com.kakaocook.www.dto.GroceryDTO;
import com.kakaocook.www.dto.MemberDTO;
import com.kakaocook.www.dto.RecipeDTO;

public class FMainCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FreezerDAO fDao = FreezerDAO.getFDao();
		GroceryDAO gDao = GroceryDAO.getGDao();
		RecipeDAO rDao = RecipeDAO.getRDao();
		MemberDAO mDao = MemberDAO.getMDao();
		FreezerDTO fDto = new FreezerDTO();
		RecipeDTO rDto = new RecipeDTO();
		ArrayList<FreezerDTO> freezer_list = new ArrayList<FreezerDTO>();
		ArrayList<RecipeDTO> possible_list = new ArrayList<RecipeDTO>();
		ArrayList<FreezerDTO> shelf_life_list = new ArrayList<FreezerDTO>();
		ArrayList<Integer> bookmarked_no = new ArrayList<Integer>();
		ArrayList<RecipeDTO> bookmarked_list = new ArrayList<RecipeDTO>();
		
		// 냉장고
		int f_curPage = 0;
		int f_totalPage = 0;
		String f_sortWay = "";
		String freezer_type = "";
		String id = "";
		
		if(request.getParameter("freezer_type") != null) {
			freezer_type = request.getParameter("freezer_type");
		} else {
			freezer_type = "all";
		}
		if(request.getParameter("f_sortWay") != null) {
			f_sortWay = request.getParameter("f_sortWay");
		} else {
			f_sortWay = "latestSort";
		}
		if(request.getParameter("f_curPage") != null) {
			f_curPage = Integer.valueOf(request.getParameter("f_curPage"));
		}
		HttpSession session = request.getSession();
		MemberDTO mDto = (MemberDTO) session.getAttribute("checkedMember");
		
		f_totalPage = fDao.getTotalPage();
		if((mDto != null)) {
			id = mDto.getId();
			ArrayList<FreezerDTO> f_list = fDao.listDAO(id, freezer_type, f_curPage, f_sortWay);
			if(f_list.size() == 0) {
				request.setAttribute("nonList", f_list);
			}
			request.setAttribute("f_list", f_list);
			
			//즐겨찾기한 레시피
			bookmarked_no = mDao.getBookmark(id);
			for(int i=0;i<bookmarked_no.size();i++) {
				int recipeNo = bookmarked_no.get(i);
				bookmarked_list.add(rDao.getRDto(recipeNo));
			}
			Collections.sort(bookmarked_list);
			request.setAttribute("bookmarked_list", bookmarked_list);
			
			//가능한 레시피
			freezer_list = fDao.getFreezerlist(id);
			possible_list = fDao.possible_listDAO(freezer_list);
			Collections.shuffle(possible_list);
			request.setAttribute("possible_list", possible_list);
			
			//유통기한
			shelf_life_list = fDao.getRestOfShelfLife(fDao.getFDto(id), id);
			request.setAttribute("shelf_life_list", shelf_life_list);
			
			//목록 갯수
			int numOfFreezerList = fDao.getNumOfFreezerList(id, request.getParameter("freezer_type"));
			request.setAttribute("numOfFreezerList", numOfFreezerList);
		} else {
			ArrayList<RecipeDTO> ran_list = rDao.ran_listDAO();
			request.setAttribute("ran_list", ran_list);
		}
		request.setAttribute("freezer_type", freezer_type);
		request.setAttribute("f_totalPage", f_totalPage);
		request.setAttribute("f_sortWay", f_sortWay);
		
		// 식재료
		int g_curPage = 0;
		int g_totalPage = 0;
		String g_sortWay = "";
		String grocery_type = "";
		
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
		
		g_totalPage = gDao.getTotalPage();
		ArrayList<GroceryDTO> g_list = gDao.listDAO(grocery_type, g_curPage, g_sortWay);
		if(g_list.size() == 0) {
			request.setAttribute("nonList", g_list);
		}
		request.setAttribute("g_list", g_list);
		request.setAttribute("grocery_type", grocery_type);
		request.setAttribute("g_totalPage", g_totalPage);
		request.setAttribute("g_sortWay", g_sortWay);
		
	}

}
