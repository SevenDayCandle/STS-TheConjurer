package pinacolada.cards.conjurer.series.genshinimpact;

import com.badlogic.gdx.graphics.Color;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_ScryPerCard;

public class MonaMegistus extends PCLCard
{
    public static final PCLCardData DATA = register(MonaMegistus.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Magical)
            .setDamage(11, array(2, 0))
            .setAffinities(2, PCLAffinity.Blue)
            .setTags(
                    PCLCardTag.Exhaust.make(1, array(0, 1))
            )
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public MonaMegistus()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.GUN04, Color.BLUE);
        addUseMove(new PMod_ScryPerCard(3).setUpgrade(1, 2).setCardTypes(CardType.ATTACK), PMove.triggerOrb(1).setUpgradeExtra(0, -1));
        addUseMove(PCond.pileHas(3, PCLCardGroupHelper.Hand).setAffinity(PCLAffinity.Blue), PMove.gain(2, PCLPowerHelper.Sorcery));
    }
}