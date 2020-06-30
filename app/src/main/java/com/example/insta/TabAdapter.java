package com.example.insta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


    public class TabAdapter extends FragmentPagerAdapter {
        public TabAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Profile profile = new Profile();
                    return profile;
                case 1:
                    User user = new User();
                    return user;
                case 2:
                    return new Post();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "profile";
                case 1:
                    return "user";
                case 2:
                    return "Post";
            }
            return null;
        }
    }
