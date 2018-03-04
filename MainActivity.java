package hire.danish.com.myinfo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

import java.lang.reflect.Array;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextMobile;
            //editTextCity;

    private Spinner dspinner;


    private Button buttonSubmit;
    private AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dspinner = (Spinner)findViewById(R.id.spinner);
        dspinner .setOnItemSelectedListener(new ItemSelectedListener());

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
       // editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextMobile = (EditText) findViewById(R.id.editTextMobile);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        awesomeValidation.addValidation(this, R.id.editTextName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.editTextEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.editTextMobile, "^[2-9]{2}[0-9]{8}$", R.string.mobilenumbererror);
        //awesomeValidation.addValidation(this, R.id.editTextCity, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.cityerror);

        buttonSubmit.setOnClickListener(this);

    }


    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {
        String firstItem = String.valueOf(dspinner.getSelectedItem());

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (firstItem.equals(String.valueOf(dspinner.getSelectedItem()))){

            }
            else {
                Toast.makeText(parent.getContext(), "You have selected :" +parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }



    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    private void submitForm(){
        if (awesomeValidation.validate()){
            Toast.makeText(this, "Validation Successful", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {

        if (view == buttonSubmit) {
            submitForm();
        }

    }
}
