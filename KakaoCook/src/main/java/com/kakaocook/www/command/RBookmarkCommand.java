package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.MemberDAO;
import com.kakaocook.www.dto.MemberDTO;
import com.kakaocook.www.dto.RecipeDTO;

public class RBookmarkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = MemberDAO.getMDao();
		MemberDTO mDto = new MemberDTO();
		mDto.setId(request.getParameter("id"));
		mDto.setBookmark(Integer.valueOf(request.getParameter("no")));
		mDao.chkBookmark(mDto);
	}

}
