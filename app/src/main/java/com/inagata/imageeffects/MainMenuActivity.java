package com.inagata.imageeffects;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
