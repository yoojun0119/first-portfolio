package com.kakaocook.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.RecipeDAO;
import com.kakaocook.www.dto.RecipeDTO;

public class RMainCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeDAO rDao = RecipeDAO.getRDao();
		int curPage = 0;
		int totalPage = 0;
		String sortWay = "";
		
		if(request.getParameter("curPage") != null) {
			curPage = Integer.valueOf(request.getParameter("curPage"));
		}
		if(request.getParameter("sortWay") != null) {
			sortWay = request.getParameter("sortWay");
		} else {
			sortWay = "latestSort";
		}
		totalPage = rDao.getTotalPage();
		ArrayList<RecipeDTO> list = rDao.listDAO(curPage, sortWay);
		request.setAttribute("sortWay", sortWay);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
	}

}
