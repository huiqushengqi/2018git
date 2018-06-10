package com.nkl.admin.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.nkl.admin.dao.ClazzDao;
import com.nkl.admin.dao.CostDao;
import com.nkl.admin.dao.CourseDao;
import com.nkl.admin.dao.InfoDao;
import com.nkl.admin.dao.ScoreDao;
import com.nkl.admin.dao.ScourceDao;
import com.nkl.admin.dao.UserDao;
import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Cost;
import com.nkl.admin.domain.Course;
import com.nkl.admin.domain.Info;
import com.nkl.admin.domain.Score;
import com.nkl.admin.domain.Scource;
import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;

public class AdminManager {

	ClazzDao clazzDao = new ClazzDao();
	CourseDao courseDao = new CourseDao();
	ScourceDao scourceDao = new ScourceDao();
	ScoreDao scoreDao = new ScoreDao();
	UserDao userDao = new UserDao();
	CostDao costDao = new CostDao();
	InfoDao infoDao = new InfoDao();

	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User> listUsers(User user, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = userDao.listUsersCount(user, conn);
		}
		List<User> users = userDao.listUsers(user, conn);

		BaseDao.closeDB(null, null, conn);
		return users;
	}

	/**
	 * @Title: editUser
	 * @Description: 编辑用户信息
	 * @param user
	 * @return void
	 */
	public void editUser(User user) {
		Connection conn = BaseDao.getConnection();

		userDao.updateUser(user, conn);

		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: queryUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User queryUser(User user) {
		Connection conn = BaseDao.getConnection();
		User _user = userDao.getUser(user, conn);
		BaseDao.closeDB(null, null, conn);
		return _user;
	}

	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void addUser(User user) {
		Connection conn = BaseDao.getConnection();
		user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		userDao.addUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void updateUser(User user) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void delUsers(User user) {
		Connection conn = BaseDao.getConnection();
		userDao.delUsers(user.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: listClazzs
	 * @Description: 班级查询
	 * @param clazz
	 * @return List<Clazz>
	 */
	public List<Clazz> listClazzs(Clazz clazz, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = clazzDao.listClazzsCount(clazz, conn);
		}
		List<Clazz> clazzs = clazzDao.listClazzs(clazz, conn);

		BaseDao.closeDB(null, null, conn);
		return clazzs;
	}

	/**
	 * @Title: editClazz
	 * @Description: 编辑班级信息
	 * @param clazz
	 * @return void
	 */
	public void editClazz(Clazz clazz) {
		Connection conn = BaseDao.getConnection();

		clazzDao.updateClazz(clazz, conn);

		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: queryClazz
	 * @Description: 班级查询
	 * @param clazz
	 * @return Clazz
	 */
	public Clazz queryClazz(Clazz clazz) {
		Connection conn = BaseDao.getConnection();
		Clazz _clazz = clazzDao.getClazz(clazz, conn);
		BaseDao.closeDB(null, null, conn);
		return _clazz;
	}

	/**
	 * @Title: addClazz
	 * @Description: 添加班级
	 * @param clazz
	 * @return void
	 */
	public void addClazz(Clazz clazz) {
		Connection conn = BaseDao.getConnection();
		clazzDao.addClazz(clazz, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateClazz
	 * @Description: 更新班级信息
	 * @param clazz
	 * @return void
	 */
	public void updateClazz(Clazz clazz) {
		Connection conn = BaseDao.getConnection();
		clazzDao.updateClazz(clazz, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delClazzs
	 * @Description: 删除班级信息
	 * @param clazz
	 * @return void
	 */
	public void delClazzs(Clazz clazz) {
		Connection conn = BaseDao.getConnection();
		clazzDao.delClazzs(clazz.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: listCourses
	 * @Description: 课程查询
	 * @param course
	 * @return List<Course>
	 */
	public List<Course> listCourses(Course course, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = courseDao.listCoursesCount(course, conn);
		}
		List<Course> courses = courseDao.listCourses(course, conn);

		BaseDao.closeDB(null, null, conn);
		return courses;
	}

	/**
	 * @Title: editCourse
	 * @Description: 编辑课程信息
	 * @param course
	 * @return void
	 */
	public void editCourse(Course course) {
		Connection conn = BaseDao.getConnection();

		courseDao.updateCourse(course, conn);

		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: queryCourse
	 * @Description: 课程查询
	 * @param course
	 * @return Course
	 */
	public Course queryCourse(Course course) {
		Connection conn = BaseDao.getConnection();
		Course _course = courseDao.getCourse(course, conn);
		BaseDao.closeDB(null, null, conn);
		return _course;
	}

	/**
	 * @Title: addCourse
	 * @Description: 添加课程
	 * @param course
	 * @return void
	 */
	public void addCourse(Course course) {
		Connection conn = BaseDao.getConnection();
		courseDao.addCourse(course, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateCourse
	 * @Description: 更新课程信息
	 * @param course
	 * @return void
	 */
	public void updateCourse(Course course) {
		Connection conn = BaseDao.getConnection();
		courseDao.updateCourse(course, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delCourses
	 * @Description: 删除课程信息
	 * @param course
	 * @return void
	 */
	public void delCourses(Course course) {
		Connection conn = BaseDao.getConnection();
		courseDao.delCourses(course.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listScources
	 * @Description: 学生选课查询
	 * @param scource
	 * @return List<Scource>
	 */
	public List<Scource> listScources(Scource scource, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = scourceDao.listScourcesCount(scource, conn);
		}
		List<Scource> scources = scourceDao.listScources(scource, conn);

		BaseDao.closeDB(null, null, conn);
		return scources;
	}
	
	/**
	 * @Title: queryScource
	 * @Description: 学生选课查询
	 * @param scource
	 * @return Scource
	 */
	public Scource queryScource(Scource scource) {
		Connection conn = BaseDao.getConnection();
		Scource _scource = scourceDao.getScource(scource, conn);
		BaseDao.closeDB(null, null, conn);
		return _scource;
	}
	
	/**
	 * @Title: addScource
	 * @Description: 添加学生选课
	 * @param scource
	 * @return void
	 */
	public void addScource(Scource scource) {
		Connection conn = BaseDao.getConnection();
		scourceDao.addScource(scource, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delScources
	 * @Description: 删除学生选课信息
	 * @param scource
	 * @return void
	 */
	public void delScources(Scource scource) {
		Connection conn = BaseDao.getConnection();
		//删除选课信息
		scourceDao.delScources(scource.getIds().split(","), conn);
		//删除成绩信息
		//scoreDao.delScoreByScourceId(scource.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listScores
	 * @Description: 成绩查询
	 * @param score
	 * @return List<Score>
	 */
	public List<Score> listScores(Score score, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = scoreDao.listScoresCount(score, conn);
		}
		List<Score> scores = scoreDao.listScores(score, conn);

		BaseDao.closeDB(null, null, conn);
		return scores;
	}
	
	/**
	 * @Title: listScoresSum
	 * @Description: 成绩查询
	 * @param score
	 * @return List<Score>
	 */
	public List<Score> listScoresSum(Score score, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = scoreDao.listScoresSumCount(score, conn);
		}
		List<Score> scores = scoreDao.listScoresSum(score, conn);

		BaseDao.closeDB(null, null, conn);
		return scores;
	}

	/**
	 * @Title: editScore
	 * @Description: 编辑成绩信息
	 * @param score
	 * @return void
	 */
	public void editScore(Score score) {
		Connection conn = BaseDao.getConnection();

		scoreDao.updateScore(score, conn);

		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: queryScore
	 * @Description: 成绩查询
	 * @param score
	 * @return Score
	 */
	public Score queryScore(Score score) {
		Connection conn = BaseDao.getConnection();
		Score _score = scoreDao.getScore(score, conn);
		BaseDao.closeDB(null, null, conn);
		return _score;
	}

	/**
	 * @Title: addScore
	 * @Description: 添加成绩
	 * @param score
	 * @return void
	 */
	public void addScore(Score score) {
		Connection conn = BaseDao.getConnection();
		scoreDao.addScore(score, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: addScoreBatch
	 * @Description: 添加成绩
	 * @param List<Score>
	 * @return void
	 * @throws SQLException 
	 */
	public void addScoreBatch(List<Score> scores) throws SQLException {
		Connection conn = BaseDao.getConnection();
		conn.setAutoCommit(false);
		for (int i = 0; i < scores.size(); i++) {
			scoreDao.importScore(scores.get(i), conn);
			if ((i+1) % 500==0) {//每500行提交一次
				conn.commit();
			}
		}
		conn.commit();
		conn.setAutoCommit(true);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateScore
	 * @Description: 更新成绩信息
	 * @param score
	 * @return void
	 */
	public void updateScore(Score score) {
		Connection conn = BaseDao.getConnection();
		scoreDao.updateScore(score, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delScores
	 * @Description: 删除成绩信息
	 * @param score
	 * @return void
	 */
	public void delScores(Score score) {
		Connection conn = BaseDao.getConnection();
		scoreDao.delScores(score.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listSingleAvgScores
	 * @Description: 查询单科平均分
	 * @param score
	 * @return Map<Integer,Score>
	 */
	public double listSingleAvgScores(Score score) {
		Connection conn = BaseDao.getConnection();
		double avg = scoreDao.listSingleAvgScores(score, conn);

		BaseDao.closeDB(null, null, conn);
		return avg;
	}
	
	/**
	 * @Title: listSumAvgScores
	 * @Description: 查询所有科目平均分
	 * @param score
	 * @return Map<Integer,Score>
	 */
	public double listSumAvgScores(Score score) {
		Connection conn = BaseDao.getConnection();
		double avg = scoreDao.listSumAvgScores(score, conn);

		BaseDao.closeDB(null, null, conn);
		return avg;
	}
	
	/**
	 * @Title: listSingleScoresSection
	 * @Description: 查询单科成绩分布
	 * @param score
	 * @return List<Score>
	 */
	public List<Score> listSingleScoresSection(Score score) {
		Connection conn = BaseDao.getConnection();
		List<Score> scores = scoreDao.listSingleScoresSection(score, conn);

		BaseDao.closeDB(null, null, conn);
		return scores;
	}
	
	/**
	 * @Title: listSumScoresSection
	 * @Description: 查询总分成绩分布
	 * @param score
	 * @return List<Score>
	 */
	public List<Score> listSumScoresSection(Score score) {
		Connection conn = BaseDao.getConnection();
		List<Score> scores = scoreDao.listSumScoresSection(score, conn);

		BaseDao.closeDB(null, null, conn);
		return scores;
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 奖惩查询
	 * @param info
	 * @return List<Info>
	 */
	public List<Info> listInfos(Info info, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = infoDao.listInfosCount(info, conn);
		}
		List<Info> infos = infoDao.listInfos(info, conn);

		BaseDao.closeDB(null, null, conn);
		return infos;
	}

	/**
	 * @Title: editInfo
	 * @Description: 编辑奖惩信息
	 * @param info
	 * @return void
	 */
	public void editInfo(Info info) {
		Connection conn = BaseDao.getConnection();

		infoDao.updateInfo(info, conn);

		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: queryInfo
	 * @Description: 奖惩查询
	 * @param info
	 * @return Info
	 */
	public Info queryInfo(Info info) {
		Connection conn = BaseDao.getConnection();
		Info _info = infoDao.getInfo(info, conn);
		BaseDao.closeDB(null, null, conn);
		return _info;
	}

	/**
	 * @Title: addInfo
	 * @Description: 添加奖惩
	 * @param info
	 * @return void
	 */
	public void addInfo(Info info) {
		Connection conn = BaseDao.getConnection();
		info.setInfo_date(DateUtil.dateToDateString(new Date()));
		infoDao.addInfo(info, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateInfo
	 * @Description: 更新奖惩信息
	 * @param info
	 * @return void
	 */
	public void updateInfo(Info info) {
		Connection conn = BaseDao.getConnection();
		infoDao.updateInfo(info, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delInfos
	 * @Description: 删除奖惩信息
	 * @param info
	 * @return void
	 */
	public void delInfos(Info info) {
		Connection conn = BaseDao.getConnection();
		infoDao.delInfos(info.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listCosts
	 * @Description: 缴费查询
	 * @param cost
	 * @return List<Cost>
	 */
	public List<Cost> listCosts(Cost cost, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = costDao.listCostsCount(cost, conn);
		}
		List<Cost> costs = costDao.listCosts(cost, conn);

		BaseDao.closeDB(null, null, conn);
		return costs;
	}

	/**
	 * @Title: editCost
	 * @Description: 编辑缴费信息
	 * @param cost
	 * @return void
	 */
	public void editCost(Cost cost) {
		Connection conn = BaseDao.getConnection();

		costDao.updateCost(cost, conn);

		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: queryCost
	 * @Description: 缴费查询
	 * @param cost
	 * @return Cost
	 */
	public Cost queryCost(Cost cost) {
		Connection conn = BaseDao.getConnection();
		Cost _cost = costDao.getCost(cost, conn);
		BaseDao.closeDB(null, null, conn);
		return _cost;
	}

	/**
	 * @Title: addCost
	 * @Description: 添加缴费
	 * @param cost
	 * @return void
	 */
	public void addCost(Cost cost) {
		Connection conn = BaseDao.getConnection();
		costDao.addCost(cost, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateCost
	 * @Description: 更新缴费信息
	 * @param cost
	 * @return void
	 */
	public void updateCost(Cost cost) {
		Connection conn = BaseDao.getConnection();
		costDao.updateCost(cost, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delCosts
	 * @Description: 删除缴费信息
	 * @param cost
	 * @return void
	 */
	public void delCosts(Cost cost) {
		Connection conn = BaseDao.getConnection();
		costDao.delCosts(cost.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
}
