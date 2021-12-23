package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.MemberDAO;
import com.kakaocook.www.dao.RecipeDAO;
import com.kakaocook.www.dto.MemberDTO;
import com.kakaocook.www.dto.RecipeDTO;

public class RGradeOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeDAO rDao = RecipeDAO.getRDao();
		RecipeDTO rDto = new RecipeDTO();
		MemberDAO mDao = MemberDAO.getMDao();
		MemberDTO mDto = new MemberDTO(); 
		
		rDto.setGradeNum(Integer.valueOf(request.getParameter("gradeNum")));
		rDto.setNo(Integer.valueOf(request.getParameter("no")));
		mDto.setAssessGradeRecipe(Integer.valueOf(request.getParameter("no")));
		mDto.setAssessGradeNum(Integer.valueOf(request.getParameter("gradeNum")));
		mDto.setId(request.getParameter("id"));
		
		rDao.gradeOKDAO(rDto);
		mDao.chkGradeNum(mDto);
		mDao.chkGrade(mDto);
	}

}
