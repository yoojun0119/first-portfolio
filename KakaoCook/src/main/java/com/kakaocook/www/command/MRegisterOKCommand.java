package com.kakaocook.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.MemberDAO;
import com.kakaocook.www.dto.MemberDTO;

public class MRegisterOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = MemberDAO.getMDao();
		MemberDTO mDto = new MemberDTO();
		
		mDto.setId(request.getParameter("id"));
		mDto.setPw(request.getParameter("pw"));
		mDto.setName(request.getParameter("name"));
		mDto.setBirthYear(Integer.valueOf(request.getParameter("birthYear")));
		mDto.setBirthMonth(Integer.valueOf(request.getParameter("birthMonth")));
		mDto.setBirthDate(Integer.valueOf(request.getParameter("birthDate")));
		mDto.setSex(request.getParameter("sex"));
		mDto.setPostcode(request.getParameter("postcode"));
		mDto.setRoadAddress(request.getParameter("roadAddress"));
		mDto.setJibunAddress(request.getParameter("jibunAddress"));
		mDto.setDetailAddress(request.getParameter("detailAddress"));
		mDto.setExtraAddress(request.getParameter("extraAddress"));
		mDto.setPhoneNumber(request.getParameter("phoneNumber"));
		mDto.setEmail(request.getParameter("email"));
		
		mDao.registerDAO(mDto);
	}

}
