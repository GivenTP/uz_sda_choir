package com.gtptechnologies.uzsdachoir;

/**
 * Created by Given Pfunguro on 3/10/2017.
 */
public class RowItem {

    private String hymn_name;
    private int pics_id;
    private String author;

    public RowItem(String hymn_name, int pics_id, String author){

        this.hymn_name = hymn_name;
        this.pics_id = pics_id;
        this.author = author;
    }

    public String getHymn_name(){return hymn_name;}

    public void setHymn_name(String hymn_name){this.hymn_name = hymn_name;}

    public int getPics_id(){return pics_id;}

    public void setPics_id(int pics_id){this.pics_id = pics_id;}

    public String getAuthor(){return author;}

    public void setAuthor(String author){this.author = author;}
}
