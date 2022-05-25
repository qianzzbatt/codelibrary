package com.codelirbrary.excel.convert;

import java.sql.JDBCType;

public interface TypeConvertor {

    /**
     * 将指定类型转换为Java类型
     * 默认使用JDBC转换器
     * @param type 类型名
     * @return Java类型名
     */
    String convertType(Object type);


}