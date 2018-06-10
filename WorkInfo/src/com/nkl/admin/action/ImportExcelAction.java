package com.nkl.admin.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Score;
import com.nkl.admin.manager.AdminManager;
import com.nkl.common.excel.pojo.RowData;
import com.nkl.common.jxls.ExcelReader;
import com.nkl.common.jxls.impl.ExcelReaderImpl;
import com.nkl.common.util.Param;
import com.nkl.common.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ImportExcelAction extends ActionSupport  {
	//封装文件标题请求参数的属性
	private String title;
	//封装导入文件域的属性
	private File upload;
	//封装导入文件类型的属性
	private String uploadContentType;
	//封装导入文件名的属性
	private String uploadFileName;
	//封装导入文件名的验证模板
	private String xmlFileName;
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public File getUpload() {
		return this.upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return this.uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return this.uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	 
	public String getXmlFileName() {
		return xmlFileName;
	}
	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}
	
	AdminManager adminManager = new AdminManager();
	
	/**
	 * @Title: Score
	 * @Description: 导入成绩
	 * @return String
	 */
	public String Score() {
		try {
			//读取导入成绩Excel信息
			ExcelReader excelReader = new ExcelReaderImpl();
			excelReader.readConfig(ImportExcelAction.class.getClassLoader().getResourceAsStream(getXmlFileName()));
			List<RowData>  rowDatas =  excelReader.getSheetRows(getUpload().getAbsolutePath(), 0);
	
			//设置提示信息
			String tipInfo = null;
			
			//导入成绩
			List<Score> scores = new ArrayList<Score>();
			if (rowDatas!=null && rowDatas.size()>0) {
				for (RowData rowData : rowDatas) {
					if (!rowData.isRowError()) {//如果没有错误信息
						Score score = new Score();
						score.setUser_name(rowData.getField("user_name"));
						score.setCourse_name(rowData.getField("course_name"));
						score.setScore_year(Integer.parseInt(rowData.getField("score_year")));
						String score_year_half = rowData.getField("score_year_half");
						if ("上半年".equals(score_year_half)) {
							score.setScore_year_half(1);
						}else {
							score.setScore_year_half(2);
						}
						score.setScore_value(Integer.parseInt(rowData.getField("score_value")));
						scores.add(score);
					}else {//如果有错误信息
						tipInfo = "学号为："+rowData.getField("user_name")+" 的信息有误！";
						break;
					}
				}
			}
			
			//判断Excel错误信息结果
			if(!StringUtil.isEmptyString(tipInfo)){
				Param.setAttribute("tip", "no");
				Param.setAttribute("tipInfo", tipInfo);
				return INPUT;
			}
			
			//导入成绩入库
			adminManager.addScoreBatch(scores);
		} catch (Exception e) {
			Param.setAttribute("tip", "no");
			Param.setAttribute("tipInfo", "后台服务器繁忙！");
			return INPUT;
		}
		Param.setAttribute("tip", "ok");
		return SUCCESS;
	}
}
