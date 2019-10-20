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

    // Variables declaration part
    TextView tv5,tv6; // Dynamic text views
    EditText f1,f2,f3; // User input fields
    SeekBar sb; // Seekbar variable
    private Button ci; // Variable for Calculate button
    int yr,fq;
    String frq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ci = (Button) findViewById(R.id.button);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // ArrayAdapter created using a default layout and array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.nums, android.R.layout.simple_spinner_item);
        // Layout for list of choices
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Spinner Adapter
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                frq = parent.getItemAtPosition(position).toString();
                // To get and store the selected value from spinner layout
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Getting values from resource xml files
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
                tv5.setText(progress + "years"); // displays selected seek bar value
                yr = progress;
                // To get and store the selected value from seekbar
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        //Below code calculates compound intrest value
        ci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float freq,compfreq;
                compfreq=1;
                //Matches spinner values from string format to integer
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
                float inv = Float.parseFloat(tmp1); // principal
                float payment = Float.parseFloat(tmp2); // Amount to be paid monthly
                float rate = Float.parseFloat(tmp3);    // Intrest rate
                float years = Float.parseFloat(String.valueOf(yr)); // Duration of intrest

                //double total = inv + payment + rate + years + freq ;

                float per = years * compfreq; // Calculates period
                float r = (rate/100)*(1/compfreq); // Calculate rate
                double ter = Math.pow(r+1,per);
                double amt = (inv*ter)+((payment*freq)*((ter-1)/r)); // Calculate final amount
                float total = (float) amt;
                tv6.setText(String.format("%.2f",total)); // Displays final amount on screen

            }
        });
    }
}
