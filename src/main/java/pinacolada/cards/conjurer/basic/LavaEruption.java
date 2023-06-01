package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.PCustomMod;
import pinacolada.ui.combat.ConjurerElementButton;
import pinacolada.ui.combat.ConjurerReactionMeter;

@VisibleCard
public class LavaEruption extends PCLCard {
    public static final PCLCardData DATA = register(LavaEruption.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setDamage(5, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setCore();

    public LavaEruption() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BURN).setChain(new LavaEruptionMod(DATA, 1), PTrait.damage(2).setUpgrade(1));
    }

    protected static class LavaEruptionMod extends PCustomMod {
        public LavaEruptionMod(PCLCardData data, int amount) {
            super(data, 0, amount);
        }

        @Override
        public int getModifiedAmount(PSkill<?> be, PCLUseInfo info) {
            return ConjurerReactionMeter.meter.getTotalReactionsMade(ConjurerElementButton.Type.Redox);
        }
    }
}
