package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MLoginChkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pre_url = (String) request.getAttribute("pre_url");
		String isLogin = (String) request.getAttribute("isLogin");
		if(pre_url.equals("../main.do")) {
			request.setAttribute("pre_url", pre_url);
		} else {
			request.setAttribute("pre_url", pre_url);
		}
		request.setAttribute("isLogin", isLogin);
	}

}
