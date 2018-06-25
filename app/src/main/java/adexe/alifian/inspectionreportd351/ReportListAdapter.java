package adexe.alifian.inspectionreportd351;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import adexe.alifian.inspectionreportd351.object.ReportObject;

/**
 * Created by adexe on 6/22/18.
 */

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ViewHolder> {

    private ArrayList<ReportObject> datalist;
    private Context context;

    public ReportListAdapter(Context context, ArrayList<ReportObject> datalist){
        this.context = context;
        this.datalist = datalist;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView lbl_no_polisi, lbl_mekanik, lbl_timestamp;
        public Button btn_detail, btn_delete, btn_edit;

        public ViewHolder(View itemView){
            super(itemView);
            lbl_no_polisi = (TextView) itemView.findViewById(R.id.lbl_no_polisi);
            lbl_mekanik = (TextView) itemView.findViewById(R.id.lbl_mekanik);
            lbl_timestamp = (TextView) itemView.findViewById(R.id.lbl_timestamp);

            btn_detail = (Button) itemView.findViewById(R.id.btn_detail);
            btn_delete = (Button) itemView.findViewById(R.id.btn_delete);
            btn_edit = (Button) itemView.findViewById(R.id.btn_edit);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list_item,parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.lbl_timestamp.setText(datalist.get(position).getTimeStamp());
        holder.lbl_mekanik.setText(datalist.get(position).getMekanik());
        holder.lbl_no_polisi.setText(datalist.get(position).getNoPolisi());

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }


}
