package com.kakaocook.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.RecipeDAO;
import com.kakaocook.www.dto.RecipeDTO;

public class MainCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeDAO rDao = RecipeDAO.getRDao();
		
		ArrayList<RecipeDTO> top_list = rDao.top_listDAO();
		request.setAttribute("top_list", top_list);
		ArrayList<RecipeDTO> ran_list = rDao.ran_listDAO();
		request.setAttribute("ran_list", ran_list);
	}

}
