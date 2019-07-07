package mypackage;

import net.rim.device.api.ui.UiApplication;

/**
 * This class extends the UiApplication class, providing a graphical user interface.
 */
public class BaseballCalc extends UiApplication {
    /**
     * Entry point for application
     * 
     * @param args
     *            Command line arguments (not used)
     */
    public static void main( String[] args ) {
        // Create a new instance of the application and make the currently
        // running thread the application's event dispatch thread.
        BaseballCalc theApp = new BaseballCalc();
        theApp.enterEventDispatcher();
    }

    /**
     * Creates a new BaseballCalc object
     */
    public BaseballCalc() {
        // Push a screen onto the UI stack for rendering.
        pushScreen( new BaseballCalcScreen() );
    }
}
