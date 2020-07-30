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
public class IndexData implements Serializable {
    String date;
    float closePoint;

    public IndexData() {
    }

    public IndexData(String date, float closePoint) {
        this.date = date;
        this.closePoint = closePoint;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public float getClosePoint() {
        return closePoint;
    }
    public void setClosePoint(float closePoint) {
        this.closePoint = closePoint;
    }
}
