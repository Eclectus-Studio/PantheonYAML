package com.eclectusstudio.pantheonYAML.parser;

import com.eclectusstudio.pantheon.common.PackMCMeta;
import com.eclectusstudio.pantheonYAML.pojo.ProjectElements;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class ProjectElementsParser {

    private static final String ELEMENTS_FILE = "projects-elements";

    public static ProjectElements parse(Path projectDirectory)
            throws IOException {

        Path elementsFile = projectDirectory.resolve(ELEMENTS_FILE);

        if (!Files.exists(elementsFile)) {
            return null;
        }

        Yaml yaml = new Yaml();

        try (InputStream input = Files.newInputStream(elementsFile)) {

            Map<String, Object> root = yaml.load(input);

            if (root == null) {
                return null;
            }

            /*
             * ---------------------------------------------------------
             * Pack MC Meta
             * ---------------------------------------------------------
             */

            PackMCMeta.Builder metaBuilder = PackMCMeta.builder();

            Object metaObject = root.get("pack-mc-meta");

            if (metaObject instanceof Map<?, ?> meta) {

                // description
                Object descriptionObject = meta.get("description");

                if (descriptionObject instanceof String description) {
                    metaBuilder.description(description);
                }

                // pack-format
                Object packFormatObject = meta.get("pack-format");

                if (packFormatObject instanceof Number packFormat) {
                    metaBuilder.packFormat(packFormat.floatValue());
                }

                // supported-formats
                Object supportedFormatsObject =
                        meta.get("supported-formats");

                if (supportedFormatsObject instanceof Map<?, ?> formats) {

                    Object minObject = formats.get("min");
                    Object maxObject = formats.get("max");

                    if (minObject instanceof Number min
                            && maxObject instanceof Number max) {

                        metaBuilder.supports(
                                min.floatValue(),
                                max.floatValue()
                        );
                    }
                }

                // features
                Object featuresObject = meta.get("features");

                if (featuresObject instanceof List<?> features) {

                    for (Object featureObject : features) {

                        if (featureObject instanceof String feature) {
                            metaBuilder.addFeature(feature);
                        }
                    }
                }

                // filters
                Object filtersObject = meta.get("filters");

                if (filtersObject instanceof List<?> filters) {

                    for (Object filterObject : filters) {

                        if (!(filterObject instanceof Map<?, ?> filter)) {
                            continue;
                        }

                        Object namespaceObject =
                                filter.get("namespace");

                        Object pathObject =
                                filter.get("path");

                        if (namespaceObject instanceof String namespace
                                && pathObject instanceof String path) {

                            metaBuilder.addFilter(
                                    namespace,
                                    path
                            );
                        }
                    }
                }

                // overlays
                Object overlaysObject = meta.get("overlays");

                if (overlaysObject instanceof List<?> overlays) {

                    for (Object overlayObject : overlays) {

                        if (!(overlayObject instanceof Map<?, ?> overlay)) {
                            continue;
                        }

                        Object directoryObject =
                                overlay.get("directory");

                        Object minObject =
                                overlay.get("min-format");

                        Object maxObject =
                                overlay.get("max-format");

                        if (directoryObject instanceof String directory
                                && minObject instanceof Number min
                                && maxObject instanceof Number max) {

                            metaBuilder.addOverlay(
                                    directory,
                                    min.intValue(),
                                    max.intValue()
                            );
                        }
                    }
                }

                // languages
                Object languagesObject = meta.get("languages");

                if (languagesObject instanceof Map<?, ?> languages) {

                    for (Map.Entry<?, ?> languageEntry :
                            languages.entrySet()) {

                        if (!(languageEntry.getKey() instanceof String code)
                                || !(languageEntry.getValue()
                                instanceof Map<?, ?> language)) {
                            continue;
                        }

                        Object nameObject =
                                language.get("name");

                        Object regionObject =
                                language.get("region");

                        Object bidirectionalObject =
                                language.get("bidirectional");

                        if (nameObject instanceof String name
                                && regionObject instanceof String region
                                && bidirectionalObject instanceof Boolean bidirectional) {

                            metaBuilder.addLanguage(
                                    code,
                                    name,
                                    region,
                                    bidirectional
                            );
                        }
                    }
                }
            }

            PackMCMeta packMCMeta = metaBuilder.build();

            ProjectElements elements =
                    new ProjectElements(packMCMeta);

            /*
             * ---------------------------------------------------------
             * Project Elements
             * ---------------------------------------------------------
             */

            Object elementsObject = root.get("elements");

            if (!(elementsObject instanceof List<?> elementList)) {
                return elements;
            }

            Path normalizedProjectDirectory =
                    projectDirectory.normalize();

            for (Object elementObject : elementList) {

                if (!(elementObject instanceof Map<?, ?> element)) {
                    continue;
                }

                Object categoryObject = element.get("category");
                Object pathObject = element.get("path");

                if (!(categoryObject instanceof String category)
                        || !(pathObject instanceof String relativePath)) {
                    continue;
                }

                Path elementPath = normalizedProjectDirectory
                        .resolve(relativePath)
                        .normalize();

                /*
                 * Prevent:
                 *
                 * ../../outside-project
                 */
                if (!elementPath.startsWith(normalizedProjectDirectory)) {
                    throw new IOException(
                            "Project element has an invalid path: "
                                    + relativePath
                    );
                }

                if (!Files.exists(elementPath)) {
                    continue;
                }

                switch (category) {

                    case "blockstate" ->
                            elements.addBlockstate(elementPath);

                    case "equipment" ->
                            elements.addEquipment(elementPath);

                    case "fonts" ->
                            elements.addFonts(elementPath);

                    case "item-definition" ->
                            elements.addItemDefinition(elementPath);

                    case "item-model" ->
                            elements.addItemModel(elementPath);

                    case "language" ->
                            elements.addLanguage(elementPath);

                    case "models" ->
                            elements.addModels(elementPath);

                    case "sounds" ->
                            elements.addSounds(elementPath);

                    case "waypoint-styles" ->
                            elements.addWaypointStyles(elementPath);

                    case "cat-variant" ->
                            elements.addCatVariant(elementPath);

                    case "chicken-variant" ->
                            elements.addChickenVariant(elementPath);

                    case "cow-variant" ->
                            elements.addCowVariant(elementPath);

                    case "damage-type" ->
                            elements.addDamageType(elementPath);

                    case "frog-variant" ->
                            elements.addFrogVariant(elementPath);

                    case "jukebox-song" ->
                            elements.addJukeboxSong(elementPath);

                    case "painting-variant" ->
                            elements.addPaintingVariant(elementPath);

                    case "pig-variant" ->
                            elements.addPigVariant(elementPath);

                    case "wolf-sound-variant" ->
                            elements.addWolfSoundVariant(elementPath);

                    case "wolf-variant" ->
                            elements.addWolfVariant(elementPath);

                    case "zombie-nautilus-variant" ->
                            elements.addZombieNautilusVariant(elementPath);

                    default ->
                            throw new IOException(
                                    "Unknown project element category: "
                                            + category
                            );
                }
            }

            return elements;
        }
    }
}
