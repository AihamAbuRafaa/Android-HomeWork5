package com.example.majdh.homework4;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class EditNoteActivity extends AppCompatActivity
{
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    EditText title;
    EditText content;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        title = (EditText)findViewById(R.id.titleET);
        content = (EditText)findViewById(R.id.contentET);

        note = (Note)getIntent().getSerializableExtra("selected_note");

        title.setText(note.getTitle());
        content.setText(note.getContent());

        ImageView imageView = findViewById(R.id.noteIV);
        String url = note.getImageURL();
        Glide.with(this)
                .load(url)
                .into(imageView);
    }

    public void updateNoteBtn_handle(View view)
    {
        TextView message = (TextView)findViewById(R.id.editNote_msg_TV);
        note.setTitle(title.getText().toString());
        note.setContent(content.getText().toString());
        Map<String,String> taskMap = new HashMap<String, String>();
        taskMap.put("title", note.getTitle());
        taskMap.put("content", note.getContent());
        taskMap.put("status", note.getStatus());
        taskMap.put("date", note.getOn_date().getTime()+"");
        taskMap.put("imageURL", note.getImageURL());
        taskMap.put("email", note.getPoster_email());
        database.child("Notes").child(note.getNoteID()).setValue(taskMap);
        message.setText("Note changes has been saved.");
    }

    public void deleteNoteBtn_handle(View view)
    {
        database.child("Notes").child(note.getNoteID()).removeValue();
        NotesActivity.setPopMsg(true);
        finish();
    }
}
