package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.interfaces.markers.OutOfCombatMove;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Rorona extends PCLCard {
    public static final PCLCardData DATA = register(Rorona.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(3, 1)
            .setHp(3, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.atelier, true);

    public Rorona() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.POISON);
        addGainPower(PTrigger.combatEnd(new RoronaCond(DATA, 1)));
    }

    protected static class RoronaCond extends PCustomCond implements OutOfCombatMove {
        public RoronaCond(PCustomCond other) {
            super(other);
        }

        public RoronaCond(PCLCardData data, int amount) {
            super(data, 0, amount);
        }

        public boolean checkCondition(PCLUseInfo info, boolean isUsing, PSkill<?> triggerSource) {
            return AbstractDungeon.player != null;
        }

        public void useOutsideOfBattle(PCLUseInfo info) {
            super.useOutsideOfBattle(info);
            AbstractPotion po = PotionHelper.getRandomPotion();
            if (po != null) {
                AbstractDungeon.player.obtainPotion(po);
            }
        }
    }
}
