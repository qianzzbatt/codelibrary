package com.codelirbrary.excel.convert;

import java.sql.JDBCType;
import java.sql.SQLType;

public class DefaultConvertor implements TypeConvertor {

    /**
     * 将JDBC类型转换为Java类型
     *
     * @param type JDBC类型名
     */
    @Override
    public String convertType(Object type) {
        StringBuilder sb = new StringBuilder();
        switch ((JDBCType)type) {
            case BIT:
            case BOOLEAN:
                sb.append("Boolean");
                break;
            case TINYINT:
            case SMALLINT:
            case INTEGER:
                sb.append("Integer");
                break;
            case BIGINT:
                sb.append("Long");
                break;
            case REAL:
                sb.append("Float");
                break;
            case FLOAT:
            case DOUBLE:
                sb.append("Double");
                break;
            case DECIMAL:
            case NUMERIC:
                sb.append("BigDecimal");
                break;
            case VARCHAR:
            case CHAR:
            case NCHAR:
            case NVARCHAR:
            case LONGVARCHAR:
            case LONGNVARCHAR:
                sb.append("String");
                break;
            case DATE:
            case TIME:
            case TIMESTAMP:
                sb.append("Date");
                break;
            case CLOB:
            case NCLOB:
            case BLOB:
            case BINARY:
            case VARBINARY:
            case LONGVARBINARY:
                sb.append("byte[]");
                break;
            default:
                sb.append("Object");

        }
        return sb.toString();
    }

}