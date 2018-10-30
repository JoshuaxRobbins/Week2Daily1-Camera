package com.example.josh.week2daily1_camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Initializations
    public EditText etSendString;
    public EditText etCallNumber;
    public EditText etPersonName;
    public EditText etPersonAge;
    public EditText etPersonGender;
    public TextView tvLoanAmount;
    public TextView tvInterestAmount;
    public SeekBar sbLoanAmount;

    int seekMin = 1000;
    int seekMax = 10000;
    int seekCurrent = 5000;



    ArrayList<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSendString = findViewById(R.id.etSendString);
        etCallNumber = findViewById(R.id.etCallNumber);
        tvLoanAmount = findViewById(R.id.tvLoanAmount);
        tvInterestAmount = findViewById(R.id.tvInterestAmount);
        sbLoanAmount = findViewById(R.id.sbLoanAmount);
        etPersonAge = findViewById(R.id.etPersonAge);
        etPersonName = findViewById(R.id.etPersonName);
        etPersonGender = findViewById(R.id.etPersonGender);


        sbLoanAmount.setMax(seekMax - seekMin);
        sbLoanAmount.setProgress(seekCurrent - seekMin);
        tvLoanAmount.setText(String.valueOf(seekCurrent));
        tvInterestAmount.setText(Integer.toString(emiCalculator(seekCurrent)));
        sbLoanAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekCurrent = progress + seekMin;
                tvLoanAmount.setText(String.valueOf(seekCurrent));
                tvInterestAmount.setText(Integer.toString(emiCalculator(seekCurrent)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void openCamera(View view) {
        Intent intent = new Intent(getApplicationContext(),CameraActivity.class);
        startActivity(intent);
    }

    public void shareString(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,etSendString.getText().toString());
        intent.setType("text/plain");
        startActivity(intent);
    }

    public void sendPersons(View view) {
        Intent intent = new Intent(getApplicationContext(),PersonListActivity.class);
        intent.putParcelableArrayListExtra("personList",personList);
        startActivity(intent);

    }
    public void addPerson(View view) {
        String personName = etPersonName.getText().toString();
        String personAge = etPersonAge.getText().toString();
        String personGender = etPersonGender.getText().toString();

        Person newPerson = new Person(personName,personAge,personGender);
        personList.add(newPerson);

    }

    public void callNumber(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + etCallNumber.getText().toString()));
        startActivity(intent);
    }

    public int emiCalculator(int loanAmount){
        float interest  = 5;
        int time = 5;

        interest = (interest / (12 * 100));
        time = 5 * 12;
        return (int)(loanAmount * interest * (float)Math.pow(1 + interest,time) /
                (float)(Math.pow(1 + interest,time) - 1));
    }


}
