package com.gegosoft.phoneticsproject.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.gegosoft.phoneticsproject.Models.AlphabetsModel;
import com.gegosoft.phoneticsproject.R;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {
        RecyclerView recyclerView;
        LinearLayoutManager linearLayoutManager;
        List<AlphabetsModel> alphabets;
        List<AlphabetsModel> smallalphabets;
        MediaPlayer mediaPlayer;
        ImageView play,trace;
        TextView smallalphs,activities;
        Animation animation;
        AlphabetsAdapter alphabetsAdapter;
        int audiofile;
        boolean iscap=true;
        String swipeside;
        int position=-1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            recyclerView=findViewById(R.id.lettersrecyclerview);
//        if (iscap){
//            audiofile = alphabets.get(0).getAudio();
//        }
//        else {
//            audiofile = smallalphabets.get(0).getAudio();
//        }
            linearLayoutManager =new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {


                    } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    int currentPosition = linearLayoutManager.findFirstVisibleItemPosition();
//                    audiofile = alphabets.get(currentPosition).getAudio();                        int currenttPosition = linearLayoutManager.findFirstVisibleItemPosition();
                        position = linearLayoutManager.findFirstVisibleItemPosition();
//                    if(swipeside.equalsIgnoreCase("Right")){
//                        if(hasNext()){
//                            next();
//                        }
//                    }
//                    else if(swipeside.equalsIgnoreCase("Left")){
//                        if(hasPreview()){
//                            preview();
//                        }
//                    }
                        if(iscap){
                            // audiofile = alphabets.get(position).getAudio();
                            smallalphs.setText(smallalphabets.get(position).getAlphabet());
                            alphabetsAdapter.datasetchanged(alphabets);
                            Log.d("audio", String.valueOf(audiofile));
                            Log.d("alphabet", alphabets.get(position).getAlphabet());

                        }
                        else if(!iscap) {
                            //  audiofile = smallalphabets.get(position).getAudio();
                            smallalphs.setText(alphabets.get(position).getAlphabet());
                            alphabetsAdapter.datasetchanged(smallalphabets);
                            Log.d("audio", String.valueOf(audiofile));
                            Log.d("alphabet", alphabets.get(position).getAlphabet());

                        }

                    }
                }

//                @Override
//                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                    if (dx > 0) {
//                        swipeside = "Right";
//                        System.out.println("Scrolled Right");
//                    } else if (dx < 0) {
//                        swipeside = "Left";
//                        System.out.println("Scrolled Left");
//                    } else {
//                        swipeside= "No Scrolled";
//                        System.out.println("No Horizontal Scrolled");
//                    }
//                }
            });

            play=findViewById(R.id.voiceimage);
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(DemoActivity.this, audiofile);
                    mediaPlayer.start();
                }
            });
            trace=findViewById(R.id.traceimage);
            smallalphs=findViewById(R.id.smalltext);
            smallalphs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(iscap){
                        //            smallalphs.setText(smallalphabets.get().getAlphabet());

                    }
                }
            });
            activities=findViewById(R.id.activitiestxt);

            getalphabets();

            SnapHelper mSnapHelper = new PagerSnapHelper();
            mSnapHelper.attachToRecyclerView(recyclerView);

            alphabetsAdapter=new AlphabetsAdapter(alphabets);
            recyclerView.setAdapter(alphabetsAdapter);
            trace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String letter = "";
                    if(iscap){
                        // audiofile = alphabets.get(position).getAudio();
                        letter = alphabets.get(position).getAlphabet();
                        smallalphs.setText(smallalphabets.get(position).getAlphabet());
                        alphabetsAdapter.datasetchanged(alphabets);
                        Log.d("audio", String.valueOf(audiofile));
                        Log.d("alphabet", alphabets.get(position).getAlphabet());

                    }
                    else if(!iscap) {
                        //  audiofile = smallalphabets.get(position).getAudio();
                        letter = smallalphabets.get(position).getAlphabet();

                    }
