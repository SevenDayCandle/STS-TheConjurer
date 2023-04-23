package pinacolada.cards.conjurer.series.genshinimpact;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Diluc extends PCLCard
{
    public static final PCLCardData DATA = register(Diluc.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.UNCOMMON)
            .setDamage(5, 1)
            .setHp(10, 2)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Diluc()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.SLASH_HEAVY);
        addUseMove(PCond.cooldown(2), PMod.bonusPerLevel(3, PCLAffinity.Red), PMove.dealDamageToAll(5, AbstractGameAction.AttackEffect.FIRE));
    }
}
