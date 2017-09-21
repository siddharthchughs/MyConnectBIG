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
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import java.util.List;

import tecnest.manage.myconnectbig.HttpDocs.HttpManger;
import tecnest.manage.myconnectbig.HttpDocs.Hyperlinks;
import tecnest.manage.myconnectbig.Pojo.ListCompniesop;


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

public class ListOfCompaniesActivityFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private ListView forecastList;

    int position = 0;
    private TextView vCount;
    int count;
    private View rootView;
    private ProgressBar bar;
    private final String STATE_MOVIES = "movie_list";
    private TextView movieData;
    public List<ListCompniesop> companiesTypes;
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
    TextView td;
    private Spinner spinDash;
    ListView lv;
    private ProgressBar pdDashboard;
    Gson gs = new Gson();
    boolean connected = false;
    private DashboardActivity mActivity;
    //    private AppSession mSession;
    Parcelable state;
    Intent in;
    TextView categoryName=null;
    String categoryid=null;
    String categoryname=null;
    public ListOfCompaniesActivityFragment() {

        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_list_of_companies, container, false);


        categoryName = rootView.findViewById(R.id.category_name);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.companyDashboard);
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
//        td.setMovementMethod(new ScrollingMovementMethod());

        in = getActivity().getIntent();
        SharedPreferences shareAccountInformation = getContext().getSharedPreferences("State", Context.MODE_PRIVATE);
        String name = shareAccountInformation.getString("category_name", "No value found").toString();

        categoryName.setText(name);
        grid = new ArrayList<>();
        Display();

        return rootView;

    }




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
            if (companiesTypes != null) {
                medicalAdapter = new MedicalAdapter(getActivity(),companiesTypes);

                mRecyclerView.setAdapter(medicalAdapter);
                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setHasFixedSize(true);
//                for (ListCompniesop dg:companiesTypes) {
//                    td.append(dg.getClient_name());
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
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setRefreshing(true);
                        Display();
                    }
                }, 1000);
            }
        });

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
//
//
//        }

//        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeView.setRefreshing(true);
//                (new Handler()).postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeView.setRefreshing(false);
//                        Display();
//                    }
//                }, 2000);
//            }
//        });
//
    }


    public void Display() {

        if (isOnline()) {

            SharedPreferences shareAccountInformation = getContext().getSharedPreferences("State", Context.MODE_PRIVATE);
            String id = shareAccountInformation.getString("category_id", "No value found").toString();

//            in = getActivity().getIntent();
            categoryid = id;

            if (categoryid != null) {

                requestData(Hyperlinks.COMPANYSELECTE_AS_PER_CATEGORY + categoryid);

            } else {
                Toast.makeText(getContext(),"Their is not id ",Toast.LENGTH_SHORT).show();
            }
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

    @Override
    public void onRefresh() {
        try {
            Thread.sleep(1000);
            Display();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
            ListCompniesop[] ds = gs.fromJson(content, ListCompniesop[].class);
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

                    companiesTypes = parseFeed(s);
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

        public List<ListCompniesop> parseFeed(String content) {

            ListCompniesop list;

            try {

                JSONArray ar = new JSONArray(content);
                List<ListCompniesop> movieList = new ArrayList<>();

                for (int i = 0; i < ar.length(); i++) {
                    JSONObject obj = ar.getJSONObject(i);

//                    String client_id = obj.getString("CL_ID");
                    String client_name = obj.getString("Name");
                    String client_companyname = obj.getString("CompanyName");
                    String client_mobilenumber = obj.getString("MobileNumber");

                    list = new ListCompniesop(client_name,client_companyname,client_mobilenumber);

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
        List<ListCompniesop> ls;
        Intent d_Intent;
        Context mcontext;
        ListCompniesop category_Position;
        String id = null;
        String title = null;
        String clientName=null;
        String clientNumber=null;
        private final String CATEGORY_PREFERENCE = "UserCategory";
        int row_index=0;
        public MyHolder holder;

        public MedicalAdapter(Context con, List<ListCompniesop> hs) {
            this.mcontext = con;
            this.ls = hs;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int i) {
            inflater = LayoutInflater.from(mcontext);
            View vw = inflater.inflate(R.layout.listcompanies, parent, false);
            MyHolder holder = new MyHolder(vw);

            return holder;
        }

        @Override
        public void onBindViewHolder(final MyHolder myHolder, final int position) {

            final ListCompniesop categoryPosition;

            category_Position = ls.get(position);

            row_index = position;
            myHolder.categoryTitle.setText(category_Position.getClient_company());
            myHolder.btn_Detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    int itemPosition = mRecyclerView.getChildLayoutPosition(view);
                    category_Position = companiesTypes.get(position);

                    id = category_Position.getId();
                    title =category_Position.getClient_company();
                    clientName = category_Position.getClient_name();
                    clientNumber = category_Position.getClient_mobilenumber();

//                    Toast.makeText(getContext(),"the state"+category_Position.getClient_company(),Toast.LENGTH_SHORT).show();

                    SharedPreferences savenb = getContext().getSharedPreferences("Detail",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editNtb = savenb.edit();
                    editNtb.putString("companyname", title);
                    editNtb.putString("clientname", clientName);
                    editNtb.putString("clientnumber", clientNumber);

                    editNtb.commit();
                    Intent d_Intent = new Intent(getContext(), DetailActivity.class);
                    d_Intent.putExtra("companyname",title);
                    d_Intent.putExtra("clientname",clientName);
                    d_Intent.putExtra("clientnumber",clientNumber);

                    startActivity(d_Intent);

                }
            });

//            myHolder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent=null;
//                    row_index=position;
//                    notifyDataSetChanged();
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

        public class MyHolder extends RecyclerView.ViewHolder {

            TextView categoryTitle;
            ImageView im;
            Button btn_Detail;
            View mView;

            public MyHolder(View itemView) {
                super(itemView);


                categoryTitle = (TextView) itemView.findViewById(R.id.companyName);
                btn_Detail = itemView.findViewById(R.id.deatilbutton);
                // mView = itemView;
            }

            //  @Override
            //  public void onClick(View view) {
//                int itemPosition = mRecyclerView.getChildLayoutPosition(view);
//                category_Position = companiesTypes.get(itemPosition);
//
//                id = category_Position.getId();
//                title =category_Position.getClient_company();
//                SharedPreferences savenb = getContext().getSharedPreferences("Deatil",Context.MODE_PRIVATE);
//                SharedPreferences.Editor editNtb = savenb.edit();
//                editNtb.putString("id", id);
//                editNtb.commit();
//                Intent d_Intent = new Intent(getContext(), DetailActivity.class);
//                d_Intent.putExtra("companyname",title);
//                startActivity(d_Intent);

//            }
        }

    }


}



