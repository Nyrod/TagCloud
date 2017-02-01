package com.pawel.tagcloud;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Pawel on 20.01.2017.
 */

public class TagCloud extends LinearLayout implements OnChangeListener{

    private TagCloudModel model;

    public TagCloud(Context context){
        super(context);
        this.model = new TagCloudModel();
        model.addListener(this);
        init();
    }

    public TagCloud(Context context, String tag){
        super(context);
        this.model = new TagCloudModel();
        model.addListener(this);
        init();
    }

    public TagCloud(Context context, List<String> tagList){
        super(context);
        this.model = new TagCloudModel();
        model.addListener(this);
        init();
    }

    private void init(){
        this.setOrientation(LinearLayout.VERTICAL);
        this.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        this.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        this.setVerticalGravity(Gravity.CENTER_VERTICAL);
    }

    @Override
    public void onChange(Object model) {
        createTagCloud();
    }

    protected void createTagCloud(){
        final List<Tag> tagList = model.getTagList();
        int numberOfRow;
        int tagToDisplay = 0;
        int tagInRow;
        this.removeAllViews();

        int a = model.getNumberOfTags();
        if(a < model.numberOfTagsToDisplay()){
            int i = model.getTagCloudSize() - 1;
            int numberOfTagsToDisplay = model.numberOfTagsToDisplay();
            while(a < numberOfTagsToDisplay){
                numberOfTagsToDisplay = 0;
                for(int y = 1; y < i; y++){
                    numberOfTagsToDisplay += 2*y;
                }
                numberOfTagsToDisplay += i;
                i--;
            }
            i++;
            numberOfRow = 2*i - 1;
            tagInRow = i;
        }
        else {
            numberOfRow = 2 * model.getTagCloudSize() - 1;
            tagToDisplay = 0;
            tagInRow = model.getTagCloudSize();
        }
            for(int i = 0; i < numberOfRow; i++){
                TableRow tableRow = new TableRow(this.getContext());
                tableRow.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tableRow.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                tableRow.setVerticalGravity(Gravity.CENTER_VERTICAL);
                for(int y = tagToDisplay; y < tagToDisplay + tagInRow && y < tagList.size(); y++){
                    Tag tag = tagList.get(y);
                    final TextView textView = new TextView(this.getContext());
                    textView.setText(tag.getName());
                    textView.setTextColor(Color.BLACK);
                    textView.setId(tag.getTagID());
                    textView.setTextSize(model.getFontSize() + tag.getSize());
                    textView.setPadding(0, 0, 10, 0);
                    textView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //onTagClick(model.getTagByID(textView.getId()).toString());
                            Toast.makeText(v.getContext(), model.getTagByID(textView.getId()).toString(), Toast.LENGTH_LONG).show();
                        }
                    });

                    if(y%2 == 0)
                        tableRow.addView(textView, 0);
                    else
                        tableRow.addView(textView);
                }
                if(i%2 == 0) {
                    this.addView(tableRow);
                    tagToDisplay+=tagInRow;
                    tagInRow--;
                }
                else {
                    this.addView(tableRow, 0);
                    tagToDisplay+=tagInRow;
                }
        }
    }

//    public abstract void onTagClick(String tagClicked);

    public int getNumberOfTags(){
        return this.model.getNumberOfTags();
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

    public int getTagCloudSize(){
        return model.getTagCloudSize();
    }

    public boolean setTagCloudSize(int tagCloudSize){
        return model.setTagCloudSize(tagCloudSize);
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
