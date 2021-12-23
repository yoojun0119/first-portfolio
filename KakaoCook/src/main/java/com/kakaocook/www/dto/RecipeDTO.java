package com.kakaocook.www.dto;

public class RecipeDTO implements Comparable<RecipeDTO>{
	private int no;
	private String id;
	private String name;
	private String title;
	private String recipe_type;
	private String food_type;
	private String ingredient_type;
	private String method_type;
	private String recipe_thumbnail_image;
	private String ingredient_name;
	private String ingredient_amount;
	private String recipe_description;
	private String recipe_image;
	private int rCnt;
	private int gradeNum;
	private int gradeCnt;
	private int gradeAverage;
	private int bookmarkCnt;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRecipe_type() {
		return recipe_type;
	}
	public void setRecipe_type(String recipe_type) {
		this.recipe_type = recipe_type;
	}
	public String getFood_type() {
		return food_type;
	}
	public void setFood_type(String food_type) {
		this.food_type = food_type;
	}
	public String getIngredient_type() {
		return ingredient_type;
	}
	public void setIngredient_type(String ingredient_type) {
		this.ingredient_type = ingredient_type;
	}
	public String getMethod_type() {
		return method_type;
	}
	public void setMethod_type(String method_type) {
		this.method_type = method_type;
	}
	public String getRecipe_thumbnail_image() {
		return recipe_thumbnail_image;
	}
	public void setRecipe_thumbnail_image(String recipe_thumbnail_image) {
		this.recipe_thumbnail_image = recipe_thumbnail_image;
	}
	public String getIngredient_name() {
		return ingredient_name;
	}
	public void setIngredient_name(String ingredient_name) {
		this.ingredient_name = ingredient_name;
	}
	public String getIngredient_amount() {
		return ingredient_amount;
	}
	public void setIngredient_amount(String ingredient_amount) {
		this.ingredient_amount = ingredient_amount;
	}
	public String getRecipe_description() {
		return recipe_description;
	}
	public void setRecipe_description(String recipe_description) {
		this.recipe_description = recipe_description;
	}
	public String getRecipe_image() {
		return recipe_image;
	}
	public void setRecipe_image(String recipe_image) {
		this.recipe_image = recipe_image;
	}
	public int getrCnt() {
		return rCnt;
	}
	public void setrCnt(int rCnt) {
		this.rCnt = rCnt;
	}
	public int getGradeNum() {
		return gradeNum;
	}
	public void setGradeNum(int gradeNum) {
		this.gradeNum = gradeNum;
	}
	public int getGradeCnt() {
		return gradeCnt;
	}
	public void setGradeCnt(int gradeCnt) {
		this.gradeCnt = gradeCnt;
	}
	public int getGradeAverage() {
		return gradeAverage;
	}
	public void setGradeAverage(int gradeAverage) {
		this.gradeAverage = gradeAverage;
	}
	public int getBookmarkCnt() {
		return bookmarkCnt;
	}
	public void setBookmarkCnt(int bookmarkCnt) {
		this.bookmarkCnt = bookmarkCnt;
	}
	
	@Override
	public int compareTo(RecipeDTO rDto) {
		if(this.name.compareTo(rDto.name) < 0) {
			return -1;
		} else if(this.name.compareTo(rDto.name) > 0){
			return 1;
		}
		return 0;
	}
}
