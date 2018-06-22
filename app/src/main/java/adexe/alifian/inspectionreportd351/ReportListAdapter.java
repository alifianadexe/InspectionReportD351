package adexe.alifian.inspectionreportd351;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by adexe on 6/22/18.
 */

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ViewHolder> {

    private ArrayList<String> datalist;
    private Context context;

    public ReportListAdapter(Context context, ArrayList<String> datalist){
        this.context = context;
        this.datalist = datalist;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView lbl_no_polisi, lbl_mekanik, lbl_timestamp;
        public Button btn_detail, btn_delete, btn_edit;

        public ViewHolder(View itemView){
            super(itemView);
            lbl_no_polisi = (TextView) itemView.findViewById(R.id.lbl_no_polisi);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
