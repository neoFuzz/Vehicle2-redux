import dev.jcps.vehicle2redux.BestTimesPanel;
import dev.jcps.vehicle2redux.TriggerEvent;
import dev.jcps.vehicle2redux.V2RApp;
import dev.jcps.vehicle2redux.Vehicle2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

@PrepareForTest(JOptionPane.class)
@RunWith(PowerMockRunner.class)  // Use PowerMockRunner for PowerMockito support
class Vehicle2Tests {
    private V2RApp app;
    private Timer mockTimer;
    private Vehicle2 mockVehicle2;

    @BeforeEach
    void setUp() {
        mockVehicle2 = mock(Vehicle2.class);
        mockTimer = mock(Timer.class);
        app = Mockito.spy(new V2RApp());

        // Mock vehicle2r in the app
        app.setVehicle2r(mockVehicle2);
        app.setTimer(mockTimer);
    }

    @Test
    void testStartGameWithNoMaps() {
        // Mock the static method of JOptionPane
        PowerMockito.mockStatic(JOptionPane.class);

        // Return an empty list of maps (no maps available)
        doReturn(new ArrayList<>()).when(mockVehicle2).getMaps();

        // Create the application object
        app = new V2RApp();
        app.setVehicle2r(mockVehicle2);

        // Call the method that triggers the dialog (startGame)
        app.startGame();

        // Verify that showMessageDialog was called
        PowerMockito.verifyStatic(JOptionPane.class);
        JOptionPane.showMessageDialog(null,
                "Can not start the game. There are no map files in the Levels/ directory",
                anyString(), JOptionPane.ERROR_MESSAGE);
    }
}
