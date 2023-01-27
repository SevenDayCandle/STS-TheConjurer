package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.VFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class RazorWind extends PCLCard
{
    public static final PCLCardData DATA = register(RazorWind.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Piercing, PCLCardTarget.AllEnemy)
            .setDamage(5, 1)
            .setAffinities(PCLAffinity.Green)
            .setCore();

    public RazorWind()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.WIND).setDamageEffect((s, m) -> VFX.razorWind(s.hb).duration);
        addUseMove(PMod.drawPer(2).setUpgrade(1).edit(f -> f.setAffinity(PCLAffinity.Green)), PMove.applyToRandom(2, PCLElementHelper.Aer));
    }
}
