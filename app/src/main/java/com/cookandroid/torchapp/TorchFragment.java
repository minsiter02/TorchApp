package com.cookandroid.torchapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TorchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TorchFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Global global = new Global();
    boolean vibrate = true;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public TorchFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TorchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TorchFragment newInstance(String param1, String param2) {
        TorchFragment fragment = new TorchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_torch, container, false);
        Button btnTorchToggle = (Button) root.findViewById(R.id.btnTorchToggle);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        btnTorchToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrate = sharedPreferences.getBoolean("torchVibrate",true);

                if(btnTorchToggle.getText().equals(getString(R.string.btnTorchOn))) {
                    btnTorchToggle.setText(R.string.btnTorchOff);
                    global.torchToggle("on", vibrate, root.getContext());
                }
                else if(btnTorchToggle.getText().equals(getString(R.string.btnTorchOff))) {
                    btnTorchToggle.setText(R.string.btnTorchOn);
                    global.torchToggle("off", vibrate,root.getContext());
                }
            }
        });
        return root;
    }




    @Override
    public void onClick(View v) {

    }

}