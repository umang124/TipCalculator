package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText etCostOfService;
    RadioGroup rgTipOptions;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchRoundUpTip;
    Button buttonCalculateTip;
    TextView tvTipAmount;
    double tipValue = 0.20;
    double totalTip = 0;
    double costOfService = 0;
    boolean roundUpTipChecked = true;
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etCostOfService = findViewById(R.id.cost_of_service);
        rgTipOptions = findViewById(R.id.tip_options);
        switchRoundUpTip = findViewById(R.id.round_up_switch);
        buttonCalculateTip = findViewById(R.id.calculate_button);
        tvTipAmount = findViewById(R.id.tip_result);

        //noinspection Convert2Lambda
        rgTipOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rgTipOptions.getCheckedRadioButtonId() == R.id.option_fifteen_percent) {
                    tipValue = 0.15;
                } else if (rgTipOptions.getCheckedRadioButtonId() == R.id.option_eighteen_percent) {
                    tipValue = 0.18;
                } else if (rgTipOptions.getCheckedRadioButtonId() == R.id.option_twenty_percent) {
                    tipValue = 0.20;
                }
            }
        });

        //noinspection Convert2Lambda
        buttonCalculateTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Double.parseDouble(etCostOfService.getText().toString());
                } catch (NumberFormatException ex) {
                    Toast.makeText(MainActivity.this, "Please enter the value in number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Double.parseDouble(etCostOfService.getText().toString()) < 0) {
                    Toast.makeText(MainActivity.this, "Please enter the value in positive number", Toast.LENGTH_SHORT).show();
                    return;
                }
                costOfService = Double.parseDouble(etCostOfService.getText().toString());
                totalTip = tipValue * costOfService;

                if (roundUpTipChecked) {
                    totalTip = Double.parseDouble(df.format(totalTip));
                }
                tvTipAmount.setText(String.valueOf(totalTip));
            }
        });

        //noinspection Convert2Lambda
        switchRoundUpTip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    roundUpTipChecked = true;
                    Toast.makeText(MainActivity.this, "CHanged true", Toast.LENGTH_SHORT).show();
                } else {
                    roundUpTipChecked = false;
                    Toast.makeText(MainActivity.this, "CHanged false", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}