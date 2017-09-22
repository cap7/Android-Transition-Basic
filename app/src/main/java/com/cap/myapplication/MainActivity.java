package com.cap.myapplication;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
Button.OnClickListener{

    Scene sceneA;
    Scene sceneB;
    Transition transition;
    FrameLayout sceneRoot;
    Button buttonGo;
    Button buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.select_option);
        buttonGo = (Button) findViewById(R.id.button_go);
        buttonBack = (Button) findViewById(R.id.button_back);

        /** SCENA **/
        sceneRoot = (FrameLayout) findViewById(R.id.scene_root);
        sceneA = Scene.getSceneForLayout(sceneRoot,R.layout.a_scene,this);
        sceneB = Scene.getSceneForLayout(sceneRoot,R.layout.b_scene,this);

        radioGroup.setOnCheckedChangeListener(this);
        buttonGo.setOnClickListener(this);
        buttonBack.setOnClickListener(this);

    }

    /** TRANSITION **/
    @Override
    public void onCheckedChanged(RadioGroup group,int checkedId) {
        switch (checkedId){
            case R.id.r_scene_transition:{
                // Transitions : fade_transition,change_bounds_transition,auto_transition
                transition = TransitionInflater.from(this).inflateTransition(R.transition.change_bounds_transition);
                break;
            }
            case R.id.r_multiple_transition:{
                transition = TransitionInflater.from(this).inflateTransition(R.transition.multiple_transition);
                break;
            }
            case R.id.r_custom_transition:{
                transition = new CustomTransition();
                break;
            }
            case R.id.r_transition_without_scenes:{
                TransitionManager.beginDelayedTransition(sceneRoot);
                View imageView = sceneRoot.findViewById(R.id.image_view_1);
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                int newSize = getResources().getDimensionPixelSize(R.dimen.image_new);
                params.width = newSize;
                params.height = newSize;
                imageView.setLayoutParams(params);
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_go:{
                TransitionManager.go(sceneB,transition);
                break;
            }
            case R.id.button_back:{
                TransitionManager.go(sceneA,transition);
                break;
            }
        }
    }
}
