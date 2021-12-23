package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.FreezerDAO;
import com.kakaocook.www.dto.FreezerDTO;

public class FDeleteOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FreezerDAO fDao = FreezerDAO.getFDao();
		FreezerDTO fDto = new FreezerDTO();
		fDto.setId(request.getParameter("id"));
		fDto.setNo(Integer.valueOf(request.getParameter("no")));
		fDao.deleteDAO(fDto);
	}

}
