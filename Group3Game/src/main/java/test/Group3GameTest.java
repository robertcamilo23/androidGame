package test;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import edu.luc.etl.cs313.android.group3Game.view.DotView;
import edu.luc.etl.cs313.android.group3Game.TouchMe;


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
        TouchMe tm = new TouchMe();
        DotView dv;
        tm.runOnUiThread(new Runnable() {
            @Override
           public void run() {
                assertEquals(2, TouchMe.MONSTERS);
                assertEquals(1, TouchMe.LEVEL);

            }
        });
    }
}
