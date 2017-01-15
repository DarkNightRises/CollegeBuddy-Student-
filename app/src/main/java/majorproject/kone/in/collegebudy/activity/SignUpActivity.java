package majorproject.kone.in.collegebudy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import majorproject.kone.in.collegebudy.Config;
import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.listener.NetworkResponseListener;
import majorproject.kone.in.collegebudy.model.Branch;
import majorproject.kone.in.collegebudy.model.College;
import majorproject.kone.in.collegebudy.network.FetchData;

/**
 * Created by kartikey on 02/09/16.
 */
public class SignUpActivity extends Activity implements NetworkResponseListener,View.OnClickListener {
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
    private FetchData fetchData;
    private Button submit;
    private int dataflow=0;
    private College college;
    private Branch branch;
    private Spinner collegeList,branchList;
    private EditText name,email,mobile_number,password,confirmpassword,section,year,student_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        submit = (Button) findViewById(R.id.submit);
        collegeList = (Spinner) findViewById(R.id.collegeList);
        submit.setOnClickListener(this);
        initialiseViews();
        getCollegeList();
    }
    public void initialiseViews(){
        branchList = (Spinner) findViewById(R.id.branchList);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        mobile_number = (EditText) findViewById(R.id.mobileNumber);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmPassword);
        section = (EditText) findViewById(R.id.section);
        year = (EditText) findViewById(R.id.year);
        student_number = (EditText) findViewById(R.id.student_number);
    }
    public void getCollegeList(){
        dataflow = 0;
        fetchData = new FetchData(SignUpActivity.this,SignUpActivity.this);
        fetchData.setType_of_request(Config.GET);
        fetchData.setUrl(Config.BASE_URL+Config.GET_COLLEGE_LIST);
        fetchData.execute();
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
      if(result!= null){
        if(dataflow == 0){
                Log.d("Result ","RESULT "+ result);
              Toast.makeText(SignUpActivity.this,"Data of college list is "+result,Toast.LENGTH_SHORT).show();

            try {
                resultObject = new JSONObject(result);
                if(resultObject.getBoolean("success") == true){
                    college = new College(resultObject.getJSONArray("data"));
                    ArrayAdapter<String> collegeListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, college.getCollegeList());
                    collegeList.setAdapter(collegeListAdapter);
                    dataflow = 1;
                    fetchData = new FetchData(SignUpActivity.this,SignUpActivity.this);
                    fetchData.setType_of_request(Config.GET);
                    fetchData.setUrl(Config.BASE_URL+Config.GET_BRANCH);

                    fetchData.execute();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(dataflow == 1){
            try {
                resultObject = new JSONObject(result);
                if(resultObject.getBoolean("success") == true) {
                   branch = new Branch(resultObject.getJSONArray("data"));
                    ArrayAdapter<String> branchListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,branch.getBranchesNames());
                    branchList.setAdapter(branchListAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }}
}

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                singup();
        }
    }

    public void singup(){

        if(validate()){

        }
    }
    public boolean validate(){
        /***
         * KARAN ATTACH ALL VALIDATION HERE
         ***/
        return true;
    }
    public JSONObject getPostData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",name.getText().toString());
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
      return null;
    }
}