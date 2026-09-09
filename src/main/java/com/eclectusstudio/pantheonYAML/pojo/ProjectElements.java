package com.eclectusstudio.pantheonYAML.pojo;

import com.eclectusstudio.pantheon.common.PackMCMeta;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProjectElements {
    // Project Info
    private final PackMCMeta packMCMeta;

    // Resource Pack
    private final List<Path> blockstate = new ArrayList<>();
    private final List<Path> equipment = new ArrayList<>();
    private final List<Path> fonts = new ArrayList<>();
    private final List<Path> itemDefinition = new ArrayList<>();
    private final List<Path> itemModel = new ArrayList<>();
    private final List<Path> language = new ArrayList<>();
    private final List<Path> models = new ArrayList<>();
    private final List<Path> sounds = new ArrayList<>();
    private final List<Path> waypointStyles = new ArrayList<>();

    //Data pack
    private final List<Path> catVariant = new ArrayList<>();
    private final List<Path> chickenVariant = new ArrayList<>();
    private final List<Path> cowVariant = new ArrayList<>();
    private final List<Path> damageType = new ArrayList<>();
    private final List<Path> frogVariant = new ArrayList<>();
    private final List<Path> jukeboxSong = new ArrayList<>();
    private final List<Path> paintingVariant = new ArrayList<>();
    private final List<Path> pigVariant = new ArrayList<>();
    private final List<Path> wolfSoundVariant = new ArrayList<>();
    private final List<Path> wolfVariant = new ArrayList<>();
    private final List<Path> zombieNautilusVariant = new ArrayList<>();

    // Constructor
    public ProjectElements(PackMCMeta packMCMeta) {
        this.packMCMeta = packMCMeta;
    }

    //Getter
    public PackMCMeta getPackMCMeta() {
        return packMCMeta;
    }

    public List<Path> getBlockstate() {
        return blockstate;
    }

    public List<Path> getEquipment() {
        return equipment;
    }

    public List<Path> getFonts() {
        return fonts;
    }

    public List<Path> getItemDefinition() {
        return itemDefinition;
    }

    public List<Path> getItemModel() {
        return itemModel;
    }

    public List<Path> getLanguage() {
        return language;
    }

    public List<Path> getModels() {
        return models;
    }

    public List<Path> getSounds() {
        return sounds;
    }

    public List<Path> getWaypointStyles() {
        return waypointStyles;
    }

    public List<Path> getCatVariant() {
        return catVariant;
    }

    public List<Path> getChickenVariant() {
        return chickenVariant;
    }

    public List<Path> getCowVariant() {
        return cowVariant;
    }

    public List<Path> getDamageType() {
        return damageType;
    }

    public List<Path> getFrogVariant() {
        return frogVariant;
    }

    public List<Path> getJukeboxSong() {
        return jukeboxSong;
    }

    public List<Path> getPaintingVariant() {
        return paintingVariant;
    }

    public List<Path> getPigVariant() {
        return pigVariant;
    }

    public List<Path> getWolfSoundVariant() {
        return wolfSoundVariant;
    }

    public List<Path> getWolfVariant() {
        return wolfVariant;
    }

    public List<Path> getZombieNautilusVariant() {
        return zombieNautilusVariant;
    }

    //Adder
    public void addBlockstate(Path path) {
        blockstate.add(path);
    }

    public void addEquipment(Path path) {
        equipment.add(path);
    }

    public void addFonts(Path path) {
        fonts.add(path);
    }

    public void addItemDefinition(Path path) {
        itemDefinition.add(path);
    }

    public void addItemModel(Path path) {
        itemModel.add(path);
    }

    public void addLanguage(Path path) {
        language.add(path);
    }

    public void addModels(Path path) {
        models.add(path);
    }

    public void addSounds(Path path) {
        sounds.add(path);
    }

    public void addWaypointStyles(Path path) {
        waypointStyles.add(path);
    }

    public void addCatVariant(Path path) {
        catVariant.add(path);
    }

    public void addChickenVariant(Path path) {
        chickenVariant.add(path);
    }

    public void addCowVariant(Path path) {
        cowVariant.add(path);
    }

    public void addDamageType(Path path) {
        damageType.add(path);
    }

    public void addFrogVariant(Path path) {
        frogVariant.add(path);
    }

    public void addJukeboxSong(Path path) {
        jukeboxSong.add(path);
    }

    public void addPaintingVariant(Path path) {
        paintingVariant.add(path);
    }

    public void addPigVariant(Path path) {
        pigVariant.add(path);
    }

    public void addWolfSoundVariant(Path path) {
        wolfSoundVariant.add(path);
    }

    public void addWolfVariant(Path path) {
        wolfVariant.add(path);
    }

    public void addZombieNautilusVariant(Path path) {
        zombieNautilusVariant.add(path);
    }
}
