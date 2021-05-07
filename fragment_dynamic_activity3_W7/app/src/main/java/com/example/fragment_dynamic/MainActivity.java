package com.example.fragment_dynamic;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements MainCallbacks, View.OnClickListener {

    FragmentTransaction ft;
    FragmentRed redFragment;
    TextView txtMsg;
    Button btnAddRedFragment;
    Button btnReplaceRedFragment;
    Button btnPop;
    Button btnRemove;
    int serialCounter = 0; //used to enumerate fragments
    String redMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        txtMsg = (TextView) findViewById(R.id.textView1Main);
        btnAddRedFragment = (Button) findViewById(R.id.button1MainShowRed);
        btnReplaceRedFragment = (Button) findViewById(R.id.button4MainReplace);
        btnPop = (Button) findViewById(R.id.button2MainPop);
        btnRemove = (Button) findViewById(R.id.button3MainRemove);
        btnAddRedFragment.setOnClickListener(this);
        btnReplaceRedFragment.setOnClickListener(this);
        btnPop.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
    }

    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
        // show message arriving to MainActivity
        txtMsg.setText( sender + "=>" + strValue );
    }

    public void onClick(View v) {
        if (v.getId() == btnAddRedFragment.getId()) {
            addRedFragment(++serialCounter);
        }
        if (v.getId() == btnReplaceRedFragment.getId()) {
            replaceRedFragment(++serialCounter);
        }
        if (v.getId() == btnPop.getId()) {
            FragmentManager fragmentManager = getFragmentManager();
            int counter = fragmentManager.getBackStackEntryCount();
            txtMsg.setText("BACKSTACK old size=" + counter);
            if (counter > 0) {
                // VERSION 1 [ popBackStack could be used as opposite of addBackStack() ]
                // pop takes a Transaction from the BackStack and a view is also deleted
                fragmentManager.popBackStackImmediate();
                txtMsg.append("\nBACKSTACK new size=" + fragmentManager.getBackStackEntryCount());
            }
        }
        if(v.getId() == btnRemove.getId() ){
            FragmentManager fragmentManager = getFragmentManager();
            int counter = fragmentManager.getBackStackEntryCount();
            txtMsg.setText("BACKSTACK old size=" + counter);
            // VERSION 2 -------------------------------------------------------
            // Removes an existing fragment from the fragmentTransaction.
            // If it was added to a container, its view is also removed from that
            // container. The BackStack may remain the same!
            Fragment f1 = fragmentManager.findFragmentByTag("RED-TAG");
            fragmentManager.beginTransaction().remove(f1).commit();
            txtMsg.append("\nBACKSTACK new size=" + fragmentManager.getBackStackEntryCount() );
            // VERSION 3 -------------------------------------------------------
            // Fragment f1 = fragmentManager.findFragmentById(R.id.main_holder);
            // fragmentManager.beginTransaction().remove(f1).commit();
            // txtMsg.append("\nBACKSTACK new size=" + fragmentManager.getBackStackEntryCount() );
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int counter = getFragmentManager().getBackStackEntryCount();
        txtMsg.setText("BACKSTACK size=" + counter);
    }
    public void addRedFragment(int intValue) {
        // create a new RED fragment, add fragment to the transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        redFragment = FragmentRed.newInstance(intValue);
        ft.add(R.id.main_holder, redFragment, "RED-TAG");
        ft.addToBackStack("MYSTACK1");
        ft.commit();
        // complete any pending insertions in the BackStack, then report its size
        getFragmentManager().executePendingTransactions();
        txtMsg.setText("BACKSTACK size =" + getFragmentManager().getBackStackEntryCount() );
    }

    public void replaceRedFragment(int intValue) {
        // create a new RED fragment, replace fragments in the transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        redFragment = FragmentRed.newInstance(intValue);
        ft.replace(R.id.main_holder, redFragment, "RED-TAG");
        ft.addToBackStack("MYSTACK1");
        ft.commit();
        // complete any pending insertions in the BackStack, then report its size
        getFragmentManager().executePendingTransactions();
        txtMsg.setText("BACKSTACK size =" + getFragmentManager().getBackStackEntryCount() );
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}