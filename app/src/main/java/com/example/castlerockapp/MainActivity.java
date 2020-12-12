package com.example.castlerockapp;

import android.content.Context;
import android.os.Bundle;

import com.example.castlerockapp.adapter.SignAdapter;
import com.example.castlerockapp.interfaces.ToastListener;
import com.example.castlerockapp.models.Sign;
import com.example.castlerockapp.retrofit.ApiRequest;
import com.example.castlerockapp.retrofit.RetrofitRequest;
import com.example.castlerockapp.view_model.SignViewModel;
import com.example.castlerockapp.view_model.SignViewModelFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LifecycleOwner, ToastListener {

    Context mContext;
    private LifecycleRegistry lifecycleRegistry;
    private static final String TAG = MainActivity.class.getSimpleName();

    private SignViewModel        mSignViewModel;
    private SignViewModelFactory myViewModelFactory;
    private ApiRequest           apiRequest;
    private LinearLayoutManager  mLinearLayoutManager;
    private RecyclerView         mRecyclerView;
    private ProgressBar          mProgressBar;
    private SwipeRefreshLayout   mRefreshLayout;
    private SignAdapter          mSignAdapter;
    private TextView             mToastTv;
    private TextView             mTitle;
    private Toolbar              toolbar;

    private ArrayList<Sign> mSignArrayList = new ArrayList<Sign>();

//    @NonNull
//    @Override
//    public Lifecycle getLifecycle() {
//        return lifecycleRegistry;
//    }

    @Override
    public void showMyCustomAppBarToast() {

        String nullText = "No Display Pages Info!";

        showToast(mToastTv, nullText);

    }

    private void showToast(final TextView toast_tv, String text) {

        toast_tv.setText(text);
        mTitle.setText("");
        mTitle.setVisibility(View.GONE);

        //Create alpha animation
        AlphaAnimation animation1 = new AlphaAnimation(0f, 1f);

        //Set duration
        animation1.setDuration(300);

        //Set that the animation changes persist once the animation finishes
        animation1.setFillAfter(true);

        //Set on AnimationEnd Listener
        animation1.setAnimationListener(new Animation.AnimationListener() {

            @Override public void onAnimationStart(Animation animation){}
            @Override public void onAnimationRepeat(Animation animation){}
            @Override public void onAnimationEnd(Animation animation){
                //After 2250 millis -> hide the toast
                new CountDownTimer(2250, 1) {
                    public void onTick(long millisUntilFinished){}
                    public void onFinish() {hideToast(toast_tv);}
                }.start();
            }

        });

        //Make the view visible
        toast_tv.setVisibility(View.VISIBLE);

        //Start animation
        toast_tv.startAnimation(animation1);

    }

    private void hideToast(final TextView toast_tv){

        //Create alpha animation
        AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);

        //Set duration
        animation1.setDuration(300);

        //Set that the animation changes persist once the animation finishes
        animation1.setFillAfter(true);

        //Set on AnimationEnd Listener
        animation1.setAnimationListener(new Animation.AnimationListener() {

            @Override public void onAnimationStart(Animation animation) { }
            @Override public void onAnimationRepeat(Animation animation) { }
            @Override public void onAnimationEnd(Animation animation) {
                //Make the view gone
                toast_tv.setVisibility(View.GONE);
                mTitle.setText("Castle Rock Test App");
                mTitle.setVisibility(View.VISIBLE);
            }

        });

        //Start animation
        toast_tv.startAnimation(animation1);

    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);

        mContext = MainActivity.this;

        toolbar  = findViewById(R.id.toolbar);
        mTitle   = findViewById(R.id.toolbar_title);
        mToastTv = findViewById(R.id.toast_tv);
        mToastTv.setVisibility(View.GONE);

        setSupportActionBar(toolbar);

        mTitle.setText("Castle Rock Test App");

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        init();

        getCarSigns();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            mSignArrayList.clear();
            mSignAdapter.notifyDataSetChanged();
            getCarSigns();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {

        mProgressBar   = (ProgressBar)        findViewById(R.id.progress_bar_sign);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sr);
        mRecyclerView  = (RecyclerView)       findViewById(R.id.recycler_view_sign);

        myViewModelFactory = new SignViewModelFactory(this.getApplication());

        // View Model
        //mSignViewModel = new ViewModelProvider(this, myViewModelFactory).get(SignViewModel.class);
        //getLifecycle().addObserver(mSignViewModel);

        // linear layout Manager
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        // Adapter
        mSignAdapter = new SignAdapter(MainActivity.this, mSignArrayList, this);
        mRecyclerView.setAdapter(mSignAdapter);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSignArrayList.clear();
                mSignAdapter.notifyDataSetChanged();
                getCarSigns();
            }
        });

    }

    /**
     * get signs from iatg.carsprogram.org api
     *
     * //@param @null
     */
    private void getCarSigns() {

        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);

        Log.i(TAG, "ATTENTION ATTENTION: Data Retrieve Working from MainActivity");

        Call<List<Sign>> call = apiRequest.getCarSigns(); // query, key

        call.enqueue(new Callback<List<Sign>>() {

            @Override
            public void onResponse(Call<List<Sign>> call, Response<List<Sign>> response) {

                Log.d(TAG, "onResponse: " + response);

                if (response.body() != null) {

                    List<Sign> signList = response.body();

                    //data.setValue(response.body());
                    Log.i(TAG, "signs size: "       + signList.size());
                    Log.i(TAG, "signs name pos 0: " + signList.get(0).getName());

                    mProgressBar.setVisibility(View.GONE);

                    Log.i(TAG, "ATTENTION ATTENTION: LiveData Working from MainActivity");

                    //List<Sign> signs = new ArrayList<Sign>(signList); // signList;
                    //List<Sign> signs = listToArrayList(signList);
                    mSignArrayList.addAll(signList);

                    //Collections.sort(mSignArrayList, Collections.reverseOrder());
                    //Collections.sort(mSignArrayList, new SortByLastUpdated());

                    mSignAdapter.notifyDataSetChanged();
                    mRefreshLayout.setRefreshing(false);

                    for (int i = 0; i < signList.size()/20; i++) {

                        String text = "From MainActivity Sign Name " + String.valueOf(i)
                                + " " + signList.get(i);
                        Log.i(TAG, text);

                    }

                }

            }

            @Override
            public void onFailure(Call<List<Sign>> call, Throwable t) {
                //data.setValue(null);
            }

        });


        // LIVEDATA OBSERVE not working
        /*mSignViewModel.getSignResponseList().observe(MainActivity.this,
                (List<Sign> signList) -> {

                    if (signList != null) {

                        mProgressBar.setVisibility(View.GONE);

                        Log.i(TAG, "ATTENTION ATTENTION: LiveData Working from MainActivity");

                        List<Sign> signs = new ArrayList<Sign>(signList); // signList;
                        //List<Sign> signs = listToArrayList(signList);
                        mSignArrayList.addAll(signs);
                        mSignAdapter.notifyDataSetChanged();

                        for (int i = 0; i < signs.size(); i++) {

                            Log.i(TAG, "From MainActivity Sign Name: " + signs.get(i));

                        }

                    }

                });*/

    }

    public static ArrayList<Sign> listToArrayList(List<Sign> myList) {

        ArrayList<Sign> arl = new ArrayList<Sign>();
        for (Sign object : myList) {
            arl.add((Sign) object);
        }
        return arl;

    }

}