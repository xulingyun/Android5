package wc.xulingyun.com.android56.dao;

/**
 * Created by 徐玲郓 on 2017/4/12.
 * 描述：
 */

public class RadarData {

    private String title;
    private double percentage;

    public RadarData(String title, double percentage) {
        this.title = title;
        this.percentage = percentage;
    }

    public String getTitle() {
        return title;
    }

    public double getPercentage() {
        return percentage;
    }
}
