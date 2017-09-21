package tecnest.manage.myconnectbig;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tecnest.manage.myconnectbig.HttpDocs.HttpManger;
import tecnest.manage.myconnectbig.HttpDocs.Hyperlinks;
import tecnest.manage.myconnectbig.Pojo.Dashboard;


//import okhttp3.Call;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

/**
 * A placeholder fragment containing a simple view.
 */

public class DashboardActivityFragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemSelectedListener {

    private static final String STATE_LIST ="statES" ;
    private ListView forecastList;

//    View v;
    int position = 0;
    private TextView vCount;
    int count;
    private View rootView;
    private ProgressBar bar;
    private final String STATE_MOVIES = "movie_list";
    private TextView movieData;
    public List<Dashboard> mdeicalType;
    private Dashboard home_Items;
    private List<MoviewGrid> grid;
    boolean mDualPane;
    int mCurCheckPosition = 0;
    private MedicalAdapter medicalAdapter;
    private Button bt_Location;
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    private final int MINIMUM_SESSION = 5000;
    private static final String MEDICAL_SERVICES = "MedicalCategory";
    private final String CATEGORY_PREFERENCE = "UserCategory";

    private int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;
    private LinearLayout ll_Manage;
    private TextView txt_Refillmanage;
    protected RecyclerView.LayoutManager mLayoutManager;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeView;
    RecyclerView mRecyclerView;
    private ProgressBar pdDashboard;
    String[] lk = {"Clients", "Update Stock", "Provide Stock", "Payment Details"};
    Gson gs = new Gson();
    boolean connected = false;
    private DashboardActivity mActivity;
    //    private AppSession mSession;
    private SharedPreferences preference;
    private Button btEnllist;
    Toolbar tb;
    Bundle vd;
    ArrayList<Dashboard> arList = new ArrayList<Dashboard>();
    String item=null;
    Spinner vSpiner;
    Dashboard gd;
    Intent in;
    String data = null;
    SharedPreferences choose;
    public DashboardActivityFragment() {

        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.statesDashboard);
//        pdDashboard = (ProgressBar) rootView.findViewById(R.id.progressDashboard);
//        mSession = AppSession.getInstance(getActivity());
        //  rootView.findViewById(R.id.Send).setOnClickListener(btCliked);
        vSpiner = rootView.findViewById(R.id.spinnerState);
//      tb = rootView.findViewById(R.id.toolbar);
//       AppCompatActivity ac = new AppCompatActivity();
        // toolbar.findViewById(R.id.spinnerState).setOnClickListener();
        List<String> categories = new ArrayList<String>();
        categories.add("ALL");
        categories.add("UT");
//        Resources rs = getResources();
//        String select = String.valueOf(rs.getStringArray(R.array.Select));
        vSpiner.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        grid = new ArrayList<>();

        if(savedInstanceState!=null){



            arList= savedInstanceState.getParcelableArrayList(STATE_MOVIES);
            medicalAdapter.setDashboardItems(arList);
            Log.i("The saved data","shown"+arList);

        }
                    Display(item);

//        else
//        {
//            Log.i("The data created again","shown");
//
//        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        vSpiner.setAdapter(dataAdapter);



        swipeView = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        swipeView.setColorScheme(R.color.colorAccent,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_green_light);
        //  pdDashboard.setVisibility(View.INVISIBLE);

        return rootView;

    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//
//    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        item = adapterView.getItemAtPosition(i).toString();

        data = item;
//        choose = getActivity().getSharedPreferences("Choose",Context.MODE_PRIVATE);
//        SharedPreferences.Editor editNtb = choose.edit();
//        editNtb.putString("name", data);
//        editNtb.commit();

