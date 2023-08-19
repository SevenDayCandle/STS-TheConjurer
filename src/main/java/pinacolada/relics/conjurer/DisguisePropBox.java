package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIInputManager;
import extendedui.EUIUtils;
import pinacolada.actions.PCLAction;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.characters.CreatureAnimationInfo;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.vfx.SmokeEffect;
import pinacolada.misc.PCLCollectibleSaveData;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class DisguisePropBox extends PCLRelic {
    public static final PCLRelicData DATA = register(DisguisePropBox.class, ConjurerResources.conjurer)
            .setProps(RelicTier.SPECIAL, LandingSound.FLAT);
    protected transient PSkill<?> monsterSkill;
    protected String currentForm;

    public DisguisePropBox() {
        super(DATA);
    }

    @Override
    protected void activateBattleEffect() {
        if (currentForm != null) {
            GameUtilities.setCreatureAnimation(player, currentForm);
        }

        monsterSkill = ConjurerPlayerData.getSkillForMonster(currentForm);
        if (monsterSkill != null) {
            monsterSkill.use(new PCLUseInfo(null, player, null), PCLActions.bottom);
        }
    }

    public String getDescriptionImpl() {
        String desc = super.getDescriptionImpl();
        return monsterSkill != null ? EUIUtils.joinStrings(EUIUtils.SPLIT_LINE, desc, formatDescription(1, monsterSkill.getPowerText())) : desc;
    }

    public int getValue() {
        return 2;
    }

    @Override
    public void onLoad(PCLCollectibleSaveData data) {
        super.onLoad(data);
        if (this.auxiliaryData != null && this.auxiliaryData.additionalData != null && this.auxiliaryData.additionalData.size() > 0) {
            currentForm = this.auxiliaryData.additionalData.get(0);
        }
    }

    protected PCLAction<AbstractCreature> selectCreatureForTransform() {
        return PCLActions.bottom.selectCreature(PCLCardTarget.Any, name).addCallback((c) -> {
            if (c.id == null) {
                String p = CreatureAnimationInfo.getRandomKey();
                if (p != null) {
                    setCreature(p);
                }
            }
            else {
                setCreature(CreatureAnimationInfo.getIdentifierString(c));
            }

        });
    }

    public void setCreature(String id) {
        currentForm = id;
        if (currentForm != null) {
            if (GameUtilities.inBattle()) {
                PCLEffects.Queue.add(new SmokeEffect(player.hb.cX, player.hb.cY, player.getCardRenderColor()));
            }
            GameUtilities.setCreatureAnimation(player, currentForm);
        }
        if (auxiliaryData == null) {
            auxiliaryData = new PCLCollectibleSaveData();
        }
        if (auxiliaryData.additionalData == null || auxiliaryData.additionalData.isEmpty()) {
            auxiliaryData.additionalData = EUIUtils.arrayList(currentForm);
        }
        else {
            auxiliaryData.additionalData.set(0, currentForm);
        }
    }

    @Override
    public void update() {
        super.update();

        if (GameUtilities.inBattle()
                && this.hb.hovered
                && EUIInputManager.rightClick.isJustPressed()) {
            selectCreatureForTransform().addCallback(() -> {
                monsterSkill = ConjurerPlayerData.getSkillForMonster(currentForm);
            });
        }
    }
}