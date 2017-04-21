package wc.xulingyun.com.android56.dao;

import wc.xulingyun.com.android56.util.TimeUtil;

/**
 * Created by 徐玲郓 on 2017/4/7.
 * 描述：
 */

public class UserInfo {

    String name;
    String icon;
    String lastTime;
    String message;
    public UserInfo() {

    }

    public UserInfo(String $Name, String $Icon, String $LastTime, String $Message) {
        name = $Name;
        icon = $Icon;
        lastTime = $LastTime;
        message = $Message;
    }

    public String getName() {
        return name;
    }

    public void setName(String $Name) {
        name = $Name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String $Icon) {
        icon = $Icon;
    }

    public String getLastTime() {
        return TimeUtil.formatSomeAgo(lastTime);
    }

    public void setLastTime(String $LastTime) {
        lastTime = $LastTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String $Message) {
        message = $Message;
    }
}
