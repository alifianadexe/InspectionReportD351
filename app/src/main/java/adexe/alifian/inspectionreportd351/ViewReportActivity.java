package adexe.alifian.inspectionreportd351;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;
import com.novoda.merlin.registerable.bind.Bindable;
import com.novoda.merlin.registerable.connection.Connectable;
import com.novoda.merlin.registerable.disconnection.Disconnectable;

import java.util.ArrayList;

import adexe.alifian.inspectionreportd351.baseactivity.AppBaseActivity;
import adexe.alifian.inspectionreportd351.object.ReportObject;

/**
 * Created by Adexe on 6/24/2018.
 */



public class ViewReportActivity extends AppBaseActivity {

    private RecyclerView recycleView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ReportObject> dataReport;

    DatabaseReference databaseReport;
    Merlin merlin;


    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.view_data_layout);

        databaseReport = FirebaseDatabase.getInstance().getReference("Report");

        recycleView = (RecyclerView) findViewById(R.id.recycle_view_list);
        recycleView.setHasFixedSize(true);

        dataReport = new ArrayList<ReportObject>();

        layoutManager  = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);

        merlin = new Merlin.Builder().withBindableCallbacks().withDisconnectableCallbacks().withConnectableCallbacks().withAllCallbacks().build(getApplicationContext());

        merlin.registerConnectable(new Connectable() {
            @Override
            public void onConnect() {
                getStatus("Get Complete! ", android.R.color.holo_green_dark);
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
                getStatus("Getting Data..", android.R.color.holo_blue_dark);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        //atach value event Listener
        databaseReport.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // clear prev data
                dataReport.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Log.e("REPORT",postSnapshot.toString());
                    ReportObject report = postSnapshot.getValue(ReportObject.class);
                    dataReport.add(report);
                }

                adapter = new ReportListAdapter(ViewReportActivity.this, dataReport,databaseReport);
                recycleView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getStatus(String str, int status){

        TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), str, TSnackbar.LENGTH_LONG);
        //snackbar.setActionTextColor(Color.WHITE);

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(this,status));

        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public void  searchQuery(){
//        databaseReference.orderByChild('_searchLastName')
//                .startAt(queryText)
//                .endAt(queryText+"\uf8ff")
    }


}
