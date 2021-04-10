package com.yoti.hoovering.service;

import com.yoti.hoovering.domain.Coordination;
import com.yoti.hoovering.domain.Hoovering;
import com.yoti.hoovering.dto.HooveringResponse;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class HooveringServiceTest {

    @Test
    public void hooverTest() {
        HooveringService hooveringService = new HooveringServiceImpl();
        Set<Coordination> patches = new HashSet<>();
        patches.add(new Coordination(1, 0));
        patches.add(new Coordination(2, 2));
        patches.add(new Coordination(2, 3));
        Hoovering hoovering = new Hoovering()
                .setRoomCoordination(new Coordination(5, 5))
                .setBotCoordination(new Coordination(1, 2))
                .setPatches(patches)
                .setInstructions("NNESEESWNWW");
        HooveringResponse hoover = hooveringService.hoover(hoovering);
        assertThat(hoover.getCoords().get(0)).isEqualByComparingTo(1);
        assertThat(hoover.getCoords().get(1)).isEqualByComparingTo(3);
        assertThat(hoover.getPatches()).isEqualByComparingTo(1);
    }

    @Test
    public void hooverWithEmptyInstructionTest() {
        HooveringService hooveringService = new HooveringServiceImpl();
        Set<Coordination> patches = new HashSet<>();
        patches.add(new Coordination(1, 0));
        patches.add(new Coordination(2, 2));
        patches.add(new Coordination(2, 3));
        Hoovering hoovering = new Hoovering()
                .setRoomCoordination(new Coordination(5, 5))
                .setBotCoordination(new Coordination(1, 2))
                .setPatches(patches);
        HooveringResponse hoover = hooveringService.hoover(hoovering);
        assertThat(hoover.getCoords().get(0)).isEqualByComparingTo(1);
        assertThat(hoover.getCoords().get(1)).isEqualByComparingTo(2);
        assertThat(hoover.getPatches()).isEqualByComparingTo(0);
    }

    @Test
    public void hooverWithEmptyPatchesTest() {
        HooveringService hooveringService = new HooveringServiceImpl();
        Hoovering hoovering = new Hoovering()
                .setRoomCoordination(new Coordination(5, 5))
                .setBotCoordination(new Coordination(1, 2))
                .setPatches(new HashSet<>())
                .setInstructions("NNESEESWNWW");
        HooveringResponse hoover = hooveringService.hoover(hoovering);
        assertThat(hoover.getCoords().get(0)).isEqualByComparingTo(1);
        assertThat(hoover.getCoords().get(1)).isEqualByComparingTo(3);
        assertThat(hoover.getPatches()).isEqualByComparingTo(0);
    }

    @Test
    public void hooverWithInvalidInstructionTest() {
        HooveringService hooveringService = new HooveringServiceImpl();
        Set<Coordination> patches = new HashSet<>();
        patches.add(new Coordination(1, 0));
        patches.add(new Coordination(2, 2));
        patches.add(new Coordination(2, 3));
        Hoovering hoovering = new Hoovering()
                .setRoomCoordination(new Coordination(5, 5))
                .setBotCoordination(new Coordination(1, 2))
                .setInstructions("NNESEESWNWWM")
                .setPatches(patches);
        assertThatIllegalStateException().isThrownBy(() -> hooveringService.hoover(hoovering));
    }
}