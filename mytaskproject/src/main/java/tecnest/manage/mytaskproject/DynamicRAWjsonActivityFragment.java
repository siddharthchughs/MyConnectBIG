package tecnest.manage.mytaskproject;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static android.content.ContentValues.TAG;
import static android.graphics.Color.BLUE;

/**
 * A placeholder fragment containing a simple view.
 */
public class DynamicRAWjsonActivityFragment extends Fragment implements View.OnClickListener {


    TextView tvData;
    List<Testpojo> td;
    Spinner spin;
    RadioButton rd;
    Testpojo tdShow;
    LinearLayout ll;
    TextView dtCreate;
    public static final String RAW_JSON = "[\n" +
            "  {\n" +
            "    \"id\":\"atrid1\",\n" +
            "    \"Name\":\"enrollment_process_id\",\n" +
            "    \"Fields\":{\"Attribute Type\":\"EditText\",\n" +
            "      \"Caption ID\":\"\",\n" +
            "      \"Sequence in Screen\":\"1\",\n" +
            "      \"Text Box Size\":\"30\",\n" +
            "      \"Caption Font\":\"Ariel\",\n" +
            "      \"Caption Size\":\"10\",\n" +
            "      \"Caption Color\":\"Black\"\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"atrid2\",\n" +
            "    \"Name\":\"course_id\",\n" +
            "    \"Fields\":{\n" +
            "      \"Attribute Type\":\"DropDown\",\n" +
            "      \"Caption ID\":\"\",\n" +
            "      \"Sequence in Screen\":\"1\",\n" +
            "      \"Text Box Size\":\"0.5f\",\n" +
            "      \"Caption Font\":\"Ariel\",\n" +
            "      \"Caption Size\":\"10\",\n" +
            "      \"Caption Color\":\"Black\"\n" +
            "    }\n" +
            "\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"atrid3\",\n" +
            "    \"Name\":\"full_name\",\n" +
            "    \"Fields\":{\n" +
            "      \"Attribute Type\":\"RadioButton\",\n" +
            "      \"Caption ID\":\"\",\n" +
            "      \"Sequence in Screen\":\"\",\n" +
            "      \"Text Box Size\":\"40\",\n" +
            "      \"Caption Font\":\"Roman\",\n" +
            "      \"Caption Size\":\"\",\n" +
            "      \"Caption Color\":\"\"\n" +
            "    }\n" +
            "\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"atrid4\",\n" +
            "    \"Name\":\"country\",\n" +
            "    \"Fields\":{\n" +
            "      \"Attribute Type\":\"Button\",\n" +
            "      \"Caption ID\":\"\",\n" +
            "      \"Sequence in Screen\":\"\",\n" +
            "      \"Text Box Size\":\"48\",\n" +
            "      \"Caption Font\":\"\",\n" +
            "      \"Caption Size\":\"14\",\n" +
            "      \"Caption Color\":\"\"\n" +
            "    }\n" +
            "\n" +
            "  }\n" +
            "\n" +
            "]";
//    public static final String JSON_TEXTVIEW = "{\"Fields\":{\"Attribute Type\":\"EditText\",\"Text Box Size\":\"25\",\"Caption Font\":\"Ariel\",\"Caption Size\":\"30\",\"Caption Color\":\"Blue\",\"ellipsize\":\"start\"}}";
//    public static final String JSON_RADIOBUTTON = "{\"Fields\":{\"Attribute Type\":\"RadioButton\",\"Text Box Size\":\"30\",\"Caption Font\":\"Ariel\",\"Caption Size\":\"20\",\"Caption Color\":\"Red\",\"Label\":\"text\"}}";
//    public static final String JSON_BUTTON = "{\"Fields\":{\"Attribute Type\":\"Button\",\"Text Box Size\":\"25\",\"Caption Font\":\"Roman\",\"Caption Size\":\"20\",\"Caption Color\":\"Blue\",\"\":\"\"}}";


