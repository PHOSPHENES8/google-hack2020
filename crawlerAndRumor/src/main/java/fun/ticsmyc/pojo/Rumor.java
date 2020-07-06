package fun.ticsmyc.pojo;


import java.util.List;
import java.util.Objects;
public class Rumor {
/**
 * "id":181,
 * "title":"可在家使用新冠病毒试剂盒自测？",
 * "mainSummary":"北京市药监局提示：普通市民家庭不可自行使用",
 * "summary":"",
 * "body":"近日，有人在朋友圈兜售某公司生产的新冠病毒抗体检测试剂盒，单价 150...使用，",
 * "sourceUrl":"",
 * "score":1000,
 * "rumorType":0*/
    private int id;
    private String title;
    private String mainSummary;
    private String summary;
    private String body;
    private String sourceUrl;
    private int score;;
    private boolean rumorType;

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getBody() {
        return body;
    }

    public String getMainSummary() {
        return mainSummary;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public boolean isRumorType() {
        return rumorType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setMainSummary(String mainSummary) {
        this.mainSummary = mainSummary;
    }

    public void setRumorType(boolean rumorType) {
        this.rumorType = rumorType;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rumor rumor = (Rumor) o;
        return score == rumor.score &&
                rumorType == rumor.rumorType &&
                Objects.equals(title, rumor.title) &&
                Objects.equals(mainSummary, rumor.mainSummary) &&
                Objects.equals(summary, rumor.summary) &&
                Objects.equals(body, rumor.body) &&
                Objects.equals(sourceUrl, rumor.sourceUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, mainSummary, summary, body, sourceUrl, score, rumorType);
    }

    @Override
    public String toString() {
        return "Rumor{" +
                "title='" + title + '\'' +
                ", mainSummary='" + mainSummary + '\'' +
                ", summary='" + summary + '\'' +
                ", body='" + body + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", score=" + score +
                ", rumorType=" + rumorType +
                '}';
    }
}