//                    startActivity(new Intent(MainActivity.this, TraceActivity.class).putExtra("letter",letter));
                }
            });

        }
        public boolean hasPreview() {
            return position > 0;
        }

        public boolean hasNext() {
            return recyclerView.getAdapter() != null &&
                    getCurrentItem() < (recyclerView.getAdapter().getItemCount()- 1);
        }

        public void preview() {
            //position = getCurrentItem();
            if (position > 0)
                //  setCurrentItem(position -1, true);
                position--;
        }

        public void next() {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter == null)
                return;

            position = getCurrentItem();
//        int count = adapter.getItemCount();
//        if (position < (count -1))
//            // setCurrentItem(position + 1, true);
//            position++;
        }

        private int getCurrentItem(){
            return ((LinearLayoutManager)recyclerView.getLayoutManager())
                    .findFirstVisibleItemPosition();
        }

        private void setCurrentItem(int position, boolean smooth){
            if (smooth)
                recyclerView.smoothScrollToPosition(position);
            else
                recyclerView.scrollToPosition(position);
        }


        private void getalphabets(){
            alphabets=new ArrayList<>();
            alphabets.add(new AlphabetsModel("A",R.raw.a1));
            alphabets.add(new AlphabetsModel("B",R.raw.a2));
            alphabets.add(new AlphabetsModel("C",R.raw.a3));
            alphabets.add(new AlphabetsModel("D",R.raw.a4));
            alphabets.add(new AlphabetsModel("E",R.raw.a5));
            alphabets.add(new AlphabetsModel("F",R.raw.a6));

            smallalphabets=new ArrayList<>();
            smallalphabets.add(new AlphabetsModel("a",R.raw.a7));
            smallalphabets.add(new AlphabetsModel("b",R.raw.a8));
            smallalphabets.add(new AlphabetsModel("c",R.raw.a9));
            smallalphabets.add(new AlphabetsModel("d",R.raw.a10));
            smallalphabets.add(new AlphabetsModel("e",R.raw.a11));
            smallalphabets.add(new AlphabetsModel("f",R.raw.a12));
        }



        public class AlphabetsAdapter  extends RecyclerView.Adapter<AlphabetsAdapter.ViewHolder>{

            List<AlphabetsModel>ialphabets;
            public AlphabetsAdapter(List<AlphabetsModel>alphabets){
                this.ialphabets=alphabets;

            }

            @NonNull
            @Override
            public AlphabetsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.capletters_recyclerview, parent, false);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull final AlphabetsAdapter.ViewHolder holder, final int position) {

                holder.caps.setText(ialphabets.get(position).getAlphabet());
                audiofile =  ialphabets.get(position).getAudio();
                holder.caps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(iscap){
                            ialphabets = smallalphabets;
                            iscap=false;
                            audiofile = ialphabets.get(position).getAudio();
                            holder.caps.setText(ialphabets.get(position).getAlphabet());
                            holder.caps.setAllCaps(false);
                            smallalphs.setText(alphabets.get(position).getAlphabet());

                        }
                        else if(!iscap) {
                            ialphabets=alphabets;
                            iscap=true;
                            audiofile = ialphabets.get(position).getAudio();
                            holder.caps.setText(ialphabets.get(position).getAlphabet());
                            holder.caps.setAllCaps(true);
                            smallalphs.setText(smallalphabets.get(position).getAlphabet());

                        }

                    }
                });




            }

            @Override
            public int getItemCount() {
                return ialphabets.size();
            }

            public class ViewHolder extends RecyclerView.ViewHolder {
                TextView caps;
                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    caps=itemView.findViewById(R.id.recyclerviewcaptext);

                }
            }
            private void datasetchanged(List<AlphabetsModel> data){
                this.ialphabets = data;
                notifyDataSetChanged();

            }

        }

    }
