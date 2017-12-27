package com.williamlaw.exddm.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.williamlaw.exdds.R;


/**
 * Created by Administrator on 2017/8/28.
 */
public class DropdownHead extends RelativeLayout {
    TextView textView;
    ImageView img;
    View bottomLine;
    private ImageView upOrDown;

    public DropdownHead(Context context) {
        this(context, null);
    }

    public DropdownHead(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropdownHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view =  LayoutInflater.from(getContext()).inflate(R.layout.dropdown_tab_button,this, true);
        textView = (TextView) view.findViewById(R.id.textView);
        img = (ImageView) view.findViewById(R.id.img);
//        initDrawablePadding(textView);
        bottomLine = view.findViewById(R.id.bottomLine);
        upOrDown = (ImageView) view.findViewById(R.id.btn_order_type);
    }

    private void initDrawablePadding(TextView tv) {
        Drawable[] drawables = tv.getCompoundDrawables();
        drawables[2].setBounds(0,0,getResources().getDimensionPixelSize(R.dimen.width_16dp),getResources().getDimensionPixelSize(R.dimen.height_16dp));
        tv.setCompoundDrawables(drawables[0],drawables[1],drawables[2],drawables[3]);
    }


    public void setText(CharSequence text) {
        textView.setText(text);
    }

    public void setChecked(boolean checked) {
        Drawable icon;
        if (checked) {
            icon = getResources().getDrawable(R.drawable.icon_active_arrow);
            textView.setTextColor(getResources().getColor(R.color.active_color));
            bottomLine.setVisibility(VISIBLE);
        } else {
            icon = getResources().getDrawable(R.drawable.icon_default_arrow);
            textView.setTextColor(getResources().getColor(R.color.default_color));
            bottomLine.setVisibility(GONE);
        }
        img.setImageDrawable(icon);
    }

    public void setUpOrDown(int img) {
        upOrDown.setImageResource(img);
    }


}
