package udb.fp180271dsm.practico2_fp180271_gh161659;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {

    private Button btnPlatos, btnHistorial,btnPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        btnPlatos = findViewById(R.id.btnMenu);
    }

    public void AbrirPlatos(View v)
    {
        Intent intent = new Intent(this, PlatillosYBebidas.class);
        startActivity(intent);
    }
}