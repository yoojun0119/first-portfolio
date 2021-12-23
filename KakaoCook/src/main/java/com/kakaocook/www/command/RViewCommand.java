package com.kakaocook.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.MemberDAO;
import com.kakaocook.www.dao.RecipeDAO;
import com.kakaocook.www.dto.RecipeDTO;

public class RViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeDAO rDao = RecipeDAO.getRDao();
		MemberDAO mDao = MemberDAO.getMDao();
		RecipeDTO rDto = new RecipeDTO();
		
		rDto.setNo(Integer.valueOf(request.getParameter("no")));
		/* rDto.setId(request.getParameter("id")); */
		rDao.viewDAO(rDto);
		ArrayList<String> ingredient_name_list = rDao.getIngredientName(rDto);
		ArrayList<String> ingredient_amount_list = rDao.getIngredientAmount(rDto);
		ArrayList<String> recipe_description_list = rDao.getRecipeDescription(rDto);
		ArrayList<String> imgPath_list = rDao.getImgPath(rDto);
		ArrayList<Integer> bookmarked_list = mDao.getBookmark(request.getParameter("id"));
		ArrayList<Integer> assessGradeRecipe_list = mDao.getAssessGradeRecipe(request.getParameter("id"));
		ArrayList<Integer> assessGradeNum_list = mDao.getAssessGradeNum(request.getParameter("id"));
		
		if(assessGradeRecipe_list.size() == 0) {
			request.setAttribute("assessGradeNum_list", null);
		} else {
			int recipeNo = Integer.valueOf(request.getParameter("no"));
			for(int i=0;i<assessGradeRecipe_list.size();i++) {
				if(assessGradeRecipe_list.get(i) == recipeNo) {
					request.setAttribute("assessGradeRecipe_no", assessGradeRecipe_list.get(i));
					request.setAttribute("assessGrade_num", assessGradeNum_list.get(i));
				}
			}
		}
		
		boolean flag = false;
		for(int i=0;i<bookmarked_list.size();i++) {
			if(bookmarked_list.get(i) == Integer.valueOf(request.getParameter("no"))) {
				flag = true;
				break;
			}
		}
		
		request.setAttribute("rDto", rDto);
		request.setAttribute("ingredient_name_list", ingredient_name_list);
		request.setAttribute("ingredient_amount_list", ingredient_amount_list);
		request.setAttribute("recipe_description_list", recipe_description_list);
		request.setAttribute("imgPath_list", imgPath_list);
		request.setAttribute("numOfIngredient", ingredient_name_list.size());
		request.setAttribute("numOfRecipeStep", recipe_description_list.size());
		request.setAttribute("bookmarked_flag", flag);
	}

}
