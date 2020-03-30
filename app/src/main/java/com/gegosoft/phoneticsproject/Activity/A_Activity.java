package com.gegosoft.phoneticsproject.Activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gegosoft.phoneticsproject.MainActivity;
import com.gegosoft.phoneticsproject.Models.Activity_A_Model;
import com.gegosoft.phoneticsproject.R;

import java.util.ArrayList;
import java.util.List;

public class A_Activity extends AppCompatActivity{
//    GridView gridView;
//    ViewGroup viewGroup;
    ImageView gridimage;
    TextView gridtext;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    ArrayList<Activity_A_Model>griddetails;
    MediaPlayer mediaPlayer;
//    List<Activity_A_Model> griddetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_layout);
        recyclerView=findViewById(R.id.customerecyclerview);
      gridLayoutManager =new GridLayoutManager(getApplicationContext(),3);
      recyclerView.setLayoutManager(gridLayoutManager);
//        gridView = findViewById(R.id.gridview);
        getdata();
GridAdapter gridAdapter=new GridAdapter(this,griddetails);
recyclerView.setAdapter(gridAdapter);
//        getdata();

    }

    private ArrayList getdata() {
        griddetails = new ArrayList<>();

        griddetails.add(new Activity_A_Model(R.drawable.angel, "Angel"));
        griddetails.add(new Activity_A_Model(R.drawable.bat, "Bat"));
        griddetails.add(new Activity_A_Model(R.drawable.ankle, "Ankle"));
        griddetails.add(new Activity_A_Model(R.drawable.ant, "Ant"));
        griddetails.add(new Activity_A_Model(R.drawable.apple, "Apple"));
        griddetails.add(new Activity_A_Model(R.drawable.arrow, "Arrow"));
        griddetails.add(new Activity_A_Model(R.drawable.bear, "Bear"));
        griddetails.add(new Activity_A_Model(R.drawable.alphabets, "Alphabets"));
        griddetails.add(new Activity_A_Model(R.drawable.aligator, "Aligator"));

        return griddetails;

    }
class  GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder>{
                ArrayList<Activity_A_Model> modelArrayList;
        Context context;
        public  GridAdapter(Context context,ArrayList<Activity_A_Model>modelArrayList){
            this.context=context;
            this.modelArrayList=modelArrayList;

    }

    @NonNull
    @Override
    public GridAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_a_recyclerview, parent, false);

        return new GridAdapter.GridViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GridAdapter.GridViewHolder holder, final int position) {
holder.gridtext.setText(modelArrayList.get(position).getName());
holder.gridimage.setImageResource(modelArrayList.get(position).getImage());
holder.gridimage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String word=modelArrayList.get(position).getName();
        char firstletter=word.charAt(0);
//        String stringletter=String.valueOf(firstletter);
        String stringletter=Character.toString(firstletter);
        if("A".equals(stringletter)) {
            mediaPlayer = MediaPlayer.create(A_Activity.this,R.raw.yes_sound);
            mediaPlayer.start();
            Toast.makeText(A_Activity.this, "Correct", Toast.LENGTH_SHORT).show();
        }
        else {
            mediaPlayer = MediaPlayer.create(A_Activity.this,R.raw.no_sound);
            mediaPlayer.start();
            Toast.makeText(A_Activity.this,"Wrong",Toast.LENGTH_SHORT).show();;

        }
        //       String word = modelArrayList.get(position).getName();
//       char firstletter = word.charAt(0);
//if(word.equals("A")){
//Toast=Toast.makeText(A_Activity.this,"Correct",Toast.LENGTH_SHORT).show();;
    }
});

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView gridimage;
        TextView gridtext;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            gridimage=itemView.findViewById(R.id.gridimage);
            gridtext=itemView.findViewById(R.id.gridtext);
        }
    }
//            ArrayList<Activity_A_Model> modelArrayList;
//        Context context;
//        public  GridAdapter(Context context,ArrayList<Activity_A_Model>modelArrayList){
//            this.context=context;
//            this.modelArrayList=modelArrayList;
//        }
//    @NonNull
//    @Override
//    public GridAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull GridAdapter.GridViewHolder holder, final int position) {
//holder.gridtext.setText(modelArrayList.get(position).getName());
//holder.gridimage.setImageResource(modelArrayList.get(position).getImage());
//gridtext.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//       String word = modelArrayList.get(position).getName();
//       char firstletter = word.charAt(0);
//if(word.equals("A")){
//Toast=Toast.makeText(A_Activity.this,"Correct",Toast.LENGTH_SHORT).show();;
//}
//    }
//    }
//
//    @Override
//    public int getItemCount() {
//        return modelArrayList.size();
//    }
//
//    public class GridViewHolder extends RecyclerView.ViewHolder {
//            ImageView gridimage;
//            TextView gridtext;
//        public GridViewHolder(@NonNull View itemView) {
//            super(itemView);
//            gridimage=itemView.findViewById(R.id.gridimage);
//            gridtext=itemView.findViewById(R.id.gridtext);
//        }
//    }
}
//    class GridAdapter extends BaseAdapter {
//        Activity_A_Model activity_a_model;
//        ArrayList<Activity_A_Model> modelArrayList;
//        Context context;
//
//        public GridAdapter(Context context, ArrayList<Activity_A_Model> modelArrayList) {
//            this.context = context;
//            this.modelArrayList = modelArrayList;
//        }
//
//        @Override
//        public int getCount() {
//            return modelArrayList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return modelArrayList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View view, ViewGroup viewGroup) {
//            if (view == null) {
//                view =  LayoutInflater.from(A_Activity.this).inflate(R.layout.activity_a_recyclerview, viewGroup, false);
//            }
//            activity_a_model=(Activity_A_Model) this.getItem(position);
//                 gridimage=findViewById(R.id.gridimage);
//                 gridtext=findViewById(R.id.gridtext);
//
//                 gridimage.setImageResource(activity_a_model.getImage());
//                 gridtext.setText(activity_a_model.getName());
//
//
//
//            return view;
//        }
//    }
}


