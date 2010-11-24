/**
 * Copyright 2010 Rikard Lundstedt
 * 
 * This file is part of APWidgets.
 * 
 * APWidgets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * APWidgets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with APWidgets. If not, see <http://www.gnu.org/licenses/>.
 */

package apwidgets;

import processing.core.PApplet;
import android.content.Context;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
/**
 * An editable text field. Add instances to {@link apwidgets.PWidgetContainer}. 
 * 
 * @author Rikard Lundstedt
 *
 */
public class PEditText extends PTextView implements OnEditorActionListener{
	
	private PEditText nextEditText = null;
	private boolean closeImeOnDone = false;
	
	private int editorInfo = EditorInfo.TYPE_NULL;
	private int getEditorInfo(){
		return editorInfo;
	}
	
	private int inputType = InputType.TYPE_NULL;
	private int getInputType(){
		return inputType;
	}
	
	/**
	 * If you have called setImeOptions(EditorInfo.IME_ACTION_DONE), 
	 * and you have set closeImeOnDone to true, the IME will close
	 * when you press done.
	 * @param closeImeOnDone
	 */
	public void setCloseImeOnDone(boolean closeImeOnDone){
		this.closeImeOnDone = closeImeOnDone;
	}
	
	/**
	 * If you have called setImeOptions(EditorInfo.IME_ACTION_NEXT), 
	 * you can use this method to specify which EditText will be focused
	 * when you press next.
	 * @param nextEditText
	 */
	public void setNextEditText(PEditText nextEditText){
		if(nextEditText==null){
			throw new NullPointerException("Have you initialized the PEditText used as an argument in calling setNextEditText?");
		}else{
			this.nextEditText = nextEditText;
		}
	}
	
	/**
	 * Creates a new editable text field. 
	 * @param x The text field's x position.
	 * @param y The text field's y position.
	 * @param width The text field's width.
	 * @param height The text field's height.
	 */
	public PEditText(int x, int y, int width, int height) {
		super(x, y, width, height, "");
		this.shouldNotSetOnClickListener = true; //otherwise ime options done, next etc doesn't work
	}
	/**
	 * Initializes the text field. Is called by {@link PWidgetContainer} 
	 * as it is added to it.
	 * 
	 */
	public void init(PApplet pApplet) {
		this.pApplet = pApplet;

		if (view == null) {
			view = new EditText(pApplet);
		}
	//	((EditText)view).setInputType(inputType);
		((EditText)view).setImeOptions(editorInfo);
		((EditText)view).setOnEditorActionListener(this);

		super.init(pApplet);
	}
	public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent){
		if(actionId == EditorInfo.IME_ACTION_NEXT){
			if(nextEditText != null){
				((EditText)nextEditText.getView()).requestFocus();
			}else{
				TextView v1 = (TextView)textView.focusSearch(View.FOCUS_RIGHT);
				if (v1 != null) {
					if (!v1.requestFocus(View.FOCUS_RIGHT)) {
						throw new IllegalStateException("unfocusable view...");//shouldn't get here for your layout
					}
				} else {
					v1 = (TextView) textView.focusSearch(View.FOCUS_DOWN);
					if(v1 != null) {
						if(!v1.requestFocus(View.FOCUS_DOWN)) {
							throw new IllegalStateException("unfocusable view..."); //should get here for your	layout
						}
					}
				}
			}
		}else if(actionId == EditorInfo.IME_ACTION_DONE){
			onClick(view);
			if(closeImeOnDone){
				InputMethodManager imm = (InputMethodManager)pApplet.getSystemService(Context.INPUT_METHOD_SERVICE);
			    imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
			}
		}
		return false;
	}
	/**
	 * You can set IMEOptions for this EditText using this method. 
	 * See list of IMEOptions here: {@linktourl http://developer.android.com/reference/android/view/inputmethod/EditorInfo.html}
	 * @param editorInfo
	 */
	public void setImeOptions(int editorInfo){
		this.editorInfo = editorInfo;
		if (initialized) {
			pApplet.runOnUiThread(new Runnable() {
				public void run() {
					((EditText) view).setImeOptions(getEditorInfo());
				}
			});
		}
	}
	/**
	 * You can set InputType here. See list of different InputTypes here: 
	 * {@linktourl http://developer.android.com/reference/android/text/InputType.html}
	 * @param inputType
	 */
	public void setInputType(int inputType){
		this.inputType = inputType;
		if (initialized) {
			pApplet.runOnUiThread(new Runnable() {
				public void run() {
					((EditText) view).setInputType(getInputType());
				}
			});
		}
	}
}