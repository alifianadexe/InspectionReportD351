package adexe.alifian.inspectionreportd351;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

                adapter = new ReportListAdapter(ViewReportActivity.this, dataReport);
                recycleView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
