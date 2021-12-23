package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.RecipeDAO;
import com.kakaocook.www.dto.RecipeDTO;

public class RDeleteOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeDAO rDao = RecipeDAO.getRDao();
		rDao.deleteDAO(Integer.valueOf(request.getParameter("no")));
	}

}
