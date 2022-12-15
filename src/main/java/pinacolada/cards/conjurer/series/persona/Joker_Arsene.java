package pinacolada.cards.conjurer.series.persona;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.misc.CombatManager;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PCustomCond;

public class Joker_Arsene extends PCLCard
{
    public static final PCLCardData DATA = register(Joker_Arsene.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.SPECIAL)
            .setDamage(4, 2)
            .setAffinities(PCLAffinity.Star)
            .setTags(PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.persona);

    public Joker_Arsene()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.GUN01);
        addUseMove(new JokerArseneMove(DATA));
    }

    protected static class JokerArseneMove extends PCustomCond
    {
        public JokerArseneMove(PCLCardData data)
        {
            super(data);
        }

        @Override
        public float modifyHitCount(PCLCard card, AbstractMonster m, float amount)
        {
            return amount + CombatManager.playerSystem.getLevel(PCLAffinity.General);
        }
    }
}
