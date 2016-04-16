package com.neerajsingh.myntrahd;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayerView;
import com.neerajsingh.myntrahd.tindercard.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class SwipeActivity extends YouTubeBaseActivity
         {

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<Data> itemList;
    private SwipeFlingAdapterView flingContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);




        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        itemList = new ArrayList<>();
        itemList.add(new Data("http://i.ytimg.com/vi/PnxsTxV8y3g/maxresdefault.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
        itemList.add(new Data("http://switchboard.nrdc.org/blogs/dlashof/mission_impossible_4-1.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
        itemList.add(new Data("http://i.ytimg.com/vi/PnxsTxV8y3g/maxresdefault.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
        itemList.add(new Data("http://switchboard.nrdc.org/blogs/dlashof/mission_impossible_4-1.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
        itemList.add(new Data("http://i.ytimg.com/vi/PnxsTxV8y3g/maxresdefault.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
        itemList.add(new Data("http://switchboard.nrdc.org/blogs/dlashof/mission_impossible_4-1.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
        itemList.add(new Data("http://i.ytimg.com/vi/PnxsTxV8y3g/maxresdefault.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
        itemList.add(new Data("http://switchboard.nrdc.org/blogs/dlashof/mission_impossible_4-1.jpg", "Modakies"));
        itemList.add(new Data("http://i.ytimg.com/vi/PnxsTxV8y3g/maxresdefault.jpg", "Neerajs"));

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

                itemList.remove(0);
                myAppAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
//                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
//                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
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

    }

    public void onActionDownPerform() {
        Log.e("action", "bingo");
    }


    public static void removeBackground() {

        viewHolder.background.setVisibility(View.GONE);
        myAppAdapter.notifyDataSetChanged();

    }



    public class ViewHolder implements YouTubePlayer.OnInitializedListener {
        private final String TAG = ViewHolder.class.getSimpleName();

        Context context;
        public FrameLayout background;
        public TextView DataText;
        public ImageView cardImage;
        public VideoView videoView;
        public YouTubePlayerView youTubeView;// = (YouTubePlayerView) findViewById(R.id.youtube_view);

        public ViewHolder(Context context) {
            this.context = context;
        }

        //        // Initializing video player with developer key
//        youTubeView.initialize(Config.DEVELOPER_KEY, this);
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
            if (!wasRestored) {

                // loadVideo() will auto play video
                // Use cueVideo() method, if you don't want to play it automatically
                player.loadVideo(Config.YOUTUBE_VIDEO_CODE);

                // Hiding player controls
                player.setPlayerStyle(PlayerStyle.CHROMELESS);
            }
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
            if (errorReason.isUserRecoverableError()) {
                errorReason.getErrorDialog((Activity) context, RECOVERY_DIALOG_REQUEST).show();
            } else {
//                String errorMessage = String.format(
//                        SwipeActivity.this.getString(R.string.error_player), errorReason.toString());
//                Toast.makeText(SwipeActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                Log.e(TAG,"Unabel to load youtube player");
            }
        }

    }

    public class MyAppAdapter extends BaseAdapter {


        public List<Data> parkingList;
        public Context context;

        private MyAppAdapter(List<Data> apps, Context context) {
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
                viewHolder = new ViewHolder(context);
                viewHolder.DataText = (TextView) rowView.findViewById(R.id.bookText);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.youTubeView = (YouTubePlayerView)rowView.findViewById(R.id.youtube_view);
                viewHolder.youTubeView.initialize(Config.DEVELOPER_KEY, viewHolder);
//                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.youtube_view);
//                viewHolder.videoView = (VideoView) rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.DataText.setText(parkingList.get(position).getDescription() + "");

//            viewHolder.
//            Glide.with(SwipeActivity.this).load(parkingList.get(position).getImagePath()).into(viewHolder.cardImage);

//            String vidAddress = "https://www.youtube.com/watch?v=lXcJFVy1pKc";
//            Uri vidUri = Uri.parse(vidAddress);
//            viewHolder.videoView.setVideoURI(vidUri);
//            viewHolder.videoView.start();
            return rowView;
        }
    }
}
