/** 
 * Copyright @ 2016  shuibian Co. Ltd. 
 * All right reserved. 
 * @author: Lijiannan 
 * date: 	 2016年7月12日下午5:57:21 
 */
package cn.crap.common.utils.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import cn.crap.common.utils.DateUtil.DateUtil;


/**
 * @description 修改时间处理方式
 * @author Lijiannan
 * @time 2016年7月12日下午5:57:21
 * @version 1.0
 */
public class ByTimeHandler extends BaseTypeHandler<String> {

	private SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYY_MM_DD_HH_mm_ss);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.
	 * PreparedStatement, int, java.lang.Object,
	 * org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setNonNullParameter(PreparedStatement paramPreparedStatement, int paramInt, String paramT, JdbcType paramJdbcType) throws SQLException {
		try {
			if(paramT == null || "".equals(paramT)){
				paramPreparedStatement.setTimestamp(paramInt, null);
			}
			Timestamp timestamp = new Timestamp(sdf.parse(paramT).getTime());
			paramPreparedStatement.setTimestamp(paramInt, timestamp);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet
	 * , java.lang.String)
	 */
	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp sqlTimestamp = rs.getTimestamp(columnName);
		return sqlTimestamp != null ? sdf.format(sqlTimestamp) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet
	 * , int)
	 */
	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp sqlTimestamp = rs.getTimestamp(columnIndex);
		return sqlTimestamp != null ? sdf.format(sqlTimestamp) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.
	 * CallableStatement, int)
	 */
	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp sqlTimestamp = cs.getTimestamp(columnIndex);
		return sqlTimestamp != null ? sdf.format(sqlTimestamp) : null;
	}

}
