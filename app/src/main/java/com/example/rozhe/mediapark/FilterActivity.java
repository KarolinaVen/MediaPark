package com.example.rozhe.mediapark;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    SeekBar seekBarBatteryBalance;
    ProgressDialog progressDialog;
    EditText plateNumber;
    int carBatteryBalance;
    Button filterButton;
    String plate;
    Double userLongitude;
    Double userLatitude;
    ImageButton closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        plateNumber = findViewById(R.id.car_number_input);

        userLongitude = getIntent().getDoubleExtra("User longitude", -1);
        userLatitude = getIntent().getDoubleExtra("User latitude", -1);

        closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });

        filterButton = findViewById(R.id.filter);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plate = plateNumber.getText().toString().toUpperCase();
                onFilterButtonClicked(carBatteryBalance, plate);
            }
        });

        seekBarBatteryBalance = (SeekBar) findViewById(R.id.battery_balance_bar);
        seekBarBatteryBalance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                TextView batteryBalance = (TextView) findViewById(R.id.balance_number);
                batteryBalance.setText("from: " + String.valueOf(progress) + " %");
                carBatteryBalance = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        progressDialog = new ProgressDialog(FilterActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        App app = (App) getApplication();
        app.getInfoCache().observe(this, new Observer<List<Info>>() {
            @Override
            public void onChanged(@Nullable List<Info> infos) {
                progressDialog.dismiss();
                if (infos != null) {
                    generateDataList(infos);
                }
            }
        });
    }

    public List<Info> calculateDistance(List<Info> infos) {
        float[] results = new float[1];
        ArrayList<Info> calculatedList = new ArrayList<>();
        for (Info i : infos) {
            android.location.Location.distanceBetween(userLatitude, userLongitude, i.getLocation().getLatitude(),
                    i.getLocation().getLongitude(), results);
            i.setDistance(results[0]);
            calculatedList.add(i);
        }
        return calculatedList;
    }

    private void generateDataList(List<Info> photoList) {
        photoList = calculateDistance(photoList);

        Collections.sort(photoList, new Comparator<Info>() {
            @Override
            public int compare(Info info, Info t1) {
                return Double.valueOf(info.getDistance()).compareTo(Double.valueOf(t1.getDistance()));
            }
        });

        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this, photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FilterActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void onFilterButtonClicked(int carBatteryBalance, String plate) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Battery", carBatteryBalance);
        intent.putExtra("Plate number", plate);
        startActivity(intent);
    }

    public void close(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
