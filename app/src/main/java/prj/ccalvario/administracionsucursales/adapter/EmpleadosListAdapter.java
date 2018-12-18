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
import prj.ccalvario.administracionsucursales.model.Empleado;


public class EmpleadosListAdapter extends RecyclerView.Adapter<EmpleadosListAdapter.EmpleadoViewHolder> {

    private final LayoutInflater mInflater;
    private List<Empleado> mEmpleados;

    public EmpleadosListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public EmpleadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_empleado, parent, false);
        EmpleadoViewHolder viewHOlder = new EmpleadoViewHolder(itemView);

        return viewHOlder;
    }

    @Override
    public void onBindViewHolder(EmpleadoViewHolder holder, int position) {
        if (mEmpleados != null) {
            holder.mTextViewNombre.setText(mEmpleados.get(position).getNombre());
            holder.mTextViewPuesto.setText(mEmpleados.get(position).getPuesto());
            holder.mTextViewRfc.setText(mEmpleados.get(position).getRfc());
        }
    }

    public void setEmpleados(List<Empleado> empleados){
        mEmpleados = empleados;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mEmpleados != null)
            return mEmpleados.size();
        else return 0;
    }

    class EmpleadoViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewNombre;
        private final TextView mTextViewPuesto;
        private final TextView mTextViewRfc;;

        private EmpleadoViewHolder(View itemView) {
            super(itemView);
            mTextViewNombre = itemView.findViewById(R.id.recyclrerView_textView_nombre);
            mTextViewPuesto = itemView.findViewById(R.id.recyclrerView_textView_puesto);
            mTextViewRfc = itemView.findViewById(R.id.recyclrerView_textView_rfc);
        }
    }
}
