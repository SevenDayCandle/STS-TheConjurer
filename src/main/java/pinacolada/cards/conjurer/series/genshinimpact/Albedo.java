package pinacolada.cards.conjurer.series.genshinimpact;


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
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Albedo extends PCLCard
{
    public static final PCLCardData DATA = register(Albedo.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Magical)
            .setDamage(3, 1)
            .setPriority(1)
            .setHp(7, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Albedo()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.EARTH);
        addUseMove(PCond.cooldown(2), PMultiSkill.choose(
                PMove.applyToEnemies(6, PCLElementHelper.Petra),
                PMove.stabilize(PCLCardTarget.AllEnemy, PCLElementHelper.Gelus)
        ));
    }
}
