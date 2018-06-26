package adexe.alifian.inspectionreportd351;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

import adexe.alifian.inspectionreportd351.object.ReportObject;

/**
 * Created by adexe on 6/22/18.
 */

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ViewHolder> {

    private ArrayList<ReportObject> datalist;
    private Context context;
    private DatabaseReference databaseReport;
    public ReportListAdapter(Context context, ArrayList<ReportObject> datalist, DatabaseReference databaseReport){

        this.context = context;
        this.datalist = datalist;
        this.databaseReport = databaseReport;

        if(datalist.size() == 0){
            Toast.makeText(context,"Nothing to show",Toast.LENGTH_LONG).show();
        }
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
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.lbl_timestamp.setText(datalist.get(position).getTimeStamp());
        holder.lbl_mekanik.setText(datalist.get(position).getMekanik());
        holder.lbl_no_polisi.setText(datalist.get(position).getNoPolisi());

        final int getPosition = position;

        holder.btn_detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                //custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.detail_popups);
                dialog.setTitle("Detail");

                TextView lbl_no_customer = (TextView) dialog.findViewById(R.id.lbl_no_customer);
                TextView lbl_catatan = (TextView) dialog.findViewById(R.id.lbl_catatan);
                TextView lbl_tipe = (TextView) dialog.findViewById(R.id.lbl_tipe);

                lbl_catatan.setText(datalist.get(getPosition).getCatatan());
                lbl_no_customer.setText(datalist.get(getPosition).getNoTelpCustomer());
                lbl_tipe.setText(datalist.get(getPosition).getType());

                Button dialogClose = (Button) dialog.findViewById(R.id.btn_close_popups);
                // if button dialog

                dialogClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case DialogInterface.BUTTON_POSITIVE:
                                deleteReport(datalist.get(position).getReportId());
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Hapus Report ini?").setPositiveButton("Yes",dialog).setNegativeButton("No",dialog).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public void deleteReport(String id){

        // set database remove value
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Report").child(id);
        // remove value
        dr.removeValue();

        Toast.makeText(context,"Report Deleted!",Toast.LENGTH_LONG).show();
    }

    public void updateReport(String no_polisi,String mekanik, String catatan, String tipe, String nowDate, String id,String no_customer){

        //creating a report object
        ReportObject reportObject = new ReportObject(no_polisi,mekanik,catatan,tipe,id, nowDate,no_customer);
    }
}
