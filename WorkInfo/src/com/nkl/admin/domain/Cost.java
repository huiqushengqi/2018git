package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class Cost extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -674161960515333295L;
	private int cost_id; // 
	private int user_id; // 
	private double cost_money; //
	private String cost_reason; //
	private String cost_date; //
	
	private String real_name; //
	
	private String random; // 
	private String ids; // 
	
	public int getCost_id() {
		return cost_id;
	}
	public void setCost_id(int cost_id) {
		this.cost_id = cost_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public double getCost_money() {
		return cost_money;
	}
	public void setCost_money(double cost_money) {
		this.cost_money = cost_money;
	}
	public String getCost_reason() {
		return cost_reason;
	}
	public void setCost_reason(String cost_reason) {
		this.cost_reason = cost_reason;
	}
	public String getCost_date() {
		return cost_date;
	}
	public void setCost_date(String cost_date) {
		this.cost_date = cost_date;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}


}
