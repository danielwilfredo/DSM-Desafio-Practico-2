package udb.fp180271dsm.practico2_fp180271_gh161659;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PlatillosYBebidas extends AppCompatActivity {

    private ListView lvPlatos;
    ArrayList<PlatosModel> dataModalArrayList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference menuRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platillos_ybebidas);
        ProgressBar pg= findViewById(R.id.progressBar);
        ExtendedFloatingActionButton btnConfirmarPedido = findViewById(R.id.btnRealizarPedido);
        pg.setVisibility(View.VISIBLE);
        menuRef = db.collection("menu");
        setUpRecyclerView();
        pg.setVisibility(View.INVISIBLE);
        btnConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<PlatosModel> listapedidos = new ArrayList<PlatosModel>();
                ArrayAdapter<PlatosModel> adp = (ArrayAdapter<PlatosModel>) lvPlatos.getAdapter();
                int count = adp.getCount();
                int coincidencias =0;
                for (int i =0;i<count;i++)
                {
                    PlatosModel row = adp.getItem(i);
                    if(row.isAñadidoAlPedido())
                    {
                        PlatosModel md = new PlatosModel();
                        md.setAñadidoAlPedido(true);
                        md.setCantidad(0);
                        md.setNombrePlato(row.getNombrePlato());
                        md.setDocumentID(row.getDocumentID());
                        md.setPrecioPlato(row.getPrecioPlato());
                        md.setDetallePlato(row.getDetallePlato());
                        listapedidos.add(md);
                        coincidencias++;
                    }
                }
                if(coincidencias!=0)
                {
                    Intent in = new Intent(PlatillosYBebidas.this,ConfirmarPedido.class);
                    in.putExtra("Pedidos", (ArrayList<PlatosModel>) listapedidos);
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(PlatillosYBebidas.this, "Selecciona una opción del menu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUpRecyclerView()
    {
        lvPlatos = findViewById(R.id.lvHistorial);
        dataModalArrayList = new ArrayList<>();
        loadDatainListview();
    }

    private void loadDatainListview() {
        db.collection("menu").orderBy("detallePlato", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                PlatosModel dataModal = d.toObject(PlatosModel.class);
                                dataModal.setDocumentID(d.getId());
                                dataModalArrayList.add(dataModal);
                            }
                            PlatosListViewAdapter adapter = new PlatosListViewAdapter(PlatillosYBebidas.this, dataModalArrayList);
                            lvPlatos.setAdapter(adapter);
                        } else {
                            Toast.makeText(PlatillosYBebidas.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PlatillosYBebidas.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
            }
        });
}}