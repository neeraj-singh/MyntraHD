package com.neerajsingh.myntrahd;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.neerajsingh.myntrahd.network.BaseRequestInterface;
import com.neerajsingh.myntrahd.network.response.Product;
import com.neerajsingh.myntrahd.swipeCards.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.neerajsingh.myntrahd.SplashActivity.PROD_LIST;

public class SwipeActivity extends YouTubeBaseActivity
{
    public BaseRequestInterface baseRequestInterface;
    public static final String VIDEO_CODE = "videoCode";
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private static final int VIDEO_SHOW_REQUEST = 1;
    private static final String TAG = SwipeActivity.class.getSimpleName();
    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<Product> itemList;
    private SwipeFlingAdapterView flingContainer;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

//        Bundle extras = getIntent().getExtras();
//        if(extras!=null && extras.containsKey(PROD_LIST)){
//            itemList = extras.getParcelableArrayList(PROD_LIST);
//        }
        itemList = SplashActivity.itemList;
        baseRequestInterface =
                MyntraHDApplication.getRetrofit().create(BaseRequestInterface.class);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

//        itemList = new ArrayList<>();
//        itemList.add(new Data("1", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness.","a"));//lXcJFVy1pKc
//        itemList.add(new Data("2", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness.","b"));//b8B-5Ec3gEs
//        itemList.add(new Data("3", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness.","c"));
//        itemList.add(new Data("http://switchboard.nrdc.org/blogs/dlashof/mission_impossible_4-1.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
//        itemList.add(new Data("http://i.ytimg.com/vi/PnxsTxV8y3g/maxresdefault.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
//        itemList.add(new Data("http://switchboard.nrdc.org/blogs/dlashof/mission_impossible_4-1.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
//        itemList.add(new Data("http://i.ytimg.com/vi/PnxsTxV8y3g/maxresdefault.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
//        itemList.add(new Data("http://switchboard.nrdc.org/blogs/dlashof/mission_impossible_4-1.jpg", "Modakies"));
//        itemList.add(new Data("http://i.ytimg.com/vi/PnxsTxV8y3g/maxresdefault.jpg", "Neerajs"));

        myAppAdapter = new MyAppAdapter(itemList, SwipeActivity.this);
        flingContainer.setAdapter(myAppAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                itemList.remove(0);
                myAppAdapter.notifyDataSetChanged();
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject

            }

