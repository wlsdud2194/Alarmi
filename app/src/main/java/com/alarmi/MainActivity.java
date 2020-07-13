package com.alarmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alarmi.database.Alarm;
import com.alarmi.database.AlarmDatabase;
import com.alarmi.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AlarmAdapter.OnItemClickListener {
    private ActivityMainBinding binding;
    private AlarmAdapter adapter;
    private AlarmDatabase db;

    class LoadTask extends AsyncTask<Void, Void, List<Alarm>> {
        @Override
        protected List<Alarm> doInBackground(Void... voids) {
            return db.alarmDAO().findAll();
        }

        @Override
        protected void onPostExecute(List<Alarm> alarms) {
            super.onPostExecute(alarms);
            adapter.setAlarmData(alarms);
            adapter.notifyDataSetChanged();
        }
    }

    class ToggleSwitchTask extends AsyncTask<Alarm, Void, List<Alarm>> {
        @Override
        protected List<Alarm> doInBackground(Alarm... args) {
            Alarm alarm = args[0];
            alarm.setActived(!alarm.getActived()); // 값 수정

            int result = db.alarmDAO().update(alarm); // 업데이트

            if (result == 1) {
                return db.alarmDAO().findAll();
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Alarm> alarms) {
            super.onPostExecute(alarms);
            if (alarms != null) {
                adapter.setAlarmData(alarms);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AlarmDatabase.getInstance(getApplicationContext());
        adapter = new AlarmAdapter(null, this);
        new LoadTask().execute();

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Main",  "getInstanceId failed", task.getException());
                            return;
                        }
                        String token = task.getResult().getToken();
                        Log.d("Main-Token", token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onItemClick(View v, int position, Alarm alarm) {
        // intent 사용
    }

    @Override
    public void onSwitchClick(View v, int position, Alarm alarm) {
        new ToggleSwitchTask().doInBackground(alarm);
    }
}
