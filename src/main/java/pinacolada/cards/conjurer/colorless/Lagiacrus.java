package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Lagiacrus extends PCLCard
{
    public static final PCLCardData DATA = register(Lagiacrus.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.UNCOMMON, PCLAttackType.Normal)
            .setDamage(3, 1, 0)
            .setHp(14, 0)
            .setAffinities(2, PCLAffinity.Blue)
            .setColorless();

    public Lagiacrus()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.BITE);
        addGainPower(PTrigger.interactable(
                PCond.exhaustRandom(1).setUpgrade(-1),
                PMove.applyToEnemies(5, PCLPowerHelper.Constricted))
        );
    }
}
