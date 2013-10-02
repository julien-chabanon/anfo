package application;

import com.stericson.RootTools.RootTools;

public class Root {

    public boolean isDeviceRooted() {
        if (checkRootMethod6()){return true;}
        return false;
    }

    
    public boolean checkRootMethod6() {
    	if (RootTools.isAccessGiven()) {
    	    return true;
    	} else {
    	    return false;
    	}
    }
}