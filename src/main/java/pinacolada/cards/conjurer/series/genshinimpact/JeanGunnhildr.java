package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class JeanGunnhildr extends PCLCard
{
    public static final PCLCardData DATA = register(JeanGunnhildr.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE)
            .setDamage(3, 2)
            .setPriority(1)
            .setHp(9, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public JeanGunnhildr()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HORIZONTAL);
        addUseMove(PCond.cooldown(1), PMove.applyToTeam(-1, PCLPowerHelper.Vulnerable, PCLPowerHelper.Frail));
    }
}
