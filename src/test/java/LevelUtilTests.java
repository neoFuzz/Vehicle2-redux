import dev.jcps.vehicle2redux.LevelMap;
import dev.jcps.vehicle2redux.LevelUtilities;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

class LevelUtilTests {
    @Test
    void mapTest() {
        ArrayList<LevelMap> maps = LevelUtilities.getLevelMaps();
        assertFalse(maps.isEmpty());
    }

    @Test
    void badLeveltest() {
        boolean lm = LevelUtilities.fileExists("/badmap.map");
        assertFalse(lm);
    }
}
