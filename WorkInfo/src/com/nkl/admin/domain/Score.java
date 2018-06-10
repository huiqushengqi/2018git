package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class Score extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5376026488216840230L;
	private int score_id; // 
	private int user_id; // 
	private int course_id; // 
	private double score_value; // 
	private int score_year; // 
	private int score_year_half; // 1-上半年 2-下半年
	private String note; // 

	private String user_name; // 
	private int user_sex; // 
	private String real_name; // 
	private String course_name; //
	private int course_type; // 
	private int clazz_id; // 
	private String clazz_name; 
	private double score_valueMin; 
	private double score_valueMax; 
	private int teacher_id; // 
	
	private int score_index; // 
	private double score_sum; // 
	
	private double score_start; // 
	private double score_end; // 
	private int sec_1; //
	private int sec_2; //
	private int sec_3; //
	private int sec_4; //
	private int sec_5; //
	private int sec_6; //
	private int sec_7; //
	
	
	private String ids;
	private String random;

	public int getScore_sec(){
		return getScore_sec_about(score_end - score_start);
	}
	
	public int getScore_sec_about(double old){
		int old_floor = (int)Math.floor(old/6);
		for (int i = 1; i <= (int)Math.ceil(old/5); i++) {
			int temp = i * 5;
			/*if (temp >= old_floor) {
				return temp;
			}*/
			if (temp > old_floor) {
				if (i > 1) {
					return (i-1) * 5;
				}else {
					return old_floor;
				}
			}
		}
		return 0;
	}
	
	public String getCourse_typeDesc(){
		switch (course_type) {
		case 1:
			return "必修课";
		case 2:
			return "选修课";
		default:
			return "";
		}
	}
	
	public String getScore_year_halfDesc(){
		switch (score_year_half) {
		case 1:
			return "上半年";
		case 2:
			return "下半年";
		default:
			return "";
		}
	}
	
	public String getUser_sexDesc(){
		switch (user_sex) {
		case 1:
			return "男";
		case 2:
			return "女";
		default:
			return "男";
		}
	}
	
	public void setScore_id(int score_id){
		this.score_id=score_id;
	}
	
	public int getScore_id(){
		return score_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setCourse_id(int course_id){
		this.course_id=course_id;
	}

	public int getCourse_id(){
		return course_id;
	}

	public void setScore_value(double score_value){
		this.score_value=score_value;
	}

	public double getScore_value(){
		return score_value;
	}

	public void setScore_year(int score_year){
		this.score_year=score_year;
	}

	public int getScore_year(){
		return score_year;
	}

	public void setScore_year_half(int score_year_half){
		this.score_year_half=score_year_half;
	}

	public int getScore_year_half(){
		return score_year_half;
	}

	public void setNote(String note){
		this.note=note;
	}

	public String getNote(){
		return note;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public int getScore_index() {
		return score_index;
	}

	public void setScore_index(int score_index) {
		this.score_index = score_index;
	}

	public int getClazz_id() {
		return clazz_id;
	}

	public void setClazz_id(int clazz_id) {
		this.clazz_id = clazz_id;
	}

	public String getClazz_name() {
		return clazz_name;
	}

	public void setClazz_name(String clazz_name) {
		this.clazz_name = clazz_name;
	}

	public double getScore_valueMin() {
		return score_valueMin;
	}

	public void setScore_valueMin(double score_valueMin) {
		this.score_valueMin = score_valueMin;
	}

	public double getScore_valueMax() {
		return score_valueMax;
	}

	public void setScore_valueMax(double score_valueMax) {
		this.score_valueMax = score_valueMax;
	}

	public double getScore_sum() {
		return score_sum;
	}

	public void setScore_sum(double score_sum) {
		this.score_sum = score_sum;
	}

	public double getScore_start() {
		return score_start;
	}

	public void setScore_start(double score_start) {
		this.score_start = score_start;
	}

	public double getScore_end() {
		return score_end;
	}

	public void setScore_end(double score_end) {
		this.score_end = score_end;
	}

	public int getSec_count(int index) {
		switch (index) {
		case 1:
			return sec_1;
		case 2:
			return sec_2;
		case 3:
			return sec_3;
		case 4:
			return sec_4;
		case 5:
			return sec_5;
		case 6:
			return sec_6;
		case 7:
			return sec_7;
		default:
			return 0;
		}
	}
	
	public int getSec_1() {
		return sec_1;
	}

	public void setSec_1(int sec_1) {
		this.sec_1 = sec_1;
	}

	public int getSec_2() {
		return sec_2;
	}

	public void setSec_2(int sec_2) {
		this.sec_2 = sec_2;
	}

	public int getSec_3() {
		return sec_3;
	}

	public void setSec_3(int sec_3) {
		this.sec_3 = sec_3;
	}

	public int getSec_4() {
		return sec_4;
	}

	public void setSec_4(int sec_4) {
		this.sec_4 = sec_4;
	}

	public int getSec_5() {
		return sec_5;
	}

	public void setSec_5(int sec_5) {
		this.sec_5 = sec_5;
	}

	public int getSec_6() {
		return sec_6;
	}

	public void setSec_6(int sec_6) {
		this.sec_6 = sec_6;
	}

	public int getSec_7() {
		return sec_7;
	}

	public void setSec_7(int sec_7) {
		this.sec_7 = sec_7;
	}

	public int getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(int user_sex) {
		this.user_sex = user_sex;
	}

	public int getCourse_type() {
		return course_type;
	}

	public void setCourse_type(int course_type) {
		this.course_type = course_type;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

}
