package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Cirno extends PCLCard
{
    public static final PCLCardData DATA = register(Cirno.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Magical, PCLCardTarget.AllEnemy)
            .setDamage(2, 0)
            .setPriority(1)
            .setHp(3, 1)
            .setAffinities(1, PCLAffinity.Blue)
            .setRTags(PCLCardTag.Ethereal)
            .setLoadout(ConjurerPlayerData.touhouProject, true);

    public Cirno()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.ICE);
        addUseMove(PCond.cooldown(0), PMove.applyToEnemies(1, PCLPowerHelper.Weak, PCLPowerHelper.Shackles));
    }
}
