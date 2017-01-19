package com.example.hdmi.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.example.hdmi.presentation.utils.Size;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    
    private static final String PRESENTATION_KEY = "presentation";
    
    private DisplayManager mDisplayManager;
    
    private ArrayList<Integer> mSavedPresentationContents;
    
    private final HashMap<Integer,PresentationView> mActivePresentations = new HashMap<Integer,PresentationView>();
    KeyboardView mKeyboardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Size.InitScreenSize(this);
        
        if (savedInstanceState != null) {
            mSavedPresentationContents = savedInstanceState.getIntegerArrayList(PRESENTATION_KEY);
        } else {
            mSavedPresentationContents = new ArrayList<Integer>();
        }
        
        mDisplayManager = (DisplayManager)getSystemService(Context.DISPLAY_SERVICE);
        
        setContentView(R.layout.activity_main);

        mKeyboardView = (KeyboardView)findViewById(R.id.keyboard_view);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        updateContents();
        connectHDMI();
        mDisplayManager.registerDisplayListener(mDisplayListener, null);
    }
    
    @Override
    protected void onPause() {
        super.onPause();

        mDisplayManager.unregisterDisplayListener(mDisplayListener);

        for (int i = 0; i < mActivePresentations.size(); i++) {
            Iterator<Integer> iter = mActivePresentations.keySet().iterator();
            while (iter.hasNext()) {
                Integer key = (Integer) iter.next();
                PresentationView presentation = (PresentationView) mActivePresentations.get(key);
                int displayId = presentation.getDisplay().getDisplayId();
                mSavedPresentationContents.add(displayId);
                presentation.dismiss();
            }
        }
        mActivePresentations.clear();
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(PRESENTATION_KEY, mSavedPresentationContents);
    }
    
    /**
     * Listens for when presentations are dismissed.
     */
    private final DialogInterface.OnDismissListener mOnDismissListener =
            new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            PresentationView presentation = (PresentationView)dialog;
            int displayId = presentation.getDisplay().getDisplayId();
            Log.d(TAG, "Presentation on display #" + displayId + " was dismissed.");
            for (int i=0;i<mSavedPresentationContents.size();i++) {
                if (mSavedPresentationContents.get(i) == displayId) {
                    mSavedPresentationContents.remove(i);
                    break;
                }
            }
            
            mActivePresentations.remove(displayId);
        }
    };
    
    private void showPresentation(Display display) {
        final int displayId = display.getDisplayId();
        if (mActivePresentations.get(displayId) != null) {
            return;
        }
        
        PresentationView presentation = new PresentationView(this, display);
        presentation.show();
        presentation.setOnDismissListener(mOnDismissListener);
        mActivePresentations.put(displayId, presentation);
        mSavedPresentationContents.add(displayId);
        mKeyboardView.setEditView(presentation.getEditText());
    }
    
    private void hidePresentation(Display display) {
        final int displayId = display.getDisplayId();
        PresentationView presentation = mActivePresentations.get(displayId);
        if (presentation == null) {
            return;
        }

        Log.d(TAG, "Dismissing presentation on display #" + displayId + ".");

        presentation.dismiss();
        mActivePresentations.remove(displayId);
    }
    
    private final DisplayManager.DisplayListener mDisplayListener =
            new DisplayManager.DisplayListener() {
        @Override
        public void onDisplayAdded(int displayId) {
            Log.d(TAG, "Display #" + displayId + " added.");
            updateContents();
            connectHDMI();
        }

        @Override
        public void onDisplayChanged(int displayId) {
            Log.d(TAG, "Display #" + displayId + " changed.");
            updateContents();
        }

        @Override
        public void onDisplayRemoved(int displayId) {
            Log.d(TAG, "Display #" + displayId + " removed.");
            updateContents();
        }
    };
    
    public void updateContents() {
        Display[] displays = mDisplayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
        
        Log.d(TAG, "There are currently " + displays.length + " displays connected.");
        for (Display display : displays) {
            Log.d(TAG, "  " + display);
        }
    }
    
    public void connectHDMI() {
    	Display[] displays = mDisplayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
        if (displays.length > 0) {
        	showPresentation(displays[0]);
        }
    }
    
    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }
    
    private PresentationView searchDisplayPresentation(int displayId) {
        Iterator<Integer> iter = mActivePresentations.keySet().iterator();
        while (iter.hasNext()) {
            Integer key = (Integer) iter.next();
            PresentationView presentation = (PresentationView) mActivePresentations.get(key);
            if (displayId == presentation.getDisplay().getDisplayId()) {
                return presentation;
            }
        }
        return null;
    }
    
    
}
