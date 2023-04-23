package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Cirno extends PCLCard
{
    public static final PCLCardData DATA = register(Cirno.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Immaterial, PCLCardTarget.AllEnemy)
            .setDamage(2, 0)
            .setHp(4, 1)
            .setAffinities(1, PCLAffinity.Blue)
            .setRTags(PCLCardTag.Ethereal)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public Cirno()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.ICE);
        addUseMove(PCond.cooldown(1), PMove.applyToEnemies(2, PCLElementHelper.Frostbite, PCLPowerHelper.Shackles));
    }
}
