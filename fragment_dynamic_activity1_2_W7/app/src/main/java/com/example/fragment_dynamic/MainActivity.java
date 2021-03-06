package com.example.fragment_dynamic;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends Activity implements MainCallbacks {

    FragmentTransaction ft;
    FragmentRed redFragment;
    FragmentBlue blueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        // create a new BLUE fragment - show it
        ft = getFragmentManager().beginTransaction();
        blueFragment = FragmentBlue.newInstance("first-blue");
        ft.replace(R.id.main_holder_blue, blueFragment);
        ft.commit();
        // create a new RED fragment - show it
        ft = getFragmentManager().beginTransaction();
        redFragment = FragmentRed.newInstance("first-red");
        ft.replace(R.id.main_holder_red, redFragment);
        ft.commit();
    }

    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
        // show message arriving to MainActivity
        Toast.makeText(getApplication(),
                " MAIN GOT>> " + sender + "\n" + strValue, Toast.LENGTH_LONG)
                .show();
        if (sender.equals("RED-FRAG")) {
            // TODO: if needed, do here something on behalf of the RED fragment
        }
        if (sender.equals("BLUE-FRAG")) {
            try {
                // forward blue-data to redFragment using its callback method
                redFragment.onMsgFromMainToFragment("\nSender: " + sender
                        + "\nMsg: " + strValue);
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
    }
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
      // get a reference to each fragment attached to the GUI
        if (fragment.getClass() == FragmentRed.class ){
            redFragment = (FragmentRed) fragment;
        }
        if (fragment.getClass() == FragmentBlue.class ){
            blueFragment = (FragmentBlue) fragment;
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}