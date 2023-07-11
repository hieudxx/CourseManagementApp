package fpoly.hieudxph21411.assignment.model;

public class TinTuc {
    private String title, desc, img, link;

    public TinTuc() {
    }

    public TinTuc(String title, String desc, String img, String link) {
        this.title = title;
        this.desc = desc;
        this.img = img;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


}
