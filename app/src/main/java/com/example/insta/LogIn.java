package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import androidx.appcompat.widget.Toolbar;

//<com.google.android.material.appbar.AppBarLayout/>
//        and for TabLayout you should use:
//        <com.google.android.material.tabs.TabLayout/>
//        For the Toolbar you should use this androidx library:
//        <androidx.appcompat.widget.Toolbar/>

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private EditText userName,password;
    private Button logIn;
    private TextView signUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName=findViewById(R.id.edtusername);
        password=findViewById(R.id.edtPassword);

        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    onClick(logIn);
                }
                return false;
            }
        });

        logIn=findViewById(R.id.btnLogin);
        signUp=findViewById(R.id.txtSignup);

        setTitle("Log In");

        signUp.setOnClickListener(this);
        logIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                ParseUser.logInInBackground(userName.getText().toString() , password.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user!=null && e==null){
                            Toast.makeText(LogIn.this,"Log in successful",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(LogIn.this,"Log in failed, please check your password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

            case R.id.txtSignup:
                Intent intent=new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
                break;
        }
    }

    public void layoutClicked(View view){
        try{
            InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}