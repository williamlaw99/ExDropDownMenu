package com.williamlaw.exddm.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.williamlaw.exdds.R;

/**
 * Created by Administrator on 2017/8/28.
 */
@SuppressLint("AppCompatCustomView")
public class DropdownItemView extends TextView {
    public static boolean upOrDown;
    public static boolean isDefault;

    public DropdownItemView(Context context) {
        this(context, null);
    }

    public DropdownItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropdownItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initDrawablePadding() {
        TextView tv = this;
        Drawable[] drawables = tv.getCompoundDrawables();
        if (drawables[0] != null) {
            drawables[0].setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.width_18dp), getResources().getDimensionPixelSize(R.dimen.height_18dp));
        }
        drawables[2].setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.width_18dp), getResources().getDimensionPixelSize(R.dimen.height_18dp));
        tv.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }


    public void bind(CharSequence text, boolean checked) {
        setText(text);
        if (checked) {
            Drawable iconR = getResources().getDrawable(R.drawable.icon_choosed);
            iconR.setBounds(0, 0, 20, 20);
            setCompoundDrawablesWithIntrinsicBounds(null, null, iconR, null);
            initDrawablePadding();
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    public void rebind(boolean upOrDown0) {

        Drawable iconL =
                upOrDown0
                        ?
                        getResources().getDrawable(R.drawable.icon_incresing)
                        :
                        getResources().getDrawable(R.drawable.icon_reducing);

        Drawable iconR = getResources().getDrawable(R.drawable.icon_choosed);

        iconL.setBounds(0, 0, 20, 20);
        iconR.setBounds(0, 0, 20, 20);
        setCompoundDrawablesWithIntrinsicBounds(iconL, null, iconR, null);
        initDrawablePadding();

    }


}
