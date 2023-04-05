package pinacolada.cards.conjurer.series.genshinimpact;


import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.interfaces.subscribers.OnBlockGainedSubscriber;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;

@VisibleCard
public class AyakaKamisato extends PCLCard
{
    public static final PCLCardData DATA = register(AyakaKamisato.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setRTags(PCLCardTag.Ethereal)
            .setDamage(14, 0, 0)
            .setPriority(1)
            .setHp(4, 1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public AyakaKamisato()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.ICE);
        addSpecialPower(0, (s, i) -> new AyakaKamisatoPower(i.source, s), 1, 1);
    }

    public static class AyakaKamisatoPower extends PSpecialCardPower implements OnBlockGainedSubscriber
    {
        public AyakaKamisatoPower(AbstractCreature owner, PSkill move)
        {
            super(DATA, owner, move);
        }

        @Override
        public void onBlockGained(AbstractCreature target, int i)
        {
            if (i > 0)
            {
                move.getActions().applyPower(target, PCLCardTarget.Single, PCLElementHelper.Frostbite, i);
            }
        }
    }
}
