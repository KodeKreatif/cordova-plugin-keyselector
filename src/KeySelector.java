package id.kodekreatif.cordova;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import android.security.KeyChain;
import android.security.KeyChainAliasCallback;
import android.util.Log;

public class KeySelector extends CordovaPlugin implements KeyChainAliasCallback {

  private static final String TAG = KeySelector.class.getSimpleName();
  private static final int REQUEST = 1;


  CallbackContext context = null;

  @Override
  public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
    if (action.equals("select")) {
      KeyChain.choosePrivateKeyAlias(cordova.getActivity(), this, new String[] { "RSA" }, null, null, -1, null); 
      context = callbackContext;

      return true;
    } else {

      return false;

    }
  }

  @Override
  public void alias(final String alias) {
    if (context != null) {
      context.success(alias);    
    } else {
      Log.d(TAG, "context is null");
    }
  }
}