        // Showing selected spinner item
        Display(item);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_MOVIES, arList);
    }
    private void onRestoreInstanceState(Bundle savedInstanceState){
//        if(savedInstanceState!=null){
//            String SomeText = savedInstanceState.getString("title");
            ///if (selectedPosition != RecyclerView.NO_POSITION) {
//                home_Items = gd.get(position);
//          //      Toast.makeText(myHolder.mView.getContext(), "You clicked " + dh.getName(), Toast.LENGTH_SHORT).show();
//                medicalAdapter = new MedicalAdapter(getActivity(), mdeicalType);
//
//                mRecyclerView.setAdapter(medicalAdapter);
//                mLayoutManager = new LinearLayoutManager(getActivity());
//                mRecyclerView.setLayoutManager(mLayoutManager);
//                mRecyclerView.setHasFixedSize(true);
//                mLayoutManager.scrollToPosition(se);

         //   }
        //}

    }




    public interface Callback {
        public void onSpinnerClicked(View radiobtn);
    }

    View.OnClickListener vSpinner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.enlist:
                    // User chose the "Settings" item, show the app settings UI...
                    Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();

                    break;

                default:
                    // If we got here, the user's action was not recognized.
                    // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(view);
                    Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    break;

            }
//            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();


        }
    };


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        if(savedInstanceState==null){
//
//
//        }
//        else {
//            Display();
//
//        }




//        fbAnalytics = FirebaseAnalytics.getInstance(getContext());
//        fbAnalytics.setMinimumSessionDuration(MINIMUM_SESSION);


    }



    protected void updated() {

        try{
            if (mdeicalType != null) {
                medicalAdapter = new MedicalAdapter(getActivity(), mdeicalType);

                mRecyclerView.setAdapter(medicalAdapter);
                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setHasFixedSize(true);
//                mRecyclerView.addItemDecoration(new RecyclerViewItemDecoration(getActivity(),getResources().getDrawable(R.drawable.line)));
//                    }
//
//                    @Override
//                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                        super.onScrolled(recyclerView, dx, dy);
//                    }
//                });
//            } else {
            }


        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(), "No internet available ! ", Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_dashboard, menu);





    }


//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.getParcelableArrayList(STATE_LIST, arList);
////vd.getParcelableArrayList(STATE_LIST,"arList");
//        super.onSaveInstanceState(outState);
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


//        int id = item.getItemId();
 //item = menu.add(Menu.NONE,Menu.NONE,103,"More");
//        switch (id) {
//            case R.id.action_settings:
////                final View addView = getLayoutInflater().inflate(R.layout.add, null);
////
////                PopupMenu mp = new PopupMenu(getContext(), menuItemView);
////
////                mp.getMenuInflater().inflate(R.menu.pop_menu, mp.getMenu());
////                mp.show();
//
//                break;
//        }

        View menuItemView = getActivity().findViewById(R.id.action_settings); // SAME ID AS MENU ID
        PopupMenu popupMenu = new PopupMenu(getContext(), menuItemView);
        popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());         //       View menuItemView = getView().findViewById(R.id.action_popup); // SAME ID AS MENU ID
        popupMenu.show();


//        switch (id) {
//            case R.id.action_create:
//                startActivity(new Intent(getContext(), CreateActivity.class));
//
//                break;
//
//            case R.id.action_find:
//                startActivity(new Intent(getContext(), ManagementSystem.class));
//                break;
//
//            case R.id.action_logout:
//                showLogOutAlert();
//                break;
//
////            case R.id.adminPanel:
////               // showLogOutAlert();
////               startActivity(new Intent(getContext(),AdminPanelActivity.class));
////                break;

//        }


        return super.onOptionsItemSelected(item);
    }

