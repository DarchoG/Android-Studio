package com.example.p3;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ParallelExample";
    private Button btnExecute;
    private ProgressBar progress1, progress2, progress3, progress4;
    private Handler handler;
    private int completedTasks = 0;
    private int auxiliarTask = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExecute = findViewById(R.id.button);
        progress1 = findViewById(R.id.progressBar);
        progress2 = findViewById(R.id.progressBar2);
        progress3 = findViewById(R.id.progressBar3);
        progress4 = findViewById(R.id.progressBar4);
        handler = new Handler(Looper.getMainLooper());

        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeParallelTasks();
            }
        });
    }

    private void executeParallelTasks() {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 100; i++) {
            final int taskNumber = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Log.d(TAG, "Tarea completada: " + taskNumber);
                    updateProgressBar();
                }
            });
        }

        executor.shutdown();
    }

    private synchronized void updateProgressBar() {
        completedTasks++;
        auxiliarTask++;

        handler.post(new Runnable() {
            @Override
            public void run() {

                if(auxiliarTask >= 25){

                    auxiliarTask = 0;

                }

                int progress = (auxiliarTask * 100) / 25;

                // Actualiza las barras de progreso en función del número de tareas completadas
                if (completedTasks <= 25) {

                    progress1.setProgress(progress);

                } else if (completedTasks <= 50) {

                    progress2.setProgress(progress);
                    progress1.setProgress(100);

                } else if (completedTasks <= 75) {

                    progress3.setProgress(progress);
                    progress2.setProgress(100);

                } else if (completedTasks <= 99) {

                    progress4.setProgress(progress);
                    progress3.setProgress(100);
                }
                else if (completedTasks == 100){

                    progress4.setProgress((100));
                }
            }
        });
    }
}