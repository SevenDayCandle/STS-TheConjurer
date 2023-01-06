package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.interfaces.markers.Hidden;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class RyujiSakamoto_CaptainKidd extends PCLCard implements Hidden
{
    public static final PCLCardData DATA = register(RyujiSakamoto_CaptainKidd.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.SPECIAL)
            .setDamage(7, 0)
            .setPriority(1)
            .setHp(14, 2)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public RyujiSakamoto_CaptainKidd()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(1), PMove.applyToEnemies(2, PCLPowerHelper.Vulnerable, PCLPowerHelper.Weak));
    }
}
