package com.gprint.common;

import com.sample.R;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class BottomMenu implements OnClickListener,OnTouchListener{  
  
    private PopupWindow popupWindow;  
    private Button btn1, btn2,btn3, btnCancel;  
    private Button btn21, btn22, btn23,btn24; 
    private View mMenuView;  
    private Activity mContext;  
    private OnClickListener clickListener;  
    private LinearLayout layout1,layout2;
   
    public BottomMenu(Activity context,OnClickListener clickListener,String menuIndex) {  
        LayoutInflater inflater = LayoutInflater.from(context);  
        this.clickListener=clickListener;  
        mContext=context;  
        mMenuView = inflater.inflate(R.layout.layout_popwindow, null);  
        btn1 = (Button) mMenuView.findViewById(R.id.btn1);  
        btn2 = (Button) mMenuView.findViewById(R.id.btn2);  
        btn3 = (Button) mMenuView.findViewById(R.id.btn3); 
        
        btn21 = (Button) mMenuView.findViewById(R.id.btn21);  
        btn22 = (Button) mMenuView.findViewById(R.id.btn22); 
        btn23 = (Button) mMenuView.findViewById(R.id.btn23);  
        btn24 = (Button) mMenuView.findViewById(R.id.btn24); 
        
        layout1 = (LinearLayout)mMenuView.findViewById(R.id.pop_layout);
        layout2 = (LinearLayout)mMenuView.findViewById(R.id.pop_layout2);
        if(menuIndex.equals("1")) {
        	layout1.setVisibility(View.VISIBLE);
        	layout2.setVisibility(View.GONE);
        }else {
        	layout2.setVisibility(View.VISIBLE);
        	layout1.setVisibility(View.GONE);
        }
        
        btn1.setOnClickListener(this);  
        btn2.setOnClickListener(this);  
        btn3.setOnClickListener(this);  
        
        btn24.setOnClickListener(this);  
        
        popupWindow=new PopupWindow(mMenuView,LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,true);  
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);  
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.half_transparent));  
        popupWindow.setBackgroundDrawable(dw);  
        mMenuView.setOnTouchListener(this);  
    }  
  
    /** 
     * 显示菜单 
     */  
    public void show(){  
        //得到当前activity的rootView  
        View rootView=((ViewGroup)mContext.findViewById(android.R.id.content)).getChildAt(0);  
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);   
    }  
      
    @Override  
    public void onClick(View view) {  
        popupWindow.dismiss();  
        switch (view.getId()) {  
        default:  
            clickListener.onClick(view);  
            break;  
        }  
    }  
  
    @Override  
    public boolean onTouch(View arg0, MotionEvent event) {  
        int height = mMenuView.findViewById(R.id.pop_layout).getTop();  
        int height2 = mMenuView.findViewById(R.id.pop_layout2).getTop();  
        int y=(int) event.getY();  
        if(event.getAction()==MotionEvent.ACTION_UP){  
            if(y<height){  
               popupWindow. dismiss();  
            }  
            else if(y<height2){  
                popupWindow. dismiss();  
             } 
        }  
        return true;  
    }  
   
}  
