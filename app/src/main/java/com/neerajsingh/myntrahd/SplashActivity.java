package com.neerajsingh.myntrahd;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.neerajsingh.myntrahd.network.response.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by neeraj.singh on 17/04/16.
 */
public class SplashActivity extends Activity {

    public static ArrayList<Product> itemList;
    ProgressDialog progressDialog;
    private static final String TAG = SplashActivity.class.getSimpleName();
    public static final String PROD_LIST = "prodList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        MyntraHDApplication.initializeRetrofit(MyntraHDApplication.BASE_URL);


        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
                //nothing
            }

            public void onFinish() {
                showLoader();
                fetchAllBiddableproduct(AccountUtils.getAccontId(SplashActivity.this));
            }
        }.start();


    }

    private void showLoader() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("");
        progressDialog.show();
    }

    private void hideLoader(){
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.hide();
        }
    }

    private void fetchAllBiddableproduct(String uniqueId) {
        Call<List<Product>> productList = MyntraHDApplication.getBaseRequestInterface().getListOfAllBiddableProducts(uniqueId);
        productList.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d(TAG, "onResponse  isSuccessful : " + response.isSuccessful() + " response " + response.body().toString());
                ArrayList<Product> productArrayList = (ArrayList<Product>) response.body();
                Intent intent = new Intent(SplashActivity.this,SwipeActivity.class);
//                intent.putExtra(PROD_LIST, productArrayList);
                itemList = productArrayList;
                hideLoader();
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure  : " + call.toString());
                Toast.makeText(SplashActivity.this, "Network failed", Toast.LENGTH_LONG).show();
                hideLoader();
            }
        });
    }
}
