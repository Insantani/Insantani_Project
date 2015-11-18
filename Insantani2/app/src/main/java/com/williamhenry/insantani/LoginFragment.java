package com.williamhenry.insantani;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class LoginFragment extends Fragment {

    private Context context;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getActionBar().setElevation(11);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        context = getContext();
        Button registration = (Button) rootView.findViewById(R.id.register_login_button);
        final TextView email = (TextView) rootView.findViewById(R.id.signIn_email);
        final TextView password = (TextView) rootView.findViewById(R.id.signIn_password);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,Registration.class);
                Bundle bundle = new Bundle();
                bundle.putString("Email", email.getText().toString());
                bundle.putString("Password", password.getText().toString());
                intent.putExtra("User", bundle);
                context.startActivity(intent);
            }
        });
        Button login = (Button) rootView.findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, MainActivity.class);

                context.startActivity(intent);
            }
        });

        return rootView;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
