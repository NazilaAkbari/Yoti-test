package com.yoti.hoovering.mapper;

import com.yoti.hoovering.domain.Coordination;
import com.yoti.hoovering.domain.Hoovering;
import com.yoti.hoovering.dto.HooveringRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class HooveringMapperTest {

    @Test
    public void mapTest() {
        HooveringMapper hooveringMapper = new HooveringMapper();
        Hoovering map = hooveringMapper.map(getHooveringRequest());
        assertThat(map.getInstructions()).isEqualTo("NNESEESWNWW");
        assertThat(map.getRoomCoordination()).isEqualTo(new Coordination(5,5));
        assertThat(map.getBotCoordination()).isEqualTo(new Coordination(1,2));
        assertThat(map.getPatches().size()).isEqualByComparingTo(3);
    }

    @Test
    public void mapEmptyInstructionTest() {
        HooveringMapper hooveringMapper = new HooveringMapper();
        HooveringRequest hooveringRequest = getHooveringRequest();
        hooveringRequest.setInstructions(null);
        Hoovering map = hooveringMapper.map(hooveringRequest);
        assertThat(map.getInstructions()).isEqualTo(null);
        assertThat(map.getRoomCoordination()).isEqualTo(new Coordination(5,5));
        assertThat(map.getBotCoordination()).isEqualTo(new Coordination(1,2));
        assertThat(map.getPatches().size()).isEqualByComparingTo(3);
    }

    @Test
    public void mapEmptyPatchesTest() {
        HooveringMapper hooveringMapper = new HooveringMapper();
        HooveringRequest hooveringRequest = getHooveringRequest();
        hooveringRequest.setPatches(null);
        Hoovering map = hooveringMapper.map(hooveringRequest);
        assertThat(map.getInstructions()).isEqualTo("NNESEESWNWW");
        assertThat(map.getRoomCoordination()).isEqualTo(new Coordination(5,5));
        assertThat(map.getBotCoordination()).isEqualTo(new Coordination(1,2));
        assertThat(map.getPatches().size()).isEqualByComparingTo(0);
    }

    @Test
    public void mapInvalidBotCoordTest() {
        HooveringMapper hooveringMapper = new HooveringMapper();
        HooveringRequest hooveringRequest = getHooveringRequest();
        List<Integer> coords=new ArrayList<>();
        coords.add(7);
        coords.add(2);
        hooveringRequest.setCoords(coords);

        assertThatIllegalArgumentException().isThrownBy(() -> hooveringMapper.map(hooveringRequest));
    }

    private HooveringRequest getHooveringRequest() {
        List<Integer> room = new ArrayList<>();
        room.add(5);
        room.add(5);
        List<Integer> coord = new ArrayList<>();
        coord.add(1);
        coord.add(2);
        return new HooveringRequest().setRoomSize(room)
                .setCoords(coord)
                .setPatches(getPatches())
                .setInstructions("NNESEESWNWW");
    }

    private List<List<Integer>> getPatches() {
        List<List<Integer>> patches = new ArrayList<>();
        List<Integer> patch1 = new ArrayList<>();
        patch1.add(1);
        patch1.add(0);
        List<Integer> patch2 = new ArrayList<>();
        patch2.add(2);
        patch2.add(2);
        List<Integer> patch3 = new ArrayList<>();
        patch3.add(2);
        patch3.add(3);
        patches.add(patch1);
        patches.add(patch2);
        patches.add(patch3);
        return patches;
    }
}