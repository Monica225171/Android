package com.example.androidstudy;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TopActivity extends AppCompatActivity {
    private SparseArray<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        initFragment();

        final RadioGroup rg_main = (RadioGroup) findViewById(R.id.rg_main);
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0;i<rg_main.getChildCount();i++){
                    RadioButton rb = (RadioButton)group.getChildAt(i);
                    if(rb.isChecked()){
                        setIndexSelectedTwo(i);
                        break;
                    }
                }
            }
        });
    }

    private void initFragment(){
        fragments = new SparseArray<>();
        fragments.put(R.id.rb_main,MainFragment.getMainFragment());
        fragments.put(R.id.rb_message,MessageFragment.getMessageFragment());
        fragments.put(R.id.rb_find,FindFragment.getFindFragment());
        fragments.put(R.id.rb_my,MyFragment.getMyFragment());
        changeFragment(fragments.get(R.id.rb_main));
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment , fragment);
        transaction.commit();
    }

    private void setIndexSelectedTwo(int index) {
        switch (index)
        {
            case 0:
                changeFragment(new MainFragment().getMainFragment());
                break;
            case 1:
                changeFragment(new MessageFragment().getMessageFragment());
                break;
            case 2:
                changeFragment(new FindFragment().getFindFragment());
                break;
            case 3:
                changeFragment(new MyFragment().getMyFragment());
                break;
            default:
                break;
        }
    }
}
