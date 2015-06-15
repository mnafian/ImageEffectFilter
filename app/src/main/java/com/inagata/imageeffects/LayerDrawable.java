package com.inagata.imageeffects;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class LayerDrawable extends ActionBarActivity {
    private ImageView testimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layer_drawable);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setTitle("Layer Drawable");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        testimage = (ImageView) findViewById(R.id.testimage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Resources r = getResources();
        Drawable[] layers = new Drawable[2];
        layers[0] = r.getDrawable(R.drawable.nabilah);


        //noinspection SimplifiableIfStatement
        /* choose wall layer option on top layer */
        switch (id) {
            case R.id.wall1:
                layers[1] = r.getDrawable(R.drawable.wall1box);
                layers[1].setAlpha(90);
                break;

            case R.id.wall2:
                layers[1] = r.getDrawable(R.drawable.wall2box);
                layers[1].setAlpha(90);
                break;

            case R.id.wall3:
                layers[1] = r.getDrawable(R.drawable.wall3box);
                layers[1].setAlpha(90);
                break;

            case R.id.wall4:
                layers[1] = r.getDrawable(R.drawable.wall4box);
                layers[1].setAlpha(90);
                break;

            case R.id.wall5:
                layers[1] = r.getDrawable(R.drawable.wall5box);
                layers[1].setAlpha(90);
                break;

            case R.id.wall6:
                layers[1] = r.getDrawable(R.drawable.wall6box);
                layers[1].setAlpha(90);
                break;

            case R.id.wall7:
                layers[1] = r.getDrawable(R.drawable.wall7box);
                layers[1].setAlpha(90);
                break;

            case R.id.wall8:
                layers[1] = r.getDrawable(R.drawable.wall8box);
                layers[1].setAlpha(90);
                break;

            case R.id.wall9:
                layers[1] = r.getDrawable(R.drawable.wall9box);
                layers[1].setAlpha(90);
                break;

            case R.id.wall10:
                layers[1] = r.getDrawable(R.drawable.wall10box);
                layers[1].setAlpha(90);
                break;

            case R.id.wall11:
                layers[1] = r.getDrawable(R.drawable.wall11box);
                layers[1].setAlpha(90);
                break;

            case R.id.wall12:
                layers[1] = r.getDrawable(R.drawable.wall12box);
                layers[1].setAlpha(90);
                break;

            case android.R.id.home:
                finish();
                return true;
        }
        android.graphics.drawable.LayerDrawable layerDrawable = new android.graphics.drawable.LayerDrawable(layers);
        testimage.setImageDrawable(layerDrawable);
        testimage.destroyDrawingCache();

        return super.onOptionsItemSelected(item);
    }
}
