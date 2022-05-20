import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import com.parkway.topwise.*;

/**
 * This class echoes a string called from JavaScript.
 */
public class TopwisePlugin extends CordovaPlugin {

    private Activity activity;
    private CordovaWebView webView;
    private CallbackContext callContext;
    private Printer printer;
    private Payment payment;



    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        System.out.println("In Execute Calling function with action " + action);
        this.callContext = callbackContext;
        if (action.equalsIgnoreCase("printAction")) {

            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        performPrint(args,callbackContext);
                    } catch (Exception ex) {
                        ex.printStackTrace(System.err);
                        PluginResult res = new PluginResult(PluginResult.Status.ERROR, ex.getMessage());
                        callbackContext.sendPluginResult(res);
                    }
                    // printer.print();
                }
            });

        }
        if(action.equalsIgnoreCase("payAction")){
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    payment.pay(callbackContext);
                }
            });
        }

        return true;
    }

    public void performPrint(CordovaArgs args, CallbackContext context) throws Exception {
        System.out.println("About to perform print");
        Object o =  args.get(0);
        new Printer(this.activity).print((JSONArray) o, context);
        // return new PluginResult(PluginResult.Status.OK);
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView){
        System.out.println("Cordova initialization being called setting up objects .....................#######");
        super.initialize(cordova, webView);
        this.activity = cordova.getActivity();
        this.webView = webView;
        this.printer = new Printer(cordova.getActivity());
        this.payment =  new Payment(cordova.getActivity());

    }}