//    public void showLogOutAlert() {
//        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
//        alert.setCancelable(false);
//        alert.setIcon(R.mipmap.info_launcher);
//        alert.setTitle(R.string.confirm);
//        alert.setMessage(R.string.areyousure);
//        alert.setPositiveButton(getResources().getString(R.string.userlogout),
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mSession.clear();
//                        Intent intent = new Intent(getContext(), LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                        //getActivity().finish();
//                    }
//                });
//
//        alert.setNegativeButton(getResources().getString(R.string.cancel), null);
//        alert.show();
//    }


    @Override
    public void onResume() {
        super.onResume();
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setRefreshing(false);
                        Display(item);
                    }
                }, 1000);
            }
        });

    }


    public void Display(String item) {

        if (isOnline()) {

            data =    item;
//            SharedPreferences shareAccountInformation = getContext().getSharedPreferences("Choose", Context.MODE_PRIVATE);
//            String name = shareAccountInformation.getString("name", "No value found").toString();

            requestData(Hyperlinks.DASHBOARD_URL+data);

        }
    }

    private void requestData(String url) {

        MoviewGrid mg = new MoviewGrid();
        mg.execute(url);

    }


    protected boolean isOnline() {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            Toast.makeText(getActivity(), "Connected to Wifi  !", Toast.LENGTH_SHORT).show();

//            if (netInfo.getType() != ConnectivityManager.TYPE_MOBILE) {
//                Toast.makeText(getActivity(), "Connected to Mobile  Data !", Toast.LENGTH_SHORT).show();
//
//            }

            return true;
        }
        else {
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

                    Display(item);
                }
            });

        }
//        try {
//            @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//
//            cm = (ConnectivityManager) getContext()
//                    .getSystemService(Context.CONNECTIVITY_SERVICE);
//
//            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
//            connected = networkInfo != null && networkInfo.isAvailable() &&
//                    networkInfo.isConnected();
//
//
//    }catch (Exception e){
//            e.printStackTrace();
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
//            alertDialogBuilder.setMessage("Your are not connected to the internet, try again later !");
//
//            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                @Override
//
//                public void onClick(DialogInterface arg0, int arg1) {
//                    getActivity().finish();
//                }
//            });
//            alertDialogBuilder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    Display();
//                }
//            });


