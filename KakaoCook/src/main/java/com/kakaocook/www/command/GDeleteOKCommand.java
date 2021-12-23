package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.GroceryDAO;

public class GDeleteOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroceryDAO gDao = GroceryDAO.getGDao();
		gDao.deleteOKDAO(request.getParameter("code"));
	}

}
