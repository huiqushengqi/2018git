package com.nkl.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Cost;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class CostDao {

	public int addCost(Cost cost, Connection conn){
		String sql = "INSERT INTO cost(cost_id,user_id,cost_money,cost_reason,cost_date) values(null,?,?,?,?)";
		Object[] params = new Object[] {
			cost.getUser_id(),
			cost.getCost_money(),
			cost.getCost_reason(),
			cost.getCost_date()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delCost(String cost_id, Connection conn){
		String sql = "DELETE FROM cost WHERE cost_id=?";

		Object[] params = new Object[] { new Integer(cost_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delCosts(String[] cost_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <cost_ids.length; i++) {
			sBuilder.append("?");
			if (i !=cost_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM cost WHERE cost_id IN(" +sBuilder.toString()+")";

		Object[] params = cost_ids;

		return BaseDao.executeUpdate(sql, params, conn);	
	}
	
	public int updateCost(Cost cost, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE cost SET ");
		if (cost.getUser_id()!=0) {
			sBuilder.append(" ,user_id = " + cost.getUser_id()+" " );
		}
		if (cost.getCost_money()!=0) {
			sBuilder.append(" ,cost_money = " + cost.getCost_money()+" " );
		}
		if (!StringUtil.isEmptyString(cost.getCost_reason())) {
			sBuilder.append(" ,cost_reason = '" + cost.getCost_reason()+"'" );
		}
		if (!StringUtil.isEmptyString(cost.getCost_date())) {
			sBuilder.append(" ,cost_date = '" + cost.getCost_date()+"'" );
		}
		sBuilder.append(" where cost_id = " + cost.getCost_id() );

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString().replaceFirst(",",""), params, conn);	
	}

	public Cost getCost(Cost cost, Connection conn){
		Cost _cost=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT s.*,u.real_name From cost s join user u on s.user_id=u.user_id");
		sBuilder.append(" WHERE 1=1 " );
		if (cost.getCost_id()!=0) {
			sBuilder.append(" and s.cost_id = " + cost.getCost_id() );
		}

		List<Object> list = BaseDao.executeQuery(Cost.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _cost = (Cost)list.get(0);
		}
		return _cost;
	}

	public List<Cost>  listCosts(Cost cost, Connection conn){
		List<Cost> costs = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT s.*,u.real_name From cost s join user u on s.user_id=u.user_id");
		sBuilder.append(" WHERE 1=1 ");
		if (cost.getCost_id()!=0) {
			sBuilder.append(" and s.cost_id = " + cost.getCost_id() );
		}
		if (cost.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + cost.getUser_id() );
		}
		if (!StringUtil.isEmptyString(cost.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + cost.getReal_name() +"%'");
		}
		
		sBuilder.append(" order by s.cost_date desc,s.cost_id asc) t");
		
		if (cost.getStart() != -1) {
			sBuilder.append(" limit " + cost.getStart() + "," + cost.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Cost.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			costs = new ArrayList<Cost>();
			for (Object object : list) {
				costs.add((Cost)object);
			}
		}
		return costs;
	}
	
	public int  listCostsCount(Cost cost, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) From cost s join user u on s.user_id=u.user_id");
		sBuilder.append(" WHERE 1=1");
		if (cost.getCost_id()!=0) {
			sBuilder.append(" and s.cost_id = " + cost.getCost_id() );
		}
		if (cost.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + cost.getUser_id() );
		}
		if (!StringUtil.isEmptyString(cost.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + cost.getReal_name() +"%'");
		}
		
		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
