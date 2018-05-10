package cn.netinnet.nna.constant;

import java.io.Serializable;

public class CodeConstant implements Serializable {
	/**
	 * 任务顺利执行完成时返回的响应代码
	 */
	public static final int CODE_OK = 200;
	public static final int CODE_Created = 201;
	public static final int CODE_Accepted = 202;
	public static final int CODE_NoContent = 204;
	public static final int CODE_PartialContent = 206;

	/**
	 * 当前请求被永久的迁移到了新的路径。
	 */
	public static final int CODE_MovedPermanently = 301;

	/**
	 * 请求的资源没有发生变化，建议使用缓存资源。
	 */
	public static final int CODE_NotModified = 304;

	/**
	 * 资源不存在或者请求无效。
	 */
	public static final int CODE_BadRequest = 400;

	/**
	 * 请求的任务需要授权访问，没有提供授权信息。
	 */
	public static final int CODE_Unauthorized = 401;

	/**
	 * 请求的任务禁止访问。
	 */
	public static final int CODE_Forbidden = 403;

	/**
	 * 请求的任务不存在，或者资源不存在。
	 */
	public static final int CODE_NotFound = 404;

	/**
	 * 不允许使用的方法。
	 */
	public static final int CODE_MethodNotAllowd = 405;

	/**
	 * 任务执行时不满足先决条件时返回的响应代码，通常用于如下情况： .参数非法 .执行环境不匹配 ...
	 */
	public static final int CODE_PreconditionFailed = 412;

	/**
	 * 请求中指定的"范围"无法满足,-因为被访问的资源不覆盖这个字节范围。
	 */
	public static final int CODE_RequestedRangeNotSatisfiable = 416;

	/**
	 * 返回数据与预期不一致或返回无效数据。
	 */
	public static final int CODE_ExpectationFailed = 417;

	/**
	 * 任务执行时发生内部错误时返回的响应代码
	 */
	public static final int CODE_InternalServerError = 500;

	/**
	 * 服务维护或故障，暂时无法处理请求
	 */
	public static final int CODE_InternalServerError1 = 503;
}
