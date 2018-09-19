package org.xuxi.codex.common.constant;


/**
 * 公共常量
 * Created by zsanything on 16/5/12.
 */
public class CommonConstant {


    /**
     * 是否有效
     */
    public enum IsActive implements IEnum<Integer, String> {
        True(1, "有效"), False(2, "无效");

        Integer value;
        String desc;

        IsActive(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public Integer getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        public static String getLocaleDesc(Integer value) {
            if (value != null) {
                for (IsActive str : IsActive.values()) {

                    if (str.value.intValue() == value.intValue()) {
                        return str.getDesc();
                    }
                }
            }

            return "";
        }
    }


    /**
     * 模板类型
     */
    public enum TemplateType implements IEnum<Integer, String> {
        CRUD(1, "CRUD");

        Integer value;
        String desc;

        TemplateType(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public Integer getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

    }


    /**
     * 用户类型
     */
    public enum UserType implements IEnum<Integer, String> {
        Admin(0, "管理员"),
        Ordinary(2, "普通用户");

        Integer value;
        String desc;

        UserType(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public Integer getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

    }


}