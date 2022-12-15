package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.PMultiSkill;

public class YuNarukami extends PCLCard
{
    public static final PCLCardData DATA = register(YuNarukami.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE)
            .setDamage(4, 3)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Star)
            .setLoadout(ConjurerPlayerData.persona);

    public YuNarukami()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SWORD19);
        addGainPower(2, PTrigger.when(PCond.onExhaust(), PMove.applyToRandom(1, PCLPowerHelper.Weak, PCLPowerHelper.Vulnerable).setAlt(true)));
        addUseMove(PCond.limited(), PMultiSkill.choose(PMove.obtain(YuNarukami_Izanagi.DATA), PMove.obtain(YuNarukami_MagatsuIzanagi.DATA)));
    }


}
