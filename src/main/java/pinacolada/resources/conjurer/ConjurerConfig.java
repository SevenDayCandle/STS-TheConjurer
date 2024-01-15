package pinacolada.resources.conjurer;

import basemod.BaseMod;
import basemod.ModPanel;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import extendedui.EUIUtils;
import extendedui.configuration.EUIConfiguration;
import extendedui.configuration.STSConfigItem;
import extendedui.ui.settings.BasemodSettingsPage;
import pinacolada.resources.PCLCharacterConfig;
import pinacolada.resources.PCLMainConfig;
import pinacolada.resources.PGR;

public class ConjurerConfig extends PCLCharacterConfig {
    private static final String MOD_ID = "CONJURER";
    public STSConfigItem<Boolean> allowColorlessForAll;
    public STSConfigItem<Boolean> allowCustom;
    public STSConfigItem<Boolean> seenSummonTutorial;

    public ConjurerConfig() {
        super(MOD_ID);
        this.allowColorlessForAll = new STSConfigItem<Boolean>(PGR.createID(id, "AllowColorlessForAll"), false);
        this.allowCustom = new STSConfigItem<Boolean>(PGR.createID(id, "AllowCustom"), true);
        this.seenSummonTutorial = new STSConfigItem<Boolean>(PGR.createID(id, "SeenSummonTutorialConjurer"), false);
    }

    public void initializeOptions() {
        panel = new ModPanel();
        settingsBlock = new BasemodSettingsPage();
        panel.addUIElement(settingsBlock);

        float yPos = BASE_OPTION_OFFSET_Y * Settings.scale;
        yPos = addToggle(0, allowColorlessForAll, ConjurerResources.conjurer.strings.optionColorless, yPos, ConjurerResources.conjurer.strings.optionColorlessDesc);
        yPos = addToggle(0, allowCustom, PGR.core.strings.options_enableCustomCards, yPos, PGR.core.strings.csel_betaCardSet);

        BaseMod.registerModBadge(ImageMaster.loadImage("images/pcl/modBadge.png"), MOD_ID, "PinaColada", "", panel);
    }

    public void loadImpl() {
        super.loadImpl();
        this.allowColorlessForAll.addConfig(this.config);
        this.allowCustom.addConfig(this.config);
        this.seenSummonTutorial.addConfig(this.config);
    }

    public void resetTutorial() {
        super.resetTutorial();
        this.seenSummonTutorial.set(false);
    }
}
