package com.yoti.hoovering.domain;


import java.util.Set;

/**
 * Hoovering class containing state of room, bot, dirty patches and instruction of bot moving
 */
public class Hoovering {
    private Coordination roomCoordination;
    private Coordination botCoordination;
    private Set<Coordination> patches;
    private String instructions;

    public Coordination getRoomCoordination() {
        return roomCoordination;
    }

    public Hoovering setRoomCoordination(Coordination roomCoordination) {
        this.roomCoordination = roomCoordination;
        return this;
    }

    public Coordination getBotCoordination() {
        return botCoordination;
    }

    public Hoovering setBotCoordination(Coordination botCoordination) {
        this.botCoordination = botCoordination;
        return this;
    }

    public Set<Coordination> getPatches() {
        return patches;
    }

    public Hoovering setPatches(Set<Coordination> patches) {
        this.patches = patches;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public Hoovering setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }
}
