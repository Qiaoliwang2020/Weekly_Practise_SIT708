package com.example.fragmentcomunicate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentcomunicate.fragments.FragmentA
import com.example.fragmentcomunicate.fragments.FragmentB

class MainActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentA = FragmentA();

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentA).commit()
    }

    override fun passDataCom(editTextInput: String) {
        val bundle = Bundle()
        bundle.putString("message",editTextInput)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentB = FragmentB()
        fragmentB.arguments = bundle;

        transaction.replace(R.id.fragment_container,fragmentB)
        transaction.commit()
    }
}


