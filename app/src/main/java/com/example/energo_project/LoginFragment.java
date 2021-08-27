package com.example.energo_project;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.energo_project.Navigation.NavigationHost;
import com.example.energo_project.model.User;
import com.example.energo_project.retrofit_interface.UserService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.login_fragment, container, false);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit);
        final TextInputLayout loginTextInput = view.findViewById(R.id.username_input);
        final TextInputEditText loginEditText = view.findViewById(R.id.username_edit);
        MaterialButton nextButton = view.findViewById(R.id.next_button);

        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String BASE_URL = "http://10.0.2.2:8080/api/user/";
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UserService userService = retrofit.create(UserService.class);
                Call<User> call = userService.getUser(loginEditText.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() != null && response.body().getParol().equals(passwordEditText.getText().toString())) {
                            passwordTextInput.setError(null); // Clear the error
                            ((NavigationHost) getActivity()).navigateTo(new FlatsFragment(), false); // Navigate to the next Fragment
                        } else {
                            passwordTextInput.setError(getString(R.string.error_password));
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        passwordTextInput.setError(getString(R.string.error_username));
                    }
                });
            }
        });
        // Clear the error once more than 8 characters are typed.
//        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (isPasswordValid(passwordEditText.getText(),loginEditText.getText())) {
//                    passwordTextInput.setError(null); //Clear the error
//                }
//                return false;
//            }
//        });
        return view;
    }




    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here
//    private boolean isPasswordValid(@Nullable Editable pass,Editable login) {
//
//        System.out.println("blin");
//        System.out.println(state[0]);
//        //return text != null && text.length() >= -1;
//        return state[0];
//    }
}

