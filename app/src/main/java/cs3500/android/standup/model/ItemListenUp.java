package cs3500.android.standup.model;

import android.media.Image;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cs3500.android.standup.R;

/**
 * Created by feliciazhang on 2/11/2017.
 */


public class ItemListenUp  {
    private String headline = "";
    private String sourcePreview = "";
    private String sourceDate = "";
    private String source = "";
//    private Image featurePhoto = ;

    public void setName(String headline) {
        this.headline = headline;
    }

    public String getHeadline() {
        return headline;
    }

    public void setSourcePreview(String sourcePreview) {
        this.sourcePreview = sourcePreview;
    }

    public String getSourcePreview() {
        return sourcePreview;
    }

    public void setSourceDate(String sourceDate) {
        this.sourceDate = sourceDate;
    }

    public String getSourceDate() {
        return sourceDate;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

  /*  public void setFeaturePhoto(Image featurePhoto) {
        this.featurePhoto = featurePhoto;
    }

    public Image getFeaturePhoto () {
        return featurePhoto;
    }*/
}