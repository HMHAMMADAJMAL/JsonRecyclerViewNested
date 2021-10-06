package com.example.newp;

import static android.widget.Toast.makeText;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.List;

public class ModalHorAdapter extends RecyclerView.Adapter<ModalHorAdapter.ChildViewHolder> {
    private List<ModelHor> ChildItemList;
    Context context;
    ModalHorAdapter(List<ModelHor> childItemList)
    {
        this.ChildItemList = childItemList;
    }
    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horiz_rec, viewGroup, false);
        return new ChildViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, @SuppressLint("RecyclerView") int position)
    {
     ModelHor modelHor=ChildItemList.get(position);
     Picasso.get().load(modelHor.getChildItemImage()).resize(600,600).into(childViewHolder.ChildItemImage);
     childViewHolder.ChildItemImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(view.getContext(), New_Image.class);
            i.putExtra("image_url",modelHor.getChildItemImage());
            view.getContext().startActivity(i);
        }
    });
    }

    @Override
    public int getItemCount()
    {
        return ChildItemList.size();
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {

         public ImageView ChildItemImage;

        ChildViewHolder(View itemView)
        {
            super(itemView);

            ChildItemImage = itemView.findViewById(R.id.child_item_image);


        }
    }
}