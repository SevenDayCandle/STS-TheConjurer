package pinacolada.cards.conjurer.colorless;


import extendedui.EUIUtils;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PTrait;
import pinacolada.skills.fields.PField_CardGeneric;
import pinacolada.skills.skills.PCustomMod;

@VisibleCard
public class ForceImpulseGundam extends PCLCard {
    public static final PCLCardData DATA = register(ForceImpulseGundam.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Normal)
            .setDamage(7, 2)
            .setHp(18, 2)
            .setAffinities(PCLAffinity.Yellow, PCLAffinity.Silver)
            .setLoadout(ConjurerPlayerData.mobileSuitGundam, true);

    public ForceImpulseGundam() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.VERTICAL_IMPACT).setChain(new ForceImpulseGundamMod(DATA, 1), PTrait.damage(4).setUpgrade(1));
        addUseMove(PCond.onWithdraw(), PMove.upgrade(0, PCLCardGroupHelper.Hand).edit(PField_CardGeneric::setRandom));
    }

    protected static class ForceImpulseGundamMod extends PCustomMod {
        public ForceImpulseGundamMod(PCLCardData data, int amount) {
            super(data, 0, amount);
        }

        @Override
        public int getModifiedAmount(PSkill<?> be, PCLUseInfo info, boolean isUsing) {
            return player != null ? EUIUtils.sumInt(player.hand.group, c -> c.timesUpgraded) : 0;
        }
    }
}
