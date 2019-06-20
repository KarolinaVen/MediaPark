package com.example.rozhe.mediapark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<Info> dataList;
    private Context context;

    public CustomAdapter(Context context, List<Info> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView plateNumber;
        TextView title;
        TextView distanceFromUser;
        private ImageView carImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            plateNumber = mView.findViewById(R.id.plateNumber);
            carImage = mView.findViewById(R.id.carImage);
            title = mView.findViewById(R.id.title);
            distanceFromUser = mView.findViewById(R.id.distance_from_user);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.plateNumber.setText(dataList.get(position).getPlateNumber());
        holder.title.setText(dataList.get(position).getModel().getTitle());
        String formatDistance = new DecimalFormat("#0.##").format(dataList.get(position).getDistance());
        holder.distanceFromUser.setText(String.valueOf(formatDistance + " m"));

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getModel().getPhotoUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.carImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

