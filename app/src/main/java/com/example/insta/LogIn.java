package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.widget.Toolbar;

//<com.google.android.material.appbar.AppBarLayout/>
//        and for TabLayout you should use:
//        <com.google.android.material.tabs.TabLayout/>
//        For the Toolbar you should use this androidx library:
//        <androidx.appcompat.widget.Toolbar/>

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {

    private EditText userName,password;
    private Button logIn;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName=findViewById(R.id.edtusername);
        password=findViewById(R.id.edtPassword);
        logIn=findViewById(R.id.btnLogin);
        signUp=findViewById(R.id.txtSignup);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
}