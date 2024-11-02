package com.example.p2;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Orden para crear una nueva clase.
        // 1°.- Necesito crear un intent denotando una transición, toma en consideración dos argumentos (Origen, Destino)
        // 2°.- Crear su archivo Java donde se le incorpore su extends y el método override, el método override modifica un método herado (polimorfismo)
        // 2.1°.- Es modificado el evento onCreate y asignado lo necesario.
        // 3°.- Creamos su layout XML.
        // 4°.- Incorporamos su manifiest, de manera aútomatica tendremos múltiples aplicaciones, aunque si es eliminado los permisos de arranque es compactada en una, sirve para debuggear.

        Thread thread = new Thread(){ // Crear un objeto de clase hilo.

            @Override public void run(){

                try{sleep(3000);}
                catch (InterruptedException Error){ Error.printStackTrace();}
                finally {

                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    startActivity(intent);
                }
            }
        };

        thread.start();
    }
}