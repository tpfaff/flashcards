/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tyler.nbtest;




import com.tyler.nbtest.cards.Subject;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

/**
 *
 * @author Tyler
 */
public class CardContentFragment extends Fragment {
        private ViewFlipper vf;
        private Subject parentSubject;
	TextView word;
	TextView def;
        float lastX=0.0f,newX=0.0f;
        //def=(TextView)getActivity().findViewById(R.id.definition_view);
	
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
                       // RelativeLayout card=(RelativeLayout)getActivity().findViewById(R.id.content_container);                       
                        //word = inflater.inflate(R.layout.card, container, false);
                        //card.addView(rootView);
		//setViews(rootView);
            
                vf=(ViewFlipper)getActivity().findViewById(R.id.word_flipper);
		word=(TextView)getActivity().findViewById(R.id.word_view);
                word.setText("Details");
		vf.setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event){
				
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					//Toast.makeText(getActivity(), "DOWN PRESSED", Toast.LENGTH_SHORT).show();
					//word.setVisibility(View.INVISIBLE);
					lastX=event.getX();
					//String msg=Float.toString(lastX);
					//showShortToast(msg);
					break;
				case MotionEvent.ACTION_UP:
					newX=event.getX();
					//String m=Float.toString(lastX);
					//showShortToast(m+"is the last x");
					if(newX<lastX){
						
						vf.setOutAnimation(getActivity(),R.anim.out_to_left);
						vf.setInAnimation(getActivity(),R.anim.in_from_right);
						vf.showPrevious();
					}else{
						vf.setOutAnimation(getActivity(),R.anim.shrink_to_middle);
						vf.setInAnimation(getActivity(),R.anim.grow_from_middle);
						vf.showNext();
					}
					break;
				}
				return true;
			}
			
		});
		
		return super.onCreateView(inflater, container, savedInstanceState);
            
      
	}

	private void setViews(View rootView){
		    
                
		
		
	}
        

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if(savedInstanceState==null){
        super.onViewCreated(view, savedInstanceState);
        }
        
    }
        
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
		super.onStart();
                word=(TextView)getActivity().findViewById(R.id.word_view);
                Resources res = getResources();
                Drawable shape = res. getDrawable(R.drawable.notecard_background);

                
                word.setBackground(shape);
              //  word.setText(parentSubject);
	}
	
	public void setCardContentFragmentFrontText(String frontText){
		word=(TextView)getActivity().findViewById(R.id.word_view);
		word.setText(frontText);
	}

	private void showShortToast(String message){
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}
}

