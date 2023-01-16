package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class JackFrost extends PCLCard
{
    public static final PCLCardData DATA = register(JackFrost.class, ConjurerResources.conjurer)
            .setSummon(0, CardRarity.COMMON, PCLAttackType.Magical)
            .setDamage(2, 1)
            .setPriority(1)
            .setHp(3, 2)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public JackFrost()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.ICE);
        addUseMove(PCond.cooldown(0), PMove.applyToSingle(2, PCLElementHelper.Gelus));
    }
}
