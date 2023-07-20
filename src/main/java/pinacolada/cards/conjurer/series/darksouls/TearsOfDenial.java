package pinacolada.cards.conjurer.series.darksouls;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import extendedui.EUIUtils;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.CombatManager;
import pinacolada.patches.power.FrailPowerPatches;
import pinacolada.patches.power.VulnerablePowerPatches;
import pinacolada.patches.power.WeakPowerPatches;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;

import java.util.HashMap;

@VisibleCard
public class TearsOfDenial extends PCLCard {
    public static final PCLCardData DATA = register(TearsOfDenial.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Self)
            .setCostUpgrades(-1)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls, true);

    public TearsOfDenial() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gain(3, PCLPowerHelper.Frail, PCLPowerHelper.Vulnerable, PCLPowerHelper.Weak));
        addSpecialPower(0, (s, i) -> new TearsOfDenialPower(i.source, s), 2, 1);
    }

    // TODO implement effect bonus subscriber to apply updates to this power
    public static class TearsOfDenialPower extends PSpecialCardPower {
        protected HashMap<String, Float> recordedBonuses = new HashMap<>();

        public TearsOfDenialPower(AbstractCreature owner, PSkill<?> move) {
            super(DATA, owner, move);
            initialize(move.amount, PowerType.DEBUFF, true);
        }

        @Override
        public void atStartOfTurn() {
            super.atStartOfTurn();
            reducePower(1);
        }

        public void onInitialApplication() {
            for (String id : getAffectedPowers()) {
                float existing = CombatManager.getPlayerEffectBonus(id);
                recordedBonuses.put(id, existing);
                CombatManager.addPlayerEffectBonus(id, (getBaseForPower(id) + existing) * -2);
            }
            // Subscribe later to prevent own effect bonus changes from triggering updates
            super.onInitialApplication();
        }

        public void onRemove() {
            super.onRemove();

            for (String id : getAffectedPowers()) {
                float existing = recordedBonuses.getOrDefault(id, 0f);
                CombatManager.addPlayerEffectBonus(id, (getBaseForPower(id) + existing) * 2);
            }
        }

        protected static String[] getAffectedPowers() {
            return EUIUtils.array(FrailPower.POWER_ID, VulnerablePower.POWER_ID, WeakPower.POWER_ID);
        }

        protected static int getBaseForPower(String id) {
            switch (id) {
                case FrailPower.POWER_ID:
                    return FrailPowerPatches.BASE_POWER;
                case VulnerablePower.POWER_ID:
                    return VulnerablePowerPatches.BASE_POWER;
                case WeakPower.POWER_ID:
                    return WeakPowerPatches.BASE_POWER;
            }
            return 0;
        }

    }
}
