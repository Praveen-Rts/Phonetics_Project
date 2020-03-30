package com.gegosoft.phoneticsproject.Activity;

import android.content.ClipData;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gegosoft.phoneticsproject.Adapter.CustomAdpater;
import com.gegosoft.phoneticsproject.Interface.Custominterface;
import com.gegosoft.phoneticsproject.Models.Activity_B_Customelist;
import com.gegosoft.phoneticsproject.Models.Activity_B_Model;
import com.gegosoft.phoneticsproject.R;

import java.util.ArrayList;
import java.util.List;


public class B_Activity extends AppCompatActivity implements CustomAdpater.Listener {
    RecyclerView dragrecyclerView,droprecyclerview;
    //List<Activity_B_Customelist> b_customelists;
    ArrayList<Activity_B_Model> b_customelists;
    ArrayList<Activity_B_Model>b_customelists1;
    CustomAdpater.Listener listener;
    TextView textemptylist;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_layout);
        dragrecyclerView=findViewById(R.id.itemdragreccylerview);
        droprecyclerview=findViewById(R.id.itemdroprecyclerview);
//        dragrecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        dragrecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        droprecyclerview.setLayoutManager(new GridLayoutManager(this,3));
//        droprecyclerview.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
//        droprecyclerview.setLayoutManager(new LinearLayoutManager(this));
        textemptylist=findViewById(R.id.textemptylist);
        //   ButterKnife.bind(this);
        onSuccess();
     //   getdata();
    }



    private ArrayList getdata() {
        b_customelists = new ArrayList<>();

        b_customelists.add(new Activity_B_Model(R.drawable.ballons, "Baloon"));
        b_customelists.add(new Activity_B_Model(R.drawable.bat, "Bat"));
        b_customelists.add(new Activity_B_Model(R.drawable.ant, "Ant"));
        b_customelists.add(new Activity_B_Model(R.drawable.bus, "Bus"));
        b_customelists.add(new Activity_B_Model(R.drawable.boat, "Boat"));
        b_customelists.add(new Activity_B_Model(R.drawable.boy, "Boy"));
        b_customelists.add(new Activity_B_Model(R.drawable.bear, "Bear"));
        b_customelists.add(new Activity_B_Model(R.drawable.alphabets, "Alphabets"));
        b_customelists.add(new Activity_B_Model(R.drawable.butterfly, "Butterfly"));

        return b_customelists;

    }
    private ArrayList getdata1() {
        b_customelists1 = new ArrayList<>();
        return b_customelists1;

    }
    private void onSuccess() {
        try {

//            CustomListResponse response = getDummyResponseObject(getDummyJsonString());

            CustomAdpater mYesterdayCustomListAdapter =
                    new CustomAdpater(B_Activity.this,getdata(), this);
            dragrecyclerView.setAdapter(mYesterdayCustomListAdapter);


            CustomAdpater mCustomListAdapter =
                    new CustomAdpater(B_Activity.this,getdata1(), this);
            droprecyclerview.setAdapter(mCustomListAdapter);

            textemptylist .setOnDragListener(mYesterdayCustomListAdapter
                    .getDragInstance());

            textemptylist.setVisibility(View.VISIBLE);
            textemptylist.setText("Drag Items start with letter B");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }








    @Override
    public void setEmptyList(boolean visibility) {
        textemptylist.setVisibility(visibility ? View.VISIBLE : View.GONE);
        droprecyclerview.setVisibility(visibility ? View.GONE : View.VISIBLE);
    }

    @Override
    public void setToast(String toast) {
        Toast.makeText(this,toast,Toast.LENGTH_SHORT).show();
        if(toast.equals("Correct")){
            mediaPlayer = MediaPlayer.create(B_Activity.this,R.raw.yes_sound);
            mediaPlayer.start();
        }
        else {
            mediaPlayer = MediaPlayer.create(B_Activity.this,R.raw.no_sound);
            mediaPlayer.start();
        }
    }
}