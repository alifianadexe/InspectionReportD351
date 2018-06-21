package adexe.alifian.inspectionreportd351;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import adexe.alifian.inspectionreportd351.baseactivity.AppBaseActivity;

public class MainActivity extends AppBaseActivity {

    public CardView crd_report , crd_view_report, crd_manage_report;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crd_report = (CardView) findViewById(R.id.crd_report);
        crd_view_report = (CardView) findViewById(R.id.crd_view_report);
        crd_manage_report = (CardView) findViewById(R.id.crd_manage_user);

        crd_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),InsertReportActivity.class);
                startActivity(i);
            }
        });

    }




}
