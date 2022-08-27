package com.example.qlsv.Features.ShowStudentList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.example.qlsv.Features.CreateStudentInfo.CreateStudentDialog;
import com.example.qlsv.Features.CreateStudentInfo.Student;
import com.example.qlsv.R;
import com.example.qlsv.database.DatabaseQueryClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    private DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);
    private List<Student> studentList = new ArrayList<>();
    private TextView studentListEmptyTextView;
    private RecyclerView recyclerView;
    private StudentListRecyclerViewAdapter studentListRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        recyclerView = (RecyclerView) findViewById(R.id.studentRecyclerView);
        studentListEmptyTextView = findViewById(R.id.emptyStudentListTextView);

        studentList.addAll(databaseQueryClass.getAllStudent());
        studentListRecyclerViewAdapter = new StudentListRecyclerViewAdapter(this, studentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(studentListRecyclerViewAdapter);


        viewVisibility();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   openStudentDialog();
            }
        });
    }

    private void openStudentDialog(Menu menu) {
        MenuInflater inflater = getMenuInflater();
    }

    private void viewVisibility() {
        if(studentList.isEmpty()){
            studentListEmptyTextView.setVisibility(View.VISIBLE);
        } else {
            studentListEmptyTextView.setVisibility(View.GONE);
        }
    }
}