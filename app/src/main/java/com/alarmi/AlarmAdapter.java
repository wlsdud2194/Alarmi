package com.alarmi;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alarmi.database.Alarm;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ItemViewHolder> {
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView timeTitle;
        TextView termText;
        Switch onOffSwitch;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTitle = itemView.findViewById(R.id.timeTitle);
            termText = itemView.findViewById(R.id.termText);
            onOffSwitch = itemView.findViewById(R.id.onOffSwitch);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position, Alarm alarm);
        void onSwitchClick(View v, int position, Alarm alarm);
    }

    private List<Alarm> alarmData;
    private OnItemClickListener listener;

    public AlarmAdapter(List<Alarm> alarmData, OnItemClickListener listener) {
        this.alarmData = alarmData;
        this.listener = listener;
    }

    public void setAlarmData(List<Alarm> alarms) {
        this.alarmData = alarms;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Alarm alarm = alarmData.get(position);

        holder.timeTitle.setText(alarm.getTime());
        holder.termText.setText(alarm.getTerm());
        holder.onOffSwitch.setChecked(alarm.getActived());
        holder.onOffSwitch.setOnClickListener(v -> {
            listener.onSwitchClick(v, position, alarm);
        });
        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(v, position, alarm);
        });

        if ((position % 2 == 0)) {
            holder.itemView.setBackgroundColor(Color.parseColor("#F7F7F7"));
        }
    }

    @Override
    public int getItemCount() {
        return (alarmData != null) ? alarmData.size() : 0;
    }
}
