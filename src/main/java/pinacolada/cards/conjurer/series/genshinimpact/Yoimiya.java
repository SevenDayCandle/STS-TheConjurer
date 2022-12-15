package pinacolada.cards.conjurer.series.genshinimpact;

import com.megacrit.cardcrawl.cards.AbstractCard;
import extendedui.EUIUtils;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.cards.conjurer.colorless.BlazingHeat;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreStrings;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.skills.skills.PMultiSkill;

public class Yoimiya extends PCLCard
{
    public static final PCLCardData DATA = register(Yoimiya.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Ranged)
            .setDamage(2, array(1, 0), 3, array(0, 1))
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setTags(PCLCardTag.Exhaust)
            .setUTags(PCLCardTag.Haste)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Yoimiya()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.FIRE02, 0.3f);
        addUseMove(new PYoimiyaCond(DATA), PMultiSkill.join(new PMove_ObtainThrowingKnife(1), PMove.gain(1, PCLPowerHelper.Poison)));
        addUseMove(PCond.limited(), PCond.checkLevel(6, PCLAffinity.Red), PMove.playCopy(1, PCLCardTarget.Self, BlazingHeat.DATA.ID));
    }

    protected static class PYoimiyaCond extends PCustomCond
    {
        public PYoimiyaCond(PCLCardData data)
        {
            super(data);
        }

        @Override
        public String getText(boolean addPeriod)
        {
            return TEXT.conditions.per(childEffect != null ? capital(childEffect.getText(false), addPeriod) : "", getSubText() + getXRawString()) + PCLCoreStrings.period(addPeriod);
        }

        @Override
        public PSkill onAddToCard(AbstractCard card)
        {
            PCLCard pCard = EUIUtils.safeCast(card, PCLCard.class);
            if (pCard != null && pCard.onDamageEffect != null && this.childEffect != null)
            {
                pCard.onDamageEffect.setCallback((i, mo) -> this.childEffect.use(i));
            }
            return super.onAddToCard(card);
        }

        @Override
        public PSkill onRemoveFromCard(AbstractCard card)
        {
            PCLCard pCard = EUIUtils.safeCast(card, PCLCard.class);
            if (pCard != null && pCard.onDamageEffect != null && pCard.onDamageEffect.hasCallback())
            {
                pCard.onDamageEffect.setCallback(null);
            }
            return super.onRemoveFromCard(card);
        }
    }
}
