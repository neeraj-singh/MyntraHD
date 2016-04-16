package com.neerajsingh.myntrahd;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.neerajsingh.myntrahd.network.BaseRequestInterface;
import com.neerajsingh.myntrahd.network.response.BidBasket;
import com.neerajsingh.myntrahd.network.response.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anshulika.ks on 17/04/16.
 */
public class DummyNetworkActivity extends Activity{
    private static final String TAG = DummyNetworkActivity.class.getSimpleName();
    private String uniqueId = null;
    public BaseRequestInterface baseRequestInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy_activity_file);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        MyntraHDApplication.initializeRetrofit(MyntraHDApplication.BASE_URL);
        baseRequestInterface =
                MyntraHDApplication.getRetrofit().create(BaseRequestInterface.class);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchAllBiddableproduct(getUniqueId());
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeBidForProductRequest(getUniqueId(), "2", "345");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllBiddedProductsForUId(getUniqueId());
            }
        });
    }

    private void fetchAllBiddableproduct(String uniqueId) {
        Call<List<Product>> productList = baseRequestInterface.getListOfAllBiddableProducts(uniqueId);
        productList.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d(TAG, "onResponse  isSuccessful : " + response.isSuccessful() + " response " + response.body().toString());
                Toast.makeText(DummyNetworkActivity.this, "Response is [" + response.body().toString() + "]", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure  : " + call.toString());
            }
        });
    }

    private void makeBidForProductRequest(String uniqueId, String productId, String bidAmount) {
        Call<Boolean> addBidForProduct = baseRequestInterface.addBidForPid(getUniqueId(), productId, bidAmount);
        addBidForProduct.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d(TAG, "onResponse  isSuccessful : " + response.isSuccessful() + " response " + response.body().toString());
                Toast.makeText(DummyNetworkActivity.this, "Response is [" + response.body().toString() + "]", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d(TAG, "onFailure  : " + call.toString());
            }
        });
    }

    public void getAllBiddedProductsForUId(String uniqueId){
        Call<List<BidBasket>> bidBasketCall = baseRequestInterface.getAllBiddedProductsWithStatus(uniqueId);
        bidBasketCall.enqueue(new Callback<List<BidBasket>>() {
            @Override
            public void onResponse(Call<List<BidBasket>> call, Response<List<BidBasket>> response) {
                Log.d(TAG, "onResponse  isSuccessful : " + response.isSuccessful() + " response " + response.body().toString());
                Toast.makeText(DummyNetworkActivity.this, "Response is [" + response.body().toString() + "]", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<BidBasket>> call, Throwable t) {
                Log.d(TAG, "onFailure  : " + call.toString());
            }
        });

    }
    public String getUniqueId() {

        if(uniqueId == null || uniqueId.length() == 0)
            uniqueId  = AccountUtils.getAccontId(DummyNetworkActivity.this);
        return uniqueId;

    }
}
