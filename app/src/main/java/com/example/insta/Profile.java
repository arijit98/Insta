package com.example.insta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class Profile extends Fragment {

    private EditText mName,mBio,mSport;
    private Button mUpdate;


    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        mName=view.findViewById(R.id.edtName);
        mBio=view.findViewById(R.id.edtBio);
        mSport=view.findViewById(R.id.edtSport);
        mUpdate=view.findViewById(R.id.btnUpdate);

        final ParseUser parseUser = ParseUser.getCurrentUser();
        if (parseUser.get("Name") == null) {
            mName.setText("");

        } else {
            mName.setText(parseUser.get("Name").toString());

        }

        if (parseUser.get("Sport") == null) {
            mSport.setText("");

        } else {
            mSport.setText(parseUser.get("Sport") + "");

        }

        if (parseUser.get("Bio") == null) {
            mBio.setText("");

        } else {
            mBio.setText(parseUser.get("Bio") + "");

        }





        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("Name",mName.getText().toString());
                parseUser.put("Bio",mBio.getText().toString());
                parseUser.put("Sport",mSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Toast.makeText(getContext(),"INFO UPDATED",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
        return view;
    }
}