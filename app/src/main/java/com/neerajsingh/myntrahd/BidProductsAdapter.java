package com.neerajsingh.myntrahd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neerajsingh.myntrahd.network.response.BidBasket;

import java.util.List;

/**
 * Created by anshulika.ks on 17/04/16.
 */
public class BidProductsAdapter extends BaseAdapter{
    private Context mContext;
    private List<BidBasket> bidBasketList;
    private LayoutInflater inflater=null;

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
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.bidded_product_item_status, null);
        holder.productImage=(ImageView) rowView.findViewById(R.id.product_image);
        holder.productName=(TextView) rowView.findViewById(R.id.productName);
        holder.productPrice=(TextView) rowView.findViewById(R.id.productPrice);
        holder.productNegativePrice=(TextView) rowView.findViewById(R.id.negetiveBidStatus);
        holder.productPositiveStatus=(TextView) rowView.findViewById(R.id.positiveBidStatus);
        holder.yourBid=(TextView) rowView.findViewById(R.id.yourBid);
        holder.reBidButton=(Button) rowView.findViewById(R.id.bidAgainButton);
        RelativeLayout relativeLayout = (RelativeLayout) rowView.findViewById(R.id.negetiveLayoutWrapper);

        holder.productPrice.setText(bidBasketList.get(position).getMrp().toString());
        holder.productName.setText(bidBasketList.get(position).getProdTitle());
        holder.productImage.setImageResource(Utils.getProdImage(bidBasketList.get(position).getDisplayImg()));
        String errorMessage = String.format(
                mContext.getString(R.string.your_bid_string), bidBasketList.get(position).getUserBid().toString());
        holder.yourBid.setText(errorMessage);

        if(bidBasketList.get(position).isUnique()) {
            relativeLayout.setVisibility(View.GONE);
            holder.productPositiveStatus.setVisibility(View.VISIBLE);
        }else {
            relativeLayout.setVisibility(View.VISIBLE);
            holder.productPositiveStatus.setVisibility(View.GONE);
        }
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
}
