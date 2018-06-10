package com.nkl.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Cost;
import com.nkl.admin.domain.Course;
import com.nkl.admin.domain.Info;
import com.nkl.admin.domain.Score;
import com.nkl.admin.domain.Scource;
import com.nkl.admin.domain.User;
import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Param;

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = new AdminManager();
	
	//抓取页面参数
	User paramsUser;
	Clazz paramsClazz;
	Course paramsCourse;
	Score paramsScore;
	Cost paramsCost;
	Info paramsInfo;
	Scource paramsScource;
	
	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证学生会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.queryUser(admin);
			Param.setSession("admin", admin);
			
			setSuccessTip("编辑成功", "modifyInfo.jsp");
			
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证学生会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}
			
			setSuccessTip("修改成功", "modifyPwd.jsp");
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: queryQuestion
	 * @Description: 根据用户名查找密保问题
	 * @return String
	 */
	public String inputUserName(){
		return "inputUserName";
	}
	public String queryQuestion(){
		try {
			 //得到账号信息
			User user = adminManager.queryUser(paramsUser);
			if (user==null) {
				tip = "该用户名不存在!";
				Param.setAttribute("user_name", paramsUser.getUser_name());
				return "inputUserName";
			}
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			tip = "查找用户名异常!";
			Param.setAttribute("user_name", paramsUser.getUser_name());
			return "inputUserName";
		}
		
		return "inputUserAnswer";
	}
	
	/**
	 * @Title: validAnswer
	 * @Description: 验证密保问题
	 * @return String
	 */
	public String validAnswer(){
		try {
			 //得到账号信息
			String answer = paramsUser.getUser_answer();
			User user = adminManager.queryUser(paramsUser);
			if (!answer.equals(user.getUser_answer())) {
				tip = "密保答案错误!";
				Param.setAttribute("user_answer", answer);
				Param.setAttribute("user", user);
				return "inputUserAnswer";
			}
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			tip = "密保答案错误!";
			Param.setAttribute("user_answer", paramsUser.getUser_answer());
			Param.setAttribute("user", adminManager.queryUser(paramsUser));
			return "inputUserAnswer";
		}
		
		return "resetPass";
	}
	
	/**
	 * @Title: resetPass
	 * @Description: 重置密码
	 * @return String
	 */
	public String resetPass(){
		try {
			 //重置密码
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip = "密码重置异常!";
			Param.setAttribute("user", adminManager.queryUser(paramsUser));
			return "resetPass";
		}
		setSuccessTip("密码重置成功", "login.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询学生
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查看学生信息
			paramsUser.setUser_type(1);
			
			//设置分页信息
			setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询学生列表
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询学生异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: queryStudent
	 * @Description: 查询学生
	 * @return String
	 */
	public String queryStudent(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//判断课程类型
			if (paramsUser.getCourse_id()!=0) {
				//查询课程
				Course course = new Course();
				course.setCourse_id(paramsUser.getCourse_id());
				course = adminManager.queryCourse(course);
				//类型判断（必修or选修）
				if (course.getCourse_type()==1) {
					paramsUser.setCourse_id(0);//必修课查询所有学生
				}
			}
			//查询学生列表
			paramsUser.setUser_type(1);
			paramsUser.setStart(-1);
			List<User> users = adminManager.listUsers(paramsUser,null); 
			
			setResult("users", users);
			
		} catch (Exception e) {
			setErrorReason("查询学生信息失败，服务器异常！",e);
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加学生页面
	 * @return String
	 */
	public String addUserShow(){
		//查询班级字典
		Clazz clazz = new Clazz();
		clazz.setStart(-1);
		List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
		Param.setAttribute("clazzs", clazzs);
		
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加学生
	 * @return String
	 */
	public String addUser(){
		try {
			//检查学生是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.queryUser(user);
			if (user!=null) {
				tip="失败，该学号已经存在！";
				Param.setAttribute("user", paramsUser);
				
				//查询班级字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);
				
				return "userEdit";
			}
			
			 //添加学生
			paramsUser.setUser_type(1);
			paramsUser.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("添加学生异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	/**
	 * @Title: addUser
	 * @Description: 批量添加学生
	 * @return String
	 */
	public String addAllUser(){
		try {
			//检查学生是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.queryUser(user);
			if (user!=null) {
				tip="失败，该学号已经存在！";
				Param.setAttribute("user", paramsUser);
				
				//查询班级字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);
				
				return "userEdit";
			}
			
			 //添加学生
			paramsUser.setUser_type(1);
			paramsUser.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("添加学生异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑学生
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到学生
			User user = adminManager.queryUser(paramsUser);
			Param.setAttribute("user", user);
			
			//查询班级字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
		} catch (Exception e) {
			setErrorTip("查询学生异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑学生
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑学生
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listUsers.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			
			//查询班级字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			return "userEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除学生
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除学生
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除学生成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("删除学生异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listTeachers
	 * @Description: 查询教师
	 * @return String
	 */
	public String listTeachers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			paramsUser.setUser_type(2);
			
			//设置分页信息
			setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询教师列表
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询教师异常", "main.jsp");
			return "infoTip";
		}
		
		return "teacherShow";
	}
	
	/**
	 * @Title: addTeacherShow
	 * @Description: 显示添加教师页面
	 * @return String
	 */
	public String addTeacherShow(){
		return "teacherEdit";
	}
	
	/**
	 * @Title: addTeacher
	 * @Description: 添加教师
	 * @return String
	 */
	public String addTeacher(){
		try {
			//检查登录名是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.queryUser(user);
			if (user!=null) {
				tip="失败，该登录名已经存在！";
				Param.setAttribute("user", paramsUser);
				return "teacherEdit";
			}
			 //添加教师
			paramsUser.setUser_type(2);
			paramsUser.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			setErrorTip("添加教师异常", "Admin_listTeachers.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editTeacher
	 * @Description: 编辑教师
	 * @return String
	 */
	public String editTeacher(){
		try {
			 //得到教师
			User user = adminManager.queryUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询教师异常", "Admin_listTeachers.action");
			return "infoTip";
		}
		
		return "teacherEdit";
	}
	
	/**
	 * @Title: saveTeacher
	 * @Description: 保存编辑教师
	 * @return String
	 */
	public String saveTeacher(){
		try {
			 //保存编辑教师
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delTeachers
	 * @Description: 删除教师
	 * @return String
	 */
	public String delTeachers(){
		try {
			 //删除教师
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除教师成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			setErrorTip("删除教师异常", "Admin_listTeachers.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listClazzs
	 * @Description: 查询班级
	 * @return String
	 */
	public String listClazzs(){
		try {
			if (paramsClazz==null) {
				paramsClazz = new Clazz();
			}
			//设置分页信息
			setPagination(paramsClazz);
			//总的条数
			int[] sum={0};
			//查询班级列表
			List<Clazz> clazzs = adminManager.listClazzs(paramsClazz,sum); 
			
			Param.setAttribute("clazzs", clazzs);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询班级异常", "main.jsp");
			return "infoTip";
		}
		
		return "clazzShow";
	}
	
	/**
	 * @Title: addClazzShow
	 * @Description: 显示添加班级页面
	 * @return String
	 */
	public String addClazzShow(){
		return "clazzEdit";
	}
	
	/**
	 * @Title: addClazz
	 * @Description: 添加班级
	 * @return String
	 */
	public String addClazz(){
		try {
			 //添加班级
			adminManager.addClazz(paramsClazz);
			
			setSuccessTip("添加成功", "Admin_listClazzs.action");
		} catch (Exception e) {
			setErrorTip("添加班级异常", "Admin_listClazzs.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: editClazz
	 * @Description: 编辑班级
	 * @return String
	 */
	public String editClazz(){
		try {
			 //得到班级
			Clazz clazz = adminManager.queryClazz(paramsClazz);
			Param.setAttribute("clazz", clazz);
		} catch (Exception e) {
			setErrorTip("查询班级异常", "Admin_listClazzs.action");
			return "infoTip";
		}
		
		return "clazzEdit";
	}
	
	/**
	 * @Title: saveClazz
	 * @Description: 保存编辑班级
	 * @return String
	 */
	public String saveClazz(){
		try {
			 //保存编辑班级
			adminManager.updateClazz(paramsClazz);
			
			setSuccessTip("编辑成功", "Admin_listClazzs.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("clazz", paramsClazz);
			return "clazzEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delClazzs
	 * @Description: 删除班级
	 * @return String
	 */
	public String delClazzs(){
		try {
			 //删除班级
			adminManager.delClazzs(paramsClazz);
			
			setSuccessTip("删除班级成功", "Admin_listClazzs.action");
		} catch (Exception e) {
			setErrorTip("删除班级异常", "Admin_listClazzs.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listCourses
	 * @Description: 查询课程
	 * @return String
	 */
	public String listCourses(){
		try {
			if (paramsCourse==null) {
				paramsCourse = new Course();
			}
			//设置分页信息
			setPagination(paramsCourse);
			//总的条数
			int[] sum={0};
			//查询课程列表
			List<Course> courses = adminManager.listCourses(paramsCourse,sum); 
			
			Param.setAttribute("courses", courses);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询课程异常", "main.jsp");
			return "infoTip";
		}
		
		return "courseShow";
	}
	
	/**
	 * @Title: addCourseShow
	 * @Description: 显示添加课程页面
	 * @return String
	 */
	public String addCourseShow(){
		//查询教师集合
		User user = new User();
		user.setUser_type(2);
		user.setStart(-1);
		List<User> teachers = adminManager.listUsers(user, null);
		Param.setAttribute("teachers", teachers);
		
		return "courseEdit";
	}
	
	/**
	 * @Title: addCourse
	 * @Description: 添加课程
	 * @return String
	 */
	public String addCourse(){
		try {
			 //添加课程
			adminManager.addCourse(paramsCourse);
			
			setSuccessTip("添加成功", "Admin_listCourses.action");
		} catch (Exception e) {
			setErrorTip("添加课程异常", "Admin_listCourses.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editCourse
	 * @Description: 编辑课程
	 * @return String
	 */
	public String editCourse(){
		try {
			 //得到课程
			Course course = adminManager.queryCourse(paramsCourse);
			Param.setAttribute("course", course);
			
			//查询教师集合
			User user = new User();
			user.setUser_type(2);
			user.setStart(-1);
			List<User> teachers = adminManager.listUsers(user, null);
			Param.setAttribute("teachers", teachers);
			
		} catch (Exception e) {
			setErrorTip("查询课程异常", "Admin_listCourses.action");
			return "infoTip";
		}
		
		return "courseEdit";
	}
	
	/**
	 * @Title: saveCourse
	 * @Description: 保存编辑课程
	 * @return String
	 */
	public String saveCourse(){
		try {
			 //保存编辑课程
			adminManager.updateCourse(paramsCourse);
			
			setSuccessTip("编辑成功", "Admin_listCourses.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("course", paramsCourse);
			
			//查询教师集合
			User user = new User();
			user.setUser_type(2);
			user.setStart(-1);
			List<User> teachers = adminManager.listUsers(user, null);
			Param.setAttribute("teachers", teachers);
			
			return "courseEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delCourses
	 * @Description: 删除课程
	 * @return String
	 */
	public String delCourses(){
		try {
			 //删除课程
			adminManager.delCourses(paramsCourse);
			
			setSuccessTip("删除课程成功", "Admin_listCourses.action");
		} catch (Exception e) {
			setErrorTip("删除课程异常", "Admin_listCourses.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listScources
	 * @Description: 查询学生选课
	 * @return String
	 */
	public String listScources(){
		try {
			if (paramsScource==null) {
				paramsScource = new Scource();
			}
			//设置分页信息
			setPagination(paramsScource);
			//总的条数
			int[] sum={0};
			//查询学生选课列表
			User admin = (User)Param.getSession("admin");
			paramsScource.setUser_id(admin.getUser_id());
			List<Scource> scources = adminManager.listScources(paramsScource,sum); 
			
			Param.setAttribute("scources", scources);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询学生选课异常", "main.jsp");
			return "infoTip";
		}
		
		return "scourceShow";
	}
	
	/**
	 * @Title: addScourceShow
	 * @Description: 显示添加学生选课页面
	 * @return String
	 */
	public String addScourceShow(){
		try {
			if (paramsCourse==null) {
				paramsCourse = new Course();
			}
			//设置分页信息
			setPagination(paramsCourse);
			//总的条数
			int[] sum={0};
			//查询课程列表
			paramsCourse.setCourse_type(2);
			List<Course> courses = adminManager.listCourses(paramsCourse,sum); 
			
			Param.setAttribute("courses", courses);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询课程异常", "main.jsp");
			return "infoTip";
		}
		
		return "scourceEdit";
	}
	
	/**
	 * @Title: addScource
	 * @Description: 添加学生选课
	 * @return String
	 */
	public String addScource(){
		try {
			//判断学生选课是否已经添加
			Scource scource = adminManager.queryScource(paramsScource);
			if (scource!=null) {
				setErrorTip("失败，该课程已经选修了！", "Admin_addScourceShow.action");
				return "infoTip";
			}
			
			//添加学生选课
			adminManager.addScource(paramsScource);
			
			setSuccessTip("选修成功", "Admin_listScources.action");
		} catch (Exception e) {
			setErrorTip("选修异常", "Admin_addScourceShow.action");
			e.printStackTrace();
		}
		
		return "infoTip";
	}
	 
	
	/**
	 * @Title: delScources
	 * @Description: 删除学生选课
	 * @return String
	 */
	public String delScources(){
		try {
			 //删除学生选课
			adminManager.delScources(paramsScource);
			
			setSuccessTip("退选成功", "Admin_listScources.action");
		} catch (Exception e) {
			setErrorTip("退选异常", "Admin_listScources.action");
		}
		
		return "infoTip";
	}
	
	
	/**
	 * @Title: listScores
	 * @Description: 查询成绩
	 * @return String
	 */
	public String listScores(){
		try {
			if (paramsScore==null) {
				paramsScore = new Score();
			}
			//设置分页信息
			setPagination(paramsScore);
			//总的条数
			int[] sum={0};
			//用户身份设置
			User admin = (User)Param.getSession("admin");//查询当前用户
			if (admin.getUser_type()==2) {
				paramsScore.setTeacher_id(admin.getUser_id());//设置教师为当前用户
			}else if (admin.getUser_type()==1) {
				paramsScore.setUser_id(admin.getUser_id());//设置学生为当前用户
			}
			//查询成绩列表
			List<Score> scores = adminManager.listScores(paramsScore,sum); 
			
			Param.setAttribute("scores", scores);
			setTotalCount(sum[0]);
			
			//查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			Course course = new Course();
			course.setStart(-1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);
			
		} catch (Exception e) {
			setErrorTip("查询成绩异常", "main.jsp");
			return "infoTip";
		}
		
		return "scoreShow";
	}
	
	/**
	 * @Title: listScoresSum
	 * @Description: 查询成绩
	 * @return String
	 */
	public String listScoresSum(){
		try {
			if (paramsScore==null) {
				paramsScore = new Score();
			}
			//设置分页信息
			setPagination(paramsScore);
			//总的条数
			int[] sum={0};
			//用户身份设置
			User admin = (User)Param.getSession("admin");//查询当前用户
			if (admin.getUser_type()==2) {
				paramsScore.setTeacher_id(admin.getUser_id());//设置教师为当前用户
			}else if (admin.getUser_type()==1) {
				paramsScore.setUser_id(admin.getUser_id());//设置学生为当前用户
			}
			
			List<Score> scores = adminManager.listScoresSum(paramsScore,sum); 
			
			Param.setAttribute("scores", scores);
			setTotalCount(sum[0]);
			
			//查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			Course course = new Course();
			course.setStart(-1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);
			
		} catch (Exception e) {
			setErrorTip("查询成绩异常", "main.jsp");
			return "infoTip";
		}
		
		return "scoreSumShow";
	}
	
	/**
	 * @Title: addScoreShow
	 * @Description: 显示添加成绩页面
	 * @return String
	 */
	public String addScoreShow(){
		//查询课程
		User admin = (User)Param.getSession("admin");
		Course course = new Course();
		course.setStart(-1);
		if (admin.getUser_type()==2) {
			course.setTeacher_id(admin.getUser_id());//设置教师ID
		}
		List<Course> courses = adminManager.listCourses(course, null);
		if(courses==null){
			courses = new ArrayList<Course>();
		}
		Param.setAttribute("courses", courses);
		
		return "scoreEdit";
	}
	
	/**
	 * @Title: addScore
	 * @Description: 添加成绩
	 * @return String
	 */
	public String addScore(){
		try {
			//判断成绩是否已经添加
			Score score = adminManager.queryScore(paramsScore);
			if (score!=null) {
				tip = "失败，该学生本次成绩已经存在！";
				Param.setAttribute("score", paramsScore);
				
				//查询课程
				User admin = (User)Param.getSession("admin");
				Course course = new Course();
				course.setStart(-1);
				if (admin.getUser_type()==2) {
					course.setTeacher_id(admin.getUser_id());//设置教师ID
				}
				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);
				
				return "scoreEdit";
			}
			
			//添加成绩
			adminManager.addScore(paramsScore);
			
			setSuccessTip("添加成功", "Admin_listScores.action");
		} catch (Exception e) {
			setErrorTip("添加成绩异常", "Admin_listScores.action");
			e.printStackTrace();
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editScore
	 * @Description: 编辑成绩
	 * @return String
	 */
	public String editScore(){
		try {
			 //得到成绩
			Score score = adminManager.queryScore(paramsScore);
			Param.setAttribute("score", score);
			
			//查询课程
			User admin = (User)Param.getSession("admin");
			Course course = new Course();
			course.setStart(-1);
			if (admin.getUser_type()==2) {
				course.setTeacher_id(admin.getUser_id());//设置教师ID
			}
			List<Course> courses = adminManager.listCourses(course, null);
			if(courses==null){
				courses = new ArrayList<Course>();
			}
			Param.setAttribute("courses", courses);
			
		} catch (Exception e) {
			setErrorTip("查询成绩异常", "Admin_listScores.action");
			return "infoTip";
		}
		
		return "scoreEdit";
	}
	
	/**
	 * @Title: saveScore
	 * @Description: 保存编辑成绩
	 * @return String
	 */
	public String saveScore(){
		try {
			 //保存编辑成绩
			adminManager.updateScore(paramsScore);
			
			setSuccessTip("编辑成功", "Admin_listScores.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("score", paramsScore);
			
			//查询课程
			User admin = (User)Param.getSession("admin");
			Course course = new Course();
			course.setStart(-1);
			if (admin.getUser_type()==2) {
				course.setTeacher_id(admin.getUser_id());//设置教师ID
			}
			List<Course> courses = adminManager.listCourses(course, null);
			if(courses==null){
				courses = new ArrayList<Course>();
			}
			Param.setAttribute("courses", courses);
			
			return "scoreEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delScores
	 * @Description: 删除成绩
	 * @return String
	 */
	public String delScores(){
		try {
			 //删除成绩
			adminManager.delScores(paramsScore);
			
			setSuccessTip("删除成绩成功", "Admin_listScores.action");
		} catch (Exception e) {
			setErrorTip("删除成绩异常", "Admin_listScores.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listSingleScoresSection
	 * @Description: 查询单科成绩分布
	 * @return String
	 */
	public String listSingleScoresSectionShow(){
		try {
			//查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			User admin = (User)Param.getSession("admin");
			Course course = new Course();
			course.setStart(-1);
			if (admin.getUser_type()==2) {
				course.setTeacher_id(admin.getUser_id());//设置教师ID
			}
			List<Course> courses = adminManager.listCourses(course, null);
			if(courses==null){
				courses = new ArrayList<Course>();
			}
			Param.setAttribute("courses", courses);
			
		} catch (Exception e) {
			setErrorReason("查询单科成绩分布失败，服务器异常！",e);
			return "error";
		}
		
		return "singleScoresSectionShow";
	}
	public String listSingleScoresSection(){
		try {
			if(paramsScore!=null){
				//查询单科成绩分布
				List<Score> list = adminManager.listSingleScoresSection(paramsScore); 
				Score _score = list.get(0);
				
				// 准备图形数据
				String[] x = new String[7];
				int[] y = new int[7];
				for (int i = 0; i < 7; i++) {
					if (i==0) {
						x[i] = "0-"+paramsScore.getScore_start();
					}else if (i==6) {
						x[i] = (paramsScore.getScore_start()+paramsScore.getScore_sec()*(i-1)) + "-" + paramsScore.getScore_end();
					}else {
						x[i] = (paramsScore.getScore_start()+paramsScore.getScore_sec()*(i-1)) + "-" + (paramsScore.getScore_start()+paramsScore.getScore_sec()*i);
					}
					y[i] = _score.getSec_count(i+1);
				}
				
				setResult("x", x);
				setResult("y", y);
			}
			
		} catch (Exception e) {
			setErrorReason("查询单科成绩分布失败，服务器异常！",e);
			return "error";
		}
		
		return "success";
	}
	
	
	/**
	 * @Title: listSumScoresSection
	 * @Description: 查询总分成绩分布
	 * @return String
	 */
	public String listSumScoresSectionShow(){
		try {
			//查询班级字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
		} catch (Exception e) {
			setErrorReason("查询总分成绩分布失败，服务器异常！",e);
			return "error";
		}
		
		return "sumScoresSectionShow";
	}
	public String listSumScoresSection(){
		try {
			if(paramsScore!=null){
				//查询成绩列表
				User admin = (User)Param.getSession("admin");//查询当前用户
				if (admin.getUser_type()==2) {
					paramsScore.setTeacher_id(admin.getUser_id());//设置教师为当前用户
				}
				
				//查询总分成绩分布
				List<Score> list = adminManager.listSumScoresSection(paramsScore); 
				Score _score = list.get(0);
				
				// 准备图形数据
				String[] x = new String[7];
				int[] y = new int[7];
				for (int i = 0; i < 7; i++) {
					if (i==0) {
						x[i] = "0-"+paramsScore.getScore_start();
					}else if (i==6) {
						x[i] = (paramsScore.getScore_start()+paramsScore.getScore_sec()*(i-1)) + "-" + paramsScore.getScore_end();
					}else {
						x[i] = (paramsScore.getScore_start()+paramsScore.getScore_sec()*(i-1)) + "-" + (paramsScore.getScore_start()+paramsScore.getScore_sec()*i);
					}
					y[i] = _score.getSec_count(i+1);
				}
				
				
				setResult("x", x);
				setResult("y", y);
			}
			
		} catch (Exception e) {
			setErrorReason("查询总分成绩分布失败，服务器异常！",e);
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 查询通告
	 * @return String
	 */
	public String listInfos(){
		try {
			if (paramsInfo==null) {
				paramsInfo = new Info();
			}
			//设置分页信息
			setPagination(paramsInfo);
			//总的条数
			int[] sum={0};
			//查询通告列表
			List<Info> infos = adminManager.listInfos(paramsInfo,sum); 
			
			Param.setAttribute("infos", infos);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询通告异常", "main.jsp");
			return "infoTip";
		}
		
		return "infoShow";
	}
	
	/**
	 * @Title: queryInfo
	 * @Description: 查询通告
	 * @return String
	 */
	public String queryInfo(){
		try {
			 //得到通告
			Info info = adminManager.queryInfo(paramsInfo);
			Param.setAttribute("info", info);
		} catch (Exception e) {
			setErrorTip("查询奖惩异常", "Admin_listInfos.action");
			return "infoTip";
		}
		
		return "infoDetail";
	}
	
	/**
	 * @Title: addInfoShow
	 * @Description: 显示添加通告页面
	 * @return String
	 */
	public String addInfoShow(){
		return "infoEdit";
	}
	
	/**
	 * @Title: addInfo
	 * @Description: 添加通告
	 * @return String
	 */
	public String addInfo(){
		try {
			 //添加通告
			adminManager.addInfo(paramsInfo);
			
			setSuccessTip("添加成功", "Admin_listInfos.action");
		} catch (Exception e) {
			setErrorTip("添加通告异常", "Admin_listInfos.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: editInfo
	 * @Description: 编辑通告
	 * @return String
	 */
	public String editInfo(){
		try {
			 //得到奖惩
			Info info = adminManager.queryInfo(paramsInfo);
			Param.setAttribute("info", info);
		} catch (Exception e) {
			setErrorTip("查询通告异常", "Admin_listInfos.action");
			return "infoTip";
		}
		
		return "infoEdit";
	}
	
	/**
	 * @Title: saveInfo
	 * @Description: 保存编辑通告
	 * @return String
	 */
	public String saveInfo(){
		try {
			 //保存编辑通告
			adminManager.updateInfo(paramsInfo);
			
			setSuccessTip("编辑成功", "Admin_listInfos.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("info", paramsInfo);
			return "infoEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delInfos
	 * @Description: 删除通告
	 * @return String
	 */
	public String delInfos(){
		try {
			 //删除通告
			adminManager.delInfos(paramsInfo);
			
			setSuccessTip("删除通告成功", "Admin_listInfos.action");
		} catch (Exception e) {
			setErrorTip("删除通告异常", "Admin_listInfos.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listCosts
	 * @Description: 查询缴费
	 * @return String
	 */
	public String listCosts(){
		try {
			if (paramsCost==null) {
				paramsCost = new Cost();
			}
			//设置分页信息
			setPagination(paramsCost);
			//总的条数
			int[] sum={0};
			//用户身份限制
			User admin = (User)Param.getSession("admin");//查询当前用户
			if (admin.getUser_type()==1) {
				paramsCost.setUser_id(admin.getUser_id());//设置学生为当前用户
			}
			//查询缴费列表
			List<Cost> costs = adminManager.listCosts(paramsCost,sum); 
			
			Param.setAttribute("costs", costs);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询缴费异常", "main.jsp");
			return "infoTip";
		}
		
		return "costShow";
	}
	
	/**
	 * @Title: addCostShow
	 * @Description: 显示添加缴费页面
	 * @return String
	 */
	public String addCostShow(){
		//查询学生
		User user = new User();
		user.setStart(-1);
		user.setUser_type(1);
		List<User> users = adminManager.listUsers(user, null);
		if (users==null) {
			users = new ArrayList<User>();
		}
		Param.setAttribute("users", users);
		
		return "costEdit";
	}
	
	/**
	 * @Title: addCost
	 * @Description: 添加缴费
	 * @return String
	 */
	public String addCost(){
		try {
			 //添加缴费
			adminManager.addCost(paramsCost);
			
			setSuccessTip("添加成功", "Admin_listCosts.action");
		} catch (Exception e) {
			setErrorTip("添加缴费异常", "Admin_listCosts.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: editCost
	 * @Description: 编辑缴费
	 * @return String
	 */
	public String editCost(){
		try {
			 //得到缴费
			Cost cost = adminManager.queryCost(paramsCost);
			Param.setAttribute("cost", cost);
			
			//查询学生
			User user = new User();
			user.setStart(-1);
			user.setUser_type(1);
			List<User> users = adminManager.listUsers(user, null);
			if (users==null) {
				users = new ArrayList<User>();
			}
			Param.setAttribute("users", users);
			
		} catch (Exception e) {
			setErrorTip("查询缴费异常", "Admin_listCosts.action");
			return "infoTip";
		}
		
		return "costEdit";
	}
	
	/**
	 * @Title: saveCost
	 * @Description: 保存编辑缴费
	 * @return String
	 */
	public String saveCost(){
		try {
			 //保存编辑缴费
			adminManager.updateCost(paramsCost);
			
			setSuccessTip("编辑成功", "Admin_listCosts.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("cost", paramsCost);
			return "costEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delCosts
	 * @Description: 删除缴费
	 * @return String
	 */
	public String delCosts(){
		try {
			 //删除缴费
			adminManager.delCosts(paramsCost);
			
			setSuccessTip("删除缴费成功", "Admin_listCosts.action");
		} catch (Exception e) {
			setErrorTip("删除缴费异常", "Admin_listCosts.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: validateAdmin
	 * @Description: admin登录验证
	 * @return boolean
	 */
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public Clazz getParamsClazz() {
		return paramsClazz;
	}

	public void setParamsClazz(Clazz paramsClazz) {
		this.paramsClazz = paramsClazz;
	}

	public Course getParamsCourse() {
		return paramsCourse;
	}

	public void setParamsCourse(Course paramsCourse) {
		this.paramsCourse = paramsCourse;
	}

	public Score getParamsScore() {
		return paramsScore;
	}

	public void setParamsScore(Score paramsScore) {
		this.paramsScore = paramsScore;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Cost getParamsCost() {
		return paramsCost;
	}

	public void setParamsCost(Cost paramsCost) {
		this.paramsCost = paramsCost;
	}

	public Info getParamsInfo() {
		return paramsInfo;
	}

	public void setParamsInfo(Info paramsInfo) {
		this.paramsInfo = paramsInfo;
	}

	public Scource getParamsScource() {
		return paramsScource;
	}

	public void setParamsScource(Scource paramsScource) {
		this.paramsScource = paramsScource;
	}
	
	 
	
}
