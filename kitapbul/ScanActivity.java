package com.mozosoft.zgr.kitapbul;

/**
 * Created by DELL on 10.07.2019.
 */
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static java.security.AccessController.getContext;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

      //  List<BarcodeFormat> formats = List.of(BarcodeFormat.QR_CODE);



        List<BarcodeFormat> formats = new ArrayList<BarcodeFormat>();
        formats.add(BarcodeFormat.EAN_13);


        mScannerView.setFormats(formats);






        //kamera için izin alındı
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }




        // Set the scanner view as the content view
    }


    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {


        //Toast.makeText(getApplicationContext(), rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
        // Do something with the result here
        // Log.v("tag", rawResult.getText()); // Prints scan results
        // Log.v("tag", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)



     //   if((rawResult.getText().substring(0,3)=="978")||(rawResult.getText().substring(0,3)=="979")) {
            MainActivity.barkodArama(rawResult.getText());
            onBackPressed();
      //  }


        // Toast.makeText(getApplicationContext(), rawResult.getText(), Toast.LENGTH_SHORT).show();

        //onBackPressed();

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}