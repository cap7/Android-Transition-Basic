package com.cap.myapplication;

import android.animation.Animator;
import android.graphics.drawable.Drawable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CAP on 22/09/2017.
 */

public class CustomTransition extends Transition {

    private static final String CHANGE_IMAGE = "myapplication:ChangeImage:src";

    private void captureValues(TransitionValues transitionValues){
        View view = transitionValues.view;
        transitionValues.values.put(CHANGE_IMAGE,view.getResources());
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        return super.createAnimator(sceneRoot, startValues, endValues);
    }
}
