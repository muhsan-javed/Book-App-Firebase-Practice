package com.muhsantech.recyclerviewtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.muhsantech.recyclerviewtestapp.databinding.ActivityRealBookBinding;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;

public class RealBookActivity extends AppCompatActivity implements DownloadFile.Listener {

    ActivityRealBookBinding binding;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRealBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("back");

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while Loading");

        progress.show();

        String url = getIntent().getStringExtra("url");
        RemotePDFViewPager remotePDFViewPager =
                new RemotePDFViewPager(RealBookActivity.this, url, this);


    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        progress.dismiss();
        PDFPagerAdapter adapter = new PDFPagerAdapter(this, extractFileNameFromURL(url));
        binding.pdfViewPager.setAdapter(adapter);

    }
    public static String extractFileNameFromURL(String url){
        return url.substring(url.lastIndexOf('/') +1 );
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}