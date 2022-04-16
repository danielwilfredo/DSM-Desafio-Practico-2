package udb.fp180271dsm.practico2_fp180271_gh161659;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PedidosListViewAdapter extends ArrayAdapter<PlatosModel> {
    private ArrayList<PlatosModel> pData;
    public double total;
    public PedidosListViewAdapter(@NonNull Context context, ArrayList<PlatosModel> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
        pData=dataModalArrayList;
        total=0;
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
        txtcantidad.setText((dataModal.getCantidad()==0)?(Integer.toString(1)):Integer.toString(dataModal.getCantidad()));
        dataModal.setCantidad(Integer.parseInt(txtcantidad.getText().toString()));
        Log.d("PROBANDO",Integer.toString(dataModal.getCantidad()));
        DecimalFormat form = new DecimalFormat("0.00");
        txtprecio.setText("$ "+form.format(dataModal.getPrecioPlato()));
        txtcantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()!=0)
                {
                    dataModal.setCantidad(Integer.parseInt(editable.toString()));
                }
                else
                {

                }

            }
        });
        return listitemView;
    }
}
