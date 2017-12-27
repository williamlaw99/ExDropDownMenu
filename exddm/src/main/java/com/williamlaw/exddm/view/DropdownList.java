package com.williamlaw.exddm.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.williamlaw.exdds.R;
import com.williamlaw.exddm.bean.DropdownItemBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */
public class DropdownList extends ScrollView {

    public LinearLayout linearLayout;
    public DropdownHead button;
    private LinkedList<DropdownItemView> mCachedViews;
    private List<? extends DropdownItemBean> list;
    public DropdownItemBean current;




    public DropdownList(Context context) {
        this(context, null);
    }

    public DropdownList(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropdownList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dropdown_tab_list, this, true);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
    }


    public void flush() {
        for (int i = 0, n = linearLayout.getChildCount(); i < n; i++) {
            View view = linearLayout.getChildAt(i);
            if (view instanceof DropdownItemView) {
                DropdownItemView itemView = (DropdownItemView) view;
                DropdownItemBean data = (DropdownItemBean) itemView.getTag();
                if (data == null) {
                    return;
                }
                boolean checked = data == current;
                String suffix = data.getSuffix();
                itemView.bind(TextUtils.isEmpty(suffix) ? data.text : data.text + suffix, checked);
                if (checked)
                    button.setText(data.text);


            }

        }
    }


    public void bind(List<? extends DropdownItemBean> list,
                     DropdownHead button,
                     final Container container,
                     int selectedId
    ) {
        current = null;
        this.list = list;
        this.button = button;
        LinkedList<View> cachedDividers = new LinkedList<>();
        mCachedViews = new LinkedList<>();
        for (int i = 0, n = linearLayout.getChildCount(); i < n; i++) {
            View view = linearLayout.getChildAt(i);
            if (view instanceof DropdownItemView) {
                mCachedViews.add((DropdownItemView) view);
            } else {
                cachedDividers.add(view);
            }
        }

        linearLayout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        boolean isFirst = true;
        for (DropdownItemBean item : list) {
            if (isFirst) {
                isFirst = false;
            } else {
                View divider = cachedDividers.poll();
                if (divider == null) {
                    divider = inflater.inflate(R.layout.dropdown_tab_list_divider, linearLayout, false);
                }
                linearLayout.addView(divider);
            }
            DropdownItemView view = mCachedViews.poll();
            if (view == null) {
                view = (DropdownItemView) inflater.inflate(R.layout.dropdown_tab_list_item, linearLayout, false);
            }
            view.setTag(item);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    DropdownItemBean data = (DropdownItemBean) v.getTag();
                    if (data == null)
                        return;
                    DropdownItemBean oldOne = current;
                    current = data;
                    flush();
                    container.hide();
                    if (oldOne != current) {
                        container.onSelectionChanged(DropdownList.this);
                    }

                    itemClickedListener.sendClickedId(data.getId(),true);

                    if (data.getId() > 6) {
                        ((DropdownItemView) v).rebind(itemClickedListener.getUpOrDown());
                    }
                }
            });
            linearLayout.addView(view);
            if (item.id == selectedId && current == null) {
                current = item;
            }
        }

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getVisibility() == VISIBLE) {
                    container.hide();
                } else {
                    container.show(DropdownList.this);
                }
            }
        });

        if (current == null && list.size() > 0) {
            current = list.get(0);
        }
        flush();
    }


    public static interface Container {
        void show(DropdownList listView);

        void hide();

        void onSelectionChanged(DropdownList view);
    }

    private OnDropItemClicked itemClickedListener;

    public void setItemClickedListener(OnDropItemClicked itemClickedListener) {
        this.itemClickedListener = itemClickedListener;
    }

    public interface OnDropItemClicked {
        void sendClickedId(int id, boolean doRefresh);

        boolean getUpOrDown();
    }

    public void restore(Container container, DropdownItemBean data) {
        if (data == null)
            return;
        DropdownItemBean oldOne = current;
        current = data;
        flush();//***********
        container.hide();
        if (oldOne != current) {
            container.onSelectionChanged(DropdownList.this);
        }

        itemClickedListener.sendClickedId(data.getId(),false);

    }


}