    public DynamicRAWjsonActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_dynamic_rawjson, container, false);

        tvData = v.findViewById(R.id.textView);
        v.findViewById(R.id.createTextview).setOnClickListener(this);
        v.findViewById(R.id.radioButton).setOnClickListener(this);
        v.findViewById(R.id.button).setOnClickListener(this);
        loadRawJson();


        return v;
    }

    public void loadRawJson() {

        Resources rsFile = getResources();
        InputStream inputStreamFromjason = rsFile.openRawResource(R.raw.jsoncreated);
        Scanner sc = new Scanner(inputStreamFromjason);

        StringBuilder sBuilder = new StringBuilder();
        while (sc.hasNext()) {
            sBuilder.append(sc.nextLine());
        }

        parseFeed(sBuilder.toString());


    }

    public void parseFeed(String content) {

        Testpojo list;
        JSONObject obj1;
        StringBuilder sBuilder = new StringBuilder();
        try {

            JSONArray ar = new JSONArray(content);
            List<Testpojo> movieList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);
                sBuilder.append("id : ").append(obj.getString("id")).append("\n");
                sBuilder.append("Name : ").append(obj.getString("Name")).append("\n");

                obj1 = obj.getJSONObject("Fields");
                sBuilder.append("Attribute Type : ").append(obj1.getString("Attribute Type")).append("\n");
                sBuilder.append("TextBox Size : ").append(obj1.getString("Text Box Size")).append("\n");
                sBuilder.append("Captionfont : ").append(obj1.getString("Caption Font")).append("\n");

                sBuilder.append("\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
        //      lm.setOrientation(LinearLayout.HORIZONTAL);


        tvData.setText(sBuilder.toString());
        tvData.setMovementMethod(new ScrollingMovementMethod());

//        final LinearLayout lm = (LinearLayout) getView().findViewById(R.id.linearLayout1);
//        EditText product = new EditText(getContext());
//        lm.setOrientation(LinearLayout.VERTICAL);
//
//        for (Testpojo jt :td
//             ) {
//            product.setText(jt.getName());
//
//        }
//
//       lm.addView(product);



    }

    public List<Testpojo> parseJson(String content) {

        Testpojo list;

        try {

            JSONArray ar = new JSONArray(content);
            List<Testpojo> movieList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {
                list = new Testpojo();

                JSONObject obj = ar.getJSONObject(i);
                JSONObject ohb = obj.getJSONObject("Field");
                list.setTextbox(obj.getString("Attribute Type"));
                list.setTextsize(obj.getString("Text Box Size"));
                list.setText(obj.getString("Text"));
//                list.setTextsize(obj.getString("Text Box Size"));

                movieList.add(list);

            }
            return movieList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public void onClick(View view) {
        Testpojo list;
        RadioButton rb;
        String iNamed;
        StringBuilder sBuilder = new StringBuilder();
        final LinearLayout lm = (LinearLayout) getView().findViewById(R.id.linearLayout1);


        int id = view.getId();
        switch (id) {
            case R.id.createTextview:

//                tdShow = new Testpojo();
//                EditText product = new EditText(getContext());
//                lm.setOrientation(LinearLayout.VERTICAL);
//                product.setText(tdShow.getTextbox());
//                product.setGravity(Gravity.LEFT);
//
//               lm.addView(product);

                EditText product = new EditText(getContext());
                lm.setOrientation(LinearLayout.VERTICAL);
              //  lm.removeView(view);
                try {
                    JSONArray ja = new JSONArray(RAW_JSON);

   //                 for (int i=0;i<ja.length();i++){
                    JSONObject emp = ja.getJSONObject(0);

                    JSONObject oj = emp.getJSONObject("Fields");
                           String empname = oj.getString("Attribute Type");
                           String textviewwidth = oj.getString("Text Box Size");
                           String textfont = oj.getString("Caption Font");
//                           String textSize = oj.getString("Caption Size");
//                           String textColor = oj.getString("Caption Color");
//                           String textStart = oj.getString("ellipsize");
                           String str = empname;

                           product.setText(str);
                           product.setWidth(Integer.parseInt(textviewwidth));
//                           product.setTextSize(Float.parseFloat(textSize));
                           product.setGravity(Gravity.LEFT);
                           lm.addView(product);
     //                 }
                    } catch (JSONException e) {
                    e.printStackTrace();
                }       Toast.makeText(getContext(), "Clicke", Toast.LENGTH_SHORT).show();


                break;
            case R.id.radioButton:
//                tdShow = new Testpojo();
                RadioButton productrd = new RadioButton(getContext());
//
                lm.setOrientation(LinearLayout.HORIZONTAL);
//                productrd.setText(tdShow.getTextbox());
//                productrd.setText(tdShow.getText());
//                lm.addView(productrd);
//                try {
//                    JSONObject emp = (new JSONObject(JSON_RADIOBUTTON)).getJSONObject("Fields");
//                    String empname = emp.getString("Attribute Type");
//                    String textviewwidth = emp.getString("Text Box Size");
//                    String textfont = emp.getString("Caption Font");
//                    String textSize = emp.getString("Caption Size");
//
//                    String str = empname;
//                    productrd.setText(str);
//                    productrd.setTextSize(Float.parseFloat(textSize));
//                    lm.addView(productrd);
//
//                    Toast.makeText(getContext(), "Clicke", Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                Toast.makeText(getContext(), "Clicke", Toast.LENGTH_SHORT).show();
                try {
                    JSONArray ja = new JSONArray(RAW_JSON);

                    //                 for (int i=0;i<ja.length();i++){
                    JSONObject emp = ja.getJSONObject(2);

                    JSONObject oj = emp.getJSONObject("Fields");
                    String empname = oj.getString("Attribute Type");
                    String textviewwidth = oj.getString("Text Box Size");
                    String textfont = oj.getString("Caption Font");
//                           String textSize = oj.getString("Caption Size");
//                           String textColor = oj.getString("Caption Color");
//                           String textStart = oj.getString("ellipsize");
                    String str = empname;

                    productrd.setText(str);
  //                  product.setWidth(Integer.parseInt(textviewwidth));
//                           product.setTextSize(Float.parseFloat(textSize));
                    productrd.setGravity(Gravity.LEFT);
                    lm.addView(productrd);
                    //                 }
                } catch (JSONException e) {
                    e.printStackTrace();
                }       Toast.makeText(getContext(), "Clicke", Toast.LENGTH_SHORT).show();

                break;

            case R.id.button:
                Button productbt = new Button(getContext());
//
                lm.setOrientation(LinearLayout.HORIZONTAL);
//
                try {
                    JSONArray ja = new JSONArray(RAW_JSON);

                    //                 for (int i=0;i<ja.length();i++){
                    JSONObject emp = ja.getJSONObject(3);

                    JSONObject oj = emp.getJSONObject("Fields");
                    String empname = oj.getString("Attribute Type");
                    String textviewwidth = oj.getString("Text Box Size");
                    String textfont = oj.getString("Caption Font");
//                           String textSize = oj.getString("Caption Size");
//                           String textColor = oj.getString("Caption Color");
//                           String textStart = oj.getString("ellipsize");
                    String str = empname;

                    productbt.setText(str);
                    //                  product.setWidth(Integer.parseInt(textviewwidth));
//                           product.setTextSize(Float.parseFloat(textSize));
                    productbt.setGravity(Gravity.CENTER);
                    lm.addView(productbt);
                    //                 }
                } catch (JSONException e) {
                    e.printStackTrace();
                }       Toast.makeText(getContext(), "Clicke", Toast.LENGTH_SHORT).show();

                break;
//            case R.id.spinner:
//
//                lm.setOrientation(LinearLayout.VERTICAL);
//                Spinner productSpin = new Spinner(getContext());
//                try {
//                    JSONObject emp = (new JSONObject(JSON_TEXTVIEW)).getJSONObject("Fields");
//                    String empname = emp.getString("Attribute Type");
//                    String textviewwidth = emp.getString("Text Box Size");
//                    String textfont = emp.getString("Caption Font");
//                    String textSize = emp.getString("Caption Size");
//                    String textColor = emp.getString("Caption Color");
//                    String textStart = emp.getString("ellipsize");
//                    String str = empname;
//Resources rs = getResources();
//           String data = rs.getResourceName(R.array.country_arrays);
//                    ArrayAdapter<String> dataAdapter;
//                    dataAdapter = new ArrayAdapter<String>(getActivity(),
//                            android.R.layout.simple_spinner_item, data);
//                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);                    productSpin.setAdapter();
//                    productSpin.setGravity(Gravity.LEFT);
//                    lm.addView(productSpin);
//
//                    Toast.makeText(getContext(), "Clicke", Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;


        }


    }
}
