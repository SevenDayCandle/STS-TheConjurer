package pinacolada.cards.conjurer.series.genshinimpact;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.cards.pcl.curse.Curse_SearingBurn;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.modifiers.PMod_ExhaustPerCard;
import pinacolada.skills.skills.base.moves.PMove_GainEnergy;

public class HuTao extends PCLCard
{
    public static final PCLCardData DATA = register(HuTao.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.UNCOMMON, PCLAttackType.Brutal, PCLCardTarget.RandomEnemy)
            .setDamage(1, array(0, 1), 3, array(1, 0))
            .setTags(
                    PCLCardTag.Exhaust.make(),
                    PCLCardTag.Ethereal.make(),
                    PCLCardTag.Innate.make(0, array(1, 0))
            )
            .setMultiformData(2)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public HuTao()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AbstractGameAction.AttackEffect.FIRE);
        addUseMove(new PMod_ExhaustPerCard(4, PCLCardGroupHelper.DrawPile).setUpgrade(0, 2), PMultiSkill.join(new PMove_GainEnergy(1), PMove.obtain(Curse_SearingBurn.DATA)));
        //AddUseMove(PCond.OnExhaust(), PMoves.Obtain(Curse_SearingBurn.DATA));
    }
}
