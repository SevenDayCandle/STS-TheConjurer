package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class YuukaKazami extends PCLCard
{
    public static final PCLCardData DATA = register(YuukaKazami.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.COMMON, PCLAttackType.Immaterial, PCLCardTarget.AllEnemy)
            .setDamage(1, 1, 2)
            .setHp(7, 1)
            .setAffinities(1, PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public YuukaKazami()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.WATER);
        addUseMove(PCond.cooldown(2), PMove.applyToEnemies(5, PCLElementHelper.Gelus,  PCLElementHelper.Aer));
    }
}
