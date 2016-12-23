package majorproject.kone.in.collegebudy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.listener.NetworkResponseListener;

/**
 * Created by kartikey on 02/09/16.
 */
public class SignUpActivity extends Activity implements NetworkResponseListener {
    private ProgressBar progressBar;
    Button singnUpButton;
   // FetchDataforLists fetchDataforLists;
    JSONObject jsonObject;
    boolean isNamePatternCorrect, isEmailPatternCorrect;
    public static String confirmPassword, name, phone, password, email, city;
    public static String nameRegex = "^[\\p{L} .'-]+$";
    public static final String emailRegex =
            "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
    private JSONObject resultObject;
    private Intent intent;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.sign_up);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
       /* findViewById(R.id.btn_submitSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = ((EditText) findViewById(R.id.name)).getText().toString();
                phone = ((EditText) findViewById(R.id.mobileNumber)).getText().toString();
                password = ((EditText) findViewById(R.id.password)).getText().toString();
                email = ((EditText) findViewById(R.id.email)).getText().toString();
               // confirmPassword = ((EditText) findViewById(R.id.confirmPassword)).getText().toString();
                if (TextUtils.isEmpty(name)) {
                    ((EditText) findViewById(R.id.name)).setError("Field Required");
                }
                if (TextUtils.isEmpty(phone)) {
                    ((EditText) findViewById(R.id.mobileNumber)).setError("Field Required");
                }
                if (TextUtils.isEmpty(email)) {
                    ((EditText) findViewById(R.id.email)).setError("Field Required");
                }
                if (TextUtils.isEmpty(password)) {
                    ((EditText) findViewById(R.id.password)).setError("Field Required");
                }
                if (TextUtils.isEmpty(confirmPassword))
                {
                    ((EditText) findViewById(R.id.confirmPassword)).setError("Field Required");
                }
                else if (checkDetails() && password.equals(confirmPassword))
                {

                Toast.makeText(SignUpActivity.this,"Sign Up Successfull",Toast.LENGTH_SHORT).show();
                } else {
                    if (isNamePatternCorrect && !isEmailPatternCorrect) {
                        Toast.makeText(SignUpActivity.this, "Enter a valid Email", Toast.LENGTH_SHORT).show();

                    } else if (isNamePatternCorrect && !isEmailPatternCorrect)
                        Toast.makeText(SignUpActivity.this, "Enter a valid Name", Toast.LENGTH_SHORT).show();
                    else if (!password.equals(confirmPassword)) {
                        Toast.makeText(SignUpActivity.this, "Password and Confirm Password do not match.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });*/
    }

    public boolean checkDetails() {
        isNamePatternCorrect = matchWithRegex(nameRegex, name);
        isEmailPatternCorrect = matchWithRegex(emailRegex, email);
        if (isNamePatternCorrect && isEmailPatternCorrect) {
            return true;
        }
        return false;
    }

    public static boolean matchWithRegex(String regex, String sampleText) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sampleText);
        return matcher.matches();
    }

    @Override
    public void preRequest() throws MalformedURLException {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void postRequest(String result) throws MalformedURLException {
        progressBar.setVisibility(View.GONE);

}}