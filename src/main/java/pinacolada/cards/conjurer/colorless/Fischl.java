package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Fischl extends PCLCard
{
    public static final PCLCardData DATA = register(Fischl.class, ConjurerResources.conjurer)
            .setSummon(0, CardRarity.UNCOMMON, PCLAttackType.Magical)
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
        addDamageMove(AttackEffects.DARK);
        addUseMove(PCond.cooldown(1), PMove.triggerAlly(PCLCardTarget.RandomAlly, 1));
    }
}
