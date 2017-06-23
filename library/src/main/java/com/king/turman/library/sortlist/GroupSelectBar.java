package com.king.turman.library.sortlist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.king.turman.library.R;

import java.util.List;

/**
 * Created by diaoqf on 2017/6/9.
 */

public class GroupSelectBar extends LinearLayout {

    private List<String> groupKeys;

    public GroupSelectBar(Context context) {
        super(context);
        init(context, null);
    }

    public GroupSelectBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);        
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_select_bar_layout, this, true);
    }

    public List<String> getGroupKeys() {
        return groupKeys;
    }

    public void setGroupKeys(List<String> groupKeys) {
        this.groupKeys = groupKeys;
    }
}
