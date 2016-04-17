package com.neerajsingh.myntrahd;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.neerajsingh.myntrahd.network.response.BidBasket;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anshulika.ks on 17/04/16.
 */
public class BiddingStatusBoardActivity extends Activity{
    private static final String TAG = BiddingStatusBoardActivity.class.getSimpleName();
    private List<BidBasket> bidBasketList = null;
    private GridView gridView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bidding_status_layout);

        bidBasketList = new ArrayList<>();

        progressDialog = new ProgressDialog(BiddingStatusBoardActivity.this);
        progressDialog.show();
        getAllBiddedProductsForUId(AccountUtils.getAccontId(BiddingStatusBoardActivity.this));
        gridView = (GridView) findViewById(R.id.gridView);

    }

    private void setContentToDisplay(GridView gridView) {
        BidProductsAdapter bidProductsAdapter = new BidProductsAdapter(this,bidBasketList);
        gridView.setAdapter(bidProductsAdapter);
    }

    public void getAllBiddedProductsForUId(String uniqueId){
        Call<List<BidBasket>> bidBasketCall = DummyNetworkActivity.baseRequestInterface.getAllBiddedProductsWithStatus(uniqueId);
        bidBasketCall.enqueue(new Callback<List<BidBasket>>() {
            @Override
            public void onResponse(Call<List<BidBasket>> call, Response<List<BidBasket>> response) {
//                Log.d(TAG, "onResponse  isSuccessful : " + response.isSuccessful() + " response " + response.body().toString());
                Toast.makeText(BiddingStatusBoardActivity.this, "Response is [" + response.body().toString() + "]", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                bidBasketList = (ArrayList<BidBasket>)response.body();
                setContentToDisplay(gridView);
        }

            @Override
            public void onFailure(Call<List<BidBasket>> call, Throwable t) {
                Log.d(TAG, "onFailure  : " + call.toString());
                progressDialog.dismiss();
            }
        });

    }
}
