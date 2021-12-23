package com.kakaocook.www.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.command.AGrantedOKCommand;
import com.kakaocook.www.command.AMainCommand;
import com.kakaocook.www.command.Command;
import com.kakaocook.www.command.FDeleteOKCommand;
import com.kakaocook.www.command.FInsertCommand;
import com.kakaocook.www.command.FMainCommand;
import com.kakaocook.www.command.FModifyOKCommand;
import com.kakaocook.www.command.GDeleteOKCommand;
import com.kakaocook.www.command.GInsertOKCommand;
import com.kakaocook.www.command.GModifyCommand;
import com.kakaocook.www.command.GModifyOKCommand;
import com.kakaocook.www.command.GSearchCommand;
import com.kakaocook.www.command.MDeleteOKCommand;
import com.kakaocook.www.command.MIDCheckOKCommand;
import com.kakaocook.www.command.MLoginChkCommand;
import com.kakaocook.www.command.MLoginCommand;
import com.kakaocook.www.command.MLoginOKCommand;
import com.kakaocook.www.command.MLogoutCommand;
import com.kakaocook.www.command.MModifyOKCommand;
import com.kakaocook.www.command.MRegisterOKCommand;
import com.kakaocook.www.command.MainCommand;
import com.kakaocook.www.command.RBookmarkCommand;
import com.kakaocook.www.command.RBookmarkDeleteOKCommand;
import com.kakaocook.www.command.RCategoryCommand;
import com.kakaocook.www.command.RDeleteOKCommand;
import com.kakaocook.www.command.RGradeOKCommand;
import com.kakaocook.www.command.RInsertOKCommand;
import com.kakaocook.www.command.RMainCommand;
import com.kakaocook.www.command.RModifyCommand;
import com.kakaocook.www.command.RModifyOKCommand;
import com.kakaocook.www.command.RSearchCommand;
import com.kakaocook.www.command.RViewCommand;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Command command = null;
		String commandPage = request.getServletPath();
		String viewPage = null;
		boolean flag = false;
		
		if(commandPage.equals("/main.do")) {
			command = new MainCommand();
			command.execute(request, response);
			viewPage = "main.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/login.do")) { // 로그인 파트
			command = new MLoginCommand();
			command.execute(request, response);
			viewPage = "login.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/loginOK.do")) {
			command = new MLoginOKCommand();
			command.execute(request, response);
			viewPage = "loginChk.do";
			flag = true;
		} else if(commandPage.equals("/layout/loginChk.do")) {
			command = new MLoginChkCommand();
			command.execute(request, response);
			viewPage = "loginChk.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/logout.do")) {
			command = new MLogoutCommand();
			command.execute(request, response);
			request.getSession().invalidate();
			viewPage = "logout.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/member_register.do")) { // 회원 파트
			viewPage = "member_register.jsp";
		} else if(commandPage.equals("/layout/member_registerOK.do")) {
			command = new MRegisterOKCommand();
			command.execute(request, response);
			viewPage = "../main.do";
		} else if(commandPage.equals("/layout/idCheckOK.do")) {
			command = new MIDCheckOKCommand();
			command.execute(request, response);
			viewPage = "idcheckForm.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/member_modify.do")) {
			viewPage = "member_modify.jsp";
		} else if(commandPage.equals("/layout/member_modifyOK.do")) {
			command = new MModifyOKCommand();
			command.execute(request, response);
			viewPage = "../main.do";
		} else if(commandPage.equals("/layout/member_deleteOK.do")) {
			command = new MDeleteOKCommand();
			command.execute(request, response);
			viewPage = "admin_main.do";
		} else if(commandPage.equals("/layout/recipe_main.do")) { // 레시피 파트
			command = new RMainCommand();
			command.execute(request, response);
			viewPage = "recipe_main.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/recipe_search.do")) {
			command = new RSearchCommand();
			command.execute(request, response);
			viewPage = "recipe_search.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/recipe_category.do")) {
			command = new RCategoryCommand();
			command.execute(request, response);
			viewPage = "recipe_category.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/recipe_insert.do")) {
			viewPage = "recipe_insert.jsp";
		} else if(commandPage.equals("/layout/recipe_insertOK.do")) {
			command = new RInsertOKCommand();
			command.execute(request, response);
			viewPage = "recipe_main.do";
		} else if(commandPage.equals("/layout/recipe_view.do")) {
			command = new RViewCommand();
			command.execute(request, response);
			viewPage = "recipe_view.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/recipe_gradeOK.do")) {
			command = new RGradeOKCommand();
			command.execute(request, response);
			viewPage = "recipe_view.do";
			flag = true;
		} else if(commandPage.equals("/layout/recipe_bookmark.do")) {
			command = new RBookmarkCommand();
			command.execute(request, response);
			viewPage = "recipe_view.do";
			flag = true;
		} else if(commandPage.equals("/layout/recipe_bookmarkDel.do")) {
			command = new RBookmarkDeleteOKCommand();
			command.execute(request, response);
			viewPage = (String) request.getAttribute("url");
		} else if(commandPage.equals("/layout/recipe_modify.do")) {
			command = new RModifyCommand();
			command.execute(request, response);
			viewPage = "recipe_modify.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/recipe_modifyOK.do")) {
			command = new RModifyOKCommand();
			command.execute(request, response);
			viewPage = "recipe_main.do";
		} else if(commandPage.equals("/layout/recipe_deleteOK.do")) {
			command = new RDeleteOKCommand();
			command.execute(request, response);
			viewPage = "recipe_main.do";
		} else if(commandPage.equals("/layout/freezer_main.do")) { // 냉장고 파트
			command = new FMainCommand();
			command.execute(request, response);
			viewPage = "freezer_main.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/freezer_insertOK.do")) {
			command = new FInsertCommand();
			command.execute(request, response);
			viewPage = "freezer_main.do";
		} else if(commandPage.equals("/layout/freezer_deleteOK.do")) {
			command = new FDeleteOKCommand();
			command.execute(request, response);
			viewPage = "freezer_main.do";
		} else if(commandPage.equals("/layout/freezer_modifyOK.do")) {
			command = new FModifyOKCommand();
			command.execute(request, response);
			viewPage = "freezer_main.do";
		} else if(commandPage.equals("/layout/grocery_search.do")) {
			command = new GSearchCommand();
			command.execute(request, response);
			viewPage = "grocery_search.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/grocery_insert.do")) {
			viewPage = "grocery_insert.jsp";
		} else if(commandPage.equals("/layout/grocery_insertOK.do")) {
			command = new GInsertOKCommand();
			command.execute(request, response);
			viewPage = "freezer_main.do";
		} else if(commandPage.equals("/layout/grocery_modify.do")) {
			command = new GModifyCommand();
			command.execute(request, response);
			viewPage = "grocery_modify.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/grocery_modifyOK.do")) {
			command = new GModifyOKCommand();
			command.execute(request, response);
			viewPage = "freezer_main.do";
		} else if(commandPage.equals("/layout/grocery_deleteOK.do")) {
			command = new GDeleteOKCommand();
			command.execute(request, response);
			viewPage = "freezer_main.do";
		} else if(commandPage.equals("/layout/admin_main.do")) { // 관리자 모드
			command = new AMainCommand();
			command.execute(request, response);
			viewPage = "admin_main.jsp";
			flag = true;
		} else if(commandPage.equals("/layout/admin_grantedOK.do")) {
			command = new AGrantedOKCommand();
			command.execute(request, response);
			viewPage = "admin_main.do";
		}
		
		if(flag) {
			RequestDispatcher rd = request.getRequestDispatcher(viewPage);
			rd.forward(request, response);
		} else {
			response.sendRedirect(viewPage);
		}
	}

}
