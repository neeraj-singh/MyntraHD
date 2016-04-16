package com.neerajsingh.myntrahd.network;

import com.neerajsingh.myntrahd.network.response.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anshulika.ks on 16/04/16.
 */
public interface BaseRequestInterface {
    @GET("/getAllproducts?")
    public Call<List<Product>> getListOfAllBiddableProducts(@Query("uid") String uid);

    @GET("/addToBidBasket?")
    Call<Boolean> addBidForPid(@Query("uid") String uid, @Query("pid") String pid, @Query("bidAmt") String bidAmt);
}
