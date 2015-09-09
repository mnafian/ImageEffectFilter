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

/**
 * Created by mnafian on 6/16/15.
 */
public class FilterAdapterDrawable extends RecyclerView.Adapter<FilterAdapterDrawable.FilterHolder> {

    private String itemData[] = {
            "Autumn",
            "Layering",
            "Pattern",
            "Contrast",
            "Violet",
            "Pattern2",
            "RockPattern",
            "Angsa",
            "Pasir",
            "Smooth",
            "Relief",
            "AquaBlue"};
    private Context mContext;

    public FilterAdapterDrawable(Context mContext) {
        super();
        this.mContext = mContext;
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
        String val = itemData[position];

        ColorGenerator generator = ColorGenerator.MATERIAL;
        // generate random color
        int color1 = generator.getRandomColor();

        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .withBorder(2)
                .fontSize(23)
                .endConfig()
                .rect();

        TextDrawable drawable = builder.build(val.substring(0,5), color1);

        holder.imFilter.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return itemData.length;
    }

    public class FilterHolder extends RecyclerView.ViewHolder {
        public ImageView imFilter;

        public FilterHolder(View itemView) {
            super(itemView);
            imFilter = (ImageView) itemView.findViewById(R.id.effectsviewimage_item);
        }
    }
}
