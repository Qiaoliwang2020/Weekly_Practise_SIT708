package com.example.fragment_dynamic;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.Date;

public class FragmentRed extends Fragment {
    MainActivity main;
    TextView txtRed;
    Button btnRedClock;
    int fragmentId;
    String selectedRedText = "";
    public static FragmentRed newInstance(int fragmentId) {
        FragmentRed fragment = new FragmentRed();
        Bundle bundle = new Bundle();
        bundle.putInt("fragmentId", fragmentId);
        fragment.setArguments(bundle);
        return fragment;
    }// newInstance
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // Activities containing this fragment must implement MainCallbacks
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException(
                    ">>> Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity();
        fragmentId = getArguments().getInt("fragmentId", -1);
    }
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        LinearLayout view_layout_red = (LinearLayout) inflater.inflate(
                R.layout.fragment_red, null);
        txtRed = (TextView) view_layout_red.findViewById(R.id.textView1Red);
        txtRed.setText( "Fragment " + fragmentId );
        btnRedClock = (Button) view_layout_red.findViewById(R.id.button1Red);
        btnRedClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRedText = "\nRed Clock:\n" + new Date().toString();
                txtRed.append(selectedRedText);
              // main.onMsgFromFragToMain("RED-FRAG", selectedRedText );
            }
        });
        return view_layout_red;
    }
}