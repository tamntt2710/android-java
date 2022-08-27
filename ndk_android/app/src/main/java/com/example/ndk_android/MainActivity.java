package com.example.ndk_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ndk_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'ndk_android' library on application startup.
    static {
        System.loadLibrary("ndk_android");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI()); // nhận các giá trị từ func .cpp
    }

    /**
     * A native method that is implemented by the 'ndk_android' native library,
     * which is packaged with this application.
     */
    // Khai báo 1 func có cùng tên với .cpp-lib
    public native String stringFromJNI();
}