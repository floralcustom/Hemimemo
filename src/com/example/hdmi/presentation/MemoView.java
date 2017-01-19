package com.example.hdmi.presentation;

import com.example.hdmi.presentation.utils.LayoutUtils;
import com.example.hdmi.presentation.utils.Size;
import com.example.hdmi.presentation.utils.ViewMaker;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MemoView extends LinearLayout{
    private static final String TAG = "MemoView";
    private EditTextView mEditView;
    
	public MemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(LinearLayout.VERTICAL);
        makeEditerView(context);
    }

	public MemoView(Context context) {
        super(context);
        makeEditerView(context);
    }
	
	public void setDisplayInfo(String info) {
	}
	
	public void setText(CharSequence text) {
	    mEditView.setText(text);
	    mEditView.setSelection(mEditView.length());
	}
	
	public CharSequence getText() {
        return mEditView.getText();
    }
	
	public EditText getEditText() {
		return mEditView;
	}
	@Override
	public void setVisibility(int visibility) {
		super.setVisibility(visibility);
	}
	
	private void makeEditerView(Context context) {
		setBackgroundColor(Color.parseColor("#FFF6D6"));
		setOrientation(LinearLayout.VERTICAL);

		LinearLayout editViewLayout = ViewMaker.LinearMaker(context, this, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		editViewLayout.setOrientation(LinearLayout.HORIZONTAL);
            mEditView = new EditTextView(context);
            mEditView.setTextSize(40 / Size.Density);
            mEditView.setTextColor(Color.BLACK);
            mEditView.setGravity(Gravity.TOP);
            editViewLayout.addView(mEditView);
            LayoutUtils.setLinearLayoutParams(mEditView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, (int)context.getResources().getDimension(R.dimen.memo_left), 0);
	}
}
