package test;


import org.junit.Test;
import android.content.pm.ActivityInfo;
import android.util.Log;


import junit.framework.TestCase;
import android.test.InstrumentationTestRunner;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import edu.luc.etl.cs313.android.group3Game.R;
import edu.luc.etl.cs313.android.group3Game.model.AutoRandomEngine;
import edu.luc.etl.cs313.android.group3Game.model.Dot;
import edu.luc.etl.cs313.android.group3Game.model.Dots;
import edu.luc.etl.cs313.android.group3Game.view.DotView;
import edu.luc.etl.cs313.android.group3Game.TouchMe;
import edu.luc.etl.cs313.android.group3Game.TouchMe.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Robert Klein on 4/30/14.
 */
public class Group3GameTest extends TestCase {

    @Test
    public void testActivityCheckTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", new TouchMe());
    }

    @Test
    public void checkInitialSettings() {
        assertEquals(0, TouchMe.MONSTERS);
        assertEquals(1, TouchMe.LEVEL);
        //EditText et1 = (EditText) new TouchMe().findViewById(R.id.text1);
        //EditText et2 = (EditText) new TouchMe().findViewById(R.id.text2);
        //TouchMe().dotGenerator
        //assertEquals(0, et1.getText());
    }
}
