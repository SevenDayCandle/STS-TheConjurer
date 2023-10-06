package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;
import pinacolada.skills.skills.base.conditions.PCond_OnAllyDeath;

@VisibleRelic
public class MonsterBone extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(MonsterBone.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.SOLID)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public MonsterBone() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(new PCond_OnAllyDeath(), PMove.playCopy(1, PCLCardTarget.RandomAlly).useParent(true)));
    }
}