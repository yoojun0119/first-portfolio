package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.MemberDAO;
import com.kakaocook.www.dto.MemberDTO;

public class AGrantedOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = MemberDAO.getMDao();
		MemberDTO mDto = new MemberDTO();
		mDao.updateGrantedDAO(request.getParameter("id"), request.getParameter("granted"));
	}

}
