package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.moves.PMove_KillAlly;

@VisibleCard
public class YuNarukami extends PCLCard {
    public static final PCLCardData DATA = register(YuNarukami.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE)
            .setDamage(3, 0)
            .setHp(6, 2)
            .setAffinities(PCLAffinity.Star)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei, true);

    public YuNarukami() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_DIAGONAL);
        addUseMove(PCond.cooldown(4),
                new PMove_KillAlly(PCLCardTarget.Self, 1),
                PMultiSkill.choose(PMove.create(YuNarukami_Izanagi.DATA.ID), PMove.create(YuNarukami_MagatsuIzanagi.DATA.ID))
        );
    }
}
