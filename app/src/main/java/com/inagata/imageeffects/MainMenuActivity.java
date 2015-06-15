package com.inagata.imageeffects;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by mnafian on 6/16/15.
 */
public class MainMenuActivity extends ActionBarActivity implements View.OnClickListener{

    private Button btEffectFactory, btLayerDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_layout);

        btEffectFactory = (Button) findViewById(R.id.bt_gles);
        btLayerDrawable = (Button) findViewById(R.id.bt_layerd);

        btLayerDrawable.setOnClickListener(this);
        btEffectFactory.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about_githubmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.About:
                Intent inAbout = new Intent(this, AboutMe.class);
                startActivity(inAbout);
                break;

            case R.id.Github:
                String url = "https://github.com/mnafian";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent actIntent;

        switch (v.getId()){
            case R.id.bt_gles:
                actIntent = new Intent(this, EffectsFilterActivity.class);
                startActivity(actIntent);
                break;
            case R.id.bt_layerd:
                actIntent = new Intent(this, LayerDrawable.class);
                startActivity(actIntent);
                break;
        }


    }
}
