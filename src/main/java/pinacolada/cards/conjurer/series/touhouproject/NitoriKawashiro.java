package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class NitoriKawashiro extends PCLCard {
    public static final PCLCardData DATA = register(NitoriKawashiro.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(2, 1)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public NitoriKawashiro() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WATER);
        addGainPower(PTrigger.interactable(
                CCond.payMatter(10),
                PMove.createRandom(1, 3, PCLCardGroupHelper.Hand).edit(f -> f.setAffinity(PCLAffinity.Blue))
        ));
    }
}
