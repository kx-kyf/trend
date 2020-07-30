package cn.how2j.trend.pojo;

import java.io.Serializable;

/**
 * 描述
 *
 * @author 不休乔木
 * @version 1.0
 * @package cn.how2j.trend.pojo*
 * @since 1.0
 */
public class Index implements Serializable {
    String code;
    String name;

    public Index() {
    }

    public Index(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
