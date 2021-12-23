package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.MemberDAO;

public class MIDCheckOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = MemberDAO.getMDao();
		String inputID = request.getParameter("inputID");
		String usedID = "";
		boolean flag = mDao.duplicateIdCheck(inputID);
		
		if(flag){
			request.setAttribute("usedID", usedID);
		} else {
			request.setAttribute("checkedID", inputID);
		}
	}

}
