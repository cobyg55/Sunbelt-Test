package com.sunbeltfactory.sunbelttest.ui.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sunbeltfactory.sunbelttest.R;
import com.sunbeltfactory.sunbelttest.activities.SignInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountFragment extends Fragment {

    private FirebaseUser user;

    @BindView(R.id.profile_image)
    ImageView ivPhoto;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_email)
    TextView tvEmail;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, root);

        user = FirebaseAuth.getInstance().getCurrentUser();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            if (name != null && !name.isEmpty()) {
                tvName.setText(name);
            }

            if (email != null && !email.isEmpty()) {
                tvEmail.setText(email);
            }

            if (photoUrl != null) {
                if (isAdded()) {
                    Glide.with(getActivity()).load(photoUrl).into(ivPhoto);
                }
            }
        }
    }

    @OnClick(R.id.btn_logout)
    public void LogOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), SignInActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}