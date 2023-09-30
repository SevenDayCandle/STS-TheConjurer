package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.RandomizedList;

import java.util.ArrayList;

@VisibleCard
public class Patchouli extends PCLCard {
    public static final PCLCardData DATA = register(Patchouli.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(4, 1)
            .setHp(9, 1)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public Patchouli() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.ELECTRIC);
        addUseMove(PCond.cooldown(2), new PatchouliCond(DATA, 2, 4).setUpgrade(1).setUpgradeExtra(1));
    }

    protected static class PatchouliCond extends PCustomCond {
        protected ArrayList<PSkill<?>> debuffs;

        public PatchouliCond(PCustomCond other) {
            super(other);
        }

        public PatchouliCond(PCLCardData data, int amount, int choices) {
            super(data, 0, amount, choices);
        }

        protected ArrayList<PSkill<?>> getDebuffs() {
            if (debuffs == null) {
                debuffs = new ArrayList<>();
                debuffs.add(PMove.applyToSingle(2, PCLPowerData.Vulnerable));
                debuffs.add(PMove.applyToSingle(2, PCLPowerData.Weak));
                debuffs.add(PMove.applyToSingle(3, PCLPowerData.Shackles));
                debuffs.add(PMove.applyToSingle(3, PCLPowerData.Poison));
                debuffs.add(PMove.applyToSingle(2, PCLPowerData.Blinded));
                debuffs.add(PMove.applyToSingle(2, PCLPowerData.Bruised));

                if (GameUtilities.getPlayerClass() == ConjurerEnum.Characters.THE_CONJURER) {
                    debuffs.add(PMove.applyToSingle(2, BlastedPower.DATA));
                    debuffs.add(PMove.applyToSingle(2, CooledPower.DATA));
                }
            }
            return debuffs;
        }

        @Override
        protected void useImpl(PCLUseInfo info, PCLActions order) {
            RandomizedList<PSkill<?>> choices = new RandomizedList<>(getDebuffs());
            while (choices.size() > extra) {
                choices.retrieve(rng, true);
            }
            order.tryChooseSkill(getPCLSource().cardData, amount, info.source, info.target, choices);
        }
    }
}
