package gears.com.lab_app_2;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import gears.com.lab_app_2.dummy.CryptoMenuItem;
import gears.com.lab_app_2.fragments.cryptoDetailFragment;

public class MainActivity extends AppCompatActivity implements MenuItemFragment.OnListFragmentInteractionListener,cryptoDetailFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();


        MenuItemFragment fragment = MenuItemFragment.newInstance(1);
        FragmentTransaction v = fm.beginTransaction();
        v.add(R.id.frame_layout_portrait,fragment);
        v.commit();

    }


    @Override
    public void onListFragmentInteraction(CryptoMenuItem.CryptoItem item) {
        cryptoDetailFragment t = cryptoDetailFragment.newInstance(item.id,item.content,item.getDetails());

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_landscape_item,t).addToBackStack(null).commit();
        }
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_portrait,t).addToBackStack(null).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}