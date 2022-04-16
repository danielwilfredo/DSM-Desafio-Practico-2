package udb.fp180271dsm.practico2_fp180271_gh161659;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ConfirmarPedido extends AppCompatActivity {

    private ListView lvPlatos;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference menuRef;
    private double Total = 0;
    TextView txt;
    private String IdFactura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);
        ArrayList<PlatosModel> pedidos =(ArrayList<PlatosModel>)getIntent().getSerializableExtra("Pedidos");
        loadDatainListview(pedidos);
        Log.d("tag",Double.toString(Total));
        txt = findViewById(R.id.txtTotal);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcularTotal();
            }
        });
    }

    private void loadDatainListview(ArrayList<PlatosModel> pedidos) {

        lvPlatos = findViewById(R.id.lvHistorial);
        PedidosListViewAdapter adapter = new PedidosListViewAdapter(ConfirmarPedido.this, pedidos);
        lvPlatos.setAdapter(adapter);

    }

    public void UpdateTotal(int cantidad, double precio)
    {


        Total += cantidad*precio;
    }

    public void CalcularTotal()
    {
        Total=0;
        ArrayAdapter<PlatosModel> datosActuales = (ArrayAdapter<PlatosModel>)lvPlatos.getAdapter();
        for (int i=0; i<datosActuales.getCount();i++)
        {
            UpdateTotal(datosActuales.getItem(i).getCantidad(),datosActuales.getItem(i).getPrecioPlato());
        }
        DecimalFormat form = new DecimalFormat("0.00");
        txt.setText(String.format("Total: $ "+form.format(Total)));
    }

    public void Confirmar(View v) {
        TextView nombre = findViewById(R.id.txtNombreCompleto);
        TextView tel = findViewById(R.id.txtTelefono);
        int lnombre = nombre.getText().toString().length();
        int ltel =tel.getText().toString().length();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        CalcularTotal();
        if(lnombre!=0 && ltel!=0 && ltel==8 && Total>0)
        {
            DecimalFormat form = new DecimalFormat("0.00");
            alert.setTitle("¿Desea confirmar su pedido?");
            alert.setMessage("Su total sera de $ "+form.format(Total));

            alert.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    GuardarPedido();
                }
            });

            alert.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    });

            alert.show();

        }
        else
        {
            Toast.makeText(ConfirmarPedido.this, "Ingrese la información necesaria para completar su pedido", Toast.LENGTH_SHORT).show();
        }

    }

    private void GuardarPedido()
    {
        TextView name = findViewById(R.id.txtNombreCompleto);
        TextView tel = findViewById(R.id.txtTelefono);
        LocalDate todaysDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedLocalDate = todaysDate.format(formatter);
        ArrayAdapter<PlatosModel> datosActuales = (ArrayAdapter<PlatosModel>)lvPlatos.getAdapter();
        ArrayList<PedidoModel> platos = new ArrayList<PedidoModel>();
        IdFactura="";
        for (int i=0; i<datosActuales.getCount();i++)
        {
            PedidoModel pedido = new PedidoModel(
                    datosActuales.getItem(i).getNombrePlato(),
                    datosActuales.getItem(i).getCantidad(),
                    datosActuales.getItem(i).getPrecioPlato()
            );
            //db.collection("pedidos").add(pedido);
            platos.add(pedido);
        }
        FacturaModel factura = new FacturaModel(
                name.getText().toString(),
                tel.getText().toString(),
                formattedLocalDate,
                Total,
                platos);
        db.collection("factura").add(factura).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                IdFactura = documentReference.getId();
                Toast.makeText(ConfirmarPedido.this, "Confirmación de Pedido Exitosa", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(ConfirmarPedido.this, Factura.class);
                in.putExtra("IdFactura", IdFactura);
                startActivity(in);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ConfirmarPedido.this, "Algo salió mal..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}