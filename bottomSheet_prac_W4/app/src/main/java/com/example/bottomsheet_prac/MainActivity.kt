package com.example.bottomsheet_prac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showBottomSheet.setOnClickListener{
          val dialog = BottomSheetDialog(this);
          val view = layoutInflater.inflate(R.layout.dialog_layout,null);

          dialog.setContentView(view);
          dialog.show();

          val closeBtn = view.findViewById<ImageView>(R.id.bt_close);

          closeBtn.setOnClickListener {
              dialog.dismiss();
          }
            
        }

    }
}