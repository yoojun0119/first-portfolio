package com.kakaocook.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.FreezerDAO;
import com.kakaocook.www.dao.GroceryDAO;
import com.kakaocook.www.dto.FreezerDTO;
import com.kakaocook.www.dto.GroceryDTO;

public class GSearchCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroceryDAO gDao = GroceryDAO.getGDao();
		FreezerDAO fDao = FreezerDAO.getFDao();
		ArrayList<FreezerDTO> shelf_life_list = new ArrayList<FreezerDTO>();
		
		String search_contents = request.getParameter("search_contents");
		
		ArrayList<GroceryDTO> g_list = gDao.searchDAO(search_contents);
		if(g_list.size() == 0) {
			request.setAttribute("nonList", g_list);
		}
		request.setAttribute("g_list", g_list);
		request.setAttribute("search_contents", search_contents);
	}

}