            @Override
            public void onRightCardExit(Object dataObject) {
//                    String bid = viewHolder.bidField.getText().toString();

//                    if (!Utils.isNullOrEmpty(bid)) {
                makeBidForProductRequest(AccountUtils.getAccontId(SwipeActivity.this), itemList.get(0).getProdId().toString(), "0");
                itemList.remove(0);

                myAppAdapter.notifyDataSetChanged();

//                    } else {
//                        viewHolder.bidField.setError("Enter bid to select item");
//                    }

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                myAppAdapter.notifyDataSetChanged();
            }
        });

        Button btn = (Button) findViewById(R.id.btnGoBidpage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBiddingPage();
            }
        });

    }

    private void openBiddingPage() {
        Intent intent = new Intent(SwipeActivity.this,BiddingStatusBoardActivity.class);
        startActivity(intent);
        finish();
    }

    public void onActionDownPerform() {
        Log.e("action", "bingo");
    }


    public static void removeBackground() {

        viewHolder.background.setVisibility(View.GONE);
        myAppAdapter.notifyDataSetChanged();

    }



    public class ViewHolder  {

        public FrameLayout background;
        public TextView DataText;
        public ImageView cardImage;
        public VideoView videoView;
        public TextView sizeS;
        public TextView sizeM;
        public TextView sizeL;
        public TextView sizeXL;
        public View isUnique;
        public Button bidButton;
        public EditText bidField;

    }

    public class MyAppAdapter extends BaseAdapter {


        public List<Product> parkingList;
        public Context context;

        private MyAppAdapter(List<Product> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
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

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.DataText = (TextView) rowView.findViewById(R.id.prod_tittle);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                viewHolder.videoView = (VideoView) rowView.findViewById(R.id.videoView);
                viewHolder.sizeS = (TextView) rowView.findViewById(R.id.text_s);
                viewHolder.sizeM = (TextView) rowView.findViewById(R.id.text_m);
                viewHolder.sizeL = (TextView) rowView.findViewById(R.id.text_l);
                viewHolder.sizeXL = (TextView) rowView.findViewById(R.id.text_xl);
//                viewHolder.isUnique = rowView.findViewById(R.id.is_unique);
//                viewHolder.bidButton = (Button) rowView.findViewById(R.id.bidButton);
//                viewHolder.bidField = (EditText) rowView.findViewById(R.id.bid_field);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.cardImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivity(position);
                }
            });
            viewHolder.DataText.setText(parkingList.get(position).getProdTitle() + "");
            String []sizes = parkingList.get(position).getSizeAvail().split(",");
            for(String size : sizes){
                if(size.equalsIgnoreCase("S")){
                    viewHolder.sizeS.setBackgroundColor(getColorForPage());
                }
                if(size.equalsIgnoreCase("M")){
                    viewHolder.sizeM.setBackgroundColor(getColorForPage());
                }
                if(size.equalsIgnoreCase("L")){
                    viewHolder.sizeL.setBackgroundColor(getColorForPage());
                }
                if(size.equalsIgnoreCase("XL")){
                    viewHolder.sizeXL.setBackgroundColor(getColorForPage());
                }
            }

//            Glide.with(SwipeActivity.this).load(parkingList.get(position).getImagePath()).into(viewHolder.cardImage);
            viewHolder.cardImage.setImageResource(Utils.getProdImage(parkingList.get(position).getDisplayImg()));
//            final EditText editText = (EditText) rowView.findViewById(R.id.bid_field);
//            viewHolder.bidButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    String bid = editText.getText().toString();
//                    Log.e("Neeraj","bid value at position : "+bid);
//                    if(!Utils.isNullOrEmpty(bid)) {
//                        makeBidForProductRequest(AccountUtils.getAccontId(context), parkingList.get(position).getProdId().toString(),bid );
//                    }else{
////                        editText.setError("Enter bid");
//                    }
//                }
//            });
            return rowView;
        }

        private void openActivity(int position) {
            if (viewHolder.cardImage.getVisibility() == View.VISIBLE) {
                Log.e("Neeraj", "Click event poisiton " + position);
                Intent intent = new Intent(SwipeActivity.this,VideoViewActivity.class);
                intent.putExtra(VIDEO_CODE, parkingList.get(position).getYoutubeURL());
                startActivityForResult(intent,VIDEO_SHOW_REQUEST);
            }
        }
    }

    private int getColorForPage() {
        return Color.parseColor("#00FF00");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode  == VIDEO_SHOW_REQUEST){

            }
        }
    }

    private void makeBidForProductRequest(String uniqueId, String productId, String bidAmount) {

        Log.e("Neeraj ","Called bidfor product");
        Call<Boolean> addBidForProduct = baseRequestInterface.addBidForPid(uniqueId, productId, bidAmount);
        showLoader();
        addBidForProduct.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response != null && response.body() != null) {
                    Toast.makeText(SwipeActivity.this, "Response is [" + ((Boolean) response.body()).booleanValue() + "]", Toast.LENGTH_LONG).show();
//                    if (((Boolean) response.body())) {
//                        viewHolder.isUnique.setBackgroundColor(getColorForPage());
//                    } else {
//                        viewHolder.isUnique.setBackgroundColor(getColorForPage());
//                    }
                    hideLoader();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d(TAG, "onFailure  : " + call.toString());
            }
        });

    }

    public void showLoader() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.show();
    }
    public void hideLoader(){
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.hide();
        }
    }
}
