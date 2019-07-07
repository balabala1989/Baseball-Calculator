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

public class BattingAverageScreen extends MainScreen implements FieldChangeListener {

	/**
	 * 
	 */
	
	private Bitmap backgroundBitmap;
	VerticalFieldManager verticalManager;
	RichTextField rtfHeading;
	RichTextField rtfResult;
	Font fontHeading = null;
	private EditField hitsEdit;
	private EditField officialBatsEdit;
	ButtonField calculateButton;
	ButtonField backButton;
	
	
	public BattingAverageScreen() {
		

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
        
        rtfHeading = new RichTextField("Batting Average", RichTextField.TEXT_ALIGN_HCENTER){
        	protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
        };
        rtfHeading.setMargin(50, 50, 40, 50);
        fontHeading = getFontToDisplay("Times New Roman", 55);
        rtfHeading.setFont(fontHeading);
        verticalManager.add(rtfHeading);
        
        hitsEdit = new EditField( "Number of Hits : ", "", 5, EDITABLE ){
			protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
			
		};
		hitsEdit.setMargin(50,60,40,50);
	    fontHeading = getFontToDisplay("Georgia", 30);
	    hitsEdit.setFont(fontHeading);
	    
		verticalManager.add(hitsEdit);
		
		officialBatsEdit = new EditField( "Number of Official at bats : ", "", 5, EDITABLE ){
			protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
			
		};
		officialBatsEdit.setMargin(50,60,40,50);
	    fontHeading = getFontToDisplay("Georgia", 30);
	    officialBatsEdit.setFont(fontHeading);
	    
		verticalManager.add(officialBatsEdit);
		
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

		double hits;
		double official = 0;
		double batAverage;
		if(field == calculateButton)
		{
			if(calculateButton.getLabel().equals("Calculate"))
			{
				calculateButton.setLabel("Reset");
				hitsEdit.setEditable(false);
				officialBatsEdit.setEditable(false);
				try {
					hits = Double.parseDouble(hitsEdit.getText());
					official = Double.parseDouble(officialBatsEdit.getText());
					if(official == 0)
					{
						Dialog.inform("Number of Official at bats cannot be zero");
					}
					else
					{
						batAverage = hits / official;
						rtfResult.setText("Batting Average (BV) : \n" + String.valueOf(batAverage));
					}
				} catch (NumberFormatException e) {
					
					Dialog.inform("Please Enter Number!!!!");
					
				}
				catch (ArithmeticException e) {
					if(official == 0)
					{
						Dialog.inform("Number of Official at bats cannot be zero");
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
				hitsEdit.setText("");
				officialBatsEdit.setText("");
				rtfResult.setText("");
				hitsEdit.setEditable(true);
				officialBatsEdit.setEditable(true);
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
