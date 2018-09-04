package org.xuxi.codex.common.utils;



import org.springframework.util.DigestUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    // int类型正则
    private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");

    /**
     * 检查是否只包含数值
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {

        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * is integer string.
     *
     * @param str 字符串
     * @return is integer
     */
    public static boolean isInteger(String str) {

        if (str == null || str.length() == 0) return false;
        return INT_PATTERN.matcher(str).matches();
    }

    /**
     * string转int,如果字符串中不是数字则反回0
     *
     * @param str
     * @return
     */
    public static int parseInteger(String str) {

        if (!isInteger(str)) return 0;
        return Integer.parseInt(str);
    }


    /**
     * 过滤特殊字符,只要是判断网页的参数传递 目前过滤的是 <,>,&
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String filterSpecial(String str) {

        if (str == null) return null;
        String res = str.trim().replaceAll("<[^>]*>", "");
        res = res.replaceAll("<", "");
        res = res.replaceAll(">", "");
        res = res.replaceAll("&", "");

        return res;
    }

    /**
     * 验证list是否为空
     *
     * @param list
     * @return
     */
    public static Boolean checkList(List list) {

        if (list != null && list.size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 按照长度获取字符串，如果超出截取最大长度，后面加...
     *
     * @param str
     * @param len
     * @return
     */
    public static String maxString(String str, Integer len) {

        if (str == null) return str;
        if (str.length() <= len) return str;
        return str.substring(0, len) + "...";
    }

    /**
     * 判断传入参数,如果是Null或者空值，返回false，不为空返回true
     *
     * @param checkAll True:所有的为空才返回,False:只要有一个为空返回
     * @param objects
     * @return
     * @author gyq
     */
    public static Boolean checkNull(Boolean checkAll, Object... objects) {

        Boolean ret = true; // 不为空
        if (objects == null) return false;
        for (Object s : objects) {
            if (null == s || ("").equals(s.toString().trim()) || ("undefined").equals(s)) {
                if (checkAll) ret = false;
                else {
                    ret = false;
                    break;
                }
            } else {
                // 所有的都为空 才返回false
                if (checkAll) {
                    ret = true;
                }
            }
        }
        return ret;
    }


    /**
     * 返回等长字符，中间补零，如果前缀+字符串>长度，返回字符串
     * eg:lengthMaxString("A",4,"3") == "A003"
     *
     * @param prefix
     * @param len
     * @param str
     * @return
     */
    public static String lengthMaxNubmer(String prefix, int len, String str) {

        if (str == null || str.length() > len) return str;

        if (prefix.length() + str.length() > len) return str;

        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        for (int i = 0; i < len - prefix.length() - str.length(); i++) {
            sb.append("0");
        }
        sb.append(str);

        return sb.toString();
    }

    /**
     * 已分隔符来输出传入的字符串
     *
     * @param split
     * @param obj
     * @return
     */
    public static String toStringSpilt(String split, Object... obj) {

        if (obj == null) return "";

        StringBuffer sbBuffer = new StringBuffer();
        for (Object s : obj) {
            if (s != null && !s.equals("")) {
                if (sbBuffer.length() == 0) {
                    sbBuffer.append(s);
                } else {
                    sbBuffer.append(split).append(s);
                }
            }
        }

        return sbBuffer.toString();
    }

    /**
     * split.
     *
     * @param ch char.
     * @return string array.
     */
    public static String[] split(String str, char ch) {

        List<String> list = new ArrayList<>();
        char c;
        int ix = 0, len = str.length();
        for (int i = 0; i < len; i++) {
            c = str.charAt(i);
            if (c == ch) {
                list.add(str.substring(ix, i));
                ix = i + 1;
            }
        }
        if (ix > 0) list.add(str.substring(ix));
        return list.toArray(new String[list.size()]);
    }

    /**
     * join string like javascript.
     *
     * @param array String array.
     * @param split split
     * @return String.
     */
    public static String join(String[] array, String split) {

        if (array.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) sb.append(split);
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static String join(Collection<String> coll, String split) {

        if (coll.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String s : coll) {
            if (isFirst) isFirst = false;
            else sb.append(split);
            sb.append(s);
        }
        return sb.toString();
    }


    /**
     * 根据指定字符串和重复次数生成新字符串
     *
     * @param str         指定字符串
     * @param repeatCount 重复次数
     * @return 返回生成的新字符串
     */
    public static String newString(String str, int repeatCount) {

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < repeatCount; i++) {
            buf.append(str);
        }
        return buf.toString();
    }

    /**
     * 隐藏字符串指定位置的字符
     *
     * @param str    指定字符串
     * @param index  起始位置
     * @param length 字符长度
     * @return 返回隐藏字符后的字符串
     */
    public static String hideChars(String str, int index, int length) {

        return hideChars(str, index, length, true);
    }

    /**
     * 隐藏字符串指定位置的字符
     *
     * @param str       指定字符串
     * @param start     起始位置
     * @param end       结束位置
     * @param confusion 是否混淆隐藏的字符个数
     * @return 返回隐藏字符后的字符串
     */
    public static String hideChars(String str, int start, int end, boolean confusion) {

        StringBuffer buf = new StringBuffer();
        if (!isEmpty(str)) {
            int startIndex = Math.min(start, end);
            int endIndex = Math.max(start, end);
            // 如果起始位置超出索引范围则默认置为0
            if (startIndex < 0 || startIndex > str.length()) {
                startIndex = 0;
            }
            // 如果结束位置超出索引范围则默认置为字符串长度
            if (endIndex < 0 || endIndex > str.length()) {
                endIndex = str.length();
            }
            String temp = newString("*", confusion ? 4 : endIndex - startIndex);
            buf.append(str).replace(startIndex, endIndex, temp);

        }
        return buf.toString();
    }

    /**
     * 将指定字符串转换成小写
     *
     * @param str 指定字符串
     * @return 返回转换后的小写字符串
     */
    public static String toLowerCase(String str) {

        StringBuffer buffer = new StringBuffer(str);
        for (int i = 0; i < buffer.length(); i++) {
            char c = buffer.charAt(i);
            buffer.setCharAt(i, Character.toLowerCase(c));
        }
        return buffer.toString();
    }

    /**
     * 将指定字符串转换成大写
     *
     * @param str 指定字符串
     * @return 返回转换后的大写字符串
     */
    public static String toUpperCase(String str) {

        StringBuffer buffer = new StringBuffer(str);
        for (int i = 0; i < buffer.length(); i++) {
            char c = buffer.charAt(i);
            buffer.setCharAt(i, Character.toUpperCase(c));
        }
        return buffer.toString();
    }

    /**
     * 将指定字符串转换成驼峰命名方式
     *
     * @param str 指定字符串
     * @return 返回驼峰命名方式
     */
    public static String toCalmelCase(String str) {

        StringBuffer buffer = new StringBuffer(str);
        if (buffer.length() > 0) {
            // 将首字母转换成小写
            char c = buffer.charAt(0);
            buffer.setCharAt(0, Character.toLowerCase(c));
            Pattern p = Pattern.compile("_\\w");
            Matcher m = p.matcher(buffer.toString());
            while (m.find()) {
                String temp = m.group(); // 匹配的字符串
                int index = buffer.indexOf(temp); // 匹配的位置
                // 去除匹配字符串中的下划线，并将剩余字符转换成大写
                buffer.replace(index, index + temp.length(), temp.replace("_", "").toUpperCase());
            }
        }
        return buffer.toString();
    }


    /**
     * 将指定数组转换成字符串
     *
     * @param objs 指定数组
     * @return 返回转换后的字符串
     */
    public static String array2String(Object[] objs) {

        StringBuffer buffer = new StringBuffer();
        if (objs != null) {
            for (int i = 0; i < objs.length; i++) {
                buffer.append(objs[i]).append(",");
            }
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }


    /**
     * 判断属性是否为日期类型
     *
     * @param clazz     数据类型
     * @param fieldName 属性名
     * @return 如果为日期类型返回true，否则返回false
     */
    public static boolean isDateType(Class clazz, String fieldName) {

        boolean flag = false;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            Object typeObj = field.getType().newInstance();
            flag = typeObj instanceof Date;
        } catch (Exception e) {
            // 把异常吞掉直接返回false
        }
        return flag;
    }

    /**
     * 如果字符串不等于空,并且不等于字符串"null",就反回原字符串,否则返回""
     *
     * @param str 字符串
     * @return
     */
    public static String ifNullToBlank(String str) {

        if ((!isEmpty(str)) && (!(str.trim().equals("null")))) {
            return str.trim();
        }

        return "";
    }

    /**
     * 如果字符串不等于空,并且不等于字符串"null",就反回原字符串,否则返回""
     *
     * @param obj object
     * @return
     */
    public static String ifNullToBlank(Object obj) {

        String ret = "";
        String s = String.valueOf(obj);
        if (isEmpty(s) || ("null".equals(s)) || ("NULL".equals(s))) {
            ret = "";
        } else ret = s;

        return ret;
    }

    /**
     * 如果字符串不等于空,并且不等于字符串"null","NULL"
     *
     * @param str 字符串
     * @return
     */
    public static boolean isNulltoString(String str) {

        if ((!isEmpty(str.toLowerCase())) && (!(str.toLowerCase().trim().equals("null")))) {
            return false;
        }

        return true;
    }



    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {

        if (obj == null) return true;

        if (obj instanceof CharSequence) return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection) return ((Collection) obj).isEmpty();

        if (obj instanceof Map) return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }

        return false;
    }

    /***
     * 合并字符数组
     *
     * @param a
     * @return
     */
    public static char[] mergeArray(char[]... a) {
        // 合并完之后数组的总长度
        int index = 0;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum = sum + a[i].length;
        }
        char[] result = new char[sum];
        for (int i = 0; i < a.length; i++) {
            int lengthOne = a[i].length;
            if (lengthOne == 0) {
                continue;
            }
            // 拷贝数组
            System.arraycopy(a[i], 0, result, index, lengthOne);
            index = index + lengthOne;
        }
        return result;
    }



    /**
     * 获取后缀名
     * @param fileName
     * @return
     */
    public static String getExtName(String fileName) {
        String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
        return prefix;
    }

    /**
     * 邮箱验证
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
