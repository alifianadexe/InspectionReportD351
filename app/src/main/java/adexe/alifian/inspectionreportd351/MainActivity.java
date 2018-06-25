package adexe.alifian.inspectionreportd351;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.androidadvance.topsnackbar.TSnackbar;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;
import com.novoda.merlin.registerable.bind.Bindable;
import com.novoda.merlin.registerable.connection.Connectable;
import com.novoda.merlin.registerable.disconnection.Disconnectable;

import adexe.alifian.inspectionreportd351.baseactivity.AppBaseActivity;

public class MainActivity extends AppBaseActivity {

    public CardView crd_report , crd_view_report, crd_manage_report;
    Merlin merlin;

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

        crd_view_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ViewReportActivity.class);
                startActivity(i);
            }
        });
        merlin = new Merlin.Builder().withBindableCallbacks().withDisconnectableCallbacks().withConnectableCallbacks().withAllCallbacks().build(getApplicationContext());

        merlin.registerConnectable(new Connectable() {
            @Override
            public void onConnect() {
                getStatus("Network Connected!", android.R.color.holo_green_dark);
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

        TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), str, TSnackbar.LENGTH_LONG);
//      snackbar.setActionTextColor(Color.WHITE);

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(this,status));

        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

}
