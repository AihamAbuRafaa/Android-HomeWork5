package com.example.majdh.homework4;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Note implements Serializable
{
    private String noteID;
    private String title;
    private String content;
    private String status;
    private Date on_date;
    private String imageURL;
    private String poster_email;

    private ArrayList<Note> notes_list = new ArrayList<Note>();

    public Note()
    { }

    public Note(String nID, String t, String dc, String s, Date d, String iurl, String pe)
    {
        this.noteID = nID;
        this.title = t;
        this.content = dc;
        this.status = s;
        this.on_date = d;
        this.imageURL = iurl;
        this.poster_email = pe;
    }

    public String getContent()
    {
        return content;
    }

    public String getStatus()
    {
        return status;
    }

    public String getTitle()
    {
        return title;
    }

    public Date getOn_date()
    {
        return on_date;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setOn_date(Date on_date)
    {
        this.on_date = on_date;
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPoster_email() {
        return poster_email;
    }

    public void setPoster_email(String poster_email) {
        this.poster_email = poster_email;
    }
}
