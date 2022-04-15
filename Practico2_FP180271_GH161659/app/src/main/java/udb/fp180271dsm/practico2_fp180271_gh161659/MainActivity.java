package udb.fp180271dsm.practico2_fp180271_gh161659;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int countSeconds=0;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtLoading = findViewById(R.id.txtLoading);
        text = "cargando";
        Handler handler = new Handler();
        while(countSeconds <= 2) {
            countSeconds++;
        handler.postDelayed(new Runnable() {
            public void run() {
                text = text+".";
                    txtLoading.setText(text);
            }
        }, 1000);
        }

        final Handler myHandler = new Handler();
        myHandler.postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }




}