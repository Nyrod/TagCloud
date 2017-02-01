package com.pawel.tagcloud;

/**
 * Created by Pawel on 20.01.2017.
 */

public class Tag implements Comparable{

    private String name;
    private String link;
    private int count;
    private int size;
    private int tagID;

    public Tag(){
        this(null, null, 1);
    }

    public Tag(String name){
        this(name, null, 1);
    }

    public Tag(String name, int count){
        this(name, null, count);
    }

    public Tag(String name, String link){
        this(name, link, 1);
    }

    public Tag(String name, String link, int count){
        this.name = name;
        this.link = link;
        this.count = count;
        tagID = TagCloudModel.TAGCOUNTER++;
    }

    public Tag(Tag tag){
        this.name = tag.getName();
        this.link = tag.getLink();
        this.count = tag.getCount();
        tagID = this.getTagID();
    }

    public int getSize(){
        return this.size;
    }

    public void setSize(int max, int min){
        double lb1 = (max-min+2)/20.0;
        double count = (double)this.count;
        int size = 1;
        while(count >= lb1*size){
            size++;
        }
        this.size = size - 5;
    }

    public int getCount() {
        return this.count;
    }

    public String getLink() {
        return this.link;
    }

    public String getName() {
        return this.name;
    }

    public void setLink(String link){
        this.link = link;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCount(int count){
        this.count = count;
    }

    public int getTagID() {
        return tagID;
    }

    @Override
    public int compareTo(Object o) {
        Tag o1 = (Tag) o;
        if(this.getCount() < o1.getCount())
            return 1;
        if(this.getCount() == o1.getCount())
            return 0;
        return -1;
    }

    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;
        Tag tag = (Tag)obj;
        if(this.name == null){
            if(tag.name != null)
                return false;
        }
        else if(!this.name.equals(tag.name))
            return false;
        return true;
    }

    public String toString(){
        return "Tag: " + this.name +", link: " + this.link + ", liczba wystapien: " + this.count + ", fontSize: " + this.size;
    }
}

