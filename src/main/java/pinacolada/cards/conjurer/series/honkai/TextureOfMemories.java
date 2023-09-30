package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class TextureOfMemories extends PCLCard {
    public static final PCLCardData DATA = register(TextureOfMemories.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Self)
            .setCostUpgrades(-1)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.honkai);

    public TextureOfMemories() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gainSummonSlots(1));
        addUseMove(PMove.play(1, PCLCardTarget.Single, PCLCardGroupHelper.DiscardPile).edit(f -> f.setType(PCLEnum.CardType.SUMMON).setRandom()));
    }
}
