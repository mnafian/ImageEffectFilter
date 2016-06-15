package com.inagata.imageeffects.Fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inagata.imageeffects.Adapter.FilterAdapterGPU;
import com.inagata.imageeffects.R;
import com.inagata.imageeffects.Utilities.FilterType;
import com.inagata.imageeffects.Utilities.RecyclerItemClickListener;

import jp.co.cyberagent.android.gpuimage.GPUImageColorInvertFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSketchFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageToonFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;
import jp.co.cyberagent.android.gpuimage.GPUImageVignetteFilter;

/**
 * Created by esafirm on 6/15/16.
 */
public class GpuFilterFragment extends Fragment {

    private RecyclerView recyclerView;
    private GPUImageView effectView;
    private Bitmap image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.imf_gpu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        effectView = (GPUImageView) view.findViewById(R.id.effectsview);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rc_filter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        final FilterAdapterGPU adapter = new FilterAdapterGPU(getActivity());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                applyEffect(adapter.getItem(position));
            }
        }));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init(){
        image = ((BitmapDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.nabilah)).getBitmap();
        effectView.setImage(image);
    }

    private void applyEffect(FilterType filterType) {
        if (filterType == null) {
            applyEffect(new GPUImageFilter());
            return;
        }

        switch (filterType) {
            case GRAYSCALE:
                applyEffect(new GPUImageGrayscaleFilter());
                break;
            case TOON:
                applyEffect(new GPUImageToonFilter());
                break;
            case VIGNETTE:
                applyEffect(new GPUImageVignetteFilter());
                break;
            case INVERT:
                applyEffect(new GPUImageColorInvertFilter());
                break;
            case SKETCH:
                applyEffect(new GPUImageSketchFilter());
                break;
        }
    }

    private void applyEffect(final GPUImageFilter filter) {
        effectView.setFilter(filter);
    }
}