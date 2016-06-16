package com.inagata.imageeffects.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.inagata.imageeffects.R;
import com.inagata.imageeffects.Utilities.FilterType;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mnafian on 6/16/15.
 */
public class FilterAdapterGPU extends RecyclerView.Adapter<FilterAdapterGPU.FilterHolder> {

    private FilterList filterList;
    private Context mContext;

    public FilterAdapterGPU(Context mContext) {
        super();
        this.mContext = mContext;
        populateData();
    }

    public FilterType getItem(int position) {
        return filterList.filters.get(position);
    }

    private void populateData() {
        filterList = new FilterList();
        filterList.addFilter("No Filter", null);
        filterList.addFilter("Toon", FilterType.TOON);
        filterList.addFilter("Sketch", FilterType.SKETCH);
        filterList.addFilter("Vignette", FilterType.VIGNETTE);
        filterList.addFilter("Invert", FilterType.INVERT);
        filterList.addFilter("Grayscale", FilterType.GRAYSCALE);
    }

    @Override
    public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.imf_filter_item, parent, false);
        FilterHolder viewHolder = new FilterHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FilterHolder holder, int position) {
        String val = filterList.names.get(position);

        ColorGenerator generator = ColorGenerator.MATERIAL;
        // generate random color
        int color1 = generator.getRandomColor();

        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .withBorder(2)
                .fontSize(23)
                .endConfig()
                .rect();

        TextDrawable drawable = builder.build(val.substring(0, Math.min(5, val.length() - 1)), color1);

        holder.imFilter.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return filterList.names.size();
    }

    public class FilterHolder extends RecyclerView.ViewHolder {
        public ImageView imFilter;

        public FilterHolder(View itemView) {
            super(itemView);
            imFilter = (ImageView) itemView.findViewById(R.id.effectsviewimage_item);
        }
    }

    private static class FilterList {
        public List<String> names = new LinkedList<>();
        public List<FilterType> filters = new LinkedList<FilterType>();

        public void addFilter(final String name, final FilterType filter) {
            names.add(name);
            filters.add(filter);
        }
    }
}
