package com.example.cicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView tv5,tv6; // Dynamic text views
    EditText f1,f2,f3; // User input field
    SeekBar sb; 
    private Button ci;
    int yr,fq;
    String frq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ci = (Button) findViewById(R.id.button);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.nums, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                frq = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sb = (SeekBar) findViewById(R.id.seekBar4);
        tv5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView6);
        f1 = (EditText) findViewById(R.id.editText2);
        f2 = (EditText) findViewById(R.id.editText3);
        f3 = (EditText) findViewById(R.id.editText4);

        //float inv = Float.parseFloat(f1.getText().toString().trim());

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv5.setText(progress + "years");
                yr = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float freq,compfreq;

                compfreq=1;

                if ( frq .equals("Monthly")){
                    freq = 1/12;
                }
                else if ( frq.equals("Quarterly")){
                    freq = 1/4;
                }
                else if ( frq.equals("Semi-Annually")){
                    freq = 1/2;
                }
                else if (frq.equals("Annually")){
                    freq = 1;
                }
                else {
                    freq = 0;
                }
                //tv6.setText("Result: " + freq);

                String tmp1 = f1.getText().toString().trim();
                String tmp2 = f2.getText().toString().trim();
                String tmp3 = f3.getText().toString().trim();
                float inv = Float.parseFloat(tmp1);
                float payment = Float.parseFloat(tmp2);
                float rate = Float.parseFloat(tmp3);
                float years = Float.parseFloat(String.valueOf(yr));

                //double total = inv + payment + rate + years + freq ;

                float per = years * compfreq;

                float r = (rate/100)*(1/compfreq);

                double ter = Math.pow(r+1,per);

                double amt = (inv*ter)+((payment*freq)*((ter-1)/r));

                float total = (float) amt;

                tv6.setText(String.format("%.2f",total));

            }
        });
    }
}
