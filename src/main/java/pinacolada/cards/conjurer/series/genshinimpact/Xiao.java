package pinacolada.cards.conjurer.series.genshinimpact;

import com.badlogic.gdx.graphics.Color;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.effects.VFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.moves.PMove_ModifyDamage;

public class Xiao extends PCLCard
{
    public static final PCLCardData DATA = register(Xiao.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.AllEnemy)
            .setDamage(6, 1, 3)
            .setTags(PCLCardTag.Ethereal)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Xiao()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove();
        addUseMove(PMove.obtainDrawPile(2, Curse_GriefSeed.DATA));
        addUseMove(PCond.exhaust(2, PCLCardGroupHelper.Hand), new PMove_ModifyDamage(1, 2));
    }

    @Override
    public void onPreUse(PCLUseInfo info)
    {
        PCLActions.bottom.playVFX(VFX.eFX(PCLEffekseerEFX.KAMEHAMEHA, player.hb.cX, player.hb.cY)
                        .setRotationDeg(180, 270, 0)
                        .setScale(1.4f)
                        .setForceEnd(-7)
                        .setColor(Color.YELLOW)
                        .setDuration(0.5f, true));
    }
}
