package vaibhav.tech.easyshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button share;
    EditText editText;
    int LAUNCH_SCAN_ACTIVITY=90345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermission();
        share=(Button)findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.editTextTextMultiLine);
        setEditTextData();
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "Empty Text!", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), ScanQR.class);
                    intent.putExtra("ttext",editText.getText().toString());
                    startActivity(intent);
                }
            }
        });
        TextView t2 = (TextView) findViewById(R.id.watchDemo);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t3 = (TextView) findViewById(R.id.textView2);
        t3.setMovementMethod(LinkMovementMethod.getInstance());
    }


    public void getPermission()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setEditTextData();
    }

    public String[] getClipboardData()
    {
        final String[] text = {""};
        try {
            ClipboardManager clipboard = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            Log.d(TAG, "getClipboardData: "+clipboard.hasPrimaryClip());
            if (clipboard != null) ;
            ClipData clip = clipboard.getPrimaryClip();
            if (clip != null) ;
            ClipData.Item item = clip.getItemAt(0);
            if (item != null) ;
            CharSequence textToPaste = item.getText();
            if (textToPaste == null) ;
            else
                text[0] = textToPaste.toString();

        }
        catch (Exception e)
        {
            Log.d(TAG, "getClipboardData: "+e.toString());
        }


        return text;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
        {
            setEditTextData();
        }
    }

    public void setEditTextData()
    {
        String[] text=getClipboardData();
        Log.d(TAG, "setEditTextData: "+text[0]);
        editText.setText(text[0]);
    }
}