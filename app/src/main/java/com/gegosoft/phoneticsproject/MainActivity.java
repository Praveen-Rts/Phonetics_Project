package com.gegosoft.phoneticsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

//import com.gegosoft.phoneticsproject.Activity.Drag_and_Drop;
import com.gegosoft.phoneticsproject.Activity.TempActivity;
import com.gegosoft.phoneticsproject.Activity.Tracing_Activity;
import com.gegosoft.phoneticsproject.Models.AlphabetsModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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
    boolean ismall=true;
    int position=-1;
    SoundPool soundPool;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.lettersrecyclerview);
        linearLayoutManager =new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {

                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    position = linearLayoutManager.findFirstVisibleItemPosition();


                    if(iscap){
//                        audiofile = alphabets.get(position).getAudio();
                        smallalphs.setText(smallalphabets.get(position).getAlphabet());
                        alphabetsAdapter.datasetchanged(alphabets);
//                        iscap=false;
                        Log.d("audio", String.valueOf(audiofile));
//                        smallalphs.setAllCaps(false);
                        Log.d("alphabet", alphabets.get(position).getAlphabet());
//                        iscap=false;
//                        ismall=false;
                    }
                     else if (!iscap){
//                         audiofile = smallalphabets.get(position).getAudio();
                        smallalphs.setText(alphabets.get(position).getAlphabet());
                        alphabetsAdapter.datasetchanged(smallalphabets);
//                        iscap=true;
//                        smallalphs.setAllCaps(true);
                        Log.d("audio", String.valueOf(audiofile));
                        Log.d("alphabet", alphabets.get(position).getAlphabet());
//                        iscap=true;
//                        iscap=true;
//                        iscap=true;
                     }
                }
            }

        });

        play=findViewById(R.id.voiceimage);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                playaudio();
//                mediaPlayer = MediaPlayer.create(MainActivity.this, audiofile);
                soundPool=new SoundPool(52, AudioManager.STREAM_MUSIC,0);
           final int     audiofiles=soundPool.load(MainActivity.this,audiofile,1);
                soundPool.play(audiofiles,1,1,1,0,1);
//                soundPool=soundPool.load(MainActivity.this,audiofile,1);
//                mediaPlayer.start();
            }
        });
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
        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //////////////
                Intent intent=new Intent(MainActivity.this,  Tracing_Activity.class);
                startActivity(intent);

            }
        });

        getalphabets();

        SnapHelper mSnapHelper = new PagerSnapHelper();
        mSnapHelper.attachToRecyclerView(recyclerView);

        alphabetsAdapter=new AlphabetsAdapter(alphabets);
        recyclerView.setAdapter(alphabetsAdapter);

        trace=findViewById(R.id.traceimage);
        trace.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String letter="";
 if(iscap){
     if(position==-1){
         position=0;
         letter = alphabets.get(position).getAlphabet();
         smallalphs.setText(smallalphabets.get(position).getAlphabet());
         alphabetsAdapter.datasetchanged(alphabets);
     }
     letter = alphabets.get(position).getAlphabet();
     smallalphs.setText(smallalphabets.get(position).getAlphabet());
     alphabetsAdapter.datasetchanged(alphabets);

 }

 else if (!iscap){

     letter = smallalphabets.get(position).getAlphabet();

 }

 startActivity(new Intent(MainActivity.this,TempActivity.class).putExtra("letter",letter));
    }
});


    }
