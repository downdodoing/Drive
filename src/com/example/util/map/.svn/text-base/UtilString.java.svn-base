package com.example.util.map;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符格式的处理工具类，提供判断字符长度，为空，编码，加密等方法 StringTools.java Create on 2012-3-6 Copyright (c) 2012-3-6 by www.yao1.cn
 * 
 * @author <a href="2602781@qq.com">王雪峰</a>
 * @version 1.0
 */
public class UtilString {

    /**
     * 判断字符是不是数字(并且为整整数)
     * 
     * @param str
     * @return
     */
    public static boolean isNumAndGreaterThanZero(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (str == null || str.trim().equals("")) {
            return false;
        } else {
            if (!isNum.matches()) {
                return false;
            } else {
                if (Integer.parseInt(str) >= 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * 判断一个字符串是否是所有空格
     * 
     * @param s
     * @return
     */
    public static boolean removeAllSpace(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return notNull(dest);
    }

    /**
     * 过滤一个字符串中的所有空格
     * 
     * @param s
     * @return
     */
    public static String removeSpace(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;

    }

    /**
     * 判断字符是否为空
     * 
     * @param s
     * @return
     */
    public static boolean notNull(Object s) {
        if (s == null || removeSpace(s.toString()).equals(""))
            return false;
        else {
            return true;
        }
    }

    /**
     * 判断是否为空并返回字符
     * 
     * @param s
     * @return
     */
    public static String notNullStr(Object s) {
        if (s == null || s == "null" || removeSpace(s.toString()).equals(""))
            return "";
        else {
            return s.toString();
        }
    }

    /**
     * 用于sql语句的字符串中单引号的过滤, 将单个单引号替换成双个单引号
     * 
     * @param s 要过滤的字符串
     * @return 返回过滤后的字符串
     */
    public static String getSqlStr(Object s) {
        if (s == null) {
            return "''";
        } else {
            String str = s.toString();
            return "'" + str.replaceAll("'", "''") + "'";
        }
    }

    /**
     * 加密字符串
     * 
     * @param csinput被加密的字符串
     * @return 返回通过MD5加密后的字符串
     */
    public static String transferMD5(String csinput, int len) {
        byte[] b, b2;
        StringBuffer buf;
        String csreturn = null;
        try {
            b = csinput.getBytes("iso-8859-1");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(b);
            b2 = md.digest();
            buf = new StringBuffer(b2.length * 2);
            for (int nLoopindex = 0; nLoopindex < b2.length; nLoopindex++) {
                if ((b2[nLoopindex] & 0xff) < 0x10) {
                    buf.append("0");
                }
                buf.append(Long.toString(b2[nLoopindex] & 0xff, len));
            }
            csreturn = new String(buf);
        } catch (Exception e) {
            csreturn = null;
        }
        return csreturn;
    }

    /**
     * 判断指定字符串的编码集
     * 
     * @param str要判断的字符串
     * @return 指定字符串的编码集。可能存在指定字符串在多种字符集下显示正常，此字符串将不能判断它的真正字符集
     */
    public static String getEncoding(String str) {
        /*
         * 将指定的字符串通过转码后如果还能和转码前的字符串匹配 将认为该字符串为转码的字符集
         */
        String encode = "UTF-8";
        // UTF-8的字符集判断
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        // GB2312的字符集判断
        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        // ISO-8859-1的字符集判断
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        // GBK的字符集判断
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        // 都不符合时返回
        return "";
    }

    /**
     * 将指定的字符串转码为UTF-8的编码格式
     * 
     * @param str -要转换的字符串
     * @return 返回转码后的字符串
     */
    public static String getUTF(String str) {
        try {
            // 得到指定字符串的编码集
            String encode = getEncoding(str);
            // 根据指定字符串的编码集得到该字符串的字节数组
            byte[] b = str.getBytes(encode);
            // 返回UTF-8编码的字符串
            return new String(b, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            return str;
        }
    }

    /**
     * 将指定的字符串转码为UTF-8的编码格式
     * 
     * @param str -要转换的字符串
     * @return 返回转码后的字符串
     */
    public static String getGBK(String str, String newEncode) {
        try {
            // 得到指定字符串的编码集
            String encode = getEncoding(str);
            // 根据指定字符串的编码集得到该字符串的字节数组
            byte[] b = str.getBytes(encode);
            // 返回UTF-8编码的字符串
            return new String(b, newEncode);
        } catch (UnsupportedEncodingException e1) {
            return str;
        }
    }

    /**
     * 判断字符长度是否超过设定长度
     * 
     * @param str 字符串
     * @param offset 设定的长度，中文汉字乘以2
     * @return
     */
    public static boolean judegeStrLength(String str, int offset) {
        if (str.length() <= offset) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param text
     * @param length
     * @param sign
     * @return
     */
    public static String subStringText(String text, int length, String sign) {
        if (text.length() <= length) {
            return text;
        } else {
            text = text.substring(0, length).toString() + sign;
            return text;
        }
    }

    /**
     * 按照字节数来截取长度
     * 
     * @param str 字符串
     * @param a 要截取的长度 汉字是2个字节，其他1个字节
     * @return
     */
    public static String interceptStr(String str, int len, String sign) {
        String temp = "";
        int k = 0;
        for (int i = 0; i < str.length(); i++) {
            byte[] b = (str.charAt(i) + "").getBytes(); // 每循环一次，将str里的值放入byte数组
            k = k + b.length;
            if (k > len) { // 如果数组长度大于6，随机跳出循环
                break;
            }
            temp = temp + str.charAt(i); // 拼接新字符串
        }
        if (k > len && sign != null) {
            temp += sign;
        }
        return temp;
    }

    /**
     * 将指定的信息写入指定的文件中
     * 
     * @param fileName -要写入的文件路径
     * @param message -要写入的信息
     * @return 是否写入成功
     */
    public static boolean writeLog(String fileName, String message) {
        OutputStreamWriter fout = null;
        String errorLog = getDateStr("yyyy-MM-dd") + ".log";

        try {
            // 根据要写入的文件路径和“UTF-8”编码创建一个文件输出流
            fout = new OutputStreamWriter(new FileOutputStream(fileName + errorLog, true), "UTF-8");
            // 在写入信息后换行
            fout.write(message);
            return true;
        } catch (Exception e) {
        } finally {
            /*
             * 刷新和关闭输出流
             */
            try {
                if (fout != null) {
                    fout.flush();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将指定的信息写入指定的文件中
     * 
     * @param fileName -要写入的文件路径
     * @param message -要写入的信息
     * @return 是否写入成功
     */
    public static boolean writeSql(String fileName, String message) {
        OutputStreamWriter fout = null;
        String errorLog = getDateStr("yyyy-MM-dd") + ".sql";

        try {
            // 根据要写入的文件路径和“UTF-8”编码创建一个文件输出流
            fout = new OutputStreamWriter(new FileOutputStream(fileName + errorLog, true), "UTF-8");
            // 在写入信息后换行
            fout.write(message);
            return true;
        } catch (Exception e) {
        } finally {
            /*
             * 刷新和关闭输出流
             */
            try {
                if (fout != null) {
                    fout.flush();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 根据格式获取当前日期
     * 
     * @param format 日期格式
     * @return
     */
    public static String getDateStr(String format) {
        String strDate = "";
        java.util.Date dDate = new java.util.Date();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
        strDate = formatter.format(dDate);
        return strDate;
    }

    /**
     * @param s
     * @return 去掉标记
     */
    public static String outTag(final String s) {
        return s.replaceAll("<.*?>", "");
    }

    /**
     * 将字符串遍历为list
     * 
     * @param cids
     * @return
     */
    public static List<String> composeList(String str, String px) {
        List<String> list = new ArrayList<String>();
        for (String c : str.split(px)) {
            list.add(c);
        }
        return list;
    }

    /**
     * 将字符串遍历为list
     * 
     * @param cids
     * @return
     */
    public static List<Integer> composeListInteger(String str, String px) {
        List<Integer> list = new ArrayList<Integer>();
        for (String c : str.split(px)) {
            list.add(Integer.parseInt(c));
        }
        return list;
    }

    /**
     * 右补位，左对齐
     * 
     * @param oriStr 原字符串
     * @param len 目标字符串长度
     * @param alexin 补位字符
     * @return 目标字符串
     */
    public static String padRight(String oriStr, int len, char alexin) {
        String str = "";
        int strlen = oriStr.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = str + oriStr;
        return str;
    }

    /**
     * 左补位，右对齐
     * 
     * @param oriStr 原字符串
     * @param len 目标字符串长度
     * @param alexin 补位字符
     * @return 目标字符串
     */
    public static String padLeft(String oriStr, int len, char alexin) {
        String str = "";
        int strlen = oriStr.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = oriStr + str;
        return str;
    }

    public static String to24Hours(Integer time) {
        if (time <= 9) {
            return padRight(time + ":00", 5, '0');
        }
        return time + ":00";
    }

    public static void main(String[] args) {
        System.out.println(to24Hours(12));
    }
}
