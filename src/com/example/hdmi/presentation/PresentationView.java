package com.example.hdmi.presentation;

import android.app.Presentation;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Display;
import android.widget.EditText;
import android.widget.TextView;

public class PresentationView extends Presentation {
    private static final String TAG = "PresentationView";
    
    private MemoView mText;
    
    public PresentationView(Context outerContext, Display display) {
        super(outerContext, display);
    }

    public void setText(CharSequence text) {
        mText.setText(text);
    }

    public CharSequence getText() {
        return mText.getText();
    }
    
    public EditText getEditText() {
    	return mText.getEditText();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation_content);
        final Display display = getDisplay();
        final int displayId = display.getDisplayId();
        mText = (MemoView)findViewById(R.id.memoview);
        mText.setDisplayInfo(" Display Id = " + displayId + " name " + display.getName());
    }
}