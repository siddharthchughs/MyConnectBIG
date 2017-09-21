package tecnest.manage.myconnectbig;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment implements View.OnClickListener{

    TextView fp,sg;
    private EditText userpassword;
    private CheckBox tShowPassword;
    private EditText userName;
    private EditText mailAddress;
    private AppSession app_Session;




    public LoginActivityFragment() {

    setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_login, container, false);
        userName = (EditText) v.findViewById(R.id.userName);
        userpassword = (EditText) v.findViewById(R.id.userPassword);
        tShowPassword = (CheckBox)v.findViewById(R.id.passwordShow);
        tShowPassword.setVisibility(View.GONE);
        app_Session = AppSession.getInstance(getActivity());

        v.findViewById(R.id.forgotPwd).setOnClickListener(buttonForgot);
    v.findViewById(R.id.signup).setOnClickListener(buttonSignup);
        userpassword.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        userpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(userpassword.getText().length()>0){
                    tShowPassword.setVisibility(View.VISIBLE);
                }
                else
                {
                    tShowPassword.setVisibility(View.GONE);

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    //Show password
                    userpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //Hide password
                    userpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });



        return v;
    }

    View.OnClickListener buttonForgot =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getContext(),ForgotPasswordActivity.class) );

        }
    };


    View.OnClickListener buttonSignup =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getContext(),RegisterActivity.class) );

        }
    };


    @Override
    public void  onClick(View view) {

        //  int id = view.getId();

//        switch (view.getId()) {
//            case R.id.forgotPwd:
//                linkForgotPassword();
//                break;
//            case R.id.signup:
//                linkSignUp();
//                break;
//
//        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_login,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.action_settings){
            startActivity(new Intent(getContext(),AboutUsActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }

    public void linkForgotPassword(){
        startActivity(new Intent(getContext(),ForgotPasswordActivity.class) );
    }

    public void linkSignUp(){
        startActivity(new Intent(getContext(),RegisterActivity.class) );
    }



    @SuppressLint("WrongConstant")
    public void update(String content) {

        Login list;

        try {
            JSONObject ar = new JSONObject(content);

            if (ar.getBoolean("Result") == true) {
                if (checkBox1.isChecked()) { // keep me signed in

                    mSession.setHasLoging(true);
                    mSession.setSkipped(true);
//                mSession.setUserId(ar.getInt("ID"));
//                mSession.setEmail(ar.getString("Email_id"));
                    mSession.setUserName(ar.getString("name"));
                    mSession.setFirstName(ar.getString("email"));
                    mSession.setPhone(ar.getString("phonenumber"));
                    //          mSession.setMobileNumber(ar.getString("PhoneNumber"));

                } else { // not want to keep me sign in
                    mSession.setHasLoging(true);
                    mSession.setUserName(ar.getString("name"));
                    mSession.setFirstName(ar.getString("email"));
                    //        mSession.setMobileNumber(ar.getString("PhoneNumber"));
                    mSession.setPhone(ar.getString("phonenumber"));

                }
                Intent in = new Intent(getContext(), AdminActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                Toast.makeText(getContext(), "User" + ar.getString("Message"), Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(getContext(), "User" + ar.getString("Message"), Toast.LENGTH_SHORT).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        JSONObject object = new JSONObject(response);
//        if (object.getBoolean("Result")) {
//
//            if (checkBox1.isChecked()) { // keep me signed in
//                mSession.setHasLoging(true);
//                mSession.setSkipped(true);
//                mSession.setUserId(object.getInt("ID"));
//                mSession.setEmail(object.getString("Email_id"));
//                mSession.setUserName(object.getString("UserName"));
//                mSession.setFirstName(object.getString("First_name"));
//                mSession.setMobileNumber(object.getString("PhoneNumber"));
//
//            }
//            else { // not want to keep me sign in
//                mSession.setHasLoging(true);
//                mSession.setUserId(object.getInt("ID"));
//                mSession.setEmail(object.getString("Email_id"));
//                mSession.setUserName(object.getString("UserName"));
//                mSession.setFirstName(object.getString("First_name"));
//                mSession.setMobileNumber(object.getString("PhoneNumber"));
//            }
//            Intent i = new Intent(mActivity, MainActivity.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(i);
//            //startActivity(new Intent(mActivity, MainActivity.class));
//            Toast.makeText(mActivity, object.getString("Message"), Toast.LENGTH_SHORT).show();
//
//        } else {
//            Utils.showDialog(mActivity, "There was a problem", object.getString("Message"));
//            //object.getBoolean("Result")
//        }
//
//    } catch (JSONException e) {
//        e.printStackTrace();
//    }

    }


}
