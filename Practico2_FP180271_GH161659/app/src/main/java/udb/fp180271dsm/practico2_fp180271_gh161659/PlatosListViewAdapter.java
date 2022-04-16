package udb.fp180271dsm.practico2_fp180271_gh161659;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PlatosListViewAdapter extends ArrayAdapter<PlatosModel> {
    private ArrayList<PlatosModel> pData;
    public PlatosListViewAdapter(@NonNull Context context, ArrayList<PlatosModel> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
        pData=dataModalArrayList;
    }
    @Override
    public int getCount() {;
        return pData.size();
    }

    @Override
    public PlatosModel getItem(int position) {
        return pData.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.plato_item, parent, false);
        }
        PlatosModel dataModal = getItem(position);
        TextView txtnombre = listitemView.findViewById(R.id.txtNombrePedido);
        TextView txtdetalle = listitemView.findViewById(R.id.txtDetalle);
        TextView txtprecio= listitemView.findViewById(R.id.txtPrecioPedido);
        CheckBox cbAñadir = listitemView.findViewById(R.id.cbAñadir);
        TextView id = listitemView.findViewById(R.id.txtId);
        txtnombre.setText(dataModal.getNombrePlato());
        txtdetalle.setText(dataModal.getDetallePlato());
        DecimalFormat form = new DecimalFormat("0.00");
        txtprecio.setText("$ "+form.format(dataModal.getPrecioPlato()));
        id.setText(dataModal.getDocumentID());
        cbAñadir.setChecked(dataModal.isAñadidoAlPedido());
        cbAñadir.setTag(position);
        cbAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPos = (int) v.getTag();
                boolean isChecked = false;
                if (pData.get(currentPos).isAñadidoAlPedido()==false){
                    isChecked=true;
                }
                Log.d("response ",currentPos+ " "+isChecked);
                pData.get(currentPos).setAñadidoAlPedido(isChecked);
                notifyDataSetChanged();
            }
        });
        return listitemView;
    }
}
