package com.inagata.imageeffects.Fragment;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.inagata.imageeffects.Adapter.FilterAdapterDrawable;
import com.inagata.imageeffects.R;
import com.inagata.imageeffects.Utilities.RecyclerItemClickListener;


public class LayerDrawableFragment extends Fragment {
    private ImageView testimage;
    private RecyclerView recList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.imf_layer_drawable, container, false);
        testimage = (ImageView) view.findViewById(R.id.testimage);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recList = (RecyclerView) view.findViewById(R.id.rc_filter);
        recList.setHasFixedSize(true);
        recList.setLayoutManager(layoutManager);

        FilterAdapterDrawable filterAdapter = new FilterAdapterDrawable(getActivity());
        recList.setAdapter(filterAdapter);

        recList.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.nabilah);
                switch (position) {
                    case 0:
                        layers[1] = r.getDrawable(R.drawable.wall1box);
                        layers[1].setAlpha(90);
                        break;

                    case 1:
                        layers[1] = r.getDrawable(R.drawable.wall2box);
                        layers[1].setAlpha(90);
                        break;

                    case 2:
                        layers[1] = r.getDrawable(R.drawable.wall3box);
                        layers[1].setAlpha(90);
                        break;

                    case 3:
                        layers[1] = r.getDrawable(R.drawable.wall4box);
                        layers[1].setAlpha(90);
                        break;

                    case 4:
                        layers[1] = r.getDrawable(R.drawable.wall5box);
                        layers[1].setAlpha(90);
                        break;

                    case 5:
                        layers[1] = r.getDrawable(R.drawable.wall6box);
                        layers[1].setAlpha(90);
                        break;

                    case 6:
                        layers[1] = r.getDrawable(R.drawable.wall7box);
                        layers[1].setAlpha(90);
                        break;

                    case 7:
                        layers[1] = r.getDrawable(R.drawable.wall8box);
                        layers[1].setAlpha(90);
                        break;

                    case 8:
                        layers[1] = r.getDrawable(R.drawable.wall9box);
                        layers[1].setAlpha(90);
                        break;

                    case 9:
                        layers[1] = r.getDrawable(R.drawable.wall10box);
                        layers[1].setAlpha(90);
                        break;

                    case 10:
                        layers[1] = r.getDrawable(R.drawable.wall11box);
                        layers[1].setAlpha(90);
                        break;

                    case 11:
                        layers[1] = r.getDrawable(R.drawable.wall12box);
                        layers[1].setAlpha(90);
                        break;
                }
                android.graphics.drawable.LayerDrawable layerDrawable = new android.graphics.drawable.LayerDrawable(layers);
                testimage.setImageDrawable(layerDrawable);
                testimage.destroyDrawingCache();
            }
        }));

        return view;
    }
}
