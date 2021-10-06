package com.example.newp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ModalVerAdapter extends RecyclerView.Adapter<ModalVerAdapter.ChildViewHolder> {

    RecyclerView recyclerViewHor;

    Context context;
    ArrayList<ModelVer> data;
    ArrayList<ModelHor> data2;
    private static final String TAG = "ModalVerAdapter";

    public ModalVerAdapter(Context context, ArrayList<ModelVer> data,ArrayList<ModelHor> data2){
        this.context=context;
        this.data=data;
        this.data2=data2;
     }
    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vertical_rec, viewGroup, false);
        return new ChildViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, int position)
    {
        ChildViewHolder myHolder= (ChildViewHolder) childViewHolder;
        childViewHolder.Titles.setText(data.get(position).getTitle());
        childViewHolder.Targets.setText(data.get(position).getTarget());
        childViewHolder.Raiseds.setText(data.get(position).getRaised());
        childViewHolder.Descriptions.setText(data.get(position).getDesc());

        recyclerViewHor.setLayoutManager(new LinearLayoutManager(recyclerViewHor.getContext(), LinearLayoutManager.HORIZONTAL, false));
        ModalHorAdapter adapterHor = new ModalHorAdapter(data2);
        recyclerViewHor.setAdapter(adapterHor);

    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {



        TextView Titles,Targets,Raiseds,Descriptions,btn;

        ChildViewHolder(View itemView)
        {
            super(itemView);
            recyclerViewHor = itemView.findViewById(R.id.rec1);

            Titles=itemView.findViewById(R.id.titles);
            Targets=itemView.findViewById(R.id.target);
            Raiseds=itemView.findViewById(R.id.raised);
            Descriptions=itemView.findViewById(R.id.des);

            btn=itemView.findViewById(R.id.btn);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if( btn.getText().toString().equalsIgnoreCase("more"))
                    {
                        Descriptions.setMaxLines(Integer.MAX_VALUE);
                        btn.setText("Showless");
                    }
                    else
                    {
                        Descriptions.setMaxLines(3);
                        btn.setText("more");
                    }

                }
            });
        }
    }
}