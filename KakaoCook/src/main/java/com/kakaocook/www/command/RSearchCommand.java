package com.kakaocook.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.RecipeDAO;
import com.kakaocook.www.dto.RecipeDTO;

public class RSearchCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeDAO rDao = RecipeDAO.getRDao();
		
		String search_contents = request.getParameter("search_contents");
		
		ArrayList<RecipeDTO> list = rDao.searchDAO(search_contents);
		if(list.size() == 0) {
			request.setAttribute("nonList", list);
		}
		request.setAttribute("list", list);
	}

}
