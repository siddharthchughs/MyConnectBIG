package tecnest.manage.mytaskproject;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.Scanner;

import static android.content.ContentValues.TAG;

/**
 * A placeholder fragment containing a simple view.
 */
public class DynamicRAWjsonActivityFragment extends Fragment implements View.OnClickListener{


    TextView tvData;
    List<Testpojo> td;
  Spinner spin;
    RadioButton rd;
    Testpojo tdShow;
    LinearLayout ll;
  TextView dtCreate;
    public DynamicRAWjsonActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_dynamic_rawjson, container, false);

        tvData = v.findViewById(R.id.textView);
       v.findViewById(R.id.createTextview).setOnClickListener(this);
//       v.findViewById(R.id.spinner);
//        v.findViewById(R.id.radioButton);
        loadRawJson();
//        ScrollView scrl=new ScrollView(getContext());
//        ScrollView scrl=new ScrollView(getContext());
//        ll=new LinearLayout(getContext());
//        ll.setOrientation(LinearLayout.VERTICAL);
//        scrl.addView(ll);
//        Button add_btn=new Button(getContext());
//        add_btn.setText("Click to add TextViiews and EditTexts");
//        ll.addView(add_btn);
//        Button add_btn=new Button(getActivity().findViewById());
//     //  add_btn.
//        add_btn.setText("Click to add TextViiews and EditTexts");
//        ll.addView(add_btn);
//        LinearLayout myll = (LinearLayout)getActivity().findViewById(R.id.linearLayout1);
//        myll.setLayoutParams(LinearLayout.WRAP_CONTENT, LinearLayout.WRAP_CONTENT);
//        myll.setOrientation(LinearLayout.VERTICAL);
//        View view = DynamicRAWjsonActivityFragment.this.getView(); // returns base view of the fragment

  //      if (view != null&&(view instanceof ViewGroup)) {

//        LinearLayout linearLayout = new LinearLayout(getActivity());
//        // Set the layout full width, full height
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        linearLayout.setLayoutParams(params);
//        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//        linearLayout.setGravity(Gravity.CENTER);
//        //or VERTICAL
//        LayoutInflater inflater = this.getLayoutInflater();
//        View currentView = inflater.inflate(R.layout.dynamic,null);
//        TextView textView = (TextView) currentView.findViewById(R.id.textView);

//trying to change the attributes. but #fails!
//        textView.setText("Setting the text from code");
//        textView.setId(3);

//Now you had customize your Textview .Now just add this customize view to your view
//setting to the view
//        parent.addView(currentView);
//        Button button = new Button(getActivity());
//
//
//        button.setText();
//        //For buttons visibility, you must set the layout params in order to give some width and height:
//        ViewGroup.LayoutParams paramsf = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//       // button.setText(tdShow.getTextbox());
//
//       // button.setLayoutParams(paramsf);
//
//         //   button.setText(tdShow.getTextbox());
////          button.setText(tdShow.getName());
//
//
//
//
//        Button button2 = new Button(getActivity());
//        button2.setLayoutParams(params);
//
//        //... and other views
//
//        ViewGroup viewGroup = (ViewGroup) v;
//
//        linearLayout.addView(button);
//        linearLayout.addView(button2);
//
//        viewGroup.addView(linearLayout);

//            ViewGroup viewGroup = (ViewGroup) view;
//            viewGroup.addView(btn);
//        }




        return v;
    }

    public void loadRawJson() {

//       for ( Testpojo tj : td) {
//           Button add_btn = new Button(getActivity().findViewById(Integer.parseInt(tj.getId()));
//           //  add_btn.
//           add_btn.setText("Click to add TextViiews and EditTexts");
//           ll.addView(add_btn);
//       }
//        LinearLayout myll = (LinearLayout)getActivity().findViewById(R.id.linearLayout1);
//        myll.setLayoutParams(LinearLayout.WRAP_CONTENT, LinearLayout.WRAP_CONTENT);
//        myll.setOrientation(LinearLayout.VERTICAL);

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


    }

    public List<Testpojo> parseJson(String content) {

        Testpojo list;

        try {

            JSONArray ar = new JSONArray(content);
            List<Testpojo> movieList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {
                list = new Testpojo();

                JSONObject obj = ar.getJSONObject(i);
                list.setTextbox(obj.getString("id"));
                list.setTextsize(obj.getString("name"));
                JSONObject ohb = obj.getJSONObject("Field");
                list.setTextbox(obj.getString("Attribute Type"));
                list.setTextsize(obj.getString("Text Box Size"));
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
        String iNamed;
        StringBuilder sBuilder = new StringBuilder();
        final LinearLayout lm = (LinearLayout)getView().findViewById(R.id.linearLayout1);

        int id = view.getId();
        switch (id){
            case R.id.createTextview:

                    lm.setOrientation(LinearLayout.HORIZONTAL);

//                for(int i=0;i<4;i++) {

                    TextView product = new TextView(getContext());
                    //     iNamed = td.get(0).getTextsize();
//                Log.i(TAG, "onClick:"+ tdShow.getName());
                    product.setText("TextView");
                    product.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);


                    lm.addView(product);

                    Toast.makeText(getContext(), "Clicke", Toast.LENGTH_SHORT).show();
//                }
                break;

//            case R.id.radioButton:

//                final LinearLayout lm = (LinearLayout)getView().findViewById(R.id.linearLayout1);
//                lm.setOrientation(LinearLayout.HORIZONTAL);
//                RadioGroup productRb = new RadioGroup(getContext());

//            productRb.set


//                lm.addView(productRb);
////
    //            Toast.makeText(getContext(),"Clicke",Toast.LENGTH_SHORT).show();
      //          break;



        }


    }
}
