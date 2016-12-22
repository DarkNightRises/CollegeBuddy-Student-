package majorproject.kone.in.collegebudy.activity;

/**
 * Created by kartikey on 02/09/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 * Created by Shobhit on 03-07-2015.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;

import com.gc.materialdesign.views.ButtonFlat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.listener.NetworkResponseListener;


public class LoginActivity extends Activity implements NetworkResponseListener, View.OnClickListener {
    public EditText mobileNumber;
    public EditText mPasswordView;
    private TextView register, forgotPassword;
    public ProgressBar progressBar;
    private boolean isInternetConnected;
    private TextView signUp;
    private SharedPreferences sharedpreferences;
    private TextView skipLogin;
    private Intent intent;
    public boolean forCompulsaryLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout_new);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        findViewById(R.id.sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
        signUp = (TextView) findViewById(R.id.sign_up);
        signUp.setOnClickListener(this);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        mPasswordView = (EditText) findViewById(R.id.password);
        ButtonFlat mEmailSignInButton = (ButtonFlat) findViewById(R.id.login);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  if (networkDetector.isConnectingToInternet()) {
                try {
                    attemptLogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //}
            }
        });

    }

    private void startMainActivityIntent(Context context) {
        intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    public void attemptLogin() throws JSONException, UnsupportedEncodingException {
        // Reset errors.
        mobileNumber.setError(null);
        mPasswordView.setError(null);
        // Store values at the time of the login attempt.
        String mobile_number = mobileNumber.getText().toString();
        String password = mPasswordView.getText().toString();
        Log.d("Username and password", "Username" + mobile_number + password);
        boolean cancel = false;
        View focusView = null;
        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("Field Required");
            focusView = mPasswordView;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(mobile_number)) {
            mobileNumber.setError("Field Required");
            focusView = mobileNumber;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("dataflow", "3");
            jsonObject.put("phone", mobile_number);
            jsonObject.put("password", password);
//            fetchDataforLists = new FetchDataforLists(LoginActivity.this, this);
//            fetchDataforLists.setUrl(Config.LOGIN);
//            fetchDataforLists.setData(jsonObject);
//            fetchDataforLists.setType_of_request(Config.POST);
//            fetchDataforLists.execute();
            Intent intent = new Intent(LoginActivity.this,NavigationActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void preRequest() throws MalformedURLException {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void postRequest(String result) throws MalformedURLException {
        progressBar.setVisibility(View.GONE);
        Log.d("Final Result", "  " + result);

    }


    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.sign_up:
            intent = new Intent(LoginActivity.this, SignUpActivity.class);
        }
        if(intent!=null){
            startActivity(intent);
        }
    }
}
