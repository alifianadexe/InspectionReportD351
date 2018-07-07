package adexe.alifian.inspectionreportd351.baseactivity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.FrameLayout;

/**
 * Created by adexe on 6/21/18.
 */

public class AppBaseActivity extends AppCompatActivity{

    //inisiasi
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstace){
        super.onCreate(savedInstace);

//        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
//        navigationView = (NavigationView) findViewById(R.id.navigation_view);
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0,0);
//        drawerLayout.setDrawerListener(drawerToggle);
          getSupportActionBar().setDisplayShowTitleEnabled(false);

        // membuat listener setiap menu
//        menu = navigationView.getMenu();
//        for (int i = 0; i < menu.size(); i++){
//            menu.getItem(i).setOnMenuItemClickListener(this);
//        }
    }

//    @Override
//    protected void onPostCreate(Bundle savedInstance){
//        super.onPostCreate(savedInstance);
//        drawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration config){
//        super.onConfigurationChanged(config);
//        drawerToggle.onConfigurationChanged(config);
//    }
//
//    @Override
//    public void setContentView(int reslayoutId){
//        if(frameLayout != null){
//            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
//            );
//            View stubView = inflater.inflate(reslayoutId, frameLayout, false);
//            frameLayout.addView(stubView);
//        }
//    }
//
//    @Override
//    public void setContentView(View view){
//        if(frameLayout != null){
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
//            );
//            frameLayout.addView(view, lp);
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem menuItem){
//        if(drawerToggle.onOptionsItemSelected(menuItem)){
//            return true;
//        }
//        return super.onOptionsItemSelected(menuItem);
//    }
//
//    @Override
//    public boolean onMenuItemClick(MenuItem menuItem) {
//        switch (menuItem.getItemId()){
//            case R.id.insert_data:
//                Intent i = new Intent(getApplicationContext(),InsertReportActivity.class);
//                startActivity(i);
//                Snackbar.make(drawerLayout, "Insert Data", Snackbar.LENGTH_SHORT).show();
//                break;
//            case R.id.management_data:
//                Intent i1 = new Intent(getApplicationContext(), ViewReportActivity.class);
//                startActivity(i1);
//                break;
//            case R.id.manage_user:
//                Snackbar.make(drawerLayout, "Manage User", Snackbar.LENGTH_SHORT).show();
//                break;
//            case R.id.about:
//                Snackbar.make(drawerLayout, "About", Snackbar.LENGTH_SHORT).show();
//                break;
//            case R.id.contact:
//                Snackbar.make(drawerLayout, "Contact", Snackbar.LENGTH_SHORT).show();
//                break;
//            case R.id.help:
//                Snackbar.make(drawerLayout, "Help", Snackbar.LENGTH_SHORT).show();
//                break;
//        }
//        return false;
//    }

}
