package com.example.majdh.homework4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NotesAdapter extends BaseAdapter
{
    LayoutInflater myInflater;
    ArrayList<Note> notes;
    Context context;

    public NotesAdapter(Context c, ArrayList<Note> n)
    {
        this.context = c;
        myInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.notes = n;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = myInflater.inflate(R.layout.notes_listview_row, null);
        LinearLayout note = (LinearLayout)v.findViewById(R.id.NoteRow_LL);
        TextView title = (TextView)v.findViewById(R.id.noteTitle);
        TextView content = (TextView)v.findViewById(R.id.noteContent);
        TextView status = (TextView)v.findViewById(R.id.noteStatus);
        TextView onDate = (TextView)v.findViewById(R.id.noteOnDate);
        title.setText(notes.get(position).getTitle());
        content.setText(notes.get(position).getContent());
        if(notes.get(position).getStatus().equals("Sent"))
        {
            status.setTextColor(context.getResources().getColor(R.color.green));
            status.setText(notes.get(position).getStatus());
        }
        else
        {
            note.setBackgroundResource(R.drawable.rec_note_bg);
            status.setTextColor(context.getResources().getColor(R.color.colorAccent));
            status.setText(notes.get(position).getStatus());
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY  HH:mm");
        String date = dateFormat.format(notes.get(position).getOn_date());
        onDate.setText(date);
        return v;
    }
}
