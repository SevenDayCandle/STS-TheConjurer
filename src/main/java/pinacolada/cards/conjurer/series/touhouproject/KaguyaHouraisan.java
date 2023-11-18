package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class KaguyaHouraisan extends PCLCard {
    public static final PCLCardData DATA = register(KaguyaHouraisan.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(2, 1)
            .setHp(4, 2)
            .setTags(PCLCardTag.Ethereal)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public KaguyaHouraisan() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.GHOST).setBonus(PCond.pileHas(0, PCLCardGroupHelper.DrawPile).edit(f -> f.setNot(true)), 13, 3);
        addGainPower(PTrigger.interactable(
                CCond.payMatter(5),
                PMove.scry(1)
        ));
    }
}
