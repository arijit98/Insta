package com.example.insta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class User extends Fragment {


    private ListView mListView;
    private ArrayList<String> mArrayList;
    private ArrayAdapter mArrayAdapter;

    public User() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        view.findViewById(R.id.listView);
        mArrayList=new ArrayList();
        mArrayAdapter= new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,mArrayList);

        ParseUser parseUser = ParseUser.getCurrentUser();
        ParseQuery<ParseUser> parseQuery=ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null){
                    if (objects.size()>0){
                        for (ParseUser user:objects){
                            mArrayList.add(user.getUsername());
                        }

                        mListView.setAdapter(mArrayAdapter);
                    }
                }

            }
        });

        return view;
    }
}