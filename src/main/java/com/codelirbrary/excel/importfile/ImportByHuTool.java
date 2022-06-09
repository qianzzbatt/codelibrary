package com.codelirbrary.excel.importfile;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.codelirbrary.excel.convert.SparkConvertor;
import com.codelirbrary.excel.convert.TypeConvertor;

import java.util.List;
import java.util.Map;

/**
 * @author huangquan
 * @Description
 * @Date 2022/5/24
 **/
public class ImportByHuTool {

    public static void main(String[] args) {
        readExcelToSql();
    }

    /**
     * 读取excel文件,转成对应实体
     *          * 信号强度rsrp
     *          *@ExcelProperty("RSRP")
     *          *@JsonProperty("rsrp_avg")
     *          *private Integer rsrpAvg;
     */
    public static void readExcelToDto(){
        //注释
        StringBuilder docStr = new StringBuilder("/**\n");
        docStr.append("* {}\n");
        docStr.append("*/\n");
        docStr.append("@ExcelProperty(\"{}\")\n");
        docStr.append("@JsonProperty(\"{}\")\n");
        docStr.append("private {} {};\n");

        //转化类型
        TypeConvertor typeConvertor = new SparkConvertor();

        //读取excel文件并指定sheet页
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(
                "C:\\Users\\admin\\Desktop\\uway\\01-表结构(1).xlsx"),
                "业务表-报表（竞对）");
        //从第三行开始读
        List<List<Object>> readAll = reader.read(3);
        for (List<Object> objects : readAll) {
                String filedName = objects.get(0).toString();
                Object fileType = objects.get(1);
                String filedMark = objects.get(4).toString();
            String result = StrUtil.format(
                    docStr.toString(),
                    filedMark,
                    filedMark,
                    filedName,
                    typeConvertor.convertType(fileType),
                    //下划线转驼峰
                    StrUtil.toCamelCase(filedName));
            System.out.println(result);
        }
    }

    public static void readExcelToSql(){

        StringBuilder sql = new StringBuilder("select ");
        String nonNullFiled = "COALESCE({},0) as {},";

        //读取excel文件并指定sheet页
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(
                        "C:\\Users\\admin\\Desktop\\uway\\01-表结构(1).xlsx"),
                "业务表-报表（场景）");
        //从第三行开始读
        List<List<Object>> readAll = reader.read(10);
        for (List<Object> objects : readAll) {
            String filedName = objects.get(0).toString();
            String nonNullFiledName =StrUtil.format(nonNullFiled,filedName,filedName);
            sql.append(nonNullFiledName);
            sql.append("\r\n");
        }
        sql.append("from ott_report_scene_m  ${ew.customSqlSegment}");
        System.out.println(sql);
    }


}
