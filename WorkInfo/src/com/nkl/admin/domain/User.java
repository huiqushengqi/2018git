package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class User extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -460922993085630428L;
	private int user_id; // 
	private String user_name; // 
	private String user_pass; // 
	private String real_name; // 
	private int user_sex; // 1：男  2：女
	private int user_age; // 
	private String user_nation;
	private String user_origin;
	private String user_mail;
	private String user_phone;
	private String user_photo;
	private int clazz_id; // 
	private String reg_date; //  
	private int user_type; // 1：学生 2：教师 3：系统管理员 
	private String user_question;
	private String user_answer;
	private String note; // 

	private String clazz_name; // 
	private int course_id; // 
	private int scource_year; // 
	private int scource_year_half; // 
	
	private String ids;
	private String random;

	
	public String getUser_typeDesc(){
		switch (user_type) {
		case 1:
			return "学生";
		case 2:
			return "班主任";
		case 3:
			return "系统管理员 ";
		default:
			return "学生";
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
	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setUser_name(String user_name){
		this.user_name=user_name;
	}

	public String getUser_name(){
		return user_name;
	}

	public void setUser_pass(String user_pass){
		this.user_pass=user_pass;
	}

	public String getUser_pass(){
		return user_pass;
	}

	public void setReal_name(String real_name){
		this.real_name=real_name;
	}

	public String getReal_name(){
		return real_name;
	}

	public void setUser_sex(int user_sex){
		this.user_sex=user_sex;
	}

	public int getUser_sex(){
		return user_sex;
	}

	public void setUser_age(int user_age){
		this.user_age=user_age;
	}

	public int getUser_age(){
		return user_age;
	}

	public void setClazz_id(int clazz_id){
		this.clazz_id=clazz_id;
	}

	public int getClazz_id(){
		return clazz_id;
	}

	public void setReg_date(String reg_date){
		this.reg_date=reg_date;
	}

	public String getReg_date(){
		return reg_date;
	}

	public void setUser_type(int user_type){
		this.user_type=user_type;
	}

	public int getUser_type(){
		return user_type;
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

	public String getClazz_name() {
		return clazz_name;
	}

	public void setClazz_name(String clazz_name) {
		this.clazz_name = clazz_name;
	}

	public String getUser_nation() {
		return user_nation;
	}

	public void setUser_nation(String user_nation) {
		this.user_nation = user_nation;
	}

	public String getUser_origin() {
		return user_origin;
	}

	public void setUser_origin(String user_origin) {
		this.user_origin = user_origin;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_photo() {
		return user_photo;
	}

	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}

	public String getUser_question() {
		return user_question;
	}

	public void setUser_question(String user_question) {
		this.user_question = user_question;
	}

	public String getUser_answer() {
		return user_answer;
	}

	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getScource_year() {
		return scource_year;
	}

	public void setScource_year(int scource_year) {
		this.scource_year = scource_year;
	}

	public int getScource_year_half() {
		return scource_year_half;
	}

	public void setScource_year_half(int scource_year_half) {
		this.scource_year_half = scource_year_half;
	}

}
