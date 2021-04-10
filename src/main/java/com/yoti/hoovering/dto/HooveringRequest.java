package com.yoti.hoovering.dto;

import com.yoti.hoovering.validator.ValidateCoordination;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Initial request for hoovering
 */
public class HooveringRequest {

    @NotEmpty
    @ValidateCoordination
    private List<Integer> roomSize;
    @NotEmpty
    @ValidateCoordination
    private List<Integer> coords;
    private List<List<Integer>> patches;
    private String instructions;

    public List<Integer> getRoomSize() {
        return roomSize;
    }

    public HooveringRequest setRoomSize(List<Integer> roomSize) {
        this.roomSize = roomSize;
        return this;
    }

    public List<Integer> getCoords() {
        return coords;
    }

    public HooveringRequest setCoords(List<Integer> coords) {
        this.coords = coords;
        return this;
    }

    public List<List<Integer>> getPatches() {
        return patches;
    }

    public HooveringRequest setPatches(List<List<Integer>> patches) {
        this.patches = patches;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public HooveringRequest setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder patchesStr = new StringBuilder();
        if (patches != null) {
            for (List<Integer> p : patches) {
                patchesStr.append("[").append(p.get(0)).append(",").append(p.get(1)).append("]");
            }
        }
        return "HooveringRequest{" +
                "roomSize=[" + roomSize.get(0) + "," + roomSize.get(1) + "]" +
                ", coords=[" + coords.get(0) + "," + coords.get(1) + "]" +
                ", patches=[" + patchesStr.toString() + "]" +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
