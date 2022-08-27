package com.example.qlsv.Features.ShowStudentList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlsv.Features.CreateStudentInfo.Student;
import com.example.qlsv.Features.UpdateStudentInfo.StudentUpdateDialogFragment;
import com.example.qlsv.R;
import com.example.qlsv.database.DatabaseQueryClass;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

public class StudentListRecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<Student> studentList;
    private DatabaseQueryClass databaseQueryClass;

    public StudentListRecyclerViewAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
        databaseQueryClass = new DatabaseQueryClass(context);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      //  Phương thức này tạo và khởi động ViewHolder cùng với View đã liên kết, nhưng không điền vào nội dung của thành phần hiển thị – ViewHolder chưa liên kết với dữ liệu cụ thể.
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
// RecyclerView gọi phương thức này để liên kết ViewHolder với dữ liệu. Phương thức này tìm nạp dữ liệu thích hợp và sử dụng dữ liệu đó để điền vào bố cục của ngăn chứa thành phần hiển th
        final int itemPosition = position;
        final Student student = studentList.get(position);

        holder.nameTextView.setText(student.getName());
        holder.registrationNumTextView.setText(String.valueOf(student.getRegistrationNumber()));
        holder.emailTextView.setText(student.getEmail());
        holder.phoneTextView.setText(student.getPhoneNumber());

        holder.crossButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder aleartDialogBuilder = new AlertDialog.Builder(context);
                aleartDialogBuilder.setMessage("Are you sure, you wanted to delete this student ? ");
                aleartDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // delete student
                    }
                });
                aleartDialogBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = aleartDialogBuilder.create();
                alertDialog.show();
            }
        });

//        holder.editButtonImageView.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                StudentUpdateDialogFragment studentUpdateDialogFragment = StudentUpdateDialogFragment.newInstance(student.getRegistrationNumber(), itemPosition, new StudentUpdateListener() {
//
//                });
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
