package com.example.activity.qingzidemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.SparseArray;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ExerciseFragment.OnTitleListener {
    private SparseArray<Fragment> fragments;
    private TextView tvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
        //RadioGroup的按钮切换事件监听
        RadioGroup rgConvert = findViewById(R.id.radiogroup);
        rgConvert.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group ,int checkId) {
                replaceFragment(fragments.get(checkId));
            }
        });
        tvTitle = findViewById(R.id.tv_title);
    }
    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

   //初始化Fragment
    private void initFragments() {
        fragments = new SparseArray<>();
        fragments.put(R.id.tab1,CourseFragment.newInstance(1));
        fragments.put(R.id.tab2,ExerciseFragment.newInstance("习题"));
        fragments.put(R.id.tab3,ExerciseFragment.newInstance("资讯"));
        fragments.put(R.id.tab4,ExerciseFragment.newInstance("我的"));

        replaceFragment(fragments.get(R.id.tab2));
    }

    //加载fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_content,fragment);
        transaction.commit();
    }
}