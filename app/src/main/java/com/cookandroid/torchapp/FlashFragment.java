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
import android.widget.SeekBar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FlashFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlashFragment extends Fragment {

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
    private boolean flashRun = false;
    public FlashFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FlashFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FlashFragment newInstance(String param1, String param2) {
        FlashFragment fragment = new FlashFragment();
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
        View root =  inflater.inflate(R.layout.fragment_flash, container, false);
        Button btnFlashToggle = (Button) root.findViewById(R.id.btnFlashToggle);
        SeekBar seekBar = (SeekBar) root.findViewById(R.id.seekBar);


        handler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg){
                String toggle = (String) msg.obj;
                global.torchToggle(toggle,root.getContext());
            }
        };FlashTread flashRunnable = new FlashTread(handler);
        btnFlashToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnFlashToggle.getText().equals(getString(R.string.btnFlashOff))) {
                    flashRunnable.stop();
                    if(thread != null)
                        thread.interrupt();
                    global.torchToggle("off",root.getContext());
                    btnFlashToggle.setText(R.string.btnFlashOn);
                }
                else if(btnFlashToggle.getText().equals(getString(R.string.btnFlashOn))) {
                    flashRunnable.start();
                    thread = new Thread(flashRunnable);
                    thread.start();
                    btnFlashToggle.setText(R.string.btnFlashOff);
                }
            }
        });
        final double flashWeight = 0.7f;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double seekBarPG = seekBar.getProgress();
                int waitTime =(int) ( Math.pow(flashWeight, seekBarPG/10) * 1000);
                Log.i("speed",Double.toString(waitTime));
                flashRunnable.setSpeed(waitTime); // 시간이므로

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        return root;

    }

}