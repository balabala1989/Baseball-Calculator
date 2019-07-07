package mypackage;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.util.StringProvider;

public class BaseballCalcScreen extends MainScreen implements FieldChangeListener {

	ButtonField baseRunningAverageButton;
	ButtonField battingAverageButton;
	ButtonField earnedRunAverageButton;
	ButtonField fieldingPercentageButton;
	ButtonField sluggingPercentageButton;
	ButtonField helpButton;
	private Bitmap backgroundBitmap;
	VerticalFieldManager verticalManager;
	RichTextField rtfHeading;
	Font fontHeading = null;
	 
    public BaseballCalcScreen() {
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
        
        rtfHeading = new RichTextField("Baseball Calculator", RichTextField.TEXT_ALIGN_HCENTER){
        	protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
        };
        rtfHeading.setMargin(50, 50, 40, 50);
        fontHeading = getFontToDisplay("Times New Roman", 55);
        rtfHeading.setFont(fontHeading);
        verticalManager.add(rtfHeading);
       
        fontHeading = getFontToDisplay("Comic Sans MS", 30);
        baseRunningAverageButton = new ButtonField( "Base Running Average", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
        baseRunningAverageButton.setChangeListener(this);
        baseRunningAverageButton.setMargin(35, 35, 25, 45);
        baseRunningAverageButton.setFont(fontHeading);
        verticalManager.add( baseRunningAverageButton );
        
        battingAverageButton = new ButtonField( "   Batting Average  ", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
        battingAverageButton.setChangeListener(this);
        battingAverageButton.setMargin(35, 35, 25, 45);
        battingAverageButton.setFont(fontHeading);
        verticalManager.add( battingAverageButton );
        
        earnedRunAverageButton = new ButtonField( " Earned Run Average ", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
        earnedRunAverageButton.setChangeListener(this);
        earnedRunAverageButton.setMargin(35, 35, 25, 45);
        earnedRunAverageButton.setFont(fontHeading);
        verticalManager.add( earnedRunAverageButton );
        
        fieldingPercentageButton = new ButtonField( " Fielding Percentage ", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
        fieldingPercentageButton.setChangeListener(this);
        fieldingPercentageButton.setMargin(35, 35, 25, 45);
        fieldingPercentageButton.setFont(fontHeading);
        verticalManager.add( fieldingPercentageButton );
        
        sluggingPercentageButton = new ButtonField( "Slugging Percentage ", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
        sluggingPercentageButton.setChangeListener(this);
        sluggingPercentageButton.setMargin(35, 35, 25, 45);
        sluggingPercentageButton.setFont(fontHeading);
        verticalManager.add( sluggingPercentageButton );
        
        helpButton = new ButtonField( "        Help        ", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
        helpButton.setChangeListener(this);
        helpButton.setMargin(35, 35, 25, 45);
        helpButton.setFont(fontHeading);
        verticalManager.add( helpButton );
        
        add(verticalManager);
        
        
       
    }
    
    public void fieldChanged(Field field, int context) {
        if(field == baseRunningAverageButton)
        {
        	UiApplication.getUiApplication().pushScreen(new BaseRunningAverageScreen());
        }
        else if(field == battingAverageButton)
        {
        	UiApplication.getUiApplication().pushScreen(new BattingAverageScreen());
        }
        else if(field == earnedRunAverageButton)
        {
        	UiApplication.getUiApplication().pushScreen(new EarnedRunAverageScreen());
        }
        else if(field == fieldingPercentageButton)
        {
        	UiApplication.getUiApplication().pushScreen(new FieldingPercentageScreen());
        }
        else if(field == sluggingPercentageButton)
        {
        	UiApplication.getUiApplication().pushScreen(new SluggingPercentageScreen());
        }
        else 
        {
        	UiApplication.getUiApplication().pushScreen(new HelpScreen());
        }
    }

    protected void makeMenu( Menu menu, int instance ) {
    	super.makeMenu(menu, instance);
        MenuItem mntm = new NewMenuItem();
        menu.add( mntm );
    }

    private class NewMenuItem extends MenuItem {
        public NewMenuItem() {
            super( new StringProvider( "Choose" ), 0, 0 );
        }

        public void run() {
        	chooseOption();
        }
    }

    private void chooseOption() {
    	
    	Dialog.inform("Please Select a Option and Continue!!!!");
       
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
