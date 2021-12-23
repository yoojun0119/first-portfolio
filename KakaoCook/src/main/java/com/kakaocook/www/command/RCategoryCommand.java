package com.kakaocook.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.RecipeDAO;
import com.kakaocook.www.dto.RecipeDTO;

public class RCategoryCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeDAO rDao = RecipeDAO.getRDao();
		RecipeDTO rDto = new RecipeDTO();
		
		int curPage = 0;
		int totalPage = 0;
		if(request.getParameter("curPage") != null) {
			curPage = Integer.valueOf(request.getParameter("curPage"));
		}
		totalPage = rDao.getTotalPage();
		
		String recipe_type = request.getParameter("recipe_type");
		String food_type = request.getParameter("food_type");
		String ingredient_type = request.getParameter("ingredient_type");
		String method_type = request.getParameter("method_type");
		
		ArrayList<RecipeDTO> list = null;
		
		if(recipe_type.equals("all_foods")) {
			recipe_type = "foods";
		}
		if(food_type.equals("all_food_dish")) {
			food_type = "dish";
		}
		if(ingredient_type.equals("all_ingredient_dish")) {
			ingredient_type = "dish";
		}
		if(method_type.equals("all_method_dish")) {
			method_type = "dish";
		}
		list = rDao.categoryDAO(recipe_type, food_type, ingredient_type, method_type, curPage);
		if(list.size() == 0) {
			request.setAttribute("nonList", list);
		}
		
		request.setAttribute("recipe_type", recipe_type);
		request.setAttribute("food_type", food_type);
		request.setAttribute("ingredient_type", ingredient_type);
		request.setAttribute("method_type", method_type);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
	}

}
