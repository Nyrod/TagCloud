package com.pawel.tagcloud;

import android.content.Context;
import android.widget.TableLayout;

import java.util.List;

/**
 * Created by Pawel on 20.01.2017.
 */

public class TagCloud extends TableLayout implements OnChangeListener{

    private TagCloudModel model;

    public TagCloud(Context context){
        super(context);
        this.model = new TagCloudModel();
    }

    public TagCloud(Context context, String tag){
        super(context);
        this.model = new TagCloudModel();
    }

    public TagCloud(Context context, List<String> tagList){
        super(context);
        this.model = new TagCloudModel();
    }

    @Override
    public void onChange(Object model) {

    }

    public int getTagSize(){
        return this.model.getTagSize();
    }

    public List<Tag> getTagList(){
        return this.model.getTagList();
    }

    public void addTag(String name){
        Tag tag = new Tag(name);
        model.addTag(tag);
    }

    public void addTag(String name, int count){
        Tag tag = new Tag(name, count);
        model.addTag(tag);
    }

    public int getFontSize(){
        return model.getFontSize();
    }

    public String getTag(String name){
        return model.getTag(name).toString();
    }

    public TagCloudModel getModel(){
        return this.model;
    }
}
