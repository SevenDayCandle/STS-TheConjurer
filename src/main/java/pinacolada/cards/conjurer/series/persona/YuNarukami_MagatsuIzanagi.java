package pinacolada.cards.conjurer.series.persona;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

public class YuNarukami_MagatsuIzanagi extends PCLCard
{
    public static final PCLCardData DATA = register(YuNarukami_MagatsuIzanagi.class, ConjurerResources.conjurer)
            .setAttack(4, CardRarity.SPECIAL)
            .setDamage(14, 4)
            .setAffinities(PCLAffinity.Star)
            .setTags(
                    PCLCardTag.Exhaust.make(),
                    PCLCardTag.Retain.make(-1)
            )
            .setLoadout(ConjurerPlayerData.persona);

    public YuNarukami_MagatsuIzanagi()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SWORD22);
        addUseMove(PMove.applyToEveryone(4, PCLPowerHelper.Strength));
        addUseMove(PCond.onTurnStart(), getSpecialMove(0, this::action, 8));
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        for (AbstractCreature c : GameUtilities.getAllCharacters(true))
        {
            int applied = MathUtils.ceil(c.maxHealth * move.amount / 100f);
            if (applied > 0)
            {
                PCLActions.bottom.applyPower(info.target, PCLCardTarget.Single, PCLPowerHelper.DelayedDamage, applied);
            }
        }
    }
}
