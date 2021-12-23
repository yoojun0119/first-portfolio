package com.kakaocook.www.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kakaocook.www.dao.FreezerDAO;
import com.kakaocook.www.dto.FreezerDTO;

public class FInsertCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FreezerDAO fDao = FreezerDAO.getFDao();
		
		String[] codeArr = request.getParameterValues("code");
		String[] purchaseArr = request.getParameterValues("numOfPurchase");
		for(int i=0; i<codeArr.length; i++) {
			FreezerDTO fDto = new FreezerDTO();
			fDto.setId(request.getParameter("id"));
			fDto.setCode(codeArr[i]);
			fDto.setNumOfPurchase(Integer.valueOf(purchaseArr[i]));
			fDao.insertDAO(fDto);
		}
	}

}
