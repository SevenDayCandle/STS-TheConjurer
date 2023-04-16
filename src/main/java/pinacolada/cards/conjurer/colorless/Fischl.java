package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Fischl extends PCLCard
{
    public static final PCLCardData DATA = register(Fischl.class, ConjurerResources.conjurer)
            .setSummon(0, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(2, 1)
            .setPriority(1)
            .setHp(3, 2)
            .setAffinities(PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.genshinImpact, true);

    public Fischl()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.GHOST);
        addUseMove(PCond.cooldown(2), PMove.createDrawPile(1, Fischl_Oz.DATA.ID));
    }
}
