package tecnest.manage.myconnectbig;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tecnest.manage.myconnectbig.HttpDocs.HttpManger;
import tecnest.manage.myconnectbig.HttpDocs.Requestdata;
import tecnest.manage.myconnectbig.Pojo.Forgotpwdop;
import tecnest.manage.myconnectbig.Pojo.Registerop;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForgotPasswordActivityFragment extends Fragment {


    private EditText userID;
    private EditText userFullname;
    private EditText userCompanyCode;
    private EditText userIO;
    private EditText userpassword;
    private EditText userPh;
    private EditText mailAddress;
    private Button sendData;
    private ProgressBar pb_Adddata;
    public List<Forgotpwdop> connectRegister;
    List<MyEmployeeInformation> task;
    private CheckBox tShowPassword;
    String ufullname;
    String userName;
    String userPhone;
    String userPwd;
    private Context mcontextAcivity;

    private final String URL_SUPERVISOR = "http://nearesthospitals.in/ForgotPassword.php";

    public ForgotPasswordActivityFragment() {
        setHasOptionsMenu(true);
    }



    //    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_register, container, false);

//          if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
        userFullname = (EditText) v.findViewById(R.id.userFullName);
        mailAddress = (EditText) v.findViewById(R.id.userName);
        userPh = (EditText) v.findViewById(R.id.userPhoneno);
        v.findViewById(R.id.Send).setOnClickListener(sent);
        pb_Adddata = (ProgressBar) v.findViewById(R.id.progressConnect);




        pb_Adddata.setVisibility(View.INVISIBLE);
        task = new ArrayList<>();
        Display();
        return v;
    }

    public void Display() {

        if (isConnection()) {

//            if (userFullname.length() > 0 && mailAddress.length() > 0 && userPh.length() > 0 ) {
//
//                ufullname = userFullname.getText().toString();
//                userName = mailAddress.getText().toString();
//
//                userPhone = userPh.getText().toString();
//                if (isValidEmail(userName) && isValidPhoneNumber(userPhone)) {

                    requestData(URL_SUPERVISOR, ufullname, userName, userPhone);
//                } else {
//
//                    Toast.makeText(getContext(), "Not Valid email or password", Toast.LENGTH_SHORT).show();
//
//                }
            } else {
                Toast.makeText(getContext(), "No data sent", Toast.LENGTH_SHORT).show();
            }
//        }
//        idname = holderUser.getStringExtra("user");
//        idnumber = holderUser.getStringExtra("number");
//        idtype = holderUser.getStringExtra("type");
//
//        SharedPreferences shareAccountInformation = getContext().getSharedPreferences(ACCOUNT_PREFERENCE, MODE_PRIVATE);
//        String  restoreaccname = shareAccountInformation.getString("accname","No value found").toString();
//        String  restoreaccnumber = shareAccountInformation.getString("accnum","No value found").toString();
//
//        textAccountName.setText(restoreaccname);
//        textAccountNumber.setText(restoreaccnumber);

//        if (isConnection()) {
//
//            holderUser = getActivity().getIntent();
//            if (holderUser == null || holderUser.getData() == null) {
//               String idlink = holderUser.getStringExtra("user");
//            }
//
//            // edit text
//            if (isConnection()) {
//
//                String us = "Rakesh";
//                String pwd = "Rakesh123";
//                requestData(URL_NETBANKING, us, pwd);
//            }
//            else
//            {
//                Toast.makeText(getContext(),"No data available for now.",Toast.LENGTH_SHORT).show();
//            }
//
//        }
    }

    private boolean isValidMobileno() {



        return  false;
    }
    public final static boolean isValidPhoneNumber(CharSequence target) {
        if (target == null || target.length() < 6 || target.length() > 13) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }

    }


    View.OnClickListener sent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Display();

        }
    };


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_register,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId()==R.id.action_settings){
            getActivity().finish();

            startActivity(new Intent(getContext(),LoginActivity.class));

        }
        return super.onOptionsItemSelected(item);

    }

    public boolean isConnection() {

        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {

            return true;
        } else {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setMessage("Your are not connected to the internet, try again later !");

            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    getActivity().finish();
                }
            });

            alertDialogBuilder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    onResume();
                }
            });


            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            return false;
        }
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

//    public void update() {
//
//        Log.i("datat", "sent");
//    }

    private void requestData(String url, String fname, String cl_ph, String email) {
        Requestdata data = new Requestdata();
        data.setMethod("POST");
        data.setUri(url);
        data.setParam("Fullname", "Siddharth Chugh");
        data.setParam("Phonenumber","9999750601");
        data.setParam("Username", "siddharthchughs@gmail.com");

        MyEmployeeInformation me = new MyEmployeeInformation();
        me.execute(data);
    }


    public class MyEmployeeInformation extends AsyncTask<Requestdata, String, String> {


        //  @SuppressLint("WrongConstant")
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (task.size() == 0) {
                pb_Adddata.setVisibility(View.VISIBLE);
            }
            task.add(this);


        }

        @Override
        protected String doInBackground(Requestdata... params) {

            String content = HttpManger.getdata(params[0]);

//            Gson gsSeatch = new Gson();
//            Registerop[] cl = gsSeatch.fromJson(content, Registerop[].class);
            return content;// "Task on Completed ";

        }

        // onPostExecute displays the results of the AsyncTask.
        @SuppressLint("WrongConstant")
        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getContext(), "Data Sent!", Toast.LENGTH_LONG).show();
            task.remove(this);

            if (task.size() == 0) {
                pb_Adddata.setVisibility(View.INVISIBLE);
                userFullname.setText("");
                mailAddress.setText("");
                userPh.setText("");
                getActivity().finish();
                Intent in = new Intent(getActivity(),LoginActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);

                Toast.makeText(getContext(), "Successfully submitted.", Toast.LENGTH_SHORT).show();

            }

            connectRegister = parseSupervisors(result);


        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }

    public List<Forgotpwdop> parseSupervisors(String content) {

        Forgotpwdop list;

        try {

            JSONArray ar = new JSONArray(content);
            List<Forgotpwdop> movieList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {


                JSONObject obj = ar.getJSONObject(i);
                String sfname = (obj.getString("Fullname"));
                String slname = (obj.getString("Phonenumber"));
                String phno = (obj.getString("Username"));

                list = new Forgotpwdop(sfname, slname, phno);
                //                list = new Supervisor();
//                list.setSupervisor_id(obj.getString("S_ID"));
//                list.setSupervisor_name(obj.getString("S_Name"));
//                list.setSupervisor_ph(obj.getString("Phonenumber"));
//                list.setSupervisor_mail(obj.getString("Emailid"));

                movieList.add(list);

            }
            return movieList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }

}


