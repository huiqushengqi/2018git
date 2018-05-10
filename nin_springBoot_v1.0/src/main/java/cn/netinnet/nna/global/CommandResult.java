/**
 *
 * 版权声明 厦门网中网软件有限公司, 版权所有 违者必究
 *
 *<br> Copyright:	Copyright (c) 2014
 *<br> Company:		厦门网中网软件有限公司
 *<br> @author		baihw
 *————————————————————————————————
 *修改记录
 *    修改者：
 *    修改时间：
 *    修改原因：
 *————————————————————————————————
 */
package cn.netinnet.nna.global;

import cn.netinnet.nna.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 程序描述: 	命令执行结果的统一格式
 * @author		baihw
 */

public class CommandResult
{
	/**
	 * 表示执行成功的响应代码
	 */
	public static final int CODEOK = 200 ;
	
	// 响应代码
	private final int _CODE ;
	// 响应消息
	private final String _MSG ;
	// 响应数据
	private final Object _DATA ;
	
	/**
	 * 
	 * @param code 	响应代码
	 * @param msg 	响应消息 
	 * @param data 	响应数据
	 */
	public CommandResult( int code, String msg, Object data )
	{
		this._CODE = code ;
		this._MSG = msg ;
		this._DATA = data ;
	}
	
	/**
	 * 从一个包含code,msg,data的map中构造出一个CommandResult实例。
	 * @param commandResultMap
	 */
	public CommandResult( Map<String, Object> commandResultMap )
	{
		if( null == commandResultMap || commandResultMap.size()<3 )
			throw new RuntimeException( "无法识别的map对象" ) ;
		final Object code = commandResultMap.get( "code" ) ;
		final Object msg = commandResultMap.get( "msg" ) ;
		final Object data = commandResultMap.get( "data" ) ;
		if( null == code )
			throw new RuntimeException( "不符合的规则的map对象" ) ;
		this._CODE = StringUtil.parseInt( code.toString(), -1 ) ;
		this._MSG = null == msg ? null : String.valueOf( msg ) ;
		this._DATA = data ;
	}
	
	/**
	 * 转换为map对象。
	 * @return
	 */
	public Map<String, Object> toMap()
	{
		Map<String, Object> result = new HashMap<String, Object>() ;
		result.put( "code", this._CODE ) ;
		result.put( "msg", this._MSG ) ;
		result.put( "data", this._DATA ) ;
		return result ;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder() ;
		sb.append( "{\"code\":\"" ).append( this._CODE ).append( "\"" ) ;
		sb.append( ",\"msg\":\"" ).append( this._MSG ).append( "\"" ) ;
		sb.append( ",\"data\":\"" ).append( this._DATA ).append( "\"}" ) ;
		return sb.toString() ;
	}
	
	/**
	 * 获取响应代码
	 * @return
	 */
	public int getCode()
	{
		return this._CODE ;
	}
	
	/**
	 * 获取响应消息
	 * @return
	 */
	public String getMsg()
	{
		return this._MSG ;
	}
	
	/**
	 * 获取响应数据 
	 * @return
	 */
	@SuppressWarnings( "unchecked" )
	public <T> T getData()
	{
		return (T)this._DATA ;
	}
	
	/**
	 * 是否成功，当前判断标准为code返回200.其它情况都认为包含错误。
	 * @return
	 */
	public boolean isOK()
	{
		return CODEOK == this._CODE ;
	}
	
}  // End class