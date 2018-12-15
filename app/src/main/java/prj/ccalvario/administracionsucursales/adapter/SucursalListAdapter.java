package prj.ccalvario.administracionsucursales.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.model.Sucursal;

public class SucursalListAdapter extends RecyclerView.Adapter<SucursalListAdapter.SucursalViewHolder> {

    private CustomItemClickListener mListener;
    private final LayoutInflater mInflater;
    private List<Sucursal> mSucursales;

    public SucursalListAdapter(Context context, CustomItemClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }

    @Override
    public SucursalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        SucursalViewHolder viewHOlder = new SucursalViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, viewHOlder.getAdapterPosition());
            }
        });

        viewHOlder.mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onEditClick(v, viewHOlder.getAdapterPosition());
            }
        });

        return viewHOlder;
    }

    @Override
    public void onBindViewHolder(SucursalViewHolder holder, int position) {
        if (mSucursales != null) {
            Sucursal current = mSucursales.get(position);
            holder.mTextViewNombre.setText(current.getNombre());
        } else {
            holder.mTextViewNombre.setText("Vacío");
        }
    }

    public void setSucursales(List<Sucursal> sucursales){
        mSucursales = sucursales;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mSucursales != null)
            return mSucursales.size();
        else return 0;
    }

    class SucursalViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewNombre;
        private final TextView mTextViewNumEmpleados;
        private final Button mButtonEdit;

        private SucursalViewHolder(View itemView) {
            super(itemView);
            mTextViewNombre = itemView.findViewById(R.id.recyclrerView_textView_nombre);
            mTextViewNumEmpleados = itemView.findViewById(R.id.recyclrerView_textView_numEmpleados);
            mButtonEdit = itemView.findViewById(R.id.recyclerView_button_edit);
        }
    }
}
