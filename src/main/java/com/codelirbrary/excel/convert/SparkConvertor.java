package com.codelirbrary.excel.convert;

import java.sql.JDBCType;

public class SparkConvertor implements TypeConvertor {

    /**
     * 将Spark类型转换为Java类型
     *
     * @param type Spark类型名
     */
    @Override
    public String convertType(Object type) {
        StringBuilder sb = new StringBuilder();
        switch ((String) type) {
            case "timestamp":
                sb.append("Date");
                break;
            case "string":
                sb.append("String");
                break;
            case "int":
            case "bigint":
                sb.append("int");
                break;
            case "double":
                sb.append("double");
                break;
        }
        return sb.toString();
    }

}