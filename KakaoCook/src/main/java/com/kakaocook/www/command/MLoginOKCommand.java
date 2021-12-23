package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kakaocook.www.dao.MemberDAO;
import com.kakaocook.www.dto.MemberDTO;

public class MLoginOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = MemberDAO.getMDao();
		MemberDTO mDto = new MemberDTO();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String rememberId = request.getParameter("rememberId");
		mDto.setId(id);
		mDto.setPw(pw);
		
		if(rememberId != null && rememberId.equals("rememberId")) {
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);
			
		} else {
			Cookie[] cookieArr = request.getCookies();
			for(int i=0;i<cookieArr.length;i++) {
				String str = cookieArr[i].getName();
				if(str.equals("id")) {
					cookieArr[i].setMaxAge(0);
					response.addCookie(cookieArr[i]);
					break;
				}
			}
		}
		mDto = mDao.loginDAO(mDto);
		if(mDto == null) {
			request.setAttribute("isLogin", "fail");
		} else {
			request.setAttribute("isLogin", "success");
			HttpSession session = request.getSession();
			session.setAttribute("checkedMember", mDto);
		}
		request.setAttribute("pre_url", request.getParameter("url"));
	}

}
