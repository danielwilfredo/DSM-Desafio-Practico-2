package udb.fp180271dsm.practico2_fp180271_gh161659;

import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HistorialListViewAdapter extends ArrayAdapter<FacturaModel> {
    private ArrayList<FacturaModel> pData;
    public double total;
    public HistorialListViewAdapter(@NonNull Context context, ArrayList<FacturaModel> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
        pData=dataModalArrayList;
        total=0;
    }
    @Override
    public int getCount() {;
        return pData.size();
    }

    @Override
    public FacturaModel getItem(int position) {
        return pData.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.historial_item, parent, false);
        }
        FacturaModel dataModal = getItem(position);
        TextView txtnombre = listitemView.findViewById(R.id.txtClienteH);
        TextView txtOrden= listitemView.findViewById(R.id.txtordenH);
        TextView txttotal= listitemView.findViewById(R.id.txtTotalFH);
        TextView txtResumen= listitemView.findViewById(R.id.txtResumenH);
        TextView txtFecha= listitemView.findViewById(R.id.txtFechaH);
        TextView txttel= listitemView.findViewById(R.id.txtTelefonoPH);
        DecimalFormat form = new DecimalFormat("0.00");
        txtOrden.setText(dataModal.getOrden());
        txtnombre.setText(dataModal.getNombreCompleto());
        txttotal.setText("$ "+form.format(dataModal.getTotalPedido()));
        txtFecha.setText(dataModal.getFecha());
        txttel.setText(dataModal.getTelefono());
        JSONArray jsArray = new JSONArray(dataModal.getPlatos());
        String Resumen="";
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
        return listitemView;
    }
}
