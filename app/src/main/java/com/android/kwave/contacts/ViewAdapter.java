package com.android.kwave.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.kwave.contacts.domain.Data;

import java.util.List;

/**
 * Created by kwave on 2017-06-01.
 */

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.Holder>{
    List<Data> datas;



    public ViewAdapter(List<Data> datas) {
        this.datas = datas;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
            Data data = datas.get(position);
        holder.setName(data.getName());
        holder.setTel(data.getTel());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        private TextView Naming,Telling;
        public Holder(View itemView) {
            super(itemView);
            Naming = (TextView) itemView.findViewById(R.id.name);
            Telling = (TextView) itemView.findViewById(R.id.tel);
        }

        public void setName(String name) {
            Naming.setText(name);
        }

        public void setTel(String tel) {
            Telling.setText(tel);
        }
    }
}

