package mypackage;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class SluggingPercentageScreen extends MainScreen implements FieldChangeListener {

	/**
	 * 
	 */
	
	private Bitmap backgroundBitmap;
	VerticalFieldManager verticalManager;
	RichTextField rtfHeading;
	RichTextField rtfResult;
	Font fontHeading = null;
	private EditField homeRunsEdit;
	private EditField triplesEdit;
	private EditField doublesEdit;
	private EditField singlesEdit;
	private EditField outsEdit;
	ButtonField calculateButton;
	ButtonField backButton;
	
	
	public SluggingPercentageScreen() {
		

		

		super(MainScreen.NO_HORIZONTAL_SCROLL | MainScreen.USE_ALL_WIDTH | MainScreen.USE_ALL_HEIGHT | MainScreen.NO_VERTICAL_SCROLL);
        setTitle( "Baseball Calculator" );
        
        backgroundBitmap = Bitmap.getBitmapResource("background.jpg");
        verticalManager  = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH | VerticalFieldManager.USE_ALL_HEIGHT){
        	 public void paint(Graphics graphics)
             {
                 //Draw the background image and then call paint.
                 graphics.drawBitmap(0, 0, Display.getWidth(),Display.getHeight(), backgroundBitmap, 0, 0);
                 super.paint(graphics);
             }
        };
        
        rtfHeading = new RichTextField("Slugging Percentage", RichTextField.TEXT_ALIGN_HCENTER){
        	protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
        };
        rtfHeading.setMargin(50, 50, 40, 50);
        fontHeading = getFontToDisplay("Times New Roman", 55);
        rtfHeading.setFont(fontHeading);
        verticalManager.add(rtfHeading);
        
        homeRunsEdit = new EditField( "Home Runs : ", "", 5, EDITABLE ){
			protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
			
		};
		homeRunsEdit.setMargin(30,40,20,30);
	    fontHeading = getFontToDisplay("Georgia", 30);
	    homeRunsEdit.setFont(fontHeading);
	    
		verticalManager.add(homeRunsEdit);
		
		triplesEdit = new EditField( "Triples : ", "", 5, EDITABLE ){
			protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
			
		};
		triplesEdit.setMargin(30,40,20,30);
	    fontHeading = getFontToDisplay("Georgia", 30);
	    triplesEdit.setFont(fontHeading);
	    
		verticalManager.add(triplesEdit);
		
		doublesEdit = new EditField( "Doubles : ", "", 5, EDITABLE ){
			protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
			
		};
		doublesEdit.setMargin(30,40,20,30);
	    fontHeading = getFontToDisplay("Georgia", 30);
	    doublesEdit.setFont(fontHeading);
	    
		verticalManager.add(doublesEdit);
		
		
		
		singlesEdit = new EditField( "Singles : ", "", 5, EDITABLE ){
			protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
			
		};
		singlesEdit.setMargin(30,40,20,30);
	    fontHeading = getFontToDisplay("Georgia", 30);
	    singlesEdit.setFont(fontHeading);
	    
		verticalManager.add(singlesEdit);
		
		
		outsEdit = new EditField( "Outs : ", "", 5, EDITABLE ){
			protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
			
		};
		outsEdit.setMargin(30,40,20,30);
	    fontHeading = getFontToDisplay("Georgia", 30);
	    outsEdit.setFont(fontHeading);
	    
		verticalManager.add(outsEdit);
		
		
		 rtfResult = new RichTextField("", RichTextField.TEXT_ALIGN_HCENTER){
	        	protected void paint(Graphics g){ 
	                g.setColor(0xffff0000);
	                super.paint(g);
	            }
	        };
	        rtfResult.setMargin(30, 30, 20, 30);
	        fontHeading = getFontToDisplay("Georgia", 30);
	        rtfResult.setFont(fontHeading);
	        verticalManager.add(rtfResult);
	        
	        fontHeading = getFontToDisplay("Comic Sans MS", 30);
	        calculateButton = new ButtonField( "Calculate", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
	        calculateButton.setChangeListener(this);
	        calculateButton.setFont(fontHeading);
	        calculateButton.setMargin(65, 40, 40, 80);
	        
	        backButton = new ButtonField( "Back", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
	        backButton.setChangeListener(this);
	        backButton.setFont(fontHeading);
	        backButton.setMargin(65, 40, 40, 80);
	        
	        
	        HorizontalFieldManager horizontalManager = new HorizontalFieldManager(USE_ALL_WIDTH);
	        horizontalManager.add(calculateButton);
	        horizontalManager.add(backButton);
		verticalManager.add(horizontalManager);
		add(verticalManager);
	
		
		
	
		
	}
	
	public void fieldChanged(Field field, int context) {

		double homeRun;
		double triple = 0;
		double doubles;
		double singles;
		double outs;
		double official = 0;
		double sluggingPercent;
		if(field == calculateButton)
		{
			if(calculateButton.getLabel().equals("Calculate"))
			{
				calculateButton.setLabel("Reset");
				homeRunsEdit.setEditable(false);
				triplesEdit.setEditable(false);
				doublesEdit.setEditable(false);
				singlesEdit.setEditable(false);
				outsEdit.setEditable(false);
				try {
					homeRun = Double.parseDouble(homeRunsEdit.getText());
					triple = Double.parseDouble(triplesEdit.getText());
					doubles = Double.parseDouble(doublesEdit.getText());
					singles = Double.parseDouble(singlesEdit.getText());
					outs = Double.parseDouble(outsEdit.getText());
					official = homeRun + triple + doubles + singles + outs;
					if(official == 0)
					{
						Dialog.inform("Home Runs, Triples, Doubles, Singles and Outs all cannot be zero");
					}
					else
					{
						sluggingPercent = ((homeRun * 4) + (triple * 3) + (doubles * 2) + (singles)) / official;
						rtfResult.setText("Slugging Percentage (SLG) : \n" + String.valueOf(sluggingPercent));
					}
				} catch (NumberFormatException e) {
					
					Dialog.inform("Please Enter Number!!!!");
					
				}
				catch (ArithmeticException e) {
					if(official == 0)
					{
						Dialog.inform("Number of Assists, Number of Putouts and Number of Errors all cannot be zero");
					}
					else
					{
						Dialog.inform("Error!!!! Please try after some time");
						UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());

					}
				}
				catch (Exception e) {

					Dialog.inform("Error!!!! Please try after some time");
					UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());

				}
				
			}
			else
			{
				calculateButton.setLabel("Calculate");
				homeRunsEdit.setText("");
				triplesEdit.setText("");
				doublesEdit.setText("");
				singlesEdit.setText("");
				outsEdit.setText("");
				rtfResult.setText("");
				homeRunsEdit.setEditable(true);
				triplesEdit.setEditable(true);
				doublesEdit.setEditable(true);
				singlesEdit.setEditable(true);
				outsEdit.setEditable(true);
			}
		}
		else if(field == backButton)
		{
			UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());
		}
		
	}
	
	private Font getFontToDisplay(String stFontName, int fontSize)
    {
    	try
        {
            FontFamily ff1 = FontFamily.forName(stFontName);
            fontHeading = ff1.getFont(Font.ITALIC | Font.EXTRA_BOLD , fontSize);
            return fontHeading;
        }
        catch (Exception e) {
			e.printStackTrace();
			Dialog.inform("Error Occurred. Please try after some time");
			return null;
		}
    }
    
    protected boolean onSavePrompt() {
        return true;
    }

}
