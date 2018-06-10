package com.nkl.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Score;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class ScoreDao {

	public int addScore(Score score, Connection conn){
		String sql = "INSERT INTO score(score_id,user_id,course_id,score_value,score_year,score_year_half,note) values(null,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			score.getUser_id(),
			score.getCourse_id(),
			score.getScore_value(),
			score.getScore_year(),
			score.getScore_year_half(),
			score.getNote()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}
	
	public int importScore(Score score, Connection conn){
		String sql = "insert into score(user_id,course_id,score_year,score_year_half,score_value) "
				   + "select t1.user_id,t2.course_id,"+score.getScore_year()+","+score.getScore_year_half()+","+score.getScore_value()+" from "
				   + "(select user_id from `user` u where u.user_name='"+score.getUser_name()+"') t1, "
				   + "(select course_id from course c where c.course_name='"+score.getCourse_name()+"') t2";
		Object[] params = null;
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delScore(String score_id, Connection conn){
		String sql = "DELETE FROM score WHERE score_id=?";

		Object[] params = new Object[] { new Integer(score_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}
	
	public int delScoreByStuId(String user_id, Connection conn){
		String sql = "DELETE FROM score WHERE user_id=?";

		Object[] params = new Object[] { new Integer(user_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}
	
	public int delScoreByScourceId(String[] scource_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <scource_ids.length; i++) {
			sBuilder.append("?");
			if (i !=scource_ids.length-1) {
				sBuilder.append(",");
			}
		}
		
		String sql = "DELETE FROM score WHERE (user_id,course_id,score_year,score_year_half) IN "
				   + "(select user_id,course_id,scource_year,scource_year_half from scource where scource_id IN(" +sBuilder.toString()+"))";

		Object[] params = scource_ids;
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delScores(String[] score_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <score_ids.length; i++) {
			sBuilder.append("?");
			if (i !=score_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM score WHERE score_id IN(" +sBuilder.toString()+")";

		Object[] params = score_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateScore(Score score, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE score SET score_id = " + score.getScore_id() +" ");
		if (score.getScore_value()!=0) {
			sBuilder.append(" ,score_value = " + score.getScore_value() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" ,score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" ,score_year_half = " + score.getScore_year_half() +" ");
		}
		sBuilder.append(" where score_id = " + score.getScore_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Score getScore(Score score, Connection conn){
		Score _score=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT s.*,u.user_name,u.user_sex,u.real_name,co.course_name,co.course_type,c.clazz_id,c.clazz_name FROM score s ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join course co on co.course_id = s.course_id  ");
		sBuilder.append("  join clazz c on c.clazz_id = u.clazz_id  ");
		sBuilder.append(" where 1=1 ");
		if (score.getScore_id()!=0) {
			sBuilder.append(" and s.score_id = " + score.getScore_id() +" ");
		}
		if (score.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() +" ");
		}
		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Score.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _score = (Score)list.get(0);
		}
		return _score;
	}

	public List<Score>  listScores(Score score, Connection conn){
		List<Score> scores = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT s.*,u.user_name,u.user_sex,u.real_name,co.course_name,co.course_type,c.clazz_id,c.clazz_name FROM score s ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join course co on co.course_id = s.course_id  ");
		sBuilder.append("  join clazz c on c.clazz_id = u.clazz_id  ");
		sBuilder.append(" where 1=1 ");

		if (score.getScore_id()!=0) {
			sBuilder.append(" and s.score_id = " + score.getScore_id() +" ");
		}
		if (score.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(score.getUser_name())) {
			sBuilder.append(" and u.user_name like '%" + score.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(score.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + score.getReal_name() +"%' ");
		}
		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getCourse_type()!=0) {
			sBuilder.append(" and co.course_type = " + score.getCourse_type() +" ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}
		if (score.getScore_valueMin()!=0) {
			sBuilder.append(" and s.score_value >= " + score.getScore_valueMin() +" ");
		}
		if (score.getScore_valueMax()!=0) {
			sBuilder.append(" and s.score_value <= " + score.getScore_valueMax() +" ");
		}
		if (score.getTeacher_id()!=0) {
			sBuilder.append(" and s.course_id in (select cc.course_id from course cc where cc.teacher_id=" + score.getTeacher_id() +") ");
		}
		
		sBuilder.append(" order by score_value desc,score_id asc) t");

		if (score.getStart() != -1) {
			sBuilder.append(" limit " + score.getStart() + "," + score.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Score.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			scores = new ArrayList<Score>();
			for (Object object : list) {
				scores.add((Score)object);
			}
		}
		return scores;
	}

	public int  listScoresCount(Score score, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM score s ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join course co on co.course_id = s.course_id  ");
		sBuilder.append("  join clazz c on c.clazz_id = u.clazz_id  ");
		sBuilder.append(" where 1=1 ");

		if (score.getScore_id()!=0) {
			sBuilder.append(" and s.score_id = " + score.getScore_id() +" ");
		}
		if (score.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(score.getUser_name())) {
			sBuilder.append(" and u.user_name like '%" + score.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(score.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + score.getReal_name() +"%' ");
		}
		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getCourse_type()!=0) {
			sBuilder.append(" and co.course_type = " + score.getCourse_type() +" ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}
		if (score.getScore_valueMin()!=0) {
			sBuilder.append(" and s.score_value >= " + score.getScore_valueMin() +" ");
		}
		if (score.getScore_valueMax()!=0) {
			sBuilder.append(" and s.score_value <= " + score.getScore_valueMax() +" ");
		}
		if (score.getTeacher_id()!=0) {
			sBuilder.append(" and s.course_id in (select cc.course_id from course cc where cc.teacher_id=" + score.getTeacher_id() +") ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
	/**
	 * @Title: listScoresSum
	 * @Description: 总分成绩
	 * @param score
	 * @param conn
	 * @return List<Score>
	 */
	public List<Score>  listScoresSum(Score score, Connection conn){
		List<Score> scores = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("select  u.user_id,u.user_name,u.real_name,u.user_sex,co.course_name,c.clazz_name,sum(s.score_value) score_sum,s.score_year,s.score_year_half from score s  ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join course co on co.course_id = s.course_id  ");
		sBuilder.append("  join clazz c on c.clazz_id = u.clazz_id  ");
		sBuilder.append(" where 1=1 ");

		if (score.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(score.getUser_name())) {
			sBuilder.append(" and u.user_name like '%" + score.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(score.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + score.getReal_name() +"%' ");
		}
		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getCourse_type()!=0) {
			sBuilder.append(" and co.course_type = " + score.getCourse_type() +" ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}
		if (score.getTeacher_id()!=0) {
			sBuilder.append(" and s.course_id in (select cc.course_id from course cc where cc.teacher_id=" + score.getTeacher_id() +") ");
		}
		
		sBuilder.append(" group by u.user_id,u.user_name,u.real_name,u.user_sex,c.clazz_name,s.score_year,s.score_year_half ");
		sBuilder.append(" order by s.score_year desc,s.score_year_half desc,score_sum desc,s.score_id asc) t");

		if (score.getStart() != -1) {
			sBuilder.append(" limit " + score.getStart() + "," + score.getLimit());
		}
		List<Object> list = BaseDao.executeQuery(Score.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			scores = new ArrayList<Score>();
			for (Object object : list) {
				scores.add((Score)object);
			}
		}
		return scores;
	}
	
	/**
	 * @Title: listScoresSumCount
	 * @Description: 总分成绩
	 * @param score
	 * @param conn
	 * @return List<Score>
	 */
	public int  listScoresSumCount(Score score, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("select count(*) from (");
		sBuilder.append("select  u.user_id,u.user_name,u.real_name,u.user_sex,co.course_name,c.clazz_name,sum(s.score_value) score_sum,s.score_year,s.score_year_half from score s  ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join course co on co.course_id = s.course_id  ");
		sBuilder.append("  join clazz c on c.clazz_id = u.clazz_id  ");
		sBuilder.append(" where 1=1 ");

		if (score.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(score.getUser_name())) {
			sBuilder.append(" and u.user_name like '%" + score.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(score.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + score.getReal_name() +"%' ");
		}
		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getCourse_type()!=0) {
			sBuilder.append(" and co.course_type = " + score.getCourse_type() +" ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}
		if (score.getTeacher_id()!=0) {
			sBuilder.append(" and s.course_id in (select cc.course_id from course cc where cc.teacher_id=" + score.getTeacher_id() +") ");
		}
		
		sBuilder.append(" group by u.user_id,u.user_name,u.real_name,u.user_sex,c.clazz_name,s.score_year,s.score_year_half");

		sBuilder.append(" ) t");

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
	/**
	 * @Title: listSingleAvgScores
	 * @Description: 查询单科平均分
	 * @param score
	 * @param conn
	 * @return double
	 */
	public double  listSingleAvgScores(Score score, Connection conn){
		double avg = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("select round(avg(s.score_value),1) from score s ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join course co on co.course_id = s.course_id  ");
		sBuilder.append("  join clazz c on c.clazz_id = u.clazz_id  ");
		sBuilder.append(" where 1=1 ");

		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}
		if (score.getTeacher_id()!=0) {
			sBuilder.append(" and s.course_id in (select cc.course_id from course cc where cc.teacher_id=" + score.getTeacher_id() +") ");
		}
		
		double count = (Double)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		avg = count;
		return avg;
	}
	
	/**
	 * @Title: listSumAvgScores
	 * @Description: 查询所有科目平均分
	 * @param score
	 * @param conn
	 * @return double
	 */
	public double  listSumAvgScores(Score score, Connection conn){
		double avg = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("select round(sum(s.score_value)/count(distinct u.user_id),1) from score s  ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join course co on co.course_id = s.course_id  ");
		sBuilder.append("  join clazz c on c.clazz_id = u.clazz_id  ");
		sBuilder.append(" where 1=1 ");

		if (score.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}
		if (score.getTeacher_id()!=0) {
			sBuilder.append(" and s.course_id in (select cc.course_id from course cc where cc.teacher_id=" + score.getTeacher_id() +") ");
		}
		
		double count = (Double)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		avg = count;
		return avg;
	}
	
	/**
	 * @Title: listSingleScoresSection
	 * @Description: 查询单科成绩分布
	 * @param score
	 * @param conn
	 * @return List<Score>
	 */
	public List<Score>  listSingleScoresSection(Score score, Connection conn){
		List<Score> scores = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("select IFNULL(sum(case when s.score_value <= "+score.getScore_start()+" then 1 else 0 end),0) sec_1, ");
		sBuilder.append("	    IFNULL(sum(case when s.score_value > "+score.getScore_start()+"  and s.score_value <= "+(score.getScore_start()+score.getScore_sec() * 1)+"  then 1 else 0 end),0) sec_2, ");
		sBuilder.append("       IFNULL(sum(case when s.score_value > "+(score.getScore_start()+score.getScore_sec() * 1)+"  and s.score_value <= "+(score.getScore_start()+score.getScore_sec() * 2)+"  then 1 else 0 end),0) sec_3, ");
		sBuilder.append("       IFNULL(sum(case when s.score_value > "+(score.getScore_start()+score.getScore_sec() * 2)+"  and s.score_value <= "+(score.getScore_start()+score.getScore_sec() * 3)+" then 1 else 0 end),0) sec_4, ");
		sBuilder.append("       IFNULL(sum(case when s.score_value > "+(score.getScore_start()+score.getScore_sec() * 3)+"  and s.score_value <= "+(score.getScore_start()+score.getScore_sec() * 4)+" then 1 else 0 end),0) sec_5, ");
		sBuilder.append("       IFNULL(sum(case when s.score_value > "+(score.getScore_start()+score.getScore_sec() * 4)+"  and s.score_value <= "+(score.getScore_start()+score.getScore_sec() * 5)+"  then 1 else 0 end),0) sec_6, ");
		sBuilder.append("       IFNULL(sum(case when s.score_value > "+(score.getScore_start()+score.getScore_sec() * 5)+"  and s.score_value <= "+score.getScore_end()+"  then 1 else 0 end),0) sec_7 ");
		sBuilder.append("  from score s  ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join course co on co.course_id = s.course_id  ");
		sBuilder.append("  join clazz c on c.clazz_id = u.clazz_id  ");
		sBuilder.append(" where 1=1 ");

		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}
		if (score.getTeacher_id()!=0) {
			sBuilder.append(" and s.course_id in (select cc.course_id from course cc where cc.teacher_id=" + score.getTeacher_id() +") ");
		}
		
		List<Object> list = BaseDao.executeQuery(Score.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			scores = new ArrayList<Score>();
			for (Object object : list) {
				scores.add((Score)object);
			}
		}
		return scores;
	}
	
	/**
	 * @Title: listSumScoresSection
	 * @Description: 查询总分成绩分布
	 * @param score
	 * @param conn
	 * @return List<Score>
	 */
	public List<Score>  listSumScoresSection(Score score, Connection conn){
		List<Score> scores = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("select IFNULL(sum(case when t.score_value <= "+score.getScore_start()+" then 1 else 0 end),0) sec_1, ");
		sBuilder.append("	    IFNULL(sum(case when t.score_value > "+score.getScore_start()+"  and t.score_value <= "+(score.getScore_start()+score.getScore_sec() * 1)+"  then 1 else 0 end),0) sec_2, ");
		sBuilder.append("       IFNULL(sum(case when t.score_value > "+(score.getScore_start()+score.getScore_sec() * 1)+"  and t.score_value <= "+(score.getScore_start()+score.getScore_sec() * 2)+"  then 1 else 0 end),0) sec_3, ");
		sBuilder.append("       IFNULL(sum(case when t.score_value > "+(score.getScore_start()+score.getScore_sec() * 2)+"  and t.score_value <= "+(score.getScore_start()+score.getScore_sec() * 3)+" then 1 else 0 end),0) sec_4, ");
		sBuilder.append("       IFNULL(sum(case when t.score_value > "+(score.getScore_start()+score.getScore_sec() * 3)+"  and t.score_value <= "+(score.getScore_start()+score.getScore_sec() * 4)+" then 1 else 0 end),0) sec_5, ");
		sBuilder.append("       IFNULL(sum(case when t.score_value > "+(score.getScore_start()+score.getScore_sec() * 4)+"  and t.score_value <= "+(score.getScore_start()+score.getScore_sec() * 5)+"  then 1 else 0 end),0) sec_6, ");
		sBuilder.append("       IFNULL(sum(case when t.score_value > "+(score.getScore_start()+score.getScore_sec() * 5)+"  and t.score_value <= "+score.getScore_end()+"  then 1 else 0 end),0) sec_7 ");
		sBuilder.append("  from  ");
		sBuilder.append("  (select s.user_id,sum(s.score_value) score_value from score s   ");
		sBuilder.append("  	  join user u on s.user_id=u.user_id ");
		sBuilder.append("     join course co on co.course_id = s.course_id  ");
		sBuilder.append("     join clazz c on c.clazz_id = u.clazz_id  ");
		sBuilder.append("    where 1=1 ");

		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}
		if (score.getTeacher_id()!=0) {
			sBuilder.append(" and s.course_id in (select cc.course_id from course cc where cc.teacher_id=" + score.getTeacher_id() +") ");
		}
		sBuilder.append("    group by s.user_id) t ");
		
		List<Object> list = BaseDao.executeQuery(Score.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			scores = new ArrayList<Score>();
			for (Object object : list) {
				scores.add((Score)object);
			}
		}
		return scores;
	}

}
