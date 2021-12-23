package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MLoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookie = request.getCookies();
		
		boolean isRemember = false;
		String id = null;
		
		if(cookie != null) {
			for(int i=0;i<cookie.length;i++) {
				String str = cookie[i].getName();
				if(str.equals("id")) {
					isRemember = true;
					id = cookie[i].getValue();
					break;
				}
			}
		}
		request.setAttribute("url", request.getParameter("url"));
		request.setAttribute("id", id);
		request.setAttribute("isRemember", isRemember);
	}

}
