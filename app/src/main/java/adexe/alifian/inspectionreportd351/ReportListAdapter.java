package adexe.alifian.inspectionreportd351;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
                dialog.setTitle(datalist.get(position).getNoPolisi());

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

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // custom dialog edit
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.edit_popups);
                dialog.setTitle("Edit");

                final EditText txt_no_polisi = (EditText) dialog.findViewById(R.id.txt_no_polisi);
                final EditText txt_no_customer = (EditText) dialog.findViewById(R.id.txt_customer);
                final EditText txt_catatan = (EditText) dialog.findViewById(R.id.txt_catatan);

                final Spinner lst_tipe = (Spinner) dialog.findViewById(R.id.spn_mekanik);
                final Spinner lst_mekanik = (Spinner) dialog.findViewById(R.id.spn_tipe);

                Button btn_update = (Button) dialog.findViewById(R.id.btn_update);

                txt_no_polisi.setText(datalist.get(position).getNoPolisi());
                txt_no_customer.setText(datalist.get(position).getNoTelpCustomer());
                txt_catatan.setText(datalist.get(position).getCatatan());

                List<String> list_mekanik = new ArrayList<String>();
                list_mekanik.add("-Pilih Mekanik-");
                list_mekanik.add("Reza");
                list_mekanik.add("Sapta");
                list_mekanik.add("Abi");
                list_mekanik.add("Chandika");
                list_mekanik.add("Trimo");
                list_mekanik.add("Eko");


                ArrayAdapter<String> adp_mekanik = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,list_mekanik);
                adp_mekanik.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                lst_mekanik.setAdapter(adp_mekanik);

                List<String> list_tipe = new ArrayList<String>();
                list_tipe.add("(none)");
                list_tipe.add("Xenia");
                list_tipe.add("Sirion");
                list_tipe.add("Ayla");
                list_tipe.add("Sigra");
                list_tipe.add("Grandmax");
                list_tipe.add("Luxio");
                list_tipe.add("Terios");
                list_tipe.add("Himax");
                list_tipe.add("Taruna");
                list_tipe.add("Zebra");

                ArrayAdapter<String> adp_tipe = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,list_tipe);
                adp_tipe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                lst_tipe.setAdapter(adp_tipe);

                lst_mekanik.setSelection(adp_mekanik.getPosition(datalist.get(position).getMekanik()));
                lst_tipe.setSelection(adp_tipe.getPosition(datalist.get(position).getType()));

                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DialogInterface.OnClickListener fandom = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        updateReport(txt_no_polisi.getText().toString(),lst_mekanik.getSelectedItem().toString(),txt_catatan.getText().toString(),lst_tipe.getSelectedItem().toString(),datalist.get(position).getTimeStamp(),datalist.get(position).getReportId(),txt_no_customer.getText().toString());
                                        dialog.dismiss();
                                        Toast.makeText(context,"Report Updated!",Toast.LENGTH_LONG).show();
                                        break;
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        break;
                                }
                            }

                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Update Report ?").setPositiveButton("Yes",fandom).setNegativeButton("No",fandom).show();
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

        // set database reference
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Report").child(id);

        //update value
        dr.setValue(reportObject);
    }
}
