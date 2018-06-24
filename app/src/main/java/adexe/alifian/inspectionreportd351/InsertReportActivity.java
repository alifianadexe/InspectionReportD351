package adexe.alifian.inspectionreportd351;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;
import com.novoda.merlin.registerable.bind.Bindable;
import com.novoda.merlin.registerable.connection.Connectable;
import com.novoda.merlin.registerable.disconnection.Disconnectable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import adexe.alifian.inspectionreportd351.baseactivity.AppBaseActivity;
import adexe.alifian.inspectionreportd351.object.ReportObject;

/**
 * Created by adexe on 6/21/18.
 */

public class InsertReportActivity extends AppBaseActivity{

    // use this in another activity
    public static final String REPORT_ID = "adexe.alifian.inspectionreportd351.reportid";
    public static final String REPORT_POLICE_NUMBER = "adexe.alifian.inspectionreportd351.reportid";

    //inisiasi
    EditText txt_no_polisi, txt_no_costumer, txt_catatan;
    Spinner lst_tipe, lst_mekanik;
    Button btn_insert;
    Merlin merlin;
    ArrayList<ReportObject> report;

    DatabaseReference databaseReport;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.insert_data_layout);

        //get an database referrence
        databaseReport = FirebaseDatabase.getInstance().getReference("Report");


        // inisiasi nama view
        txt_no_polisi = (EditText) findViewById(R.id.txt_no_polisi);
        txt_no_costumer = (EditText) findViewById(R.id.txt_no_customer);
        txt_catatan = (EditText) findViewById(R.id.txt_catatan);

        lst_tipe = (Spinner) findViewById(R.id.lst_tipe);
        lst_mekanik = (Spinner) findViewById(R.id.lst_mekanik);

        btn_insert = (Button) findViewById(R.id.btn_insert);

        List<String> list_mekanik = new ArrayList<String>();
        list_mekanik.add("Adi");
        list_mekanik.add("Domber");
        list_mekanik.add("Adexe");

        ArrayAdapter<String> adp_mekanik = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list_mekanik);

        adp_mekanik.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lst_mekanik.setAdapter(adp_mekanik);

        List<String> list_tipe = new ArrayList<String>();
        list_tipe.add("AVANZA");
        list_tipe.add("TOYOTA");
        list_tipe.add("MAKENKI");
        list_tipe.add("DROP");

        ArrayAdapter<String> adp_tipe = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list_tipe);

        adp_tipe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lst_tipe.setAdapter(adp_tipe);



        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addReport();
            }
        });

        merlin = new Merlin.Builder().withConnectableCallbacks().build(getApplicationContext());
        merlin.registerConnectable(new Connectable() {
            @Override
            public void onConnect() {
                getStatus("Network Connected!", android.R.color.holo_green_light);
            }
        });

        merlin.registerDisconnectable(new Disconnectable() {
            @Override
            public void onDisconnect() {
                getStatus("Network Disconnected!", android.R.color.holo_red_light);
            }
        });

        merlin.registerBindable(new Bindable() {
            @Override
            public void onBind(NetworkStatus networkStatus) {
                getStatus("Connecting..", android.R.color.holo_blue_dark);
            }
        });
    }

    public void addReport(){
        // values
        String no_polisi = txt_no_polisi.getText().toString().trim();
        String catatan = txt_catatan.getText().toString();
        String no_customer = txt_no_costumer.getText().toString();
        String tipe = lst_tipe.getSelectedItem().toString();
        String mekanik = lst_mekanik.getSelectedItem().toString();


        if(!TextUtils.isEmpty(no_polisi)){

            //get now Date

            String nowDate = Calendar.getInstance().getTime().toString();

            // generate unique id using push().getKey()
            String id = databaseReport.push().getKey();

            //creating a report object
            ReportObject reportObject = new ReportObject(id,nowDate,mekanik,no_polisi,catatan,no_customer,tipe);

            //save
            databaseReport.child(id).setValue(reportObject);

            Toast.makeText(this, "Report Added!", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "No Polisi Wajib Diisi!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        merlin.bind();
    }

    @Override
    protected void onPause(){
        super.onPause();
        merlin.unbind();
    }

    public void getStatus(String str, int status){

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),str, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();

        view.setBackgroundColor(ContextCompat.getColor(this, status));

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;

        view.setLayoutParams(params);
        snackbar.show();

    }
 }
