package com.example.blockfacecullingfix;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockFaceCullingFixMod implements ModInitializer {
    public static final String MOD_ID = "blockfacecullingfix";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("BlockFaceCullingFix мод загружен! Оптимизация рендера граней блоков активирована.");
    }
}
