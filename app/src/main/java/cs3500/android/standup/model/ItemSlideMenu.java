package cs3500.android.standup.model;

/**
 * Created by feliciazhang on 2/11/2017.
 */

public class ItemSlideMenu {

    private int image;
    private String title;

    public ItemSlideMenu(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
