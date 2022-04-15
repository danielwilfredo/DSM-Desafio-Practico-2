package udb.fp180271dsm.practico2_fp180271_gh161659;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
                startActivity(new Intent(PlatillosYBebidas.this,ConfirmarPedido.class));
            }
        });
    }

    private void setUpRecyclerView()
    {
        lvPlatos = findViewById(R.id.lvPlatos);
        dataModalArrayList = new ArrayList<>();
        loadDatainListview();
    }

    private void loadDatainListview() {
        db.collection("menu").orderBy("nombrePlato").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                PlatosModel dataModal = d.toObject(PlatosModel.class);
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