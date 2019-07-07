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

public class EarnedRunAverageScreen extends MainScreen implements FieldChangeListener {

	/**
	 * 
	 */
	
	
	private Bitmap backgroundBitmap;
	VerticalFieldManager verticalManager;
	RichTextField rtfHeading;
	RichTextField rtfResult;
	Font fontHeading = null;
	private EditField earnedRunsEdit;
	private EditField inningsEdit;
	ButtonField calculateButton;
	ButtonField backButton;
	
	
	public EarnedRunAverageScreen() {
		

		

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
        
        rtfHeading = new RichTextField("Earned Run Average", RichTextField.TEXT_ALIGN_HCENTER){
        	protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
        };
        rtfHeading.setMargin(50, 50, 40, 50);
        fontHeading = getFontToDisplay("Times New Roman", 55);
        rtfHeading.setFont(fontHeading);
        verticalManager.add(rtfHeading);
        
        earnedRunsEdit = new EditField( "Earned Runs : ", "", 5, EDITABLE ){
			protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
			
		};
		earnedRunsEdit.setMargin(50,60,40,50);
	    fontHeading = getFontToDisplay("Georgia", 30);
	    earnedRunsEdit.setFont(fontHeading);
	    
		verticalManager.add(earnedRunsEdit);
		
		inningsEdit = new EditField( "Innings Pitched : ", "", 5, EDITABLE ){
			protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
			
		};
		inningsEdit.setMargin(50,60,40,50);
	    fontHeading = getFontToDisplay("Georgia", 30);
	    inningsEdit.setFont(fontHeading);
	    
		verticalManager.add(inningsEdit);
		
		 rtfResult = new RichTextField("", RichTextField.TEXT_ALIGN_HCENTER){
	        	protected void paint(Graphics g){ 
	                g.setColor(0xffff0000);
	                super.paint(g);
	            }
	        };
	        rtfResult.setMargin(50, 50, 40, 50);
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

		double earned;
		double innings = 0;
		double earnedRun;
		if(field == calculateButton)
		{
			if(calculateButton.getLabel().equals("Calculate"))
			{
				calculateButton.setLabel("Reset");
				earnedRunsEdit.setEditable(false);
				inningsEdit.setEditable(false);
				try {
					earned = Double.parseDouble(earnedRunsEdit.getText());
					innings = Double.parseDouble(inningsEdit.getText());
					if(innings == 0)
					{
						Dialog.inform("Innings Pitched cannot be zero");
					}
					else
					{
						earnedRun = (earned * 9) / innings;
						rtfResult.setText("Earned Run Average (ERA) : \n" + String.valueOf(earnedRun));
					}
				} catch (NumberFormatException e) {
					
					Dialog.inform("Please Enter Number!!!!");
					
				}
				catch (ArithmeticException e) {
					if(innings == 0)
					{
						Dialog.inform("Innings Pitched cannot be zero");
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
				earnedRunsEdit.setText("");
				inningsEdit.setText("");
				rtfResult.setText("");
				earnedRunsEdit.setEditable(true);
				inningsEdit.setEditable(true);
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
