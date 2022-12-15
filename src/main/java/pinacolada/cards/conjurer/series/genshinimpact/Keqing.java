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

public class Keqing extends PCLCard
{
    public static final PCLCardData DATA = register(Keqing.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(3, array(1, 0), 3, array(0, 0))
            .setTags(
                    PCLCardTag.Exhaust.make(1, array(1, 0)),
                    PCLCardTag.Haste.make(0, 1)
            )
            .setAffinities(PCLAffinity.Green)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Keqing()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SWORD20, Color.PURPLE, 0.4f);
        addUseMove(PMove.fetch(1, PCLCardGroupHelper.DiscardPile, PCLCardGroupHelper.DrawPile).setAffinity(PCLAffinity.Green), PMove.retain(1).useParent(true));
        addUseMove(PCond.semiLimited(), PCond.onDraw(), PMove.applyToEnemies(1, PCLPowerHelper.Flowed));
    }
}
