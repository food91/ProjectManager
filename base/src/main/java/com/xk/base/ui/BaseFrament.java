package com.xk.base.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xk.base.log.X;

public class BaseFrament extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
        X.L("--------------"+getClass().getName());
    }
}
