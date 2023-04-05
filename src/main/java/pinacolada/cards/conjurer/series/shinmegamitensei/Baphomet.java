package pinacolada.cards.conjurer.series.shinmegamitensei;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.conjurer.basic.Overheat;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Baphomet extends PCLCard
{
    public static final PCLCardData DATA = register(Baphomet.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Piercing)
            .setDamage(5, 0)
            .setPriority(1)
            .setHp(13, 4)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Baphomet()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AbstractGameAction.AttackEffect.FIRE);
        addGainPower(PTrigger.interactable(
                PCond.takeDamage(6).setUpgrade(-1),
                PMove.create(2, Overheat.DATA), PMove.modifyCost(-1, 1).useParent(true))
        );
    }
}
