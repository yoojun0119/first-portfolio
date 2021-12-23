package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.GroceryDAO;
import com.kakaocook.www.dto.GroceryDTO;

public class GModifyOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroceryDAO gDao = GroceryDAO.getGDao();
		GroceryDTO gDto = new GroceryDTO();
		
		gDto.setNo(Integer.valueOf(request.getParameter("no")));
		gDto.setName(request.getParameter("name"));
		String g_type = request.getParameter("type");
		if(g_type.equals("meat")) {
			gDto.setCode("M");
		} else if(g_type.equals("seafood")) {
			gDto.setCode("S");
		} else if(g_type.equals("vegetable")) {
			gDto.setCode("V");
		} else if(g_type.equals("processed")) {
			gDto.setCode("P");
		} else if(g_type.equals("grain")) {
			gDto.setCode("G");
		} else if(g_type.equals("etc")) {
			gDto.setCode("E");
		}
		gDto.setType(request.getParameter("type"));
		gDto.setPackage_amount(Integer.valueOf(request.getParameter("package_amount")));
		gDto.setPackage_unit(request.getParameter("package_unit"));
		gDto.setTotal_amount(Integer.valueOf(request.getParameter("total_amount")));
		gDto.setShelf_life(request.getParameter("shelf_life"));
		gDto.setStorage_method(request.getParameter("storage_method"));
		
		gDao.modifyOKDAO(gDto);
	}

}
