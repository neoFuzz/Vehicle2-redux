import dev.jcps.vehicle2redux.BestTimesPanel;
import dev.jcps.vehicle2redux.LevelSelectPanel;
import dev.jcps.vehicle2redux.V2RApp;
import dev.jcps.vehicle2redux.Vehicle2;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class V2RAppGUITest {

    private static FrameFixture window;

    @BeforeAll
    static void setUp() {
        // Create an instance of the main app (V2RApp)
        V2RApp v2r = new V2RApp();

        v2r.setVehicle2r(new Vehicle2());
        v2r.setLevelSelectPanel(new LevelSelectPanel(v2r.getVehicle2r().maps));
        v2r.setBtp(new BestTimesPanel());

        v2r.getVehicle2r().addEventListener(v2r);
        v2r.getLevelSelectPanel().addEventListener(v2r);
        v2r.getOptionsPanel().addEventListener(v2r);
        v2r.getBtp().addEventListener(v2r);
        v2r.add(v2r.getMp(), BorderLayout.CENTER);

        V2RApp.setGameState(V2RApp.MENU_PANEL);

        window = new FrameFixture(v2r);  // Wrap the app window with AssertJ Swing
        window.show(new Dimension(640, 480));  // Show the window for interaction
    }

    @Test
    void test1StartGameButton() {
        // Verify that the window has the expected components
        window.button("btnStart").requireVisible();

        // Click the "Start Game" button
        window.button("btnStart").click();

        // Verify the game state has changed to GAME_PANEL
        assertThat(V2RApp.getGameState()).isEqualTo(V2RApp.GAME_PANEL);

        // Press ESC key
        window.pressAndReleaseKeys(KeyEvent.VK_ESCAPE);

        // Check back on main menu
        assertThat(V2RApp.getGameState()).isEqualTo(V2RApp.MENU_PANEL);
    }

    @Test
    void test2LevelSelect() {
        // nextButton prevButton endButton exitButton
        // Verify that the window has the expected components
        window.button("btnLevelSelect").requireVisible();

        // Click the "level select" button
        window.button("btnLevelSelect").click();

        // click these buttons: nextButton prevButton endButton exitButton
        window.button("nextButton").click();
        assertNotEquals(0, ((LevelSelectPanel) window.panel("levelSelect")
                .target()).getCurrentPage());
        window.button("prevButton").click();
        window.button("endButton").click();
        window.button("exitButton").click();

        assertThat(V2RApp.getGameState()).isEqualTo(V2RApp.MENU_PANEL);
    }

    @Test
    void test3OptionPanel() {
        // Verify that the window has the expected components
        window.button("btnOptions").requireVisible();

        // Click the "Options" button
        window.button("btnOptions").click();

        // Check option menu is open
        assertThat(V2RApp.getGameState()).isEqualTo(V2RApp.OPTIONS_PANEL);

        // Confirm exit options button works
        window.button("btnExit").click();
        assertThat(V2RApp.getGameState()).isEqualTo(V2RApp.MENU_PANEL);

        window.button("btnOptions").click();
        window.button("btnBTPanel").click();
        assertThat(V2RApp.getGameState()).isEqualTo(V2RApp.BEST_TIMES);
        window.button("btnExit").click();
    }
}
