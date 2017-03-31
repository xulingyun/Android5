package wc.xulingyun.com.android56;

/**
 * Created by 徐玲郓 on 2017/2/20.
 * 描述：
 */

public class ItemDao {

    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;
    public static final int TYPE_THREE = 2;

    int type;
    String icon;
    String title;
    String content;
    String date;
    int count;
    int SecondType;

    public String getContent() {
        return content;
    }

    public void setContent(String $Content) {
        content = $Content;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int $Count) {
        count = $Count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String $Date) {
        date = $Date;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String $Icon) {
        icon = $Icon;
    }

    public int getSecondType() {
        return SecondType;
    }

    public void setSecondType(int $SecondType) {
        SecondType = $SecondType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String $Title) {
        title = $Title;
    }

    public int getType() {
        return type;
    }

    public void setType(int $Type) {
        type = $Type;
    }

    @Override
    public String toString() {
        return "ItemDao{" +
                "content='" + content + '\'' +
                ", type=" + type +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", count=" + count +
                ", SecondType=" + SecondType +
                '}';
    }
}
