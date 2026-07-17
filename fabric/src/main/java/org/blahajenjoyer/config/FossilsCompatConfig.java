package org.blahajenjoyer.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Per-animal enable/disable switches, one JSON section per compat mod:
 * {@code {"alexsmobs": {"tiger": true, "grizzly_bear": false, ...}, "naturalist": {...}}}.
 * Only mods that are actually loaded get a section; sections/keys are merged in (never
 * removed) so installing a new supported mod later just extends the existing file.
 */
public class FossilsCompatConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger("fossils_compat");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type SCHEMA = new TypeToken<Map<String, Map<String, Boolean>>>() {}.getType();

    private static Map<String, Map<String, Boolean>> data = new LinkedHashMap<>();
    private static Path path;

    /**
     * @param modAnimalKeys every supported mod id mapped to the full list of animal keys it
     *                      could ever register, regardless of whether that mod is loaded
     */
    public static void init(Map<String, List<String>> modAnimalKeys) {
        path = FabricLoader.getInstance().getConfigDir().resolve("fossils_compat.json");
        load();

        boolean changed = false;
        for (Map.Entry<String, List<String>> mod : modAnimalKeys.entrySet()) {
            if (!FabricLoader.getInstance().isModLoaded(mod.getKey())) {
                continue;
            }
            Map<String, Boolean> section = data.computeIfAbsent(mod.getKey(), k -> new LinkedHashMap<>());
            for (String key : mod.getValue()) {
                if (!section.containsKey(key)) {
                    section.put(key, true);
                    changed = true;
                }
            }
        }

        if (changed || !Files.exists(path)) {
            save();
        }
    }

    public static boolean isEnabled(String mod, String key) {
        Map<String, Boolean> section = data.get(mod);
        if (section == null) {
            return true;
        }
        return section.getOrDefault(key, true);
    }

    private static void load() {
        if (!Files.exists(path)) {
            return;
        }
        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            Map<String, Map<String, Boolean>> loaded = GSON.fromJson(reader, SCHEMA);
            if (loaded != null) {
                data = loaded;
            }
        } catch (IOException e) {
            LOGGER.error("Failed to read fossils_compat.json, using defaults", e);
        }
    }

    private static void save() {
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, GSON.toJson(data).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            LOGGER.error("Failed to write fossils_compat.json", e);
        }
    }
}
