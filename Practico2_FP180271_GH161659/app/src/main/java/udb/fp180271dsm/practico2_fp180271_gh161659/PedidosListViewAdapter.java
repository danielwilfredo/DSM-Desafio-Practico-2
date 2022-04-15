package udb.fp180271dsm.practico2_fp180271_gh161659;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PedidosListViewAdapter extends ArrayAdapter<PlatosModel> {
    private ArrayList<PlatosModel> pData;
    public PedidosListViewAdapter(@NonNull Context context, ArrayList<PlatosModel> dataModalArrayList) {
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
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.pedido_item, parent, false);
        }
        PlatosModel dataModal = getItem(position);
        TextView txtnombre = listitemView.findViewById(R.id.txtNombrePedido);
        TextView txtcantidad = listitemView.findViewById(R.id.txtCantidadPedido);
        TextView txtprecio= listitemView.findViewById(R.id.txtPrecioPedido);
        txtnombre.setText(dataModal.getNombrePlato());
        txtcantidad.setText(dataModal.getDetallePlato());
        txtprecio.setText("$ "+Integer.toString(dataModal.getPrecioPlato()));

        return listitemView;
    }
}
