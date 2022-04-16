package udb.fp180271dsm.practico2_fp180271_gh161659;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Historiales extends AppCompatActivity {

    EditText txtnumero;
    ListView lvHistorial;
    ArrayList<FacturaModel> dataModalArrayList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference facturaRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historiales);
        txtnumero = findViewById(R.id.txtHistorialTel);
        facturaRef = db.collection("factura");
    }

    public void BuscarHistorial(View v)
    {
        lvHistorial = findViewById(R.id.lvHistorial);
        dataModalArrayList = new ArrayList<>();
        loadDatainListview(txtnumero.getText().toString());
    }

    private void loadDatainListview(String tel) {
       facturaRef.whereEqualTo("telefono",tel).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                FacturaModel dataModal = d.toObject(FacturaModel.class);
                                dataModal.setOrden(d.getId());
                                dataModal.setPlatos((ArrayList<PedidoModel>)d.get("platos"));;
                                dataModalArrayList.add(dataModal);
                            }
                            HistorialListViewAdapter adapter = new HistorialListViewAdapter(Historiales.this, dataModalArrayList);
                            lvHistorial.setAdapter(adapter);
                        } else {
                            Toast.makeText(Historiales.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Historiales.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
            }
        });
}}