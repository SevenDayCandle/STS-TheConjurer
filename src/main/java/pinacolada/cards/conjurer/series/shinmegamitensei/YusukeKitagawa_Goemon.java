package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.moves.PMove_ExhaustAlly;

@VisibleCard
public class YusukeKitagawa_Goemon extends PCLCard
{
    public static final PCLCardData DATA = register(YusukeKitagawa_Goemon.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.SPECIAL, PCLAttackType.Piercing, PCLCardTarget.AllEnemy)
            .setDamage(12, 2)
            .setPriority(1)
            .setHp(12, 3)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public YusukeKitagawa_Goemon()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(0), PMove.applyToEnemies(6, PCLElementHelper.Gelus), new PMove_ExhaustAlly(PCLCardTarget.Self, 1));
    }
}
