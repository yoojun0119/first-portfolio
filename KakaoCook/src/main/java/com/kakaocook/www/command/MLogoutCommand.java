package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MLogoutCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pre_url = request.getParameter("url");
		
		if(pre_url == null || pre_url.equals("")) {
			request.setAttribute("pre_url", "../main.do");
			return;
		}
		if(pre_url.equals("main.do")) {
			request.setAttribute("pre_url", "../"+pre_url);
		} else {
			request.setAttribute("pre_url", pre_url);
		}
	}

}
