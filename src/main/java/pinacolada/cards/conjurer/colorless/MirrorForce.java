package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;

@VisibleCard
public class MirrorForce extends PCLCard {
    public static final PCLCardData DATA = register(MirrorForce.class, ConjurerResources.conjurer)
            .setSkill(3, CardRarity.RARE, PCLCardTarget.Self)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.yuGiOh, true);

    public MirrorForce() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, (s, i) -> new MirrorForcePower(i.source, s), 2).setUpgrade(1);
    }

    public static class MirrorForcePower extends PSpecialCardPower {
        public MirrorForcePower(AbstractCreature owner, PSkill<?> move) {
            super(DATA, owner, move);
            initialize(move.amount);
        }

        @Override
        public void atEndOfRound() {
            super.atEndOfRound();
            removePower();
        }

        public int onAttacked(DamageInfo info, int damageAmount) {
            if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && amount > 0) {
                this.flash();
                reducePower(1);
                PCLActions.bottom.dealDamage(move.getSourceCreature(), info.owner, damageAmount, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE)
                        .setDamageEffect(ConjurerEFK.MGC_W2_Shield_OnHit);
                return 0;
            }
            return damageAmount;
        }
    }
}
