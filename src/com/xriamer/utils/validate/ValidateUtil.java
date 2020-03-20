package com.xriamer.utils.validate;

public class ValidateUtil {
    //使用static表示这个类中不打算使用属性
//    public static boolean validateEmpty(HttpServletRequest request, String paramName){
//        String value=request.getParameter(paramName);
//    }

    /**
     * 验证输入的数据是否为空
     *
     * @param data 输入数据
     * @return 如果不是null返回true, 否则返回false
     */
    public static boolean validateEmpty(String data) {
        if (data == null || "".equals(data)) {
            return false;
        }
        return true;
    }

    /**
     * 进行数据的正则操作验证
     *
     * @param data  要验证的数据
     * @param regex 要执行验证的正则表达式
     * @return 如果验证通过返回true，否则返回false
     */
    public static boolean validateRegex(String data, String regex) {
        if (validateEmpty(data)) {
            return data.matches(regex);
        }
        return false;
    }

    /**
     * 验证两个数据是否相同，不区分大小
     *
     * @param dataa 数据一
     * @param datab 数据二
     * @return 如果相同返回true, 否则返回false
     */
    public static boolean validateSame(String dataa, String datab) {
        if (validateEmpty(dataa) && validateEmpty(datab)) {
            return dataa.equalsIgnoreCase(datab);
        }
        return false;
    }
}
