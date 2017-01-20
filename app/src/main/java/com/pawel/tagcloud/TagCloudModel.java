package com.pawel.tagcloud;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Pawel on 20.01.2017.
 */

public class TagCloudModel extends Observable {

    public static int TAGCOUNTER = 0;
    private Map<String, Tag> cloud;
    private String defaultLink;
    private int fontSize;
    private Tag maxCount;
    private Tag minCount;

    public TagCloudModel(){
        this("");
    }

    public TagCloudModel(String defaultLink){
        cloud = new HashMap<String, Tag>();
        this.defaultLink = defaultLink;
        this.fontSize = 15;
        this.maxCount = new Tag("", "", 0);
        this.minCount = new Tag("", "", 0);
    }

    public List<Tag> getTagList(){
        List<Tag> tagList = new LinkedList<>();

        if(this.cloud == null){
            return tagList;
        }
        for(Tag tag: cloud.values()){
            tagList.add(tag);
        }
        return tagList;
    }

    public void addTag(Tag tag){
        Tag tag2 = null;
        if(ifContain(tag.getName())){
            tag2 = cloud.get(tag.getName());
            tag2.setCount(tag2.getCount() + tag.getCount());
        }
        else{
            tag2 = new Tag(tag.getName(), tag.getLink(), tag.getCount());
            cloud.put(tag2.getName(), tag2);
        }
        this.checkMinMax(tag2);
        this.setTagsSize();
        this.notifyObservers(tag);
    }

    protected void checkMinMax(Tag tag){
        if(maxCount.getCount() < tag.getCount())
            maxCount = tag;
        else if(minCount.getName().equals(tag.getName())){
            if(minCount.getCount() > tag.getCount())
                minCount = tag;
            else if(maxCount.getCount() < tag.getCount())
                maxCount = tag;
        }
        else if(minCount.getCount() > tag.getCount() || minCount.getCount() == 0)
            minCount = tag;
        }

    protected void setTagsSize(){
        LinkedList<Tag> tagsList = (LinkedList<Tag>) this.getTagList();
        int min = minCount.getCount();
        int max = maxCount.getCount();
        for(Tag tag: tagsList){
            tag.setSize(max, min);
        }
    }

//	public void addTag(String name, String link, int count){
//		if(ifContain(name)){
//			Tag tag = cloud.get(name);
//			tag.setCount(tag.getCount() + count);
//		}
//		else{
//			Tag tag = new Tag(name, link, count);
//			cloud.put(name, tag);
//		}
//		this.fireChange();
//	}
//
//	public void addTag(String name, String link){
//		if(ifContain(name)){
//			Tag tag = cloud.get(name);
//			tag.setCount(tag.getCount() + 1);
//		}
//		else{
//			Tag tag = new Tag(name, link);
//			cloud.put(name, tag);
//		}
//		this.fireChange();
//	}
//
//	public void addTag(String name){
//		if(ifContain(name)){
//			Tag tag = cloud.get(name);
//			tag.setCount(tag.getCount() + 1);
//		}
//		else{
//			Tag tag = new Tag(name, this.defaultLink);
//			cloud.put(name, tag);
//		}
//		this.fireChange();
//	}

    public int getFontSize(){
        return this.fontSize;
    }

    public void setSize(int fontSize){
        this.fontSize = fontSize;
    }

    public boolean ifContain(String name){
        return cloud.containsKey(name);
    }

    public String getLink(String name){
        if(ifContain(name)){
                return cloud.get(name).getLink();
        }
            return null;
    }

    public void setLink(String name, String link){
        Tag tag = cloud.get(name);
        tag.setLink(link);
    }

    public int getCount(String name){
        if(ifContain(name)){
            return cloud.get(name).getCount();
        }
        return 0;
    }

    public void setCount(String name, int count){
        Tag tag = cloud.get(name);
        tag.setCount(count);
        //this.fireChange(tag);
    }

    public void setDefaultLink(String defaultLink){
        this.defaultLink = defaultLink;
    }

    public String getDefaultLink(){
        return this.defaultLink;
    }

    public Map<String, Tag> getCloud(){
        return this.cloud;
    }

    public int getTagSize(){
        return this.cloud.size();
    }

    public Tag getTag(String name){
        if(ifContain(name))
            return cloud.get(name);
        return null;
    }
}
