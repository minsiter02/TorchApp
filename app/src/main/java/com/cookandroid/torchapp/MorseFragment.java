package com.cookandroid.torchapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.vane.hanguleditor.HangulEditor;
import com.vane.hanguleditor.HangulSplitItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MorseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MorseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Handler handler;
    Thread thread;
    Global global = new Global();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MorseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MorseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MorseFragment newInstance(String param1, String param2) {
        MorseFragment fragment = new MorseFragment();
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
        View root = inflater.inflate(R.layout.fragment_morse, container, false);
        Button btnMoresToggle = (Button) root.findViewById(R.id.btnMoresToggle);
        EditText edtMores = (EditText) root.findViewById(R.id.edtMores);

        handler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg){
                String toggle = (String) msg.obj;
                global.torchToggle(toggle,root.getContext());
            }
        };MorseThread moresRunnable = new MorseThread(handler,btnMoresToggle);

        btnMoresToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtMoresText = edtMores.getText().toString();
                String inputEdtMoresText = global.convertText(edtMoresText);
                String morseCode = global.convertMorseCode(inputEdtMoresText);
                moresRunnable.setMorseCode(morseCode);

                if(btnMoresToggle.getText().equals(getString(R.string.btnMorseStop))) {
                    moresRunnable.stop();
                    if(thread != null)
                        thread.interrupt();
                    global.torchToggle("off",root.getContext());
                    btnMoresToggle.setText(R.string.btnMoresPlay);
                }
                else if(btnMoresToggle.getText().equals(getString(R.string.btnMoresPlay))) {
                    moresRunnable.start();
                    thread = new Thread(moresRunnable);
                    thread.start();
                    btnMoresToggle.setText(R.string.btnMorseStop);
                }
            }
        });
        return root;
    }


}