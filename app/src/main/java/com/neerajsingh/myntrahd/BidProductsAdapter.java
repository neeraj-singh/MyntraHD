package com.neerajsingh.myntrahd;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
public class BidProductsAdapter extends BaseAdapter{
    private static final String TAG = BidProductsAdapter.class.getSimpleName();
    private Context mContext;
    private List<BidBasket> bidBasketList;
    private LayoutInflater inflater=null;
    private Holder holder;

    public BidProductsAdapter(Context mContext, List<BidBasket> bidBasketList) {
        this.mContext = mContext;
        this.bidBasketList = bidBasketList;
        this.inflater = ( LayoutInflater )mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
    }

    @Override
    public int getCount() {
        return this.bidBasketList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = convertView;
        if(rowView==null) {
            holder = new Holder();
            rowView = inflater.inflate(R.layout.bidded_product_item_status, null);
            holder.productImage = (ImageView) rowView.findViewById(R.id.product_image);
            holder.productName = (TextView) rowView.findViewById(R.id.productName);
            holder.productPrice = (TextView) rowView.findViewById(R.id.productPrice);
            holder.productNegativePrice = (TextView) rowView.findViewById(R.id.negetiveBidStatus);
            holder.productPositiveStatus = (TextView) rowView.findViewById(R.id.positiveBidStatus);
            holder.yourBid = (TextView) rowView.findViewById(R.id.yourBid);
            holder.reBidButton = (Button) rowView.findViewById(R.id.bidAgainButton);
            rowView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        RelativeLayout relativeLayout = (RelativeLayout) rowView.findViewById(R.id.negetiveLayoutWrapper);

        holder.productPrice.setText("\u20B9 "+bidBasketList.get(position).getMrp().toString());
        holder.productName.setText(bidBasketList.get(position).getProdTitle());
        holder.productImage.setImageResource(Utils.getProdImage(bidBasketList.get(position).getDisplayImg()));

        String errorMessage = String.format(
                mContext.getString(R.string.your_bid_string), bidBasketList.get(position).getUserBid().toString());
        if(bidBasketList.get(position).getUserBid().equals("99999")) {
            errorMessage = "NA";
        }
        holder.yourBid.setText(errorMessage);

        if(bidBasketList.get(position).isUnique()) {
            relativeLayout.setVisibility(View.GONE);
            holder.productPositiveStatus.setVisibility(View.VISIBLE);
        }else {
            relativeLayout.setVisibility(View.VISIBLE);
            holder.productPositiveStatus.setVisibility(View.GONE);
        }
        holder.reBidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlertDialog(mContext, position);
            }
        });
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "You Clicked "+bidBasketList.get(position).getProdTitle(), Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

    private class Holder
    {
        TextView productPrice;
        TextView productPositiveStatus;
        TextView productNegativePrice;
        ImageView productImage;
        TextView yourBid;
        Button reBidButton;
        TextView productName;
    }

    public void createAlertDialog(Context context, final int position){
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_aleart_dialog);

        final EditText editText = (EditText) dialog.findViewById(R.id.bidEditText);
        Button submitBidValueButton = (Button)dialog.findViewById(R.id.submitBidValue);

        // if button is clicked, close the custom dialog
        submitBidValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bidAmount = editText.getText().toString();
                if(bidAmount != null && bidAmount.length()>0)
                    addAndGetUpdatedBidBasket(AccountUtils.getAccontId(mContext), bidBasketList.get(position).getProdId().toString(),bidAmount );
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void addAndGetUpdatedBidBasket(String uniqueId, String productId, String bidAmount){
        Call<List<BidBasket>> bidBasketCall = MyntraHDApplication.getBaseRequestInterface().addAndGetUpdatedBidBasket(uniqueId, productId, bidAmount);
        bidBasketCall.enqueue(new Callback<List<BidBasket>>() {
            @Override
            public void onResponse(Call<List<BidBasket>> call, Response<List<BidBasket>> response) {
                Log.d(TAG, "onResponse  isSuccessful : " + response.isSuccessful() + " response " + response.body().toString());
//                Toast.makeText(mContext, "Response is [" + response.body().toString() + "]", Toast.LENGTH_LONG).show();
                bidBasketList.clear();
                bidBasketList = response.body();
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<BidBasket>> call, Throwable t) {
//                Log.d(TAG, "onFailure  : " + call.toString());
            }
        });

    }
}
