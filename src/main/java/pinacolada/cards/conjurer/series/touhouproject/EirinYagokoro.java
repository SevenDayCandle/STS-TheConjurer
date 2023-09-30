package pinacolada.cards.conjurer.series.touhouproject;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.interfaces.subscribers.OnPotionUseSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.base.modifiers.PMod_PerPotion;

@VisibleCard
public class EirinYagokoro extends PCLCard {
    public static final PCLCardData DATA = register(EirinYagokoro.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(2, 0)
            .setHp(4, 1)
            .setAffinities(1, PCLAffinity.Blue, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.touhouProject, true);

    public EirinYagokoro() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.POISON).setBonus(new PMod_PerPotion(), 2, 1);
        addSpecialPower(0, (s, i) -> new EirinYagokoroPower(i.source, i.source, s), 1, 1);
    }

    public static class EirinYagokoroPower extends PSpecialCardPower implements OnPotionUseSubscriber {
        public static final PCLPowerData PDATA = createFromCard(EirinYagokoroPower.class, DATA);

        public EirinYagokoroPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public void onUsePotion(AbstractPotion potion) {
            for (AbstractPower po : player.powers) {
                if (po.type == PowerType.DEBUFF) {
                    PCLActions.bottom.removePower(player, po);
                    flash();
                    return;
                }
            }
        }
    }
}
