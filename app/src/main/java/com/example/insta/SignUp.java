package com.example.insta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
    private EditText userName, passWord,userEmail;
    private Button signUp;
    private TextView logIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);

        userName = findViewById(R.id.edtUserNameS);
        passWord = findViewById(R.id.edtPasswordS);
        userEmail = findViewById(R.id.edtEmail);

        setTitle("Sign Up");

        signUp = findViewById(R.id.btnSignUp);
        logIn = findViewById(R.id.txtLogIn);

        passWord.setOnKeyListener(this);

        signUp.setOnClickListener(SignUp.this);
        logIn.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMedia();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSignUp:
                if (passWord.getText().toString().equals("")
                        || userEmail.getText().toString().equals("")
                            || userEmail.getText().toString().equals("")) {
                    Toast.makeText(SignUp.this, "Columns cannot be left empty", Toast.LENGTH_SHORT).show();
                } else {
                    ParseUser user = new ParseUser();
                    user.setEmail(userEmail.getText().toString());
                    user.setUsername(userName.getText().toString());
                    user.setPassword(passWord.getText().toString());

                    final ProgressDialog progressDialog= new ProgressDialog(SignUp.this);
                    progressDialog.setMessage("Signing in as "+ userName.getText());
                    progressDialog.show();

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(SignUp.this, "Successful", Toast.LENGTH_SHORT).show();
                                transitionToSocialMedia();


                            } else {
                                Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

                }
                break;

            case R.id.txtLogIn:
                Intent intent=new Intent(SignUp.this,LogIn.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
            onClick(signUp);
        }
        return false;
    }

    public void layoutTapped(View view){
        try{
            InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void transitionToSocialMedia(){
        Intent intent= new Intent(SignUp.this,SocialMedia.class);
        startActivity(intent);
    }
}
