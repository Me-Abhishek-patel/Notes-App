package com.ciberciti.notes.ui.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.ciberciti.notes.R;

public class AuthActivity extends AppCompatActivity {

    final int LOGIN_FRAGMENT_ID = 1;
    final int REGISTER_FRAGMENT_ID = 2;
    LoginFragment loginFragment;
    RegisterFragment registerFragment;
    FragmentTransaction transaction;
    TextView tvSwitchFragment;
    int activeFragmentId = 1;
    FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        tvSwitchFragment = findViewById(R.id.tvRegister);
        tvSwitchFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (activeFragmentId) {
                    case LOGIN_FRAGMENT_ID:
                        manager.beginTransaction().replace(R.id.fragment_container_view, registerFragment).commit();
                        tvSwitchFragment.setText(R.string.log_in);
                        activeFragmentId = REGISTER_FRAGMENT_ID;
                        break;
                    case REGISTER_FRAGMENT_ID:
                        manager.beginTransaction().replace(R.id.fragment_container_view,loginFragment).commit();
                        activeFragmentId = LOGIN_FRAGMENT_ID;
                        tvSwitchFragment.setText(R.string.register_now);
                        break;


                }
            }
        });
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        activeFragmentId = 1;
        transaction.add(R.id.fragment_container_view, loginFragment, "REGISTER_FRAGMENT");

        transaction.commit();


    }
}