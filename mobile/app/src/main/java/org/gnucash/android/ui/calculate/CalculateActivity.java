package org.gnucash.android.ui.calculate;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.gnucash.android.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CalculateActivity extends Activity {

    private List<Double> numbers = new ArrayList<Double>();
    private RequestQueue queue;
    private final static String WEB_APP_ENDPOINT = "http://10.0.2.2:7777/webapp/rest/WebApp/getResult";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_calculate);
        numbers.clear();
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    /**
     * Add a number to the list of numbers.
     *
     * @param view the GUI element clicked
     */
    public void addNumber(View view){
        EditText numberEditText = (EditText) findViewById(R.id.text_box_number);
        boolean isValidNumber = false;
        double numberValue = -1;
        if(numberEditText.getText()!=null){
            String numberString = numberEditText.getText().toString();
            if(!numberString.equals("")){
                try {
                    numberValue = Double.parseDouble(numberString);
                    numbers.add(new Double(numberValue));
                    isValidNumber = true;
                } catch (NumberFormatException nfe) {

                }
            }
        }
        TextView message = (TextView) findViewById(R.id.label_message);
        if(isValidNumber){
            message.setText("Added number: "+numberValue);
            numberEditText.setText("");
            return;
        }
        else{
            message.setText("The number is not valid");
            return;
        }

    }
    /**
     * Clear the list of numbers.
     *
     * @param view the GUI element clicked
     */
    public void clear(View view){
        numbers.clear();
        TextView message = (TextView) findViewById(R.id.label_message);
        message.setText("Numbers cleared");
        EditText numberEditText = (EditText) findViewById(R.id.text_box_number);
        numberEditText.setText("");
    }

    public void computeMean(View view){
        TextView message = (TextView) findViewById(R.id.label_message);
        if(numbers.size()==0){
            message.setText("No numbers to average");
            return;
        }
        try {
            JSONArray numberJSONsArray = new JSONArray();
            for (Double number : numbers) {
                numberJSONsArray.put(number.doubleValue());
            }
            JSONObject numbersJSON = new JSONObject();
            numbersJSON.put("numbers", numberJSONsArray);
            //make request to web app
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, CalculateActivity.WEB_APP_ENDPOINT,
                    numbersJSON, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    TextView resultMessage = (TextView) findViewById(R.id.label_message);
                    try {
                        resultMessage.setText("CSCI5802Spring2023: The average is: " + response.getDouble("result"));
                        numbers.clear();
                    }
                    catch(JSONException jsonException){
                        resultMessage.setText("Could not read response from webapp");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    TextView resultMessage = (TextView) findViewById(R.id.label_message);
                    resultMessage.setText("Could not contact webapp");
                }
            });
            // Add the request to the RequestQueue.
            queue.add(jsonObjectRequest);
        }
        catch(JSONException jsonException){
            message.setText("Could not contact webapp");
        }

    }


}
