package com.example.hdmi.presentation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hdmi.presentation.utils.LayoutUtils;
import com.example.hdmi.presentation.utils.Size;

public class KeyboardView extends LinearLayout implements OnClickListener {
	private static final String TAG = "KeyboardView";
	private EditText mEditText;
    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(LinearLayout.VERTICAL);
        setDeminSize();
        makeKeyboardFunction(context);
        makeKeyboardNumber(context);
        makeKeyboardAlphabet1(context);
        makeKeyboardAlphabet2(context);
        makeKeyboardAlphabet3(context);
        makeKeyboardSpace(context);
        
    }
//    <dimen name="keyboard_top">70dp</dimen>
//    <dimen name="key_layout_height">58dp</dimen>
//    
//    <dimen name="keysize">44dp</dimen>
//    <dimen name="keysize_margin_lr">1dp</dimen>
//    <dimen name="keysize_stroke">4dp</dimen>
//    <dimen name="keysize_round">10dp</dimen>
//    <dimen name="keysize_enter_width">60dp</dimen>
//    <dimen name="keysize_space_width">340dp</dimen>
//    <dimen name="keysize_key_alpha_2_left">16dp</dimen>
//    <dimen name="keysize_space_left">60dp</dimen>
    private int key_layout_height;
    private int key_layout_left;
    private int keysize;
    private int keysize_margin_lr;

    private int key_function_layout_height;
    private int key_function_width;
    private int key_function_height;
    private int key_function_esc_width;
    
    private int keysize_caps_width;
    private int keysize_enter_width;
    
    private int keysize_shift_width;

    private int keysize_control_width;
    private int keysize_option_width;
    private int keysize_space_width;
    
    private int keyboard_bottom;
    private void setDeminSize() {
    	if ((Size.DisplayWidth == 720 && Size.DisplayHeight == 1280) ||
		(Size.DisplayWidth == 1280 && Size.DisplayHeight == 720)) {
    		key_layout_height = 100;
    		keysize = 84;
    		keysize_margin_lr = 2;
    		key_layout_left = (Size.DisplayWidth - ((keysize * 14) + (keysize_margin_lr + 13))) / 2;
    		
    		key_function_layout_height = 80;
    		key_function_width = 107;
    		key_function_height = 70;
    		key_function_esc_width = 110;
    		
    		keysize_caps_width = 114;  
    		keysize_enter_width = 142;
    		
    		keysize_shift_width = 128;
    		
    		keysize_control_width = 158;
    		keysize_option_width = 142;
    		keysize_space_width = 600;
    		
    		keyboard_bottom = 20;
    	} else if (Size.DisplayHeight == 768) {
    		key_layout_height = 100;
    		keysize = 80;
    		keysize_margin_lr = 2;
    		key_layout_left = (1194 - ((keysize * 14) + (keysize_margin_lr + 13))) / 2;
    		
    		key_function_layout_height = 76;
    		key_function_width = 101;
    		key_function_height = 68;
    		key_function_esc_width = 114;
    		
    		keysize_caps_width = 110;  
    		keysize_enter_width = 132;
    		
    		keysize_shift_width = 120;
    		
    		keysize_control_width = 158;
    		keysize_option_width = 142;
    		keysize_space_width = 555;
    		
    		keyboard_bottom = 20;
    	} else {
    		key_layout_height = getDimen(getContext(), R.dimen.key_layout_height);
    		keysize = getDimen(getContext(), R.dimen.keysize);
    		keysize_margin_lr = getDimen(getContext(), R.dimen.keysize_margin_lr);
    		key_layout_left = (Size.DisplayWidth - ((keysize * 14) + (keysize_margin_lr + 13))) / 2;
    		
    		key_function_layout_height = getDimen(getContext(), R.dimen.key_function_layout_height);
    		key_function_width = getDimen(getContext(), R.dimen.key_function_width);
    		key_function_height = getDimen(getContext(), R.dimen.key_function_height);
    		key_function_esc_width = getDimen(getContext(), R.dimen.key_function_esc_width);
    		
    		keysize_caps_width = getDimen(getContext(), R.dimen.keysize_caps_width);
    		keysize_enter_width = getDimen(getContext(), R.dimen.keysize_enter_width);
    		
    		keysize_shift_width = getDimen(getContext(), R.dimen.keysize_shift_width);
    		
    		keysize_control_width = getDimen(getContext(), R.dimen.keysize_control_width);
    		keysize_option_width = getDimen(getContext(), R.dimen.keysize_option_width);
    		keysize_space_width = getDimen(getContext(), R.dimen.keysize_space_width);
    		
    		keyboard_bottom = getDimen(getContext(), R.dimen.keyboard_bottom);
    	}
    }
    public KeyboardView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void setEditView(EditText editview) {
    	mEditText = editview;
    }
    
    private LinearLayout makeKeyboardFunction(Context context) {
        LinearLayout layout = LinearMaker(context, this, LayoutParams.WRAP_CONTENT, key_function_layout_height,key_layout_left,0);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(getKey(layout,"ESC",KeyEvent.KEYCODE_UNKNOWN,key_function_esc_width,key_function_height));
        layout.addView(getKey(layout,"F1",KeyEvent.KEYCODE_UNKNOWN,key_function_width,key_function_height));
        layout.addView(getKey(layout,"F2",KeyEvent.KEYCODE_UNKNOWN,key_function_width,key_function_height));
        layout.addView(getKey(layout,"F3",KeyEvent.KEYCODE_UNKNOWN,key_function_width,key_function_height));
        layout.addView(getKey(layout,"F4",KeyEvent.KEYCODE_UNKNOWN,key_function_width,key_function_height));
        layout.addView(getKey(layout,"F5",KeyEvent.KEYCODE_UNKNOWN,key_function_width,key_function_height));
        layout.addView(getKey(layout,"F6",KeyEvent.KEYCODE_UNKNOWN,key_function_width,key_function_height));
        layout.addView(getKey(layout,"F7",KeyEvent.KEYCODE_UNKNOWN,key_function_width,key_function_height));
        layout.addView(getKey(layout,"F8",KeyEvent.KEYCODE_UNKNOWN,key_function_width,key_function_height));
        layout.addView(getKey(layout,"F9",KeyEvent.KEYCODE_UNKNOWN,key_function_width,key_function_height));
        layout.addView(getKey(layout,"F10",KeyEvent.KEYCODE_UNKNOWN,key_function_width,key_function_height));
        
        return layout;
    }
    
    private LinearLayout makeKeyboardNumber(Context context) {
        LinearLayout layout = LinearMaker(context, this, LayoutParams.WRAP_CONTENT, key_layout_height,key_layout_left,0);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(getKey(layout,"'",KeyEvent.KEYCODE_GRAVE));
        layout.addView(getKey(layout,"1",KeyEvent.KEYCODE_1));
        layout.addView(getKey(layout,"2",KeyEvent.KEYCODE_2));
        layout.addView(getKey(layout,"3",KeyEvent.KEYCODE_3));
        layout.addView(getKey(layout,"4",KeyEvent.KEYCODE_4));
        layout.addView(getKey(layout,"5",KeyEvent.KEYCODE_5));
        layout.addView(getKey(layout,"6",KeyEvent.KEYCODE_6));
        layout.addView(getKey(layout,"7",KeyEvent.KEYCODE_7));
        layout.addView(getKey(layout,"8",KeyEvent.KEYCODE_8));
        layout.addView(getKey(layout,"9",KeyEvent.KEYCODE_9));
        layout.addView(getKey(layout,"0",KeyEvent.KEYCODE_0));
        layout.addView(getKey(layout,"-",KeyEvent.KEYCODE_MINUS));
        layout.addView(getKey(layout,"=",KeyEvent.KEYCODE_NUMPAD_EQUALS));
        layout.addView(getKey(layout,"<-",KeyEvent.KEYCODE_DEL));
        
        return layout;
    }
    
    private LinearLayout makeKeyboardAlphabet1(Context context) {
        LinearLayout layout = LinearMaker(context, this, LayoutParams.WRAP_CONTENT, key_layout_height,key_layout_left,0);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(getKey(layout,"Tab",KeyEvent.KEYCODE_UNKNOWN));
        layout.addView(getKey(layout,"Q",KeyEvent.KEYCODE_Q));
        layout.addView(getKey(layout,"W",KeyEvent.KEYCODE_W));
        layout.addView(getKey(layout,"E",KeyEvent.KEYCODE_E));
        layout.addView(getKey(layout,"R",KeyEvent.KEYCODE_R));
        layout.addView(getKey(layout,"T",KeyEvent.KEYCODE_T));
        layout.addView(getKey(layout,"Y",KeyEvent.KEYCODE_Y));
        layout.addView(getKey(layout,"U",KeyEvent.KEYCODE_U));
        layout.addView(getKey(layout,"I",KeyEvent.KEYCODE_I));
        layout.addView(getKey(layout,"O",KeyEvent.KEYCODE_O));
        layout.addView(getKey(layout,"P",KeyEvent.KEYCODE_P));
        layout.addView(getKey(layout,"[",KeyEvent.KEYCODE_LEFT_BRACKET));
        layout.addView(getKey(layout,"]",KeyEvent.KEYCODE_RIGHT_BRACKET));
        layout.addView(getKey(layout,"\\",KeyEvent.KEYCODE_BACKSLASH));
        return layout;
    }
    
    private LinearLayout makeKeyboardAlphabet2(Context context) {
        LinearLayout layout = LinearMaker(context, this, LayoutParams.WRAP_CONTENT, key_layout_height,key_layout_left,0);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(getKey(layout,"Caps",KeyEvent.KEYCODE_UNKNOWN,keysize_caps_width));
        layout.addView(getKey(layout,"A",KeyEvent.KEYCODE_A));
        layout.addView(getKey(layout,"S",KeyEvent.KEYCODE_S));
        layout.addView(getKey(layout,"D",KeyEvent.KEYCODE_D));
        layout.addView(getKey(layout,"F",KeyEvent.KEYCODE_F));
        layout.addView(getKey(layout,"G",KeyEvent.KEYCODE_G));
        layout.addView(getKey(layout,"H",KeyEvent.KEYCODE_H));
        layout.addView(getKey(layout,"J",KeyEvent.KEYCODE_J));
        layout.addView(getKey(layout,"K",KeyEvent.KEYCODE_K));
        layout.addView(getKey(layout,"L",KeyEvent.KEYCODE_L));
        layout.addView(getKey(layout,";",KeyEvent.KEYCODE_SEMICOLON));
        layout.addView(getKey(layout,"'",KeyEvent.KEYCODE_APOSTROPHE));
        layout.addView(getKey(layout,"enter",KeyEvent.KEYCODE_ENTER,keysize_enter_width));
        return layout;
    }
    
    private LinearLayout makeKeyboardAlphabet3(Context context) {
        LinearLayout layout = LinearMaker(context, this, LayoutParams.WRAP_CONTENT, key_layout_height,key_layout_left,0);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(getKey(layout,"Shift",KeyEvent.KEYCODE_UNKNOWN,keysize_shift_width));
        layout.addView(getKey(layout,"Z",KeyEvent.KEYCODE_Z));
        layout.addView(getKey(layout,"X",KeyEvent.KEYCODE_X));
        layout.addView(getKey(layout,"C",KeyEvent.KEYCODE_C));
        layout.addView(getKey(layout,"V",KeyEvent.KEYCODE_V));
        layout.addView(getKey(layout,"B",KeyEvent.KEYCODE_B));
        layout.addView(getKey(layout,"N",KeyEvent.KEYCODE_N));
        layout.addView(getKey(layout,"M",KeyEvent.KEYCODE_M));
        layout.addView(getKey(layout,",",KeyEvent.KEYCODE_COMMA));
        layout.addView(getKey(layout,".",KeyEvent.KEYCODE_PERIOD));
        layout.addView(getKey(layout,"/",KeyEvent.KEYCODE_SLASH));
        layout.addView(getKey(layout,"∧",KeyEvent.KEYCODE_DPAD_UP));
        layout.addView(getKey(layout,"Shift",KeyEvent.KEYCODE_UNKNOWN,keysize_shift_width));
        return layout;
    }
    
    private LinearLayout makeKeyboardSpace(Context context) {
        LinearLayout layout = LinearMaker(context, this, LayoutParams.WRAP_CONTENT, key_layout_height,key_layout_left,0,0,keyboard_bottom);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(getKey(layout,"Control",KeyEvent.KEYCODE_UNKNOWN,keysize_control_width));
        layout.addView(getKey(layout,"Option",KeyEvent.KEYCODE_UNKNOWN,keysize_option_width));
        layout.addView(getKey(layout,"space",KeyEvent.KEYCODE_SPACE,keysize_space_width));
        layout.addView(getKey(layout,"<",KeyEvent.KEYCODE_DPAD_LEFT));
        layout.addView(getKey(layout,"∨",KeyEvent.KEYCODE_DPAD_DOWN));
        layout.addView(getKey(layout,">",KeyEvent.KEYCODE_DPAD_RIGHT));
        return layout; 
    }
    private Button getKey(LinearLayout parent, String key,int id) {
    	return getKey(parent,key,id,keysize);
    }
    
    private Button getKey(LinearLayout parent, String key,int id, int width) {
    	return getKey(parent,key,id,width,keysize);
    }
    
    private Button getKey(LinearLayout parent, String key,int id, int width, int height) {
        Button keyBtn = new Button(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		width,
        		height);
        params.leftMargin = keysize_margin_lr;
        keyBtn.setLayoutParams(params);
        keyBtn.setId(id);
        keyBtn.setText(key);
        
        keyBtn.setBackgroundResource(R.drawable.key_selector);
        keyBtn.setOnClickListener(this);
        return keyBtn;
    }
    
	public static LinearLayout LinearMaker(Context context, ViewGroup parentView, int width, int height) {
		return LinearMaker(context,parentView,width,height,0,0,0,0);
	}
	
	public static LinearLayout LinearMaker(Context context, ViewGroup parentView, int width, int height, int x, int y) {
		return LinearMaker(context,parentView,width,height,x,y,0,0);
	}
	
	public static LinearLayout LinearMaker(Context context, ViewGroup parentView, int width, int height, int x, int y, int right, int bottom ) {
		LinearLayout view = new LinearLayout(context);
		parentView.addView(view);
		LayoutUtils.setLayoutParams(parentView,view,width,height,x,y,right,bottom);
		return view;
	}
	
    public static int getDimen(Context context, int res) {
        return (int)context.getResources().getDimension(res);
    }

	@Override
	public void onClick(View v) {

		if (mEditText != null) {
			Vibrator vibe = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
			vibe.vibrate(10);
			playSoundEffect( SoundEffectConstants.CLICK );
			if (v.getId() != KeyEvent.KEYCODE_UNKNOWN) {
				KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN,v.getId());
				mEditText.onKeyDown(v.getId(), event);
			}
		}
	}
	
	public static ShapeDrawable makeRoundDrawable(int alpha, int round, int color, int strokeWidth) {
		ShapeDrawable rounddraw = new ShapeDrawable(new RoundRectShape(new float[]{round,round,round,round,round,round,round,round}, null, null));
		rounddraw.getPaint().setColor(color);
		rounddraw.getPaint().setStyle(Style.STROKE);
		rounddraw.getPaint().setStrokeWidth(strokeWidth);
		rounddraw.getPaint().setStrokeCap(Paint.Cap.ROUND);
		
		rounddraw.setAlpha(alpha);

        return rounddraw;
	}
}