//    public boolean hasNext() {
//        return recyclerView.getAdapter() != null &&
//                getCurrentItem() < (recyclerView.getAdapter().getItemCount()- 1);
//    }
//    private int getCurrentItem(){
//        return ((LinearLayoutManager)recyclerView.getLayoutManager())
//                .findFirstVisibleItemPosition();
//    }
//    private void next(){
//RecyclerView.Adapter adapter=recyclerView.getAdapter();
//if(adapter==null)
//    return;
//        position = getCurrentItem();
//        int count = adapter.getItemCount();
//        if (position > (count -1))
//            // setCurrentItem(position + 1, true);
//            position++;
//
//    }
//    public boolean hasPrevious(){
//        return position > 0;
//    }
//    private void previous(){
//        if (position > 0);
//            //  setCurrentItem(position -1, true);
////            position--;
//
//    }
//private void playaudio(){
//if(position==0){
//if(iscap){
//    iscap=false;
//    audiofile=alphabets.get(position).getAudio();
//}
//else {
//    iscap=true;
//    audiofile=smallalphabets.get(position).getAudio();
//}
//
//}
//
//
//}
private void getalphabets(){
alphabets=new ArrayList<>();

    alphabets.add(new AlphabetsModel("A",R.raw.a1));
    alphabets.add(new AlphabetsModel("B",R.raw.a2));
    alphabets.add(new AlphabetsModel("C",R.raw.a3));
    alphabets.add(new AlphabetsModel("D",R.raw.a4));
    alphabets.add(new AlphabetsModel("E",R.raw.a5));
    alphabets.add(new AlphabetsModel("F",R.raw.a6));
    /////////
    alphabets.add(new AlphabetsModel("G",R.raw.a6));
    alphabets.add(new AlphabetsModel("H",R.raw.a6));
    alphabets.add(new AlphabetsModel("I",R.raw.a6));
    alphabets.add(new AlphabetsModel("J",R.raw.a6));
    alphabets.add(new AlphabetsModel("K",R.raw.a6));
    alphabets.add(new AlphabetsModel("L",R.raw.a6));
    alphabets.add(new AlphabetsModel("M",R.raw.a6));
    alphabets.add(new AlphabetsModel("N",R.raw.a6));
    alphabets.add(new AlphabetsModel("O",R.raw.a6));
    alphabets.add(new AlphabetsModel("P",R.raw.a6));
    alphabets.add(new AlphabetsModel("Q",R.raw.a6));
    alphabets.add(new AlphabetsModel("R",R.raw.a6));
    alphabets.add(new AlphabetsModel("S",R.raw.a6));
    alphabets.add(new AlphabetsModel("T",R.raw.a6));
    alphabets.add(new AlphabetsModel("U",R.raw.a6));
    alphabets.add(new AlphabetsModel("V",R.raw.a6));
    alphabets.add(new AlphabetsModel("W",R.raw.a6));
    alphabets.add(new AlphabetsModel("X",R.raw.a6));
    alphabets.add(new AlphabetsModel("Y",R.raw.a6));
    alphabets.add(new AlphabetsModel("Z",R.raw.a6));


    smallalphabets=new ArrayList<>();
    smallalphabets.add(new AlphabetsModel("a",R.raw.a7));
    smallalphabets.add(new AlphabetsModel("b",R.raw.a8));
    smallalphabets.add(new AlphabetsModel("c",R.raw.a9));
    smallalphabets.add(new AlphabetsModel("d",R.raw.a10));
    smallalphabets.add(new AlphabetsModel("e",R.raw.a11));
    smallalphabets.add(new AlphabetsModel("f",R.raw.a12));
    /////
    smallalphabets.add(new AlphabetsModel("g",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("h",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("i",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("j",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("k",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("l",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("m",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("n",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("o",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("p",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("q",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("r",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("s",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("t",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("u",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("v",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("w",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("x",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("y",R.raw.a12));
    smallalphabets.add(new AlphabetsModel("z",R.raw.a12));
}







    class AlphabetsAdapter  extends RecyclerView.Adapter<AlphabetsAdapter.ViewHolder>{

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
//                    holder.caps.setText(alphabets.get(position).getAlphabet());
                    holder.caps.setText(ialphabets.get(position).getAlphabet());
                    holder.caps.setAllCaps(false);
                    smallalphs.setText(alphabets.get(position).getAlphabet());
                }
                else if(!iscap){
                    ialphabets=alphabets;
                    iscap=true;
                    audiofile = ialphabets.get(position).getAudio();
//                    holder.caps.setText(smallalphabets.get(position).getAlphabet());
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



