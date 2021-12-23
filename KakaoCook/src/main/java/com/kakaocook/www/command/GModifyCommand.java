package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.GroceryDAO;
import com.kakaocook.www.dto.GroceryDTO;

public class GModifyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroceryDAO gDao = GroceryDAO.getGDao();
		GroceryDTO gDto = new GroceryDTO();
		
		gDto.setCode(request.getParameter("code"));
		gDto = gDao.modifyDAO(gDto);
		
		request.setAttribute("gDto", gDto);
	}

}
