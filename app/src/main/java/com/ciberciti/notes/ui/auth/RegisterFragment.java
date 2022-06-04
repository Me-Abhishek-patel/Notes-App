package com.ciberciti.notes.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.ciberciti.notes.R;
import com.ciberciti.notes.data.entities.User;
import com.ciberciti.notes.databinding.FragmentRegisterBinding;
import com.ciberciti.notes.ui.MainActivity;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;
    RegisterFragmentClickHandlers registerFragmentClickHandlers;
    private AuthViewModel authViewModel;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        binding = DataBindingUtil.bind(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        authViewModel = new ViewModelProvider(getActivity()).get(AuthViewModel.class);
        registerFragmentClickHandlers = new RegisterFragmentClickHandlers(getActivity());
        binding.setAuthViewModel(authViewModel);
        binding.setRegisterClickHandler(registerFragmentClickHandlers);


    }

    @Override
    public void onResume() {
        super.onResume();
        authViewModel.userEmail = "";
    }

    private void registerUser() {
        authViewModel.register().observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user!=null) {
                    authViewModel.addSession(user.userId);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

    }

    public class RegisterFragmentClickHandlers {
        Context context;

        public RegisterFragmentClickHandlers(Context context) {
            this.context = context;
        }

        public void onRegisterClicked(View view) {
            registerUser();

        }
    }

}