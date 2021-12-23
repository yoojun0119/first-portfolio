package com.kakaocook.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.RecipeDAO;
import com.kakaocook.www.dto.RecipeDTO;

public class RModifyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeDAO rDao = RecipeDAO.getRDao();
		RecipeDTO rDto = new RecipeDTO();
		
		rDto.setNo(Integer.valueOf(request.getParameter("no")));
		
		rDto = rDao.modifyDAO(rDto);
		ArrayList<String> ingredient_name_list = rDao.getIngredientName(rDto);
		ArrayList<String> ingredient_amount_list = rDao.getIngredientAmount(rDto);
		ArrayList<String> recipe_description_list = rDao.getRecipeDescription(rDto);
		ArrayList<String> imgPath_list = rDao.getImgPath(rDto);
		
		request.setAttribute("rDto", rDto);
		request.setAttribute("ingredient_name_list", ingredient_name_list);
		request.setAttribute("ingredient_amount_list", ingredient_amount_list);
		request.setAttribute("recipe_description_list", recipe_description_list);
		request.setAttribute("imgPath_list", imgPath_list);
		request.setAttribute("numOfIngredient", ingredient_name_list.size());
		request.setAttribute("numOfRecipeStep", recipe_description_list.size());
	}

}
