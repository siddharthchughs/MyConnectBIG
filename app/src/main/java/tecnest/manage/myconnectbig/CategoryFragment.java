package tecnest.manage.myconnectbig;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

import android.os.Handler;
import java.util.List;

import tecnest.manage.myconnectbig.HttpDocs.HttpManger;
import tecnest.manage.myconnectbig.Pojo.Categoryop;


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

public class CategoryFragment extends Fragment {

    private ListView forecastList;

    int position = 0;
    private TextView vCount;
    int count;
    private View rootView;
    private ProgressBar bar;
    private final String STATE_MOVIES = "movie_list";
    private TextView movieData;
    public List<Categoryop> categoryTypes;
    private Categoryop home_Items;
    private List<MoviewGrid> grid;
    private Toolbar tb;
    boolean mDualPane;
    private Spinner choose;
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
    // TextView td;
    private Spinner spinDash;
    ListView lv;
    private ProgressBar pdDashboard;
    Gson gs = new Gson();
    boolean connected = false;
    private DashboardActivity mActivity;
    private SharedPreferences preference=null;
    //    private AppSession mSession;
    Parcelable state;

    public CategoryFragment() {

        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_category, container, false);




        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.categoryDashboard);
//        pdDashboard = (ProgressBar) rootView.findViewById(R.id.progressDashboard);
//        mSession = AppSession.getInstance(getActivity());

        swipeView = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        swipeView.setColorScheme(R.color.colorAccent,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_green_light);
//        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//
//
//
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//
//
//            }
//        });

        //  pdDashboard.setVisibility(View.INVISIBLE);
        grid = new ArrayList<>();
        Display();

        return rootView;

    }


//    public static List<Dashboard> getData() {
//
//        List<Dashboard> ls = new ArrayList<>();
//        String[] lk = {"Clients", "Update Stock", "Provide Stock", "Payment Details"};
//        //    int[] in = {R.drawable.acmln,R.drawable.upstn,R.drawable.stockpn,R.drawable.paydn };
//        //int[] img = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher };
//
//        for (int i = 0; i < lk.length; ++i) {
//            Dashboard info = new Dashboard();
//            info.name = lk[i];
//            //      info.img = in[i];
//            ls.add(info);
//        }
//        return ls;
//    }


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
            if (categoryTypes != null) {
                medicalAdapter = new MedicalAdapter(getActivity(),categoryTypes);

                mRecyclerView.setAdapter(medicalAdapter);
                mLayoutManager = new GridLayoutManager(getActivity(),2);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setHasFixedSize(true);
//                for (Dashboard dg:mdeicalType
//                     ) {
//                    td.append(dg.getName());
//
//                }

            } else {
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

        //      MenuItem it = menu.findItem(R.id.action_settings);

//        getActivity().getMenuInflater().inflate(R.menu.submenu, it.getSubMenu());



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
//                startActivity(new Intent(getContext(), SettingsActivity.class));

                break;


        }


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
//        SharedPreferences pref_Movie_Selected = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        String movieType = pref_Movie_Selected.getString(
//                getString(R.string.movie_key_value),
//                getString(R.string.highest));
//        Log.v("The type ", "Sorted :" + movieType);
//
//        if (movieType.equals(getString(R.string.popular))) {
//
//            requestData(Hyperlinks.CATEGORY_URL);
//            if (state != null) {
//                mRecyclerView.requestFocus();
//                //      mRecyclerView.onRestoreInstanceState(state);
//            }
//
//        } else if (!movieType.equals(R.string.highest)) {
//
//            requestData(Hyperlinks.CATEGORY_ALPABETICORDER);
//            if (state != null) {
//                mRecyclerView.requestFocus();
//                //    gridView.onRestoreInstanceState(state);
//            }


//        }

        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setRefreshing(false);
                        Display();
                    }
                }, 1000);
            }
        });

    }


    public void Display() {

        if (isOnline()) {

            requestData("http://nearesthospitals.in/Categories_SelectAlphabetic.php");

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

                    Display();
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
            Categoryop[] ds = gs.fromJson(content, Categoryop[].class);
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

                    categoryTypes = parseFeed(s);
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

        public List<Categoryop> parseFeed(String content) {

            Categoryop list;

            try {

                JSONArray ar = new JSONArray(content);
                List<Categoryop> movieList = new ArrayList<>();

                for (int i = 0; i < ar.length(); i++) {
                    JSONObject obj = ar.getJSONObject(i);

                    String category_id = obj.getString("C_ID");
                    String category_name = obj.getString("C_Name");

                    list = new Categoryop(category_id,category_name);

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

        private LayoutInflater inflater;
        List<Categoryop> ls;
        Intent nextIntent;
        Context mcontext;
        Categoryop category_Position;
        String id = null;
        String title = null;
        private final String CATEGORY_PREFERENCE = "UserCategory";
        int row_index=0;
        public MyHolder holder;

        public MedicalAdapter(Context con, List<Categoryop> hs) {
            this.mcontext = con;
            this.ls = hs;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int i) {
            inflater = LayoutInflater.from(mcontext);
            View vw = inflater.inflate(R.layout.category_items, parent, false);
            MyHolder holder = new MyHolder(vw);

            return holder;
        }

        @Override
        public void onBindViewHolder(final MyHolder myHolder, final int position) {

            category_Position = ls.get(position);

//            row_index = position;
            myHolder.categoryTitle.setText(category_Position.getCategory_name());


//            myHolder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String name = category_Position.getCategory_name();
//                    preference = getActivity().getSharedPreferences("State",Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editNtb = preference.edit();
//                    editNtb.putString("name", name);
//                    editNtb.commit();
//
////                    Intent intent = null;
//  //                  category_Position = ls.get(position);
//                    String id = category_Position.getCategory_id();
//    //                    row_index = position;
//      //              notifyDataSetChanged();
//
////                    intent  = new Intent(getContext(),ListOfCompanies.class);
////                    intent.putExtra("category_name",name);
////                    intent.putExtra("category_id",id);
////                    startActivity(intent);
//Toast.makeText(getContext(),"the state"+name,Toast.LENGTH_SHORT).show();
//
//                }
//            });

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

        public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            TextView categoryTitle;
            ImageView im;
            View mView;

            public MyHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);

                categoryTitle = (TextView) itemView.findViewById(R.id.categoryName);
                //   mView = itemView;
            }

            @Override
            public void onClick(View view) {
                int itemPosition = mRecyclerView.getChildLayoutPosition(view);
                home_Items = categoryTypes.get(itemPosition);

                id = home_Items.getCategory_id();
                title = home_Items.getCategory_name();
                SharedPreferences savenb = getContext().getSharedPreferences("State",Context.MODE_PRIVATE);
                SharedPreferences.Editor editNtb = savenb.edit();
                editNtb.putString("category_id", id);
                editNtb.putString("category_name", title);
                editNtb.commit();
                nextIntent  = new Intent(getContext(),ListOfCompaniesActivity.class);
                nextIntent.putExtra("category_name",title);
                nextIntent.putExtra("category_id",id);
                startActivity(nextIntent);
//   Toast.makeText(getContext(),"the state"+id+title,Toast.LENGTH_SHORT).show();


            }
        }

    }


}