//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
//
//        }
        return false;

    }

    @Override
    public void onRefresh() {

        try {
            Thread.sleep(1000);
            Display(item);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

//    View.OnClickListener btCliked = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.Send:
//                    // TODO Auto-generated method stub
//                    Intent in = new Intent(getContext(),EnlistActivity.class);
//                    startActivity(in);
//
//                    break;
//                default:
//                    // TODO Auto-generated method stub
//                    break;
//            }
//
//        }
//    };



    public class MoviewGrid extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (grid.size() == 0) {

//                pdDashboard.setVisibility(View.VISIBLE);
            }
            grid.add(this);


        }

        @Override
        protected String doInBackground(String... params) {

            String content = HttpManger.getData(params[0]);

            Gson gs = new Gson();
            Dashboard[] ds = gs.fromJson(content,Dashboard[].class);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                grid.remove(this);
                if (grid.size() == 0) {
//                    pdDashboard.setVisibility(View.INVISIBLE);
                }

                if (s != null) {

                    mdeicalType = parseFeed(s);
                    updated();
// run();
                } else {
                    Toast.makeText(getActivity(), "No data available  !", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


//        void run() throws IOException {
//
//            OkHttpClient client = new OkHttpClient();
//
//            Request request = new Request.Builder()
//                    .url(URL_HOSPITAL_LINK)
//                    .build();
//
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    call.cancel();
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//
//                    final String myResponse = response.body().string();
//
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
////                            txtString.setText(myResponse);
//                            updated();
//                        }
//                    });
//
//                }
//            });
//        }

        public List<Dashboard> parseFeed(String content) {

            Dashboard list;

            try {

                JSONArray ar = new JSONArray(content);
                List<Dashboard> movieList = new ArrayList<>();

                for (int i = 0; i < ar.length(); i++) {
                    JSONObject obj = ar.getJSONObject(i);
                    String id = obj.getString("SD_ID");
                    String statename= obj.getString("StateName");
                    list = new Dashboard(id,statename);

                    movieList.add(list);

                }
                return movieList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }


        }




    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

//        Display();

    }

    public class MedicalAdapter extends RecyclerView.Adapter<MedicalAdapter.MyHolder> {

        public ArrayList<Dashboard> ad = new ArrayList<>();
        private View.OnClickListener click;
        private LayoutInflater inflater;
        List<Dashboard> ls;
        Dashboard dh;
        Intent d_Intent;
        Context mcontext;
        int selectedPosition=-1;
        String id = null;
        String title = null;
        private final String CATEGORY_PREFERENCE = "UserCategory";
        int row_index=0;
        public MyHolder holder;

        public MedicalAdapter(Context con, List<Dashboard> hs) {
            this.mcontext = con;
            this.ls = hs;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int i) {
            inflater = LayoutInflater.from(mcontext);
            View vw = inflater.inflate(R.layout.statelayout_items, parent, false);
            MyHolder holder = new MyHolder(vw);

            return holder;
        }

        public void setDashboardItems(ArrayList<Dashboard> listDashboard){

            this.ad = listDashboard;
            notifyItemRangeChanged(0,listDashboard.size());


        }

        public void setClick(View.OnClickListener click) {
            this.click = click;
        }

        @Override
        public void onBindViewHolder(final MyHolder myHolder, final int position) {
            final Dashboard current= ls.get(position);
            Intent intent = null;
//            current = ls.get(position);

//            row_index = position;
            myHolder.categoryTitle.setText(current.getName());


            myHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    String name = current.getName();
//                    String id = current.getId();
///                    if(selectedPosition==position)
   //                    myHolder.mView.setBackgroundResource(R.drawable.ic);

//                    selectedPosition=position;
//                    notifyDataSetChanged();
//
//                    if(row_index==position){
//                        myHolder.mView.setBackgroundColor(Color.parseColor("#567845"));
//                        myHolder.categoryTitle.setTextColor(Color.parseColor("#ffffff"));
//                    }
//                    else
//                    {
//                        holder.mView.setBackgroundColor(Color.parseColor("#ffffff"));
//                        holder.categoryTitle.setTextColor(Color.parseColor("#000000"));
//                    }
                    Intent intent = null;

                    preference = getActivity().getSharedPreferences("State",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editNtb = preference.edit();
                    editNtb.putString("name", current.getName());
                    editNtb.commit();


                    intent  = new Intent(getContext(),Category.class);
                    intent.putExtra("name",current.getName());
                    startActivity(intent);
//Toast.makeText(getContext(),"the state"+current.getName(),Toast.LENGTH_SHORT).show();
                //    int pos = getAdapterPosition();

                    // check if item still exists
                    if (selectedPosition != RecyclerView.NO_POSITION) {
                        dh = ls.get(selectedPosition);
                        Toast.makeText(myHolder.mView.getContext(), "You clicked " + dh.getName(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

            //    }


//            myHolder.mView.setOnTouchListener(new View.OnTouchListener() {
//                @Override public boolean onTouch(View v, MotionEvent event) {
//
//                    if (event.getAction()==MotionEvent.ACTION_DOWN){
////                        Resources res = getResources();
////                        Drawable img = res.getDrawable(R.drawable.ripple_dashoboard);
//                        myHolder.mView.setBackgroundColor(Color.DKGRAY);
//                    }if (event.getAction()==MotionEvent.ACTION_UP){
//                        myHolder.mView.setBackgroundColor(Color.WHITE);
//                    }
//
//                    return false;
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return ls.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {

            TextView categoryTitle;
            ImageView im;
            View mView;

            public MyHolder(View itemView) {
                super(itemView);

                categoryTitle = (TextView) itemView.findViewById(R.id.stateName);
                mView = itemView;
            }


        }

    }


    public interface OnClick{
        public void onItemClick(View v);

    }



}



