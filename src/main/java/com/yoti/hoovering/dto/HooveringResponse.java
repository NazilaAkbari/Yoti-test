package com.yoti.hoovering.dto;

import java.util.List;
import java.util.Objects;

/**
 * Final response of hoovering which contains final coordination of
 * bot and number of cleaned patches
 */
public class HooveringResponse {
    private List<Integer> coords;
    private Integer patches;

    public List<Integer> getCoords() {
        return coords;
    }

    public HooveringResponse setCoords(List<Integer> coords) {
        this.coords = coords;
        return this;
    }

    public Integer getPatches() {
        return patches;
    }

    public HooveringResponse setPatches(Integer patches) {
        this.patches = patches;
        return this;
    }

    @Override
    public String toString() {
        return "HooveringResponse{" +
                "coords=[" + coords.get(0) + "," + coords.get(1) + "]" +
                ", patches=" + patches +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HooveringResponse response = (HooveringResponse) o;
        return Objects.equals(coords, response.coords) &&
                Objects.equals(patches, response.patches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coords, patches);
    }
}
