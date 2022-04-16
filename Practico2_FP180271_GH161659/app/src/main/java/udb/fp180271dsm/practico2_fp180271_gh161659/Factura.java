package udb.fp180271dsm.practico2_fp180271_gh161659;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Factura extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference docRef;
    private TextView txtOrden;
    private TextView txtFecha;
    private TextView txtCliente;
    private TextView txtTel;
    private TextView txtResumen;
    private TextView txtTotal;
    private String Resumen="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
        String IdFactura = getIntent().getStringExtra("IdFactura");
        docRef = db.collection("factura").document(IdFactura);
        txtCliente = findViewById(R.id.txtCliente);
        txtFecha = findViewById(R.id.txtFecha);
        txtTotal = findViewById(R.id.txtTotalF);
        txtTel = findViewById(R.id.txtTelefonoP);
        txtResumen = findViewById(R.id.txtResumen);
        txtOrden = findViewById(R.id.txtorden);
        txtOrden.setText(IdFactura);
        CargarFactura();
    }

    private void CargarFactura()
    {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                       txtCliente.setText(document.get("nombreCompleto").toString());
                       txtFecha.setText(document.get("fecha").toString());
                       txtTel.setText(document.get("telefono").toString());
                       DecimalFormat form = new DecimalFormat("0.00");
                       txtTotal.setText("$" +form.format(document.get("totalPedido")));

                        ArrayList<PedidoModel> platos = (ArrayList<PedidoModel>)document.get("platos");
                        Log.d("TAG", platos.toString());
                        JSONArray jsArray = new JSONArray(platos);
                        Log.d("TAG", jsArray.toString());
                        for(int i =0;i<jsArray.length();i++)
                        {
                            try {
                                JSONObject obj = jsArray.getJSONObject(i);
                                Resumen +=("- $"+form.format(obj.get("precio"))+" x "+ obj.get("cantidad") +" --> "+obj.get("nombrePlato")+"\n");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                       txtResumen.setText(Resumen);
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
    }

    private void VolverMenu(View v)
    {
        Intent in = new Intent(Factura.this,MenuPrincipal.class);
        startActivity(in);
        finish();
    }
}