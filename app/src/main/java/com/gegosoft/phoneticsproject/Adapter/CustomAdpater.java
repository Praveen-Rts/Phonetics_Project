package com.gegosoft.phoneticsproject.Adapter;

import android.content.ClipData;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gegosoft.phoneticsproject.Activity.B_Activity;
import com.gegosoft.phoneticsproject.Models.Activity_B_Model;
import com.gegosoft.phoneticsproject.R;

import java.util.ArrayList;

public class CustomAdpater extends RecyclerView.Adapter<CustomAdpater.MyViewHolder> {
    ArrayList<Activity_B_Model> myModelArrayList;
    Listener mListener;
MediaPlayer mediaPlayer;
    public CustomAdpater(B_Activity b_activity, ArrayList<Activity_B_Model> myModelArrayList, Listener listener) {
        this.myModelArrayList = myModelArrayList;
        this.mListener = listener;

    }

    @NonNull
    @Override
    public CustomAdpater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_b_itemdragrecyclerview, parent, false);
        return new CustomAdpater.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdpater.MyViewHolder holder, int position) {
        holder.itemtext.setText(myModelArrayList.get(position).getName());
        holder.imageView.setImageResource(myModelArrayList.get(position).getImage());
        holder.cardView.setTag(position);
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });
        holder.cardView.setOnDragListener(new CustomAdpater.DragListener(mListener));
    }

    public CustomAdpater.DragListener getDragInstance() {
        if (mListener != null) {
            return new CustomAdpater.DragListener(mListener);
        } else {
            Log.e("Route Adapter: ", "Initialize listener first!");
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return myModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemtext;
        CardView cardView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemtext = itemView.findViewById(R.id.gridtext);
            cardView = itemView.findViewById(R.id.cardview);
            imageView = itemView.findViewById(R.id.gridimage);
        }
    }

    public ArrayList<Activity_B_Model> getMyModelArrayList() {
        return myModelArrayList;
    }

    public void updateCustomList(ArrayList<Activity_B_Model> modelArrayList) {
        this.myModelArrayList = modelArrayList;
    }

    public interface Listener {
        void setEmptyList(boolean visibility);
        void setToast(String toast);
    }

    public class DragListener implements View.OnDragListener {

        boolean isDropped = false;
        CustomAdpater.Listener mListener;

        public DragListener(CustomAdpater.Listener listener) {
            this.mListener = listener;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundColor(Color.LTGRAY);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundColor(Color.YELLOW);
                    break;

                case DragEvent.ACTION_DROP:

                    isDropped = true;
                    int positionSource = -1;
                    int positionTarget = -1;

                    View viewSource = (View) event.getLocalState();

                    if (v.getId() == R.id.cardview || v.getId() == R.id.textemptylist) {
                        //RecyclerView target = (RecyclerView) v.getParent();
                        RecyclerView target;
                        if (v.getId() == R.id.textemptylist) {
                            target = (RecyclerView)
                                    v.getRootView().findViewById(R.id.itemdroprecyclerview);
                        } else {
                            target = (RecyclerView) v.getParent();
                            positionTarget = (int) v.getTag();
                        }

                        RecyclerView source = (RecyclerView) viewSource.getParent();
                        Log.d("sourceId", String.valueOf(source.getId()));


                        CustomAdpater adapterSource = (CustomAdpater) source.getAdapter();
                        positionSource = (int) viewSource.getTag();

                        Activity_B_Model customList = (Activity_B_Model) adapterSource.getMyModelArrayList().get(positionSource);
                        ArrayList<Activity_B_Model> customListSource = adapterSource.getMyModelArrayList();
                        if (customListSource.get(positionSource).getName().startsWith("B")) {
                            customListSource.remove(positionSource);
                            adapterSource.updateCustomList(customListSource);
                            adapterSource.notifyDataSetChanged();
                            CustomAdpater adapterTarget = (CustomAdpater) target.getAdapter();
                            ArrayList<Activity_B_Model> customListTarget = adapterTarget.getMyModelArrayList();
                            if (positionTarget >= 0) {
                                customListTarget.add(positionTarget, customList);
                            } else {
                                customListTarget.add(customList);
                            }
                            adapterTarget.updateCustomList(customListTarget);
                            adapterTarget.notifyDataSetChanged();
                            v.setVisibility(View.VISIBLE);

                            if (source.getId() == R.id.itemdroprecyclerview
                                    && adapterSource.getItemCount() < 1) {
                                mListener.setEmptyList(true);
                            }

                            if (v.getId() == R.id.textemptylist) {
                                mListener.setEmptyList(false);
                            }
                            mListener.setToast("Correct");
                        } else {
                            Log.d("DRAG", "WRONG");
                            mListener.setToast("WRONG");
                        }


                    }
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundColor(0);
                    break;

                default:
                    break;
            }

            if (!isDropped) {
                View vw = (View) event.getLocalState();
                vw.setVisibility(View.VISIBLE);
            }

            return true;

        }

    }

}

