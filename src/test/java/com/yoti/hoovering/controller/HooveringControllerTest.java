package com.yoti.hoovering.controller;


import com.yoti.hoovering.dto.HooveringRequest;
import com.yoti.hoovering.dto.HooveringResponse;
import com.yoti.hoovering.repository.HooveringLogRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HooveringControllerTest {

    private final static String url = "/hoover";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private HooveringLogRepository logRepository;

    @AfterEach
    public void teardown() {
        logRepository.deleteAll();
    }

    @Test
    public void hooverTest() {
        HooveringRequest hooveringRequest = getHooveringRequest();
        ResponseEntity<HooveringResponse> responseEntity = restTemplate
                .postForEntity(url,
                        hooveringRequest,
                        HooveringResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(getHooveringResponse());
        assertThat(logRepository.count()).isEqualTo(1);
    }

    @Test
    public void hooverEmptyRoomCoordTest() {
        HooveringRequest hooveringRequest = getHooveringRequest();
        hooveringRequest.setRoomSize(new ArrayList<>());
        ResponseEntity<HooveringResponse> responseEntity = restTemplate
                .postForEntity(url,
                        hooveringRequest,
                        HooveringResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
        assertThat(logRepository.count()).isEqualTo(0);
    }

    @Test
    public void hooverEmptyBotCoordTest() {
        HooveringRequest hooveringRequest = getHooveringRequest();
        hooveringRequest.setCoords(new ArrayList<>());
        ResponseEntity<HooveringResponse> responseEntity = restTemplate
                .postForEntity(url,
                        hooveringRequest,
                        HooveringResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
        assertThat(logRepository.count()).isEqualTo(0);
    }

    @Test
    public void hooverEmptyInstructionTest() {
        HooveringRequest hooveringRequest = getHooveringRequest();
        hooveringRequest.setInstructions(null);

        HooveringResponse hooveringResponse = getHooveringResponse();
        List<Integer> coords = new ArrayList<>();
        coords.add(1);
        coords.add(2);
        hooveringResponse.setPatches(0).setCoords(coords);
        ResponseEntity<HooveringResponse> responseEntity = restTemplate
                .postForEntity(url,
                        hooveringRequest,
                        HooveringResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(hooveringResponse);
        assertThat(logRepository.count()).isEqualTo(1);
    }

    @Test
    public void hooverEmptyPatchesTest() {
        HooveringRequest hooveringRequest = getHooveringRequest();
        hooveringRequest.setPatches(null);

        HooveringResponse hooveringResponse = getHooveringResponse();
        hooveringResponse.setPatches(0);
        ResponseEntity<HooveringResponse> responseEntity = restTemplate
                .postForEntity(url,
                        hooveringRequest,
                        HooveringResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(hooveringResponse);
        assertThat(logRepository.count()).isEqualTo(1);
    }

    @Test
    public void hooverWithSkidTest() {
        HooveringRequest hooveringRequest = getHooveringRequest();
        hooveringRequest.setInstructions("NNESEESWNWWWWW");

        HooveringResponse hooveringResponse = getHooveringResponse();
        List<Integer> coords = new ArrayList<>();
        coords.add(0);
        coords.add(3);
        hooveringResponse.setCoords(coords);

        ResponseEntity<HooveringResponse> responseEntity = restTemplate
                .postForEntity(url,
                        hooveringRequest,
                        HooveringResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(hooveringResponse);
        assertThat(logRepository.count()).isEqualTo(1);
    }

    @Test
    public void invalidRoomCoordTest() {
        HooveringRequest hooveringRequest = getHooveringRequest();
        List<Integer> room = new ArrayList<>();
        room.add(-2);
        room.add(-3);
        hooveringRequest.setRoomSize(room);
        ResponseEntity<HooveringResponse> responseEntity = restTemplate
                .postForEntity(url,
                        hooveringRequest,
                        HooveringResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void invalidBotCoordTest() {
        HooveringRequest hooveringRequest = getHooveringRequest();
        List<Integer> bot = new ArrayList<>();
        bot.add(-2);
        bot.add(-3);
        hooveringRequest.setCoords(bot);
        ResponseEntity<HooveringResponse> responseEntity = restTemplate
                .postForEntity(url,
                        hooveringRequest,
                        HooveringResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void botCoordNotInRoomTest() {
        HooveringRequest hooveringRequest = getHooveringRequest();
        List<Integer> bot = new ArrayList<>();
        bot.add(6);
        bot.add(1);
        hooveringRequest.setCoords(bot);
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(url,
                        hooveringRequest,
                        String.class);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void invalidInstructionTest() {
        HooveringRequest hooveringRequest = getHooveringRequest();
        hooveringRequest.setInstructions("NNMMSS");
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(url,
                        hooveringRequest,
                        String.class);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    private HooveringResponse getHooveringResponse() {
        List<Integer> coords = new ArrayList<>();
        coords.add(1);
        coords.add(3);
        return new HooveringResponse().setCoords(coords).setPatches(1);
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