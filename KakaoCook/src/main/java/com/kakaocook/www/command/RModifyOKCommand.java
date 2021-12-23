package com.kakaocook.www.command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakaocook.www.dao.RecipeDAO;
import com.kakaocook.www.dto.MemberDTO;
import com.kakaocook.www.dto.RecipeDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RModifyOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeDAO rDao = RecipeDAO.getRDao();
		RecipeDTO rDto = new RecipeDTO();
		String test = null;
		
		int size = 1024 * 1024 * 5;
//		String path = request.getSession().getServletContext().getRealPath("images/upload");
		String path = "C:\\Work\\web\\KakaoCook\\src\\main\\webapp\\images\\upload";
		
		try {
			MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
			
			String[] ingredient_nameArr = multi.getParameterValues("ingredient_name");
			String[] ingredient_amountArr = multi.getParameterValues("ingredient_amount");
			String[] recipe_descriptionArr = multi.getParameterValues("recipe_description");
			
			String ingredient_nameArr_str = getStr(ingredient_nameArr);
			String ingredient_ImageArr_str = getStr(ingredient_amountArr);
			String recipe_descriptionArr_str = getStr(recipe_descriptionArr);
			
			String recipe_thumbnail_str = getThumbnailPath(request, multi);
			String recipe_imageArr_str = getRecipePath(request, multi);
			
			rDto.setNo(Integer.valueOf(multi.getParameter("no")));
			rDto.setName(multi.getParameter("name"));
			rDto.setTitle(multi.getParameter("title"));;
			rDto.setRecipe_type(multi.getParameter("recipe_type"));
			rDto.setFood_type(multi.getParameter("food_type"));
			rDto.setIngredient_type(multi.getParameter("ingredient_type"));
			rDto.setMethod_type(multi.getParameter("method_type"));
			rDto.setRecipe_thumbnail_image(recipe_thumbnail_str);
			rDto.setIngredient_name(ingredient_nameArr_str);
			rDto.setIngredient_amount(ingredient_ImageArr_str);
			rDto.setRecipe_description(recipe_descriptionArr_str);
			rDto.setRecipe_image(recipe_imageArr_str);
			rDao.modifyOKDAO(rDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private String getStr(String[] arr) {
		String result = "";
		
		for(int i=0;i<arr.length;i++) {
			if(i == arr.length-1) {
				result+= arr[i];
			} else {
				result+= arr[i]+"||";
			}
		}
		return result;
	}
	private String getThumbnailPath(HttpServletRequest request, MultipartRequest multi) {
//		String path = request.getSession().getServletContext().getRealPath("images/upload");
		String path = "C:\\Work\\web\\KakaoCook\\src\\main\\webapp\\images\\upload";
		String recipe_thumbnail = "";
		String oldThumb = multi.getParameter("oldThumb");
		String file = "";
		
		Enumeration<String> files = multi.getFileNames();
		
		while(files.hasMoreElements()) {
			String str = files.nextElement();
			file = multi.getFilesystemName(str);
			
			if(str.equals("recipe_thumbnail_image")) {
				if(file == null) {
					recipe_thumbnail = oldThumb;
				} else {
					recipe_thumbnail = "../images/upload/"+file;
					File deleteImg = new File(path+"\\"+(oldThumb.substring(17)));
				    
			    	if(deleteImg.exists()) {
			    	    deleteImg.delete();
			    	} else {
			    		System.out.println("이전 사진 삭제 실패");
			    	}
				}
			} else {
				continue;
			}
		}
		return recipe_thumbnail;
	}
	private String getRecipePath(HttpServletRequest request, MultipartRequest multi) {
		RecipeDAO rDao = RecipeDAO.getRDao();
		RecipeDTO rDto = new RecipeDTO();
		rDto.setNo(Integer.valueOf(multi.getParameter("no")));
		rDto = rDao.getRDto(rDto);
		ArrayList<String> oldImages = rDao.getImgPath(rDto);
		
//		String path = request.getSession().getServletContext().getRealPath("images/upload");
		String path = "C:\\Work\\web\\KakaoCook\\src\\main\\webapp\\images\\upload";
		String result = "";
		String file = "";
		int cnt = 0;
		
		Vector<String> recipe_list = new Vector();
		Enumeration<String> files = multi.getFileNames();
		
		while(files.hasMoreElements()) {
			String str = files.nextElement();
			file = multi.getFilesystemName(str);
			
			if(str.equals("recipe_thumbnail_image")) {
				continue;
			} else if(str.contains("recipe_image")) {
				if(file == null) {
					recipe_list.add(oldImages.get(cnt).toString());
				} else {
					recipe_list.add("../images/upload/"+file);
					File deleteImg = new File(path+"\\"+(oldImages.get(cnt).toString().substring(17)));
				    
			    	if(deleteImg.exists()) {
			    	    deleteImg.delete();
			    	} else {
			    		System.out.println("이전 사진 삭제 실패");
			    	}
				}
			}
			cnt++;
		}
		String[] recipe_imageArr = new String[recipe_list.size()];
		recipe_list.copyInto(recipe_imageArr);
		Arrays.sort(recipe_imageArr);

		result = getStr(recipe_imageArr);
		return result;
	}
}
